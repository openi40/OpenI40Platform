package com.openi40.mes.io.mqtt.generic.output;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

public class GenericalMQTTOutputMessage extends AbstractOI40IOTApplicationMessage {
	private byte payload[] = null;
	private String topic = null;
	private String channelId = null;
	private String integrationId = null;
	private int mqttMsgId=0;
	private int mqttQos=0;
	public GenericalMQTTOutputMessage() {
		
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
