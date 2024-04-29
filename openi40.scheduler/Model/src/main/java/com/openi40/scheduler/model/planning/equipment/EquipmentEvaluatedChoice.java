package com.openi40.scheduler.model.planning.equipment;

import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public class EquipmentEvaluatedChoice extends PlanChoice {
	public EquipmentEvaluatedChoice(EquipmentChoice equipmentPlan, TimeSegment setupTimeRange, TimeSegment workTimeRange, List<IOperation> reversibleOperations) {
		super(equipmentPlan, setupTimeRange, workTimeRange, reversibleOperations);

	}

	public EquipmentEvaluatedChoice(EquipmentChoice plan, TimeSegmentRequirement setupRequirement, TimeSegmentRequirement workRequirement, TaskEquipmentInfo choosedTaskEquipment, List<IOperation> reversibleOperations) {
		super(plan, setupRequirement, workRequirement, reversibleOperations);
		setPlanned(choosedTaskEquipment);
	}

	private TaskEquipmentInfo planned;

	public final TaskEquipmentInfo getPlanned() {
		return planned;
	}

	public final void setPlanned(TaskEquipmentInfo value) {
		planned = value;
	}
}