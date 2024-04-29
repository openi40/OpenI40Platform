package com.openi40.scheduler.engine.rules.date;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.dates.DateChoice;
import com.openi40.scheduler.model.planning.dates.DateEvaluatedChoice;
import com.openi40.scheduler.model.rules.DateRule;
import com.openi40.scheduler.model.rules.Rule.ConstraintPriority;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
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
@DefaultImplementation(implemented = IDatePlanSolver.class, entityClass = DateRule.class)
public class DatePlanSolverImpl extends BusinessLogic<DateRule> implements IDatePlanSolver {
	static Logger LOGGER = LoggerFactory.getLogger(DatePlanSolverImpl.class);

	public DatePlanSolverImpl() {

	}

	private boolean IsConstraintSafisfied(DateRule dateConstraintRule, TimeSegmentRequirement requirement) {
		boolean isOk = false;
		if (dateConstraintRule.getEndAlignment() != null) {
			switch (dateConstraintRule.getEndAlignment()) {
			case END_BEFORE_END_ASAP:
			case END_ON_END_PRECISELY:
				isOk = requirement.getEndDateTime() != null
						&& requirement.getEndDateTime().compareTo(dateConstraintRule.getDate()) <= 0;
			}
		}
		if (dateConstraintRule.getStartAlignment() != null) {
			switch (dateConstraintRule.getStartAlignment()) {
			case START_AFTER_START_ASAP:
			case START_ON_START_PRECISELY:
				isOk = ((dateConstraintRule.getEndAlignment() != null && isOk)
						|| (dateConstraintRule.getEndAlignment() == null)) && requirement.getStartDateTime() != null
						&& requirement.getStartDateTime().compareTo(dateConstraintRule.getDate()) >= 0;

			}
		}
		return isOk;

	}

	@Override
	public DateChoice createPlan(DateRule rule, ApsSchedulingSet action, ApsLogicDirection direction) {
		DateRule constraint = rule;
		DateChoice dateConstraintSatisfactionPlan = new DateChoice(constraint);
		return dateConstraintSatisfactionPlan;
	}

	@Override
	public List<DateEvaluatedChoice> generateChoices(DateChoice plan, TimeSegmentRequirement SetupTimeRange,
			TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin DateConstraintRuleHandler.ElaborateSolvingPlanOptions(...)", "");
		List<DateEvaluatedChoice> outOptions = new ArrayList<DateEvaluatedChoice>();
		DateRule dateConstraintRule = (DateRule) plan.getConstraint();
		dateConstraintRule.setCurrentlySatisfied(false);
		TimeSegmentRequirement WorkTimeRangeOut = null, SetupTimeRangeOut = null;

		DateEvaluatedChoice option = null;
		if (dateConstraintRule.getEndAlignment() != null && (dateConstraintRule.getPriority()==ConstraintPriority.MANDATORY || plan.getConstraint().getTargetTask().getParentSchedulingSet().getAlgorithmDirection()==ApsLogicDirection.BACKWARD)) {
			switch (dateConstraintRule.getEndAlignment()) {

			case END_BEFORE_END_ASAP:
			case END_ON_END_PRECISELY: {
				switch (dateConstraintRule.getTimeRangeType()) {
				case TASK_WHOLE_TIME:
				case WORK_TIME: {
					if (WorkTimeRange != null && !IsConstraintSafisfied(dateConstraintRule, WorkTimeRange)) {
						WorkTimeRangeOut = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME, null,
								EndDateTimeAlignment.END_BEFORE_END_ASAP);
						WorkTimeRangeOut.setEndDateTime(dateConstraintRule.getDate());
					}
				}
					break;
				case SETUP_TIME: {
					if (SetupTimeRange != null && !IsConstraintSafisfied(dateConstraintRule, SetupTimeRange)) {
						SetupTimeRangeOut = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME, null,
								EndDateTimeAlignment.END_BEFORE_END_ASAP);
						SetupTimeRangeOut.setEndDateTime(dateConstraintRule.getDate());
					}
				}
					break;
				default:
					throw new OpenI40Exception(
							"TimeRangeType=" + dateConstraintRule.getTimeRangeType() + " unmanaged and not meaningful");
				}
			}
			}
		}
		if (dateConstraintRule.getStartAlignment() != null && dateConstraintRule.getPriority()==ConstraintPriority.MANDATORY) {

			switch (dateConstraintRule.getStartAlignment()) {
			case START_AFTER_START_ASAP:
			case START_ON_START_PRECISELY: {
				switch (dateConstraintRule.getTimeRangeType()) {

				case WORK_TIME: {
					if (WorkTimeRange != null && !IsConstraintSafisfied(dateConstraintRule, WorkTimeRange)) {
						WorkTimeRangeOut = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME,
								StartDateTimeAlignment.START_AFTER_START_ASAP, null);
						WorkTimeRangeOut.setStartDateTime(dateConstraintRule.getDate());
					}

				}
					break;
				case TASK_WHOLE_TIME:
				case SETUP_TIME: {
					if (SetupTimeRange != null && !IsConstraintSafisfied(dateConstraintRule, SetupTimeRange)) {
						SetupTimeRangeOut = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME, null,
								EndDateTimeAlignment.END_BEFORE_END_ASAP);
						SetupTimeRangeOut.setStartDateTime(dateConstraintRule.getDate());
					}
				}
					break;
				default:
					throw new OpenI40Exception(
							"TimeRangeType=" + dateConstraintRule.getTimeRangeType() + " unmanaged and not meaningful");
				}
			}

			}
		}

		if (SetupTimeRangeOut != null || WorkTimeRangeOut != null) {
			outOptions.add(option = new DateEvaluatedChoice(plan, SetupTimeRangeOut, WorkTimeRangeOut,
					new ArrayList<IOperation>()));
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End DateConstraintRuleHandler.ElaborateSolvingPlanOptions(...)",
					"Return WorkTimeRangeOut=>" + WorkTimeRangeOut + " SetupTimeRangeOut=>" + SetupTimeRangeOut);
		return outOptions;
	}

	@Override
	public boolean isRuleOk(DateRule dateConstraintRule) {
		boolean isOk = false;
		Task task = dateConstraintRule.getTargetTask();
		TimeSegment consideredSegment = null;
		if (dateConstraintRule.getTimeRangeType() != null) {
			switch (dateConstraintRule.getTimeRangeType()) {
			case SETUP_TIME:
				consideredSegment = task.getSetupPhaseExecution();
				break;
			case WORK_TIME:
				consideredSegment = task.getWorkPhaseExecution();
				break;
			default:
				consideredSegment = task.getMainTimeRange();
			}
		} else {
			consideredSegment = task.getMainTimeRange();
		}
		if (dateConstraintRule.getEndAlignment() != null) {
			switch (dateConstraintRule.getEndAlignment()) {

			case END_BEFORE_END_ASAP:
			case END_ON_END_PRECISELY:
				isOk = consideredSegment.isUpperLimited()
						&& consideredSegment.getEndDateTime().compareTo(dateConstraintRule.getDate()) <= 0;
			}
		}
		if (dateConstraintRule.getStartAlignment() != null) {
			switch (dateConstraintRule.getStartAlignment()) {
			case START_AFTER_START_ASAP:
			case START_ON_START_PRECISELY:
				isOk = ((dateConstraintRule.getEndAlignment() != null && isOk)
						|| dateConstraintRule.getEndAlignment() == null) && consideredSegment.isLowerLimited()
						&& consideredSegment.getStartDateTime().compareTo(dateConstraintRule.getDate()) >= 0;

			}
		}

		return isOk;
	}

}