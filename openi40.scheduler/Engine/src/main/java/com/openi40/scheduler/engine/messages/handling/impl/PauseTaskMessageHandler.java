package com.openi40.scheduler.engine.messages.handling.impl;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.PauseTaskMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;

public class PauseTaskMessageHandler extends AbstractSpecializedMessageHandler<PauseTaskMessage> {

	public PauseTaskMessageHandler(Class<PauseTaskMessage> messageType) {
		super(messageType);
		
	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<PauseTaskMessage>.MessageRelatedObjects contextObjects,
			PauseTaskMessage message, ApsData context) throws ApsMessageValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<PauseTaskMessage>.MessageRelatedObjects contextObjects,
			PauseTaskMessage message, ApsData context) throws ApsMessageManagementException {
		// TODO Auto-generated method stub
		return null;
	}

}
