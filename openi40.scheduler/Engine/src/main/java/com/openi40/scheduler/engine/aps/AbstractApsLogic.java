package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IEnvironment;
import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.material.IMaterialTimeLineManager;
import com.openi40.scheduler.engine.rules.IRuleBuilder;
import com.openi40.scheduler.engine.rules.IRulePlanSolver;
import com.openi40.scheduler.engine.rules.date.IDatePlanSolver;
import com.openi40.scheduler.engine.rules.equipment.IEquipmentPlanSolver;
import com.openi40.scheduler.engine.rules.material.IMaterialPlanSolver;
import com.openi40.scheduler.engine.rules.planner.IPlanner;
import com.openi40.scheduler.engine.rules.planner.ProductionMonitoringUtil;
import com.openi40.scheduler.engine.rules.tasksrelation.ITasksRelationsPlanSolver;
import com.openi40.scheduler.engine.taskssort.ITasksSort;
import com.openi40.scheduler.engine.taskssort.SorterHelper;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SortOption;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.timeline.TimeLineException;
import com.openi40.scheduler.model.planning.Plan;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.rules.DateRule;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.rules.Rule.ConstraintPriority;
import com.openi40.scheduler.model.rules.TasksRelationRule;
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
 *         Base class for Scheduling algorithms implementations with base
 *         building blocks implementations
 */
public abstract class AbstractApsLogic extends BusinessLogic<ApsSchedulingSet> implements IApsLogic {
	static Logger LOGGER = LoggerFactory.getLogger(AbstractApsLogic.class);

	public abstract ApsLogicDirection getDirection();

