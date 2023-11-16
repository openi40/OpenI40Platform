package com.openi40.mes.io.mqtt.generic.input;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.handlers.IMicroKernel;

@Service
public class GenericalMQTTInputReceiver implements IMqttArrivedSuccessfully {
	static Logger LOGGER = LoggerFactory.getLogger(GenericalMQTTInputReceiver.class);
	private String channelId = null;
	private String integrationId = null;
	IMicroKernel microKernel = null;
	private Map<String, String> topicToAssetCodeMap = new HashMap<String, String>();

	public GenericalMQTTInputReceiver(@Autowired IMicroKernel kernel) {
		this.microKernel = kernel;
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin messageArrived('" + topic + "',message)");
		}
		GenericalMQTTInputMessage inputMessage = new GenericalMQTTInputMessage();
		inputMessage.setChannelId(channelId);
		inputMessage.setIntegrationId(integrationId);
		inputMessage.setTopic(topic);
		inputMessage.setPayload(message.getPayload());
		inputMessage.setMqttMsgId(message.getId());
		inputMessage.setMqttQos(message.getQos());
		inputMessage.setFrom("mqtt::" + channelId + "::" + integrationId + "::topic:" + topic);
		inputMessage.setTo(IMicroKernel.MICROKERNEL_ID);
		inputMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
		inputMessage.setAssetCode(this.topicToAssetCodeMap.get(topic));
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Routing message to microkernel.onMessage(message)");
		}
		microKernel.onMessage(inputMessage, null);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End messageArrived('" + topic + "',message)");
		}
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	public Map<String, String> getTopicToAssetCodeMap() {
		return topicToAssetCodeMap;
	}

	public void setTopicToAssetCodeMap(Map<String, String> topicToAssetCodeMap) {
		this.topicToAssetCodeMap = topicToAssetCodeMap;
	}

}
