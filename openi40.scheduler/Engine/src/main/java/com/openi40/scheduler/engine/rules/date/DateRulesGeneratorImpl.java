package com.openi40.scheduler.engine.rules.date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.rules.DateRule;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentType;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@DefaultImplementation(entityClass = Task.class, implemented = IDateRulesGenerator.class)
public class DateRulesGeneratorImpl extends BusinessLogic<Task> implements IDateRulesGenerator {

	@Override
	public List<DateRule> rebuildRules(List<DateRule> actualRules, Task task, ApsLogicOptions options) {
		Map<String, Object> environment = new HashMap<String, Object>();
		environment.put("task", task);
		String algoType = task.getParentSchedulingSet().getAlgorithmType();

		List<DateRule> dateRules = new ArrayList<DateRule>();
		if (task.getAskedDeliveryDateTime() != null) {
			DateRule dateConstraintRule = new DateRule(task, Rule.ConstraintOrigin.SCHEDULING,
					Rule.ConstraintPriority.WARNS, task.getAskedDeliveryDateTime(), null,
					EndDateTimeAlignment.END_BEFORE_END_ASAP);
			dateConstraintRule.setUnmetConstraintMessage(
					new ApsMessage(this,task, ApsMessageConstrants.DELIVERY_DATE_CONSTRAINT_NOT_MET, environment,task.getContext()));
			dateRules.add(dateConstraintRule);
		}

		DateRule windowMinimumBound = new DateRule(task, Rule.ConstraintOrigin.SCHEDULING,
				Rule.ConstraintPriority.MANDATORY,
				DateUtil.discretize(task.getContext().getSchedulingWindow().getStartDateTime()),
				StartDateTimeAlignment.START_AFTER_START_ASAP, null, TimeSegmentType.SETUP_TIME);
		DateRule windowMaximumBound = new DateRule(task, Rule.ConstraintOrigin.SCHEDULING,
				Rule.ConstraintPriority.MANDATORY,
				DateUtil.discretize(task.getContext().getSchedulingWindow().getEndDateTime()), null,
				EndDateTimeAlignment.END_BEFORE_END_ASAP, TimeSegmentType.WORK_TIME);
		windowMinimumBound.setUnmetConstraintMessage(
				new ApsMessage(this,task, ApsMessageConstrants.WINDOW_DATE_START_DATE_CONSTRAINT_NOT_MET, environment,task.getContext()));
		windowMaximumBound.setUnmetConstraintMessage(
				new ApsMessage(this,task, ApsMessageConstrants.WINDOW_DATE_END_DATE_CONSTRAINT_NOT_MET, environment,task.getContext()));
		dateRules.add(windowMinimumBound);
		dateRules.add(windowMaximumBound);
		if (task.getMinProductionDateConstraint() != null) {
			DateRule MinimumProductionBound = new DateRule(task, Rule.ConstraintOrigin.SCHEDULING,
					Rule.ConstraintPriority.MANDATORY, DateUtil.discretize(task.getMinProductionDateConstraint()),
					StartDateTimeAlignment.START_AFTER_START_ASAP, null, TimeSegmentType.SETUP_TIME);
			dateRules.add(MinimumProductionBound);
			windowMinimumBound.setUnmetConstraintMessage(new ApsMessage(this,task,
					ApsMessageConstrants.MIN_PRODUCTION_START_DATE_CONSTRAINT_NOT_MET, environment,task.getContext()));
		}
		if (task.getMaxProductionDateConstraint() != null) {
			DateRule MaximumProductionBound = new DateRule(task, Rule.ConstraintOrigin.SCHEDULING,
					Rule.ConstraintPriority.MANDATORY, DateUtil.discretize(task.getMaxProductionDateConstraint()), null,
					EndDateTimeAlignment.END_BEFORE_END_ASAP, TimeSegmentType.WORK_TIME);
			dateRules.add(MaximumProductionBound);
			MaximumProductionBound.setUnmetConstraintMessage(
					new ApsMessage(this,task, ApsMessageConstrants.MAX_PRODUCTION_END_DATE_CONSTRAINT_NOT_MET, environment,task.getContext()));
		}
		return dateRules;
	}

}