	public ApsLogicOptions createDefaultOptions(ApsSchedulingSet EntityObject) {

		ApsLogicOptions defaultOptions = new ApsLogicOptions();
		SorterHelper[] list = SorterHelper.PROPERTIES_LIST;
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				SorterHelper sorterHelper = list[i];
				SortOption opt = new SortOption(sorterHelper.getPropertyName(), sorterHelper.getDescription(),
						sorterHelper.getLongDescription(),
						(sorterHelper.getDefaultDirection() == null
								|| !sorterHelper.getDefaultDirection().equalsIgnoreCase("DESC"))
										? ApsLogicOptions.SortDirection.ASC
										: ApsLogicOptions.SortDirection.DESC);
				defaultOptions.getSortOptions().add(opt);
			}
		}
		return defaultOptions;
	}

	public void schedule(ApsSchedulingSet action, ApsLogicNotifiedObjects observer) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin AbstractSchedulingAlgorithm.Schedule(action)", "Running main scheduler loop");
		ApsData context = action.getContext();
		ApsLogicOptions options = action.getOptions();
		IEnvironment environment = action.getContext();
		List<Task> tasks = createCustomOrderedTaskList(action);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Listing Tasks order for action:" + action.getId());
			for (Task task : tasks) {
				LOGGER.debug("Task:" + task.getCode());
			}
			LOGGER.debug("Listed!");
		}
		List<PlanGraphItem> decisionNodes = new ArrayList<PlanGraphItem>();
		boolean scheduled = true;
		Hashtable hashTable = new Hashtable();
		for (Task task : tasks) {
			PlanGraphItem decisionNode = null;
			task.setDecisionGraphItem(null);
			// If we have task state that is under production monitoring
			// we let its state be calculated by IProductionMonitoringScheduler that is
			// able to calculate task status according to received production monitoring
			// updates
			if (ProductionMonitoringUtil.isUnderProduction(task)) {
				IPlanner planner = this.componentsFactory.create(IPlanner.class, action, context);
				decisionNode = planner.doProductionSupervision(task, action,
						observer != null ? observer.getConstraintSolutionListener() : null, getDirection());
			} else {
				// let extended class implement the single task scheduling policy
				decisionNode = schedule(task, action, observer);
			}
			scheduled = scheduled && task.isSuccessfullyScheduled();
			if (task.isSuccessfullyScheduled()) {
				if (task.getProduction() != null) {
					task.getProduction().setAvailabilityDateTime(task.getWorkPhaseExecution().getEndDateTime());
				}
				for (ItemConsumed consumption : task.getMaterialConsumptions()) {
					if (task.getWorkPhaseExecution() != null && task.getWorkPhaseExecution().getEndDateTime() != null) {
						consumption.setSatisfiedDateTime(task.getWorkPhaseExecution().getEndDateTime());
					}
				}
				IMaterialTimeLineManager materialTimeLineManager = this.componentsFactory
						.create(IMaterialTimeLineManager.class, action.getContext(), action.getContext());
				try {
					materialTimeLineManager.addScheduledTask(task, context);
				} catch (TimeLineException e) {
					throw new OpenI40Exception("error in timeline management", e);
				}
			}
			if (decisionNode != null) {
				decisionNodes.add(decisionNode);
				hashTable.put(decisionNode.getTask().getId(), decisionNode);
			}

		}
		action.setScheduled(scheduled);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End AbstractSchedulingAlgorithm.Schedule(action)", "Running main scheduler loop");
	}

	protected abstract PlanGraphItem schedule(Task task, ApsSchedulingSet EntityObject,
			ApsLogicNotifiedObjects observer);

	/**
	 * Uses scheduling options and eventual custom Task order evaluation factory to
	 * create the ordered list of tasks according to ordering criteria.
	 * 
	 * @param sAction
	 * @return
	 */
	protected List<Task> createCustomOrderedTaskList(ApsSchedulingSet sAction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin CreateCustomOrderedTaskList(..) Custom ordering ScheduledTask(s)", "");
		ITasksSort tasksSort = this.componentsFactory.create(ITasksSort.class, sAction, sAction.getContext());
		List<Task> outval = tasksSort.sort(sAction);
		Map<String, Task> checkDuplicates = new HashMap<>();
		if (outval != null) {
			for (Task task : outval) {
				if (checkDuplicates.containsKey(task.getId())) {
					String _msg = "This ordering algorithms makes tasks duplicates!!! for task=>" + task.getId()
							+ " order:" + task.getWorkOrderCode() + " seqCode:" + task.getSequenceCode();
					LOGGER.error(_msg);
					throw new OpenI40Exception(_msg);
				}
				checkDuplicates.put(task.getId(), task);
			}
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End CreateCustomOrderedTaskList(..)", "");
		return outval;
	}

	/**
	 * Rebuilds all constraints on the passed task
	 * 
	 * @param task
	 */
	protected void regenerateTaskConstraints(Task task) {
		if (!task.isLocked()) {
			IRuleBuilder constraintBuilder = this.componentsFactory.create(IRuleBuilder.class, task, task.getContext());
			constraintBuilder.rebuildRules(task);
		}
	}

	protected final void applyPlanOperations(PlanGraphItem outNode) {

		for (Plan plan : outNode.getPlans()) {
			if (plan.getChoosed() != null) {
				for (IOperation operation : plan.getChoosed().getOperations()) {
					if (!operation.isApplied()) {
						operation.apply(this.componentsFactory);
					}
				}
			}
		}
	}

	protected final void undoPlanOperations(Plan plan) {

		if (plan.getChoosed() != null) {
			for (IOperation operation : plan.getChoosed().getOperations()) {
				if (operation.isApplied()) {
					operation.reverse(this.componentsFactory);
				}
			}
		}

	}

	protected final void undoPlanOperations(PlanGraphItem outNode) {

		for (Plan plan : outNode.getPlans()) {
			undoPlanOperations(plan);
		}
	}

	protected final boolean areAllPlansSatisfactory(PlanGraphItem outNode) {
		boolean allConstraintsAreMet = true;
		for (Plan plan : outNode.getPlans()) {
			boolean thisConstraintIsMet = false;
			IRulePlanSolver constraintEvaluator = this.componentsFactory.create(IRulePlanSolver.class,
					plan.getConstraint(), outNode.getTask().getParentSchedulingSet());
			thisConstraintIsMet = constraintEvaluator.isRuleOk(plan.getConstraint());
			// 29/10/2020 thisConstraintIsMet is considered only if the constraint is
			// mandatory
			if (plan.getConstraint().getPriority() == ConstraintPriority.MANDATORY) {
				allConstraintsAreMet = allConstraintsAreMet && thisConstraintIsMet;
			}
		}
		return allConstraintsAreMet;
	}

	protected boolean verifyAllConstraints(Task task, ApsLogicNotifiedObjects notifiedObject) {
		boolean allConstraintsOk = true;
		for (int i = 0; i < task.getConstraintRules().size(); i++) {
			Rule constraintRule = task.getConstraintRules().get(i);
			IRulePlanSolver handler = null;
			if (constraintRule instanceof DateRule) {
				DateRule rule = (DateRule) constraintRule;
				handler = componentsFactory.create(IDatePlanSolver.class, rule, task.getParentSchedulingSet());
			} else if (constraintRule instanceof MaterialRule) {
				MaterialRule rule = (MaterialRule) constraintRule;
				handler = componentsFactory.create(IMaterialPlanSolver.class, rule, task.getParentSchedulingSet());
			} else if (constraintRule instanceof EquipmentRule) {
				EquipmentRule rule = (EquipmentRule) constraintRule;
				handler = componentsFactory.create(IEquipmentPlanSolver.class, rule, task.getParentSchedulingSet());
			} else if (constraintRule instanceof TasksRelationRule) {
				TasksRelationRule rule = (TasksRelationRule) constraintRule;
				handler = componentsFactory.create(ITasksRelationsPlanSolver.class, rule,
						task.getParentSchedulingSet());
			} else
				throw new OpenI40Exception("Type of constraint unsupported=>" + constraintRule.getClass().getName());
			boolean thisConstraintResult = handler.isRuleOk(constraintRule);
			constraintRule.setCurrentlySatisfied(thisConstraintResult);
			if (!thisConstraintResult && constraintRule.getUnmetConstraintMessage() != null) {
				task.getMessages().add(constraintRule.getUnmetConstraintMessage());
			}
			if (notifiedObject != null && notifiedObject.getConstraintRuleListener() != null) {
				try {
					notifiedObject.getConstraintRuleListener().verifiedState(constraintRule, task,
							thisConstraintResult);
				} catch (Throwable th) {
					LOGGER.error("Error in executor of listener", th);
				}
			}
			if (!thisConstraintResult) {
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Constraint=>" + constraintRule + " not resolved!", "");
			}
			if (constraintRule.getPriority() == ConstraintPriority.MANDATORY) {
				allConstraintsOk = allConstraintsOk && thisConstraintResult;
			}
		}

		return allConstraintsOk;
	}

	private void undoAllNodes(PlanGraphItem root) {
		List<PlanGraphItem> childs = root.getChilds();
		for (PlanGraphItem childNode : childs) {
			undoAllNodes(childNode);
		}

		for (Plan plan : root.getPlans()) {
			if (plan.getChoosed() != null) {
				for (IOperation operation : plan.getChoosed().getOperations()) {
					operation.reverse(this.componentsFactory);
				}

			}
		}

		root.getTask().setSuccessfullyScheduled(false);

	}

	private void undoUnallocableNodes(PlanGraphItem root) {
		List<PlanGraphItem> childs = root.getChilds();
		for (PlanGraphItem childNode : childs) {
			undoUnallocableNodes(childNode);
		}
		if (!root.getTask().isSuccessfullyScheduled()) {
			for (Plan plan : root.getPlans()) {
				if (plan.getChoosed() != null) {
					for (IOperation operation : plan.getChoosed().getOperations()) {
						operation.reverse(this.componentsFactory);
					}

				}
			}

		}
	}
}