package com.openi40.scheduler.engine.rules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.rules.date.IDateRulesGenerator;
import com.openi40.scheduler.engine.rules.equipment.IEquipmentRulesGenerator;
import com.openi40.scheduler.engine.rules.material.IMaterialRulesGenerator;
import com.openi40.scheduler.engine.rules.tasksrelation.ITasksRelationRulesGenerator;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.rules.DateRule;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.rules.Rule.ConstraintPriority;
import com.openi40.scheduler.model.tasks.Task;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IRuleBuilder.class, entityClass = Task.class)
public class RuleBuilderImpl extends BusinessLogic<Task> implements IRuleBuilder {
	static Logger LOGGER = LoggerFactory.getLogger(RuleBuilderImpl.class);

	public void rebuildRules(Task task) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin RuleBuilderImpl.rebuildRules(...)", "");
		// Delete all non locked constraintsRule
		List<Rule> constraintsList = task.getConstraintRules();
		List<Rule> removeRulesList = new ArrayList<Rule>();
		for (Rule constraint : constraintsList) {
			if (!constraint.isLocked()) {
				removeRulesList.add(constraint);
			}
		}
		for (Rule constraint : removeRulesList) {
			task.getConstraintRules().remove(constraint);
		}

		ApsLogicOptions schedulingOptions = task.getParentSchedulingSet().getOptions();
		IDateRulesGenerator dateRulesGenerator = componentsFactory.create(IDateRulesGenerator.class, task,
				task.getParentSchedulingSet());
		IMaterialRulesGenerator materialRulesGenerator = componentsFactory.create(IMaterialRulesGenerator.class, task,
				task.getParentSchedulingSet());
		IEquipmentRulesGenerator equipmentRulesGenerator = componentsFactory.create(IEquipmentRulesGenerator.class,
				task, task.getParentSchedulingSet());
		ITasksRelationRulesGenerator tasksRelationRulesGenerator = componentsFactory
				.create(ITasksRelationRulesGenerator.class, task, task.getParentSchedulingSet());
		List<DateRule> dateRules = dateRulesGenerator.rebuildRules(task.getRulesByClass(DateRule.class), task,
				schedulingOptions);
		List<MaterialRule> materialRules = materialRulesGenerator.rebuildRules(task.getRulesByClass(MaterialRule.class),
				task, schedulingOptions);
		List<EquipmentRule> equipmentRules = equipmentRulesGenerator
				.rebuildRules(task.getRulesByClass(EquipmentRule.class), task, schedulingOptions);
		List<TasksRelationRule> tasksRelationRules = tasksRelationRulesGenerator
				.rebuildRules(task.getRulesByClass(TasksRelationRule.class), task, schedulingOptions);
		task.getConstraintRules().addAll(dateRules);
		task.getConstraintRules().addAll(materialRules);
		task.getConstraintRules().addAll(equipmentRules);
		task.getConstraintRules().addAll(tasksRelationRules);
		// if task is produced/aborted/under production its constraint rule will became
		// all WARNING and
		// compatible to scheduling in every situation
		if (task.isProductionLock()) {
			for (Rule rule : task.getConstraintRules()) {
				rule.setPriority(ConstraintPriority.WARNS);
			}
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End  RuleBuilderImpl.rebuildRules(...)", "");
	}

}