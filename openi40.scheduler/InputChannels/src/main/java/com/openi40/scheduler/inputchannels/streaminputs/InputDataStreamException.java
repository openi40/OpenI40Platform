package com.openi40.scheduler.inputchannels.streaminputs;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.datamodel.ValidationMessage;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public class InputDataStreamException extends Exception {

	public InputDataStreamException() {
		
	}

	public InputDataStreamException(String message) {
		super(message);
		
	}

	public InputDataStreamException(Throwable cause) {
		super(cause);
		
	}

	public InputDataStreamException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public InputDataStreamException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
	List<ValidationMessage> validationMessages=new ArrayList<ValidationMessage>();
	public InputDataStreamException(String message, List<ValidationMessage> validations) {
		super(message);
		this.validationMessages=validations;
	}

	public List<ValidationMessage> getValidationMessages() {
		return validationMessages;
	}

}
