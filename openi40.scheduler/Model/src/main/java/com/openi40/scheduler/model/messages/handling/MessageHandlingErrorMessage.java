package com.openi40.scheduler.model.messages.handling;

import lombok.Data;

@Data
public class MessageHandlingErrorMessage {
	protected String errorCode = null;
	protected String errorMsg = null;
	protected String entityType = null;
	protected String entityCode = null;
}