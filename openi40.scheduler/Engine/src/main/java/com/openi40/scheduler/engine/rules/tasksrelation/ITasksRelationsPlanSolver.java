package com.openi40.scheduler.engine.rules.tasksrelation;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.rules.IRulePlanSolver;
import com.openi40.scheduler.model.planning.tasksrelations.TasksRelationChoice;
import com.openi40.scheduler.model.planning.tasksrelations.TasksRelationEvaluatedChoice;
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
public interface ITasksRelationsPlanSolver extends IRulePlanSolver<TasksRelationRule, TasksRelationChoice, TasksRelationEvaluatedChoice> {

}
