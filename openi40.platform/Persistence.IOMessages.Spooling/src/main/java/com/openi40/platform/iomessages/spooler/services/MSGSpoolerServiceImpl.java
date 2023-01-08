package com.openi40.platform.iomessages.spooler.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.iomessages.spooler.model.MSGSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MSGSpoolerEntryProcessed;
import com.openi40.platform.iomessages.spooler.repositories.MSGSpoolerEntryProcessedRepository;
import com.openi40.platform.iomessages.spooler.repositories.MSGSpoolerEntryRepository;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;

@Service
public class MSGSpoolerServiceImpl implements IMSGSpoolingService {
	static Logger LOGGER = LoggerFactory.getLogger(MSGSpoolerServiceImpl.class);
	@Autowired
	MSGSpoolerEntryRepository spoolerRepository;
	@Autowired
	MSGSpoolerEntryProcessedRepository spoolProcessRepository;
	@Autowired
	ObjectMapper objectMapper;

	public MSGSpoolerServiceImpl() {
	}

	@Override
	@Transactional
	public void add(String dataSourceName, String dataSetName, String dataSetVariant, AbstractBaseIOMessage message)
			throws MSGSpoolerException {
		if (message == null)
			return;
		MSGSpoolerEntry entry = new MSGSpoolerEntry();
		entry.setDataSourceName(dataSourceName);
		entry.setDataSetName(dataSetName);
		entry.setDataSetVariant(dataSetVariant);
		entry.setMsgClassName(message.getClass().getName());
		entry.setProcessedStatus("UNPROCESSED");
		entry.setTimestampMemorized(new Timestamp(System.currentTimeMillis()));
		entry.setMessageTimestamp(new Timestamp(message.getMessageTimestamp().getTime()));
		try {
			entry.setJsonDump(objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			throw new MSGSpoolerException("Cannot dump message", e);
		}

		spoolerRepository.saveAndFlush(entry);
	}

	@Override
	@Transactional
	public void evaluateMessages(String dataSourceName, String dataSetName, String dataSetVariant,
			Function<AbstractBaseIOMessage, MsgIngestionResult> function) {
		List<MSGSpoolerEntry> recvd = spoolerRepository.findByProcessedStatus(dataSourceName, dataSetName,
				dataSetVariant, MSGSpoolerEntry.MSG_STATUS_RECEIVED);

		for (MSGSpoolerEntry msgSpoolerEntry : recvd) {
			MSGSpoolerEntryProcessed processed = new MSGSpoolerEntryProcessed();
			processed.setMsgEntryId(msgSpoolerEntry.getMsgEntryId());
			try {
				Class<AbstractBaseIOMessage> msgType = (Class<AbstractBaseIOMessage>) Class
						.forName(msgSpoolerEntry.getMsgClassName());
				AbstractBaseIOMessage msg = objectMapper.readValue(msgSpoolerEntry.getJsonDump(), msgType);
				MsgIngestionResult msgResult = function.apply(msg);
				if (msgResult.successfull) {
					msgSpoolerEntry.setProcessedStatus(MSGSpoolerEntry.MSG_STATUS_PROCESSED);

				} else {
					msgSpoolerEntry.setProcessedStatus(MSGSpoolerEntry.MSG_STATUS_ERROR);
				}
				msgSpoolerEntry.setProcessedTimestamp(new Timestamp(System.currentTimeMillis()));
				processed.setErrorCode(msgResult.errorCode);
				processed.setErrorMessage(msgResult.errorMessage);
				processed.setProcessSuccessfully(msgResult.successfull);
				spoolerRepository.saveAndFlush(msgSpoolerEntry);
				spoolProcessRepository.saveAndFlush(processed);
			} catch (Throwable e) {
				LOGGER.error("Cannot manage message class:" + msgSpoolerEntry.getMsgClassName() + " for entry = "
						+ msgSpoolerEntry.getMsgEntryId(), e);
				try {
					msgSpoolerEntry.setProcessedStatus(MSGSpoolerEntry.MSG_STATUS_ERROR);
					processed.setErrorCode(e.getClass().getName());
					processed.setErrorMessage(getStackTrace(e));
					spoolerRepository.saveAndFlush(msgSpoolerEntry);
					spoolProcessRepository.saveAndFlush(processed);
				} catch (Throwable t) {
					LOGGER.error("Error in saving cycle", t);
				}
			}
		}
	}

	private String getStackTrace(Throwable th) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		th.printStackTrace(new PrintStream(bos));
		try {
			bos.flush();
		} catch (IOException e) {

		}
		return bos.toString();
	}

}
