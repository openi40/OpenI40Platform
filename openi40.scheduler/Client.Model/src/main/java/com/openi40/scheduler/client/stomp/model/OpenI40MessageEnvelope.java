package com.openi40.scheduler.client.stomp.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.openi40.scheduler.client.model.ClientDto;

public class OpenI40MessageEnvelope<Model extends ClientDto> {
	Model payload = null;
	String messageId=UUID.randomUUID().toString();
	Timestamp messageTime=new Timestamp(System.currentTimeMillis());
	public OpenI40MessageEnvelope() {

	}

	public OpenI40MessageEnvelope(Model p) {
		this.payload = p;
	}

	public Model getPayload() {
		return payload;
	}

	public void setPayload(Model payload) {
		this.payload = payload;
	}

	public void setPayloadType(String p) {

	}

	public String getPayloadType() {
		if (payload != null) {
			return payload.getClass().getSimpleName();
		} else
			return "NoContent";
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
