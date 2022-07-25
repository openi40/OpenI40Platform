package com.openi40.scheduler.engine.messages.handling.impl;

import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.tasks.TaskStatus;

public interface IMessagesManagementStateMachineGraphService {
	TaskStatus[] getPrerequisiteTaskStates(AbstractBaseMessage baseMessage);

	TaskStatus getNextTaskState(AbstractBaseMessage baseMessage);

	ReservableObjectAvailability[] getPrerequisiteMachineStates(AbstractBaseMessage baseMessage);

	ReservableObjectAvailability getNextMachineStates(AbstractBaseMessage baseMessage);

}
