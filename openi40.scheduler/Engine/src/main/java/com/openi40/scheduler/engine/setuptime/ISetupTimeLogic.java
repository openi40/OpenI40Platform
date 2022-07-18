package com.openi40.scheduler.engine.setuptime;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
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
 *
 * Recalculates setup time for a specific machine+secondary resource
 * configuration
 */
@BusinessInterface(entityClass = TaskEquipmentInfo.class)
public interface ISetupTimeLogic extends IBusinessLogic<TaskEquipmentInfo> {

	/**
	 * Retrieves/calculates setup time
	 * 
	 * @param equipment
	 * @param scheduledActivity
	 * @return
	 */
	double calculateSetupTime(TaskEquipmentInfo equipment, Task scheduledActivity);

}