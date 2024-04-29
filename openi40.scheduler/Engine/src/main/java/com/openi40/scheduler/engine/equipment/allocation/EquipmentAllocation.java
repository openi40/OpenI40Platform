package com.openi40.scheduler.engine.equipment.allocation;

import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
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
public class EquipmentAllocation {
	private List<IOperation> Operations;

	public final List<IOperation> getOperations() {
		return Operations;
	}

	private TaskEquipmentInfo ChoosedTaskEquipment;

	public final TaskEquipmentInfo getChoosedTaskEquipment() {
		return ChoosedTaskEquipment;
	}

	public EquipmentAllocation(List<IOperation> operations, TaskEquipmentInfo choosedTaskEquipment) {
		this.Operations = operations;
		this.ChoosedTaskEquipment = choosedTaskEquipment;
	}
}