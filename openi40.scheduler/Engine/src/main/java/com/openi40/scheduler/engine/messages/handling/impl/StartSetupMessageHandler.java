package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.tasks.TaskStatus;

@Service
public class StartSetupMessageHandler extends AbstractSpecializedMessageHandler<StartSetupMessage> {

	public StartSetupMessageHandler() {
		super(StartSetupMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<StartSetupMessage>.MessageRelatedObjects contextObjects,
			StartSetupMessage message, ApsData context) throws ApsMessageValidationException {
		
	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<StartSetupMessage>.MessageRelatedObjects contextObjects,
			StartSetupMessage message, ApsData context) throws ApsMessageManagementException {
		contextObjects.task.setStatus(TaskStatus.EXECUTING_SETUP);
		contextObjects.task.setAcquiredStartSetup(message.getMessageTimestamp());
		contextObjects.machine.setAvailability(ReservableObjectAvailability.EXECUTING_SETUP);
		contextObjects.task.setLocked(true);
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;
	}

}