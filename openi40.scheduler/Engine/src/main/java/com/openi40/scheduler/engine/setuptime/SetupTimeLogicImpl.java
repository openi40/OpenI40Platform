package com.openi40.scheduler.engine.setuptime;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.equipment.TaskProcessMetaInfo;
import com.openi40.scheduler.model.tasks.Task;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = ISetupTimeLogic.class, entityClass = TaskEquipmentInfo.class)
public class SetupTimeLogicImpl extends BusinessLogic<TaskEquipmentInfo> implements ISetupTimeLogic {
	public double calculateSetupTime(TaskEquipmentInfo equipment, Task scheduledActivity) {
		double setupTime = 0.0;

		String choosenMachineCode = equipment.getExecution() != null && equipment.getExecution().getResource() != null && equipment.getExecution().getResource().getChoosenEquipment() != null ? equipment.getExecution().getResource().getChoosenEquipment().getCode() : null;

		if (equipment.getTaskInfo() != null) {

			TaskProcessInfo infos = choosenMachineCode != null ? equipment.getTaskInfo().GetMachineInfo(choosenMachineCode) : null;
			if (infos != null && infos.getSetupTime() > 0) {
				setupTime = infos.getSetupTime();
			} else {
				setupTime = equipment.getTaskInfo().getSetupTime();
			}
		} else if (equipment.getMetaInfo() != null && equipment.getMetaInfo().getTaskMetaInfo() != null) {

			TaskProcessMetaInfo infos = choosenMachineCode != null ? equipment.getMetaInfo().getTaskMetaInfo().GetMachineMetaInfo(choosenMachineCode) : null;
			if (infos != null && infos.getSetupTime() > 0) {
				setupTime = infos.getSetupTime();
			} else {
				setupTime = equipment.getMetaInfo().getTaskMetaInfo().getSetupTime();
			}
		}
		return setupTime;

	}
}