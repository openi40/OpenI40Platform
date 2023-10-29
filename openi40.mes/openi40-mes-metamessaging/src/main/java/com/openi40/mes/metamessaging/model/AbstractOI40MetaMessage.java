package com.openi40.mes.metamessaging.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Data;

public abstract class AbstractOI40MetaMessage implements Serializable {
	private String msgId = UUID.randomUUID().toString();
	private String from = null;
	private String to = null;

	private List<String> handlersList = new ArrayList<String>();
	private Map<String, Boolean> traversedHandlers = new HashMap<String, Boolean>();

	public void addHandlerId(String handlerId) {
		this.handlersList.add(handlerId);
		this.traversedHandlers.put(handlerId, true);
	}

	public boolean isAlreadyHandledFrom(String handlerId) {
		return this.traversedHandlers.containsKey(handlerId);
	}

	public AbstractOI40MetaMessage() {

	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<String> getHandlersList() {
		return new ArrayList<String>(this.handlersList);
	}

}
