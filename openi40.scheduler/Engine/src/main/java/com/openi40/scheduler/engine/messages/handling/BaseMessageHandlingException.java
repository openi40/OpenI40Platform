package com.openi40.scheduler.engine.messages.handling;

import java.util.ArrayList;
import java.util.List;

public class BaseMessageHandlingException extends Exception {
	List<MessageHandlingErrorMessage> messages = new ArrayList<MessageHandlingErrorMessage>();

	public BaseMessageHandlingException(List<MessageHandlingErrorMessage> messages) {
		this.messages = messages;
	}

	public BaseMessageHandlingException(List<MessageHandlingErrorMessage> messages, String message) {
		super(message);
		this.messages = messages;
	}

	public BaseMessageHandlingException(List<MessageHandlingErrorMessage> messages, Throwable cause) {
		super(cause);
		this.messages = messages;
	}

	public BaseMessageHandlingException(List<MessageHandlingErrorMessage> messages, String message, Throwable cause) {
		super(message, cause);
		this.messages = messages;
	}

	public BaseMessageHandlingException(List<MessageHandlingErrorMessage> messages, String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.messages = messages;
	}

	public List<MessageHandlingErrorMessage> getMessages() {
		return messages;
	}

}
