package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.EndWorkMessage;

@Service
public class EndWorkMessageHandler extends AbstractSpecializedMessageHandler<EndWorkMessage> {

	public EndWorkMessageHandler() {
		super(EndWorkMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<EndWorkMessage>.MessageRelatedObjects contextObjects,
			EndWorkMessage message, ApsData context) throws ApsMessageValidationException {
		checkAcquiredMachineCodeCoherency(contextObjects, message);

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<EndWorkMessage>.MessageRelatedObjects contextObjects,
			EndWorkMessage message, ApsData context) throws ApsMessageManagementException {
		contextObjects.task.setAcquiredEndWork(message.getMessageTimestamp());
		contextObjects.task.setLocked(true);
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;
	}

}
