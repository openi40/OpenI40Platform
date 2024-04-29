package com.openi40.scheduler.engine.equipment.allocation;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.RealTimeSegmentRequirements;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *
 *         Reading available equipment configurations tries to generate their
 *         possible calendar allocations
 */
@BusinessInterface(entityClass = EquipmentRule.class)
public interface IEquipmentAllocator extends IBusinessLogic<EquipmentRule> {
	/**
	 * Generates all possible equipment allocation in the time ranges passed as
	 * parameters.
	 * 
	 * @param constraint
	 * @param SetupTimeRange
	 * @param WorkTimeRange
	 * @param apsLogicOptions
	 * @param scheduledActivity
	 * @param context
	 */
	List<EquipmentAllocation> calculateAllocations(EquipmentRule constraint, TimeSegmentRequirement SetupTimeRange,
			TimeSegmentRequirement WorkTimeRange, ApsLogicOptions apsLogicOptions, Task task, ApsData context);

	/**
	 * Generates all possible equipment allocation in the time ranges passed as
	 * parameters with the specific configurations passed as II parameter.
	 * 
	 * @param constraint
	 * @param SetupTimeRange
	 * @param WorkTimeRange
	 * @param apsLogicOptions
	 * @param scheduledActivity
	 * @param context
	 */
	List<EquipmentAllocation> calculateAllocations(EquipmentRule constraint, List<TaskEquipmentInfo> equipmentInfos,
			TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange,
			ApsLogicOptions apsLogicOptions, Task task, ApsData context);

	List<EquipmentAllocation> calculateRealtimeProductionAllocations(Machine usedMachine, EquipmentRule constraint,
			TaskEquipmentInfo taskEquipmentInfo, RealTimeSegmentRequirements realtimeTaskRequirements,
			ApsSchedulingSet schedulingSet, Task task, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener, ApsData context);
}