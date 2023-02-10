package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractMachineStatusMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;

@Service
public class MessagesManagementStateMachineGraphServiceImpl extends AbstractSpecializedMessageHandler<AbstractMachineStatusMessage> {

	public MessagesManagementStateMachineGraphServiceImpl() {
		super(AbstractMachineStatusMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<AbstractMachineStatusMessage>.MessageRelatedObjects contextObjects,
			AbstractMachineStatusMessage message, ApsData context) throws ApsMessageValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<AbstractMachineStatusMessage>.MessageRelatedObjects contextObjects,
			AbstractMachineStatusMessage message, ApsData context) throws ApsMessageManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
