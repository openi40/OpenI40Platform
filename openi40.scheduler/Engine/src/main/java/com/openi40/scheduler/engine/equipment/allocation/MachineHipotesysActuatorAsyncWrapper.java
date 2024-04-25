package com.openi40.scheduler.engine.equipment.allocation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;

@Service
public class MachineHipotesysActuatorAsyncWrapper {
	@Async
	CompletableFuture<List<EquipmentAllocation>> allocateMachine(final TaskEquipmentInfo taskEquipmentInfo,
			final ApsLogicDirection direction, final EquipmentRule constraint,
			final TimeSegmentRequirement _initialSetupTimeRange, final TimeSegmentRequirement _initialWorkTimeRange,
			final Machine resourceOption, final ApsLogicOptions apsLogicOptions,
			final IContextualBusinessLogicFactory componentsFactory, final Task task, final ApsData context) {
		
		return new CompletableFuture<List<EquipmentAllocation>>().completeAsync(()->{
			return MachineHipotesysActuator.Instance.allocateMachine(taskEquipmentInfo, direction, constraint,
					_initialSetupTimeRange, _initialWorkTimeRange, resourceOption, apsLogicOptions,
					componentsFactory, task, context);
		});

	}
}
