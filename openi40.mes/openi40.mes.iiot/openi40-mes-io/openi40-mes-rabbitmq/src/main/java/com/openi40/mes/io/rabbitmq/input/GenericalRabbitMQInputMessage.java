package com.openi40.mes.io.rabbitmq.input;

import java.io.Serializable;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.VolatileMessageType;
@VolatileMessageType
public class GenericalRabbitMQInputMessage extends AbstractOI40IOTMetaMessage implements Serializable{
	private byte[] payload = null;
	private String topic = null;
	private String channelId = null;
	private String integrationId = null;
	private String assetCode = null;
	private String rabbitMessageId=null;
	public GenericalRabbitMQInputMessage() {
		// TODO Auto-generated constructor stub
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
	public String getRabbitMessageId() {
		return rabbitMessageId;
	}
	public void setRabbitMessageId(String rabbitMessageId) {
		this.rabbitMessageId = rabbitMessageId;
	}

}
