package com.openi40.mes.datamodel.spooler;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.datamodel.repositories.OI40DBMesSpoolMsgRepository;
import com.openi40.mes.metamessaging.handlers.PollableMessagesStreamFactory;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.SpooledRetryEnvelopeMessage;
import com.openi40.mes.metamessaging.model.jsonutil.MetaMsgJsonUtil;

@Service
public class RetrySpoolerPollableMessagesStreamFactory implements PollableMessagesStreamFactory {
	static Logger LOGGER = LoggerFactory.getLogger(RetrySpoolerPollableMessagesStreamFactory.class);
	OI40DBMesSpoolMsgRepository repository = null;

	public RetrySpoolerPollableMessagesStreamFactory(@Autowired OI40DBMesSpoolMsgRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Stream<AbstractOI40IOTMetaMessage> stream() {
		Stream<AbstractOI40IOTMetaMessage> outStream = null;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin stream()");
		}
		outStream = repository.findAllMessagesToRetry().map((entry) -> {
			AbstractOI40IOTMetaMessage outMessage = null;
			try {
				Class<? extends AbstractOI40IOTMetaMessage> type = (Class<? extends AbstractOI40IOTMetaMessage>) Class
						.forName(entry.getMessageType());
				outMessage = MetaMsgJsonUtil.materialize(new ByteArrayInputStream(entry.getPayload().getBytes()), type);
				if (outMessage instanceof SpooledRetryEnvelopeMessage) {
					SpooledRetryEnvelopeMessage msg=(SpooledRetryEnvelopeMessage) outMessage;
					outMessage=msg.getContent();
				}
				entry.setSent(true);
				this.repository.save(entry);
			} catch (Throwable e) {

				LOGGER.error("Error in accessing spool", e);
			}
			return outMessage;
		});
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End stream()");
		}
		return outStream;
	}

}
