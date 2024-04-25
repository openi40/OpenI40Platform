package com.openi40.mes.io.mqtt.generic.input;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.VolatileMessageType;


@VolatileMessageType
public class GenericalMQTTInputMessage extends AbstractOI40IOTMetaMessage {
	private byte[] payload = null;
	private String topic = null;
	private String channelId = null;
	private String integrationId = null;
	private String assetCode = null;
	private int mqttMsgId = 0;
	private int mqttQos = 0;

	public GenericalMQTTInputMessage() {

	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public int getMqttMsgId() {
		return mqttMsgId;
	}

	public void setMqttMsgId(int mqttMsgId) {
		this.mqttMsgId = mqttMsgId;
	}

	public int getMqttQos() {
		return mqttQos;
	}

	public void setMqttQos(int mqttQos) {
		this.mqttQos = mqttQos;
	}

}
