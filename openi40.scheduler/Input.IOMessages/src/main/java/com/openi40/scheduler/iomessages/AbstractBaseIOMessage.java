package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractBaseIOMessage  implements Serializable {
	protected String code = UUID.randomUUID().toString();
	protected String description = null;
	protected Date receivedTimestamp = null;
	protected Date messageTimestamp = null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getReceivedTimestamp() {
		return receivedTimestamp;
	}
	public void setReceivedTimestamp(Date receivedTimestamp) {
		this.receivedTimestamp = receivedTimestamp;
	}
	public Date getMessageTimestamp() {
		return messageTimestamp;
	}
	public void setMessageTimestamp(Date messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}	
}
