package com.openi40.scheduler.engine.messages.handling.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.messages.AbortTaskMessage;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.EndSetupMessage;
import com.openi40.scheduler.model.messages.EndWorkMessage;
import com.openi40.scheduler.model.messages.OutOfOrderMachineMessage;
import com.openi40.scheduler.model.messages.PauseTaskMessage;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.messages.StartWorkMessage;
import com.openi40.scheduler.model.messages.TaskProductionUpdateMessage;
import com.openi40.scheduler.model.messages.UnderMantainmentMachineMessage;
import com.openi40.scheduler.model.tasks.TaskStatus;

@Service
public class StateMachineGraphServiceImpl implements IMessagesManagementStateMachineGraphService {

	public StateMachineGraphServiceImpl() {

	}

	static final Map<Class<? extends AbstractBaseMessage>, TaskStatus[]> taskStatePrerequisites = new HashMap<Class<? extends AbstractBaseMessage>, TaskStatus[]>();
	static final Map<Class<? extends AbstractBaseMessage>, TaskStatus> taskNextState = new HashMap<Class<? extends AbstractBaseMessage>, TaskStatus>();
	static final Map<Class<? extends AbstractBaseMessage>, ReservableObjectAvailability[]> machineStatePrerequisites = new HashMap<Class<? extends AbstractBaseMessage>, ReservableObjectAvailability[]>();
	static final Map<Class<? extends AbstractBaseMessage>, ReservableObjectAvailability> machineNextState = new HashMap<Class<? extends AbstractBaseMessage>, ReservableObjectAvailability>();
	static {
		// Loading static state graph configuration
		// StartSetupMessage
		taskStatePrerequisites.put(StartSetupMessage.class, new TaskStatus[] { TaskStatus.NOT_YET_EXECUTED });
		machineStatePrerequisites.put(StartSetupMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.AVAILABLE });
		taskNextState.put(StartSetupMessage.class, TaskStatus.EXECUTING_SETUP);
		machineNextState.put(StartSetupMessage.class, ReservableObjectAvailability.EXECUTING_SETUP);
		// EndSetupMessage
		taskStatePrerequisites.put(EndSetupMessage.class, new TaskStatus[] { TaskStatus.EXECUTING_SETUP });
		machineStatePrerequisites.put(EndSetupMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.EXECUTING_SETUP });
		taskNextState.put(EndSetupMessage.class, TaskStatus.SETUP_DONE);
		machineNextState.put(EndSetupMessage.class, ReservableObjectAvailability.SETUP_DONE);
		// StartWorkMessage
		taskStatePrerequisites.put(StartWorkMessage.class,
				new TaskStatus[] { TaskStatus.NOT_YET_EXECUTED, TaskStatus.EXECUTING_SETUP, TaskStatus.SETUP_DONE });
		machineStatePrerequisites.put(StartWorkMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.AVAILABLE,
						ReservableObjectAvailability.EXECUTING_SETUP, ReservableObjectAvailability.SETUP_DONE });
		taskNextState.put(StartWorkMessage.class, TaskStatus.EXECUTING_WORK);
		machineNextState.put(StartWorkMessage.class, ReservableObjectAvailability.EXECUTING_WORK);
		// TaskProductionUpdateMessage
		taskStatePrerequisites.put(TaskProductionUpdateMessage.class, new TaskStatus[] { TaskStatus.EXECUTING_WORK });
		machineStatePrerequisites.put(TaskProductionUpdateMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.EXECUTING_WORK });
		taskNextState.put(TaskProductionUpdateMessage.class, TaskStatus.EXECUTING_WORK);
		machineNextState.put(TaskProductionUpdateMessage.class, ReservableObjectAvailability.EXECUTING_WORK);
		// EndWorkMessage
		taskStatePrerequisites.put(EndWorkMessage.class, new TaskStatus[] { TaskStatus.EXECUTING_WORK });
		machineStatePrerequisites.put(EndWorkMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.EXECUTING_WORK });
		taskNextState.put(EndWorkMessage.class, TaskStatus.EXECUTED);
		machineNextState.put(EndWorkMessage.class, ReservableObjectAvailability.AVAILABLE);
		// AbortTaskMessage
		taskStatePrerequisites.put(AbortTaskMessage.class,
				new TaskStatus[] { TaskStatus.EXECUTING_WORK, TaskStatus.NOT_YET_EXECUTED, TaskStatus.EXECUTING_SETUP,
						TaskStatus.EXECUTING_WORK, TaskStatus.SETUP_DONE, TaskStatus.PAUSED });
		taskNextState.put(AbortTaskMessage.class, TaskStatus.ABORTED);
		// PauseTaskMessage
		taskStatePrerequisites.put(PauseTaskMessage.class,
				new TaskStatus[] { TaskStatus.EXECUTING_WORK, TaskStatus.EXECUTING_SETUP, TaskStatus.SETUP_DONE });
		machineStatePrerequisites.put(PauseTaskMessage.class,
				new ReservableObjectAvailability[] { ReservableObjectAvailability.EXECUTING_WORK,
						ReservableObjectAvailability.EXECUTING_SETUP, ReservableObjectAvailability.SETUP_DONE });
		taskNextState.put(PauseTaskMessage.class, TaskStatus.PAUSED);
		machineNextState.put(PauseTaskMessage.class, ReservableObjectAvailability.PAUSED);
		// UnderMantainmentMessage
		machineStatePrerequisites.put(UnderMantainmentMachineMessage.class, ReservableObjectAvailability.values());
		machineNextState.put(UnderMantainmentMachineMessage.class, ReservableObjectAvailability.UNDER_MANTAINMENT);
		// BrokenMachineMessage
		machineStatePrerequisites.put(OutOfOrderMachineMessage.class, ReservableObjectAvailability.values());
		machineNextState.put(OutOfOrderMachineMessage.class, ReservableObjectAvailability.OUT_OF_ORDER);

	}

	@Override
	public TaskStatus[] getPrerequisiteTaskStates(AbstractBaseMessage baseMessage) {

		return taskStatePrerequisites.get(baseMessage.getClass());
	}

	@Override
	public TaskStatus getNextTaskState(AbstractBaseMessage baseMessage) {

		return taskNextState.get(baseMessage.getClass());
	}

	@Override
	public ReservableObjectAvailability[] getPrerequisiteMachineStates(AbstractBaseMessage baseMessage) {

		return machineStatePrerequisites.get(baseMessage.getClass());
	}

	@Override
	public ReservableObjectAvailability getNextMachineStates(AbstractBaseMessage baseMessage) {

		return machineNextState.get(baseMessage.getClass());
	}

}
