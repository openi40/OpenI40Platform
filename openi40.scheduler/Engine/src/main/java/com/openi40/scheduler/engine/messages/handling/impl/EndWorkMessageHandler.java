package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.EndWorkMessage;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.messages.StartWorkMessage;

@Service
public class EndWorkMessageHandler extends AbstractSpecializedMessageHandler<EndWorkMessage> {

	public EndWorkMessageHandler() {
		super(EndWorkMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<EndWorkMessage>.MessageRelatedObjects contextObjects,
			EndWorkMessage message, ApsData context) throws ApsMessageValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected ApsMessageManagementResponse apply(
			AbstractSpecializedMessageHandler<EndWorkMessage>.MessageRelatedObjects contextObjects,
			EndWorkMessage message, ApsData context) throws ApsMessageManagementException {
		// TODO Auto-generated method stub
		return null;
	}

}
