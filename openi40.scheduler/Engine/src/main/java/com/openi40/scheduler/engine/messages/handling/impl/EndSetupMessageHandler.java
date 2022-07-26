package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.EndSetupMessage;

@Service
public class EndSetupMessageHandler extends AbstractSpecializedMessageHandler<EndSetupMessage> {

	public EndSetupMessageHandler() {
		super(EndSetupMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<EndSetupMessage>.MessageRelatedObjects contextObjects,
			EndSetupMessage message, ApsData context) throws ApsMessageValidationException {
		checkAcquiredMachineCodeCoherency(contextObjects, message);

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<EndSetupMessage>.MessageRelatedObjects contextObjects,
			EndSetupMessage message, ApsData context) throws ApsMessageManagementException {
		contextObjects.task.setAcquiredEndSetup(message.getMessageTimestamp());
		contextObjects.task.setLocked(true);
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;
	}

}
