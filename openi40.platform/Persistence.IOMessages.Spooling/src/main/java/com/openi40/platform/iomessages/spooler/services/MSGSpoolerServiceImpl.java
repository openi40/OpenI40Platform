package com.openi40.platform.iomessages.spooler.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntryProcessed;
import com.openi40.platform.iomessages.spooler.repositories.MSGSpoolerEntryProcessedRepository;
import com.openi40.platform.iomessages.spooler.repositories.MSGSpoolerEntryRepository;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.IContextAwareObjext;
import com.openi40.scheduler.model.aps.ApsData;

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
		if (message.getCode() == null || message.getCode().trim().length() == 0)
			throw new MSGSpoolerException("Recvd message does not have a code");
		List<MsgSpoolerEntry> checkDuplicates = spoolerRepository.findByCode(dataSourceName, dataSetName,
				dataSetVariant, message.getCode());
		if (!checkDuplicates.isEmpty()) {
			throw new MSGSpoolerException("Recvd message with code " + message.getCode() + " is duplicate for "
					+ dataSourceName + "/" + dataSetName + "/" + dataSetVariant);
		}
		MsgSpoolerEntry entry = new MsgSpoolerEntry();
		entry.setDataSourceName(dataSourceName);
		entry.setDataSetName(dataSetName);
		entry.setDataSetVariant(dataSetVariant);
		entry.setCode(message.getCode());
		entry.setMsgClassName(message.getClass().getName());
		entry.setProcessedStatus(MsgSpoolerEntry.MSG_STATUS_RECEIVED);
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
		List<MsgSpoolerEntry> recvd = spoolerRepository.findByProcessedStatus(dataSourceName, dataSetName,
				dataSetVariant, MsgSpoolerEntry.MSG_STATUS_RECEIVED);

		for (MsgSpoolerEntry msgSpoolerEntry : recvd) {
			MsgSpoolerEntryProcessed processed = new MsgSpoolerEntryProcessed();
			processed.setMsgEntryId(msgSpoolerEntry.getMsgEntryId());
			try {
				Class<AbstractBaseIOMessage> msgType = (Class<AbstractBaseIOMessage>) Class
						.forName(msgSpoolerEntry.getMsgClassName());
				AbstractBaseIOMessage msg = objectMapper.readValue(msgSpoolerEntry.getJsonDump(), msgType);
				MsgIngestionResult msgResult = function.apply(msg);
				if (msgResult.successfull) {
					msgSpoolerEntry.setProcessedStatus(MsgSpoolerEntry.MSG_STATUS_PROCESSED);

				} else {
					msgSpoolerEntry.setProcessedStatus(MsgSpoolerEntry.MSG_STATUS_ERROR);
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
					msgSpoolerEntry.setProcessedStatus(MsgSpoolerEntry.MSG_STATUS_ERROR);
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

	@Override
	public boolean isEmptySpoolingQueue(String dataSourceName, String dataSetName, String dataSetVariant) {
		List<MsgSpoolerEntry> recvd = spoolerRepository.findByProcessedStatus(dataSourceName, dataSetName,
				dataSetVariant, MsgSpoolerEntry.MSG_STATUS_RECEIVED);
		return recvd.isEmpty();
	}

}
