package com.openi40.scheduler.engine.rules.tasksrelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
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
@DefaultImplementation(entityClass = Task.class,implemented = ITasksRelationRulesGenerator.class)
public class TasksRelationRulesGeneratorImpl extends BusinessLogic<Task> implements ITasksRelationRulesGenerator {

	@Override
	public List<TasksRelationRule> rebuildRules(List<TasksRelationRule> actualRules, Task task,
			ApsLogicOptions schedulingOptions) {
		List<TasksRelationRule>  rules=new ArrayList<TasksRelationRule>();
		IApsLogic algorithm = componentsFactory.create(IApsLogic.class, task.getParentSchedulingSet().getAlgorithmType(), task.getParentSchedulingSet(), task.getContext());
		Map<String,Object> environment=new HashMap<String,Object>();
		environment.put("task", task);
		if (algorithm.getDirection() == ApsLogicDirection.FORWARD) {
			List<TaskEdge> childTasks = task.getChildTasks();
			for (TaskEdge edge : childTasks) {
				Task childTask = edge.getProducerTask();
				environment.put("relatedTask", childTask);
				// Constraint do not finish before child tasks
				TasksRelationRule finishAfterChildConstraintRule = new TasksRelationRule(task, Rule.ConstraintOrigin.SCHEDULING, Rule.ConstraintPriority.WARNS, childTask, edge);
				
				finishAfterChildConstraintRule.setUnmetConstraintMessage(new ApsMessage(this, ApsMessageConstrants.FINISH_AFTER_CHILD_CONSTRAINT_RULE,environment,task.getContext()));
				rules.add(finishAfterChildConstraintRule);
			}
		} else if (algorithm.getDirection() == ApsLogicDirection.BACKWARD) {
			if (task.getParentTask() != null) {
				// Adding the relation with parent task, will be the coordinator to understand
				// its meaning
				environment.put("relatedTask", task.getParentTask().getConsumerTask());
				TasksRelationRule finishAtParentStart = new TasksRelationRule(task, Rule.ConstraintOrigin.SCHEDULING, Rule.ConstraintPriority.WARNS, task.getParentTask().getConsumerTask(), task.getParentTask());
				finishAtParentStart.setUnmetConstraintMessage(new ApsMessage(this, ApsMessageConstrants.FINISH_TASK_TIME_FOLLOWING_TASK_START_TIME_RELATION,environment,task.getContext()));
				rules.add(finishAtParentStart);
			}
		}
		return rules;
	}

}
