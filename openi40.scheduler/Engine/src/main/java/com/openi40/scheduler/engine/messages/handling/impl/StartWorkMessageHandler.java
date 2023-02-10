package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.StartWorkMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;

@Service
public class StartWorkMessageHandler extends AbstractSpecializedMessageHandler<StartWorkMessage> {

	public StartWorkMessageHandler() {
		super(StartWorkMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<StartWorkMessage>.MessageRelatedObjects contextObjects,
			StartWorkMessage message, ApsData context) throws ApsMessageValidationException {
		checkAcquiredMachineCodeCoherency(contextObjects, message);

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<StartWorkMessage>.MessageRelatedObjects contextObjects,
			StartWorkMessage message, ApsData context) throws ApsMessageManagementException {
		contextObjects.task.setAcquiredStartWork(message.getMessageTimestamp());
		contextObjects.task.setAcquiredWorkUsedResources(message.getUsedResources());
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;
	}

}
