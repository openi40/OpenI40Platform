package com.openi40.scheduler.engine.changeover;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.equipment.TaskEquipmentChangeOverInfo;
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
 * This component calculates change of equipment(s) timing due to changeover
 * matrix or other algorithms
 */
@BusinessInterface(entityClass = TaskEquipmentChangeOverInfo.class)
public interface IChangeOverLogic extends IBusinessLogic<TaskEquipmentChangeOverInfo> {
	/**
	 * This method retrieves nominal change over time for the task specified as
	 * first argument in the change of equipment reppresented by the second
	 * parameter.
	 * 
	 * @param task
	 * @param changeOverInfo
	 * @return
	 */
	double calculateChangeOverTime(Task task, TaskEquipmentChangeOverInfo changeOverInfo);
	boolean isChangeOverManaged(Task task, TaskEquipmentChangeOverInfo changeOverInfo);
}