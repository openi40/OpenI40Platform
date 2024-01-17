package com.openi40.mes.io.rabbitmq.output;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

public class GenericalRabbitMQOutputMessage extends AbstractOI40IOTApplicationMessage {
	private byte payload[] = null;
	private String topic = null;
	private String routingKey=null;
	private String channelId = null;
	private String integrationId = null;
	private String contentType=null;
	public GenericalRabbitMQOutputMessage() {

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

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
}
