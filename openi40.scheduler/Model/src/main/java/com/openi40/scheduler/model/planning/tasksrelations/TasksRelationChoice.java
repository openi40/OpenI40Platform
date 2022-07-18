package com.openi40.scheduler.model.planning.tasksrelations;

import com.openi40.scheduler.model.planning.Plan;
import com.openi40.scheduler.model.rules.TasksRelationRule;
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
public class TasksRelationChoice extends Plan<TasksRelationRule> {
	public TasksRelationChoice(TasksRelationRule constraint) {
		super(constraint);

	}

}