package com.openi40.scheduler.engine.worktime;

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
 * This component can calculate the nominal working time for an equipment and a
 * specific task
 */
@BusinessInterface(entityClass = TaskEquipmentInfo.class)
public interface IWorkTimeLogic extends IBusinessLogic<TaskEquipmentInfo> {
	/**
	 * Returns the working time
	 * 
	 * @param scheduledActivity
	 * @param entityWithCapacity
	 */
	double calculateWorkTime(Task scheduledActivity, TaskEquipmentInfo entityWithCapacity);
	double calculateWorkTime(Task scheduledActivity,double qty, TaskEquipmentInfo entityWithCapacity);
}