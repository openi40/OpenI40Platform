package com.openi40.scheduler.engine.tasksoffset;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.TasksRelationRule;
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
@BusinessInterface(entityClass = TasksRelationRule.class)
public interface ITasksRelationOffset extends IBusinessLogic<TasksRelationRule> {
	public double calculateStartWorkOffset(TasksRelationRule rule, TaskEquipmentInfo producerEquipment, TaskEquipmentInfo consumerEquipment, ApsSchedulingSet apsAction);
	public double calculateEndWorkOffset(TasksRelationRule rule, TaskEquipmentInfo producerEquipment, TaskEquipmentInfo consumerEquipment, ApsSchedulingSet apsAction);
	
}