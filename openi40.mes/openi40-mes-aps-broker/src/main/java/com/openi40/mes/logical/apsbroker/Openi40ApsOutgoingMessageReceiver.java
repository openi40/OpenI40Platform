package com.openi40.mes.logical.apsbroker;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.openi40.mes.logical.apsbroker.config.Openi40ApsBrokerConfig;
import com.openi40.mes.logical.apsbroker.model.Openi40ApsOutgoingMessage;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;
import com.openi40.scheduler.iomessages.AbortTaskIOMessage;

@Service
@Qualifier(MessageReceiver.IOT_APPLICATION_RECEIVER)
public class Openi40ApsOutgoingMessageReceiver implements MessageReceiver<AbstractOI40IOTApplicationMessage> {
	Openi40ApsBrokerConfig config;
	static Logger LOGGER = LoggerFactory.getLogger(Openi40ApsOutgoingMessageReceiver.class);
	@Autowired
	RestTemplate restTemplate;

	public Openi40ApsOutgoingMessageReceiver(@Autowired Openi40ApsBrokerConfig config) {
		this.config = config;
	}

	@Override
	public void onMessage(AbstractOI40IOTApplicationMessage msg, MessagingEnvironment environment) {

		Openi40ApsOutgoingMessage message = (Openi40ApsOutgoingMessage) msg;
		String url = generatePostUrl(message);
		try {
			ResponseEntity<Void> response = restTemplate.postForEntity(url, message.getApsContent(), Void.class);
			HttpStatus statusCode = response.getStatusCode();
			if (statusCode != null && statusCode.is2xxSuccessful()) {

			} else {
				if (message.getRetryCount() >= this.config.getMaxRetry()) {
					message.setRetryCount(message.getRetryCount() + 1);
					int retryDelay = config.getRetryDelay() != null ? config.getRetryDelay() : 30000;
					if (retryDelay <= 0) {
						retryDelay = 30000;
					}
					MessageReceiver retrySender = environment.getSpooledRetrySender(null, retryDelay);
					retrySender.onMessage(message, environment);

				} else {
					MessageReceiver unmanageableSender = environment.getUnmanageableMessageSender();
					unmanageableSender.onMessage(message, environment);
				}
			}
		} catch (RestClientException exc) {
			LOGGER.error("Error posting to APS", exc);
			if (config.getWaitIfOnline() != null && config.getWaitIfOnline()) {
				int retryDelay = config.getRetryDelayOffline() != null ? config.getRetryDelayOffline() : 60000;
				if (retryDelay <= 0) {
					retryDelay = 60000;
				}
				MessageReceiver retrySender = environment.getSpooledRetrySender(null, retryDelay);
				retrySender.onMessage(message, environment);
			}
		}
	}

	private String generatePostUrl(Openi40ApsOutgoingMessage msg) {
		String methodFragment = "ioMessages/IOMessagesSpoolingController/";
		methodFragment += "spool" + msg.getApsContent().getClass().getSimpleName();
		String baseUrl = config.getApsRestInterfaceConfig().getUrl();
		if (!baseUrl.endsWith("/")) {
			baseUrl += "/";
		}
		String completeUrl = baseUrl + methodFragment + "/" + URLEncoder.encode(config.getDataSourceName()) + "/"
				+ URLEncoder.encode(config.getDataSetName()) + "/" + URLEncoder.encode(config.getDataSetVariant())
				+ "/";
		return completeUrl;
	}

	@Override
	public boolean isCanManage(AbstractOI40IOTApplicationMessage msg) {
		boolean hasContent = false;
		if (msg instanceof Openi40ApsOutgoingMessage) {
			if (config.getEnabled() != null && config.getEnabled()) {
				LOGGER.warn("OPENI40 Aps message outgoing denied for disabled REST gateway configuration");
			}
			Openi40ApsOutgoingMessage message = (Openi40ApsOutgoingMessage) msg;
			hasContent = message.getApsContent() != null;
			if (!hasContent) {
				LOGGER.error("OPENI40 Aps message outgoing received message without aps content");
			}
		}
		return msg instanceof Openi40ApsOutgoingMessage && config.getEnabled() != null && config.getEnabled()
				&& hasContent;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {
		ManagedMessageType[] types = { new ManagedMessageType(Openi40ApsOutgoingMessage.class) {
			@Override
			public boolean isManageExtensions() {

				return true;
			}
		} };

		return types;
	}

}
