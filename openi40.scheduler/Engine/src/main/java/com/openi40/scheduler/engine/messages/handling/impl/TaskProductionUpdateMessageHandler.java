package com.openi40.scheduler.engine.messages.handling.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.TaskProductionUpdateMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;

@Service
public class TaskProductionUpdateMessageHandler extends AbstractSpecializedMessageHandler<TaskProductionUpdateMessage> {

	public TaskProductionUpdateMessageHandler() {
		super(TaskProductionUpdateMessage.class);

	}

	@Override
	protected void contextObjectAwareMessageValidation(
			AbstractSpecializedMessageHandler<TaskProductionUpdateMessage>.MessageRelatedObjects contextObjects,
			TaskProductionUpdateMessage message, ApsData context) throws ApsMessageValidationException {
		checkAcquiredMachineCodeCoherency(contextObjects, message);

	}

	@Override
	protected ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			AbstractSpecializedMessageHandler<TaskProductionUpdateMessage>.MessageRelatedObjects contextObjects,
			TaskProductionUpdateMessage message, ApsData context) throws ApsMessageManagementException {
		if (contextObjects.task.getAcquiredProductionUpdate() == null
				|| contextObjects.task.getAcquiredProductionUpdate().before(message.getMessageTimestamp())) {
			contextObjects.task.setAcquiredProductionUpdate(message.getMessageTimestamp());
		}
		if (message.isIncremental()) {
			double produced = contextObjects.task.getQtyProduced() + message.getProduced();
			contextObjects.task.setQtyProduced(produced);
		} else {
			double produced = message.getProduced();
			contextObjects.task.setQtyProduced(produced);
		}
		ApsMessageManagementResponse response = new ApsMessageManagementResponse();
		response.setReschedule(true);
		return response;

	}

}
