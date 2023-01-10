package com.openi40.scheduler.engine.messages.handling.impl;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.UnderMantainmentMachineMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;

public class UnderMantainmentMachineMessageHandler
		extends AbstractSpecializedMessageHandler<UnderMantainmentMachineMessage> {

	public UnderMantainmentMachineMessageHandler(Class<UnderMantainmentMachineMessage> messageType) {
		super(messageType);
		
	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<UnderMantainmentMachineMessage>.MessageRelatedObjects contextObjects,
			UnderMantainmentMachineMessage message, ApsData context) throws ApsMessageValidationException {
		

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<UnderMantainmentMachineMessage>.MessageRelatedObjects contextObjects,
			UnderMantainmentMachineMessage message, ApsData context) throws ApsMessageManagementException {
		
		return null;
	}

}
