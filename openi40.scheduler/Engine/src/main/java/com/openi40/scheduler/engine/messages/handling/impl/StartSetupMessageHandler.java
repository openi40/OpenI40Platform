package com.openi40.scheduler.engine.messages.handling.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.engine.messages.handling.MessageHandlingErrorMessage;
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
		checkAcquiredMachineCodeCoherency(contextObjects, message);
	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<StartSetupMessage>.MessageRelatedObjects contextObjects,
			StartSetupMessage message, ApsData context) throws ApsMessageManagementException {
		
		
		contextObjects.task.setAcquiredStartSetup(message.getMessageTimestamp());
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;
	}

}
