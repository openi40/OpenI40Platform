package com.openi40.scheduler.engine.rules.tasksrelation;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.tasksoffset.ITasksRelationOffset;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.tasksrelations.TasksRelationChoice;
import com.openi40.scheduler.model.planning.tasksrelations.TasksRelationEvaluatedChoice;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
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
@DefaultImplementation(implemented = ITasksRelationsPlanSolver.class, entityClass = TasksRelationRule.class)
public class TasksRelationPlanSolverImpl extends BusinessLogic<TasksRelationRule> implements ITasksRelationsPlanSolver {
	static Logger LOGGER = LoggerFactory.getLogger(TasksRelationPlanSolverImpl.class);

	public TasksRelationPlanSolverImpl() {

	}

	private TimeSegmentRequirement createSetupRequirement() {
		return new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
	}

	private TimeSegmentRequirement createWorkRequirement() {
		return new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
	}

	private TasksRelationEvaluatedChoice generateSolvingPlanOption(TasksRelationChoice plan, TasksRelationRule rule,
			TimeSegment SetupPhase, TimeSegment WorkPhase) {
		double offset =0.0;
		//ON START_AFTER_END there is no meaning in offset for batching, for all other alignments it is to be taken in consideration
		if (rule.getPeriodAlignment()!= PeriodsAlignmentType.START_AFTER_END) {
			offset=calculateOffset(rule, rule.getTargetTask());
		}
		TimeSegmentRequirement SetupRequirement = null, WorkRequirement = null;

		IApsLogic logic = componentsFactory.create(IApsLogic.class,
				rule.getTargetTask().getParentSchedulingSet().getAlgorithmType(),
				rule.getTargetTask().getParentSchedulingSet(), rule.getTargetTask().getContext());
		switch (logic.getDirection()) {
		case FORWARD: {
			switch (rule.getTimeRangeType()) {
			case WORK_TIME: {

				switch (rule.getPeriodAlignment()) {
				case START_AFTER_END: {
					if (rule.getRelatedTask().getEndDateTime() != null
							&& rule.getRelatedTask().getEndDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME,
								StartDateTimeAlignment.START_AFTER_START_ASAP, null);
						WorkRequirement.setStartDateTime(DateUtil.add(rule.getRelatedTask().getEndDateTime(),
								GregorianCalendar.MINUTE, (int) offset));
					}
				}
					break;
				case START_AFTER_START: {
					if (rule.getRelatedTask().getStartDateTime() != null
							&& rule.getRelatedTask().getStartDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME,
								StartDateTimeAlignment.START_AFTER_START_ASAP, null);
						WorkRequirement.setStartDateTime(DateUtil.add(rule.getRelatedTask().getStartDateTime(),
								GregorianCalendar.MINUTE, (int) offset));
					}

				}
					break;
				case END_AFTER_END: {
					if (rule.getRelatedTask().getEndDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME, null,
								EndDateTimeAlignment.END_ON_END_PRECISELY);
						WorkRequirement.setEndDateTime(DateUtil.add(rule.getRelatedTask().getEndDateTime(),
								GregorianCalendar.MINUTE, (int) Math.round(offset)));
					}
				}
					break;
				default:
					throw new OpenI40Exception(
							"The alignment type " + rule.getPeriodAlignment() + " is not yet implemented");
				}
			}
				break;

			default:
				throw new OpenI40Exception(
						"The TimeRangeType=>" + rule.getTimeRangeType() + " is not yet supported from this algorithm");
			}
		}
			break;
		case BACKWARD: {
			switch (rule.getTimeRangeType()) {
			// IN bacward scheduling the edge is reported reversed so simply use alignment
			// from parent for the child in a opposite way from start than from end
			// using right alignments
			case WORK_TIME: {

				switch (rule.getPeriodAlignment()) {
				case START_AFTER_END: {
					if (rule.getRelatedTask().getStartDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME, null,
								EndDateTimeAlignment.END_BEFORE_END_ASAP);
						WorkRequirement.setEndDateTime(DateUtil.add(rule.getRelatedTask().getStartDateTime(),
								GregorianCalendar.MINUTE, -(int) Math.round(offset)));

					}
				}
					break;
				case START_AFTER_START: {
					if (rule.getRelatedTask().getStartDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME,
								StartDateTimeAlignment.START_AFTER_START_ASAP, null);
						WorkRequirement.setStartDateTime(DateUtil.add(rule.getRelatedTask().getStartDateTime(),
								GregorianCalendar.MINUTE, -(int) Math.round(offset)));
					}

				}
					break;
				case END_AFTER_END: {
					if (rule.getRelatedTask().getEndDateTime() != null) {
						WorkRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME, null,
								EndDateTimeAlignment.END_ON_END_PRECISELY);
						WorkRequirement.setEndDateTime(DateUtil.add(rule.getRelatedTask().getEndDateTime(),
								GregorianCalendar.MINUTE, (int) Math.round(offset)));
					}
				}
					break;
				default:
					throw new OpenI40Exception(
							"The alignment type " + rule.getPeriodAlignment() + " is not yet implemented");
				}
			}
				break;
			default:
				throw new OpenI40Exception(
						"The TimeRangeType=>" + rule.getTimeRangeType() + " is not yet supported from this algorithm");
			}
		}
			break;
		}

		return new TasksRelationEvaluatedChoice(plan, SetupRequirement, WorkRequirement, new ArrayList<IOperation>());
	}

	protected boolean before(Date parameter, Date toMatch, double offset) {
		return parameter != null && toMatch != null && parameter != null && toMatch != null
				&& parameter.compareTo(DateUtil.add(toMatch, GregorianCalendar.MINUTE, (int) Math.round(offset))) <= 0;
	}

	protected boolean after(Date parameter, Date toMatch, double offset) {
		return parameter != null && toMatch != null && parameter != null && toMatch != null
				&& parameter.compareTo(DateUtil.add(toMatch, GregorianCalendar.MINUTE, (int) Math.round(offset))) >= 0;
	}

	protected boolean IsConstraintSafisfied(TasksRelationRule tasksRelationConstraintRule,
			TimeSegment timeRangeParameter, TimeSegment timeRangeToMatch, double offset) {
		boolean isOk = false;

		switch (tasksRelationConstraintRule.getPeriodAlignment()) {
		case BETWEEN: {
			isOk = timeRangeToMatch.isInRange(timeRangeParameter);
		}
			break;
		case START_AFTER_END: {
			isOk = after(timeRangeParameter.getStartDateTime(), timeRangeToMatch.getEndDateTime(), offset);
		}
			break;
		case START_AFTER_START: {
			isOk = after(timeRangeParameter.getStartDateTime(), timeRangeToMatch.getStartDateTime(), offset);
		}
		case END_AFTER_END: {
			isOk=after(timeRangeParameter.getEndDateTime(),timeRangeToMatch.getEndDateTime(),offset);
		}
			break;

		default:
			throw new OpenI40Exception("The alignment type " + tasksRelationConstraintRule.getPeriodAlignment()
					+ " is not yet implemented");

		}
		return isOk;
	}

	protected double calculateOffset(TasksRelationRule tasksRelationConstraintRule, Task task) {
		double offset = 0L;
		ITasksRelationOffset offsetEvaluator = this.componentsFactory.create(ITasksRelationOffset.class,
				tasksRelationConstraintRule, task.getParentSchedulingSet());
		//offset = offsetEvaluator.calculateStartWorkOffset(tasksRelationConstraintRule, task.getParentSchedulingAction());
		return offset;
	}

	@Override
	public TasksRelationChoice createPlan(TasksRelationRule rule, ApsSchedulingSet action, ApsLogicDirection direction) {
		TasksRelationChoice satisfactionPlan = new TasksRelationChoice(rule);
		return satisfactionPlan;
	}

	@Override
	public List<TasksRelationEvaluatedChoice> generateChoices(TasksRelationChoice plan,
			TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction) {
		TasksRelationRule rule = (TasksRelationRule) plan.getConstraint();
		List<TasksRelationEvaluatedChoice> outList = new ArrayList<TasksRelationEvaluatedChoice>();
		switch (rule.getTimeRangeType()) {
		case SETUP_TIME: {
			TasksRelationEvaluatedChoice option = generateSolvingPlanOption(plan, rule,
					rule.getRelatedTask().getSetupPhaseExecution(), null);
			outList.add(option);
		}
			break;
		case WORK_TIME: {
			TasksRelationEvaluatedChoice option = generateSolvingPlanOption(plan, rule, null,
					rule.getRelatedTask().getWorkPhaseExecution());
			outList.add(option);
		}
			break;
		case TASK_WHOLE_TIME: {
			TasksRelationEvaluatedChoice option = generateSolvingPlanOption(plan, rule,
					rule.getRelatedTask().getSetupPhaseExecution(), rule.getRelatedTask().getWorkPhaseExecution());
			outList.add(option);
		}
			break;
		default:
			throw new OpenI40Exception(
					"The TimeRangeType=" + rule.getTimeRangeType() + " is not managed here and not meaningful");
		}

		return outList;
	}

	@Override
	public boolean isRuleOk(TasksRelationRule tasksRelationConstraintRule) {
		boolean isOk = false;

		Task task = tasksRelationConstraintRule.getTargetTask();
		Task dependentTask = tasksRelationConstraintRule.getEdge() != null
				? tasksRelationConstraintRule.getEdge().getConsumerTask()
				: tasksRelationConstraintRule.getTargetTask();
		Task constraintTask = tasksRelationConstraintRule.getEdge() != null
				? tasksRelationConstraintRule.getEdge().getProducerTask()
				: tasksRelationConstraintRule.getRelatedTask();
		double offset = calculateOffset(tasksRelationConstraintRule, task);
		switch (tasksRelationConstraintRule.getTimeRangeType()) {
		case SETUP_TIME: {
			isOk = IsConstraintSafisfied(tasksRelationConstraintRule, dependentTask.getSetupPhaseExecution(),
					constraintTask.getSetupPhaseExecution(), offset);
		}
			break;
		case WORK_TIME: {
			isOk = IsConstraintSafisfied(tasksRelationConstraintRule, dependentTask.getWorkPhaseExecution(),
					constraintTask.getWorkPhaseExecution(), offset);
		}
			break;
		case TASK_WHOLE_TIME: {
			isOk = IsConstraintSafisfied(tasksRelationConstraintRule, dependentTask.getMainTimeRange(),
					constraintTask.getMainTimeRange(), offset);
		}
			break;
		default:
			throw new OpenI40Exception("The TimeRangeType=" + tasksRelationConstraintRule.getTimeRangeType()
					+ " is not managed here and not meaningful");
		}
		return isOk;
	}
}