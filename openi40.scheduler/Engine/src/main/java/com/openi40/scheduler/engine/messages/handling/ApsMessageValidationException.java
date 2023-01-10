package com.openi40.scheduler.engine.messages.handling;

import java.util.List;

import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
public class ApsMessageValidationException extends BaseMessageHandlingException {

	public ApsMessageValidationException(List<MessageHandlingErrorMessage> messages, String message) {
		super(messages, message);
		
	}

	

}
