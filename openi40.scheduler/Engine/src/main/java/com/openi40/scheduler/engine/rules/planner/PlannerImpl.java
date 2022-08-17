package com.openi40.scheduler.engine.rules.planner;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.UserDestinationMessageHandler;

import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.equipment.configuration.IEquipmentConfigurator;
import com.openi40.scheduler.engine.resallocator.IResourcesAllocator;
import com.openi40.scheduler.engine.resallocator.ResourcesCombination;
import com.openi40.scheduler.engine.rules.IRuleBuilder;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.engine.rules.RulePlanningEvent.RuleEventType;
import com.openi40.scheduler.engine.rules.date.IDatePlanSolver;
import com.openi40.scheduler.engine.rules.equipment.IEquipmentPlanSolver;
import com.openi40.scheduler.engine.rules.material.IMaterialPlanSolver;
import com.openi40.scheduler.engine.rules.tasksrelation.ITasksRelationsPlanSolver;
import com.openi40.scheduler.engine.setuptime.ISetupTimeLogic;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SchedulingPriorities;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.planning.Plan;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.planning.dates.DateChoice;
import com.openi40.scheduler.model.planning.dates.DateEvaluatedChoice;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.material.MaterialChoice;
import com.openi40.scheduler.model.planning.material.MaterialEvaluatedChoice;
import com.openi40.scheduler.model.planning.tasksrelations.TasksRelationChoice;
import com.openi40.scheduler.model.rules.DateRule;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;

import lombok.Data;

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
@DefaultImplementation(implemented = IPlanner.class, entityClass = ApsSchedulingSet.class)
public class PlannerImpl extends BusinessLogic<ApsSchedulingSet> implements IPlanner {
	static Logger LOGGER = LoggerFactory.getLogger(PlannerImpl.class);

	protected void restrictTimeSegment(TimeSegment outRange, TimeSegment newConditionRange) {
		if (outRange.isLowerLimited() && newConditionRange.isLowerLimited()) {
			outRange.setStartDateTime(
					DateUtil.getInstance().Max(newConditionRange.getStartDateTime(), outRange.getStartDateTime()));
		} else if (newConditionRange.isLowerLimited()) {
			outRange.setStartDateTime(newConditionRange.getStartDateTime());
		}

		if (outRange.isUpperLimited() && newConditionRange.isUpperLimited()) {
			outRange.setEndDateTime(
					DateUtil.getInstance().Min(newConditionRange.getEndDateTime(), outRange.getEndDateTime()));
		} else if (newConditionRange.isUpperLimited()) {
			outRange.setEndDateTime(newConditionRange.getEndDateTime());
		}
	}

	public PlanGraphItem doPlanSupervision(Task task, ApsSchedulingSet action,
			IRuleSolutionListener constraintSolutionListener, TimeSegmentRequirement SetupTimeRangeSpec,
			TimeSegmentRequirement WorkTimeRangeSpec, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin PlannerImpl.doPlanSupervision(..)", "task.id=>" + task.getId());
		ApsLogicOptions schedulingOptions = action.getOptions();
		PlanGraphItem decisionNode = initializePlans(task, direction);
		TimeSegmentRequirement SetupTimeRange = new TimeSegmentRequirement(SetupTimeRangeSpec);
		TimeSegmentRequirement WorkTimeRange = new TimeSegmentRequirement(WorkTimeRangeSpec);
		// Try to update each solution plan to best fitting in the from-to timerange
		List<DateChoice> dateConstraintPlans = filterPlansByType(decisionNode, DateChoice.class);
		List<TasksRelationChoice> tasksRelationConstraintSatisfactionPlans = filterPlansByType(decisionNode,
				TasksRelationChoice.class);
		List<EquipmentChoice> equipmentPlans = filterPlansByType(decisionNode, EquipmentChoice.class);
		List<MaterialChoice> materialPlans = filterPlansByType(decisionNode, MaterialChoice.class);
		// Each date constraint satisfactionplan has only a satisfactionOption with a
		// single entry
		// specificating begin and/or end of a setup/work stage
		for (DateChoice dateConstraintPlan : dateConstraintPlans) {
			IDatePlanSolver handler = componentsFactory.create(IDatePlanSolver.class,
					dateConstraintPlan.getConstraint(), task.getContext());
			List<DateEvaluatedChoice> dateSatisfactionOptions = handler.generateChoices(dateConstraintPlan,
					SetupTimeRange, WorkTimeRange, direction);
			for (PlanChoice satisfactionOption : dateSatisfactionOptions) {
				if (satisfactionOption.getSetup() != null) {
					restrictTimeSegment(SetupTimeRange, satisfactionOption.getSetup());
				}

				if (satisfactionOption.getWork() != null) {
					restrictTimeSegment(WorkTimeRange, satisfactionOption.getWork());
				}

				dateConstraintPlan.setChoosed(satisfactionOption);
				break;
			}
		}
		IResourcesAllocator allocator = componentsFactory.create(IResourcesAllocator.class, task, action.getContext());
		ResourcesCombination activeCombination = allocator.elaborateAllocation(equipmentPlans, materialPlans, task,
				SetupTimeRange, WorkTimeRange, action, direction, constraintSolutionListener);
		if (activeCombination != null) {
			task.setEquipment(activeCombination.getEquipmentAllocationOption().getPlanned());
			TaskEquipmentInfo equipment = task.getEquipment();
			task.getSetupPhaseExecution().Add(equipment.getPreparation().getEquipmentEventsGroup());
			task.getWorkPhaseExecution().Add(equipment.getExecution().getEquipmentEventsGroup());
			allocator.reserveResources(activeCombination);

			Machine machine = equipment.getExecution().getResource().getChoosenEquipment();
			// Rotate task equipment stack for changeover evaluation
			switch (direction) {
			case FORWARD: {
				if (machine != null) {

					TaskEquipmentInfo oldEquipment = machine.getCurrentEquipmentSetting();
					machine.setCurrentEquipmentSetting(equipment);
					if (oldEquipment != null) {
						machine.getEquipmentSettingHistory().add(equipment);
					}
				}
			}
				;
				break;
			case BACKWARD: {
				// TODO: THINK ON HOW TO IMPLEMENT CHANGEOVER STACK IN BACKWARD ALGORITHMS
			}
				;
				break;
			}
			if (constraintSolutionListener != null) {
				RulePlanningEvent<TaskEquipmentInfo> equipmentEvent = new RulePlanningEvent<TaskEquipmentInfo>(this,
						task, activeCombination.getEquipmentAllocationOption().getParent().getConstraint(),
						RuleEventType.CONSTRAINT_SOLVED, task.getEquipment());
				constraintSolutionListener.onConstraintSolutionEvent(equipmentEvent);
				for (MaterialEvaluatedChoice materialOption : activeCombination.getMaterialAllocationOptions()) {
					RulePlanningEvent<List<SupplyReservation>> materialEvent = new RulePlanningEvent<List<SupplyReservation>>(
							this, task, materialOption.getParent().getConstraint(), RuleEventType.CONSTRAINT_SOLVED,
							materialOption.getGeneratedReservations());
					constraintSolutionListener.onConstraintSolutionEvent(materialEvent);
				}
			}
		} else {
			if (constraintSolutionListener != null) {

			}
			LOGGER.warn("Combination between material and equipment not found!");
		}

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End PlannerImpl.doPlanSupervision(..)", "task.id=>" + task.getId());
		return decisionNode;
	}

	protected <PlanSuperType extends Plan> List<PlanSuperType> filterPlansByType(PlanGraphItem decisionNode,
			Class<PlanSuperType> type) {
		List<PlanSuperType> outPlans = new ArrayList<PlanSuperType>();
		for (Plan plan : decisionNode.getPlans()) {
			boolean tempVar = type.isAssignableFrom(plan.getClass());
			PlanSuperType castedPlan = tempVar ? (PlanSuperType) plan : null;
			if (tempVar) {
				outPlans.add(castedPlan);
			}
		}
		return outPlans;
	}

	protected PlanGraphItem initializePlans(Task task, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin DefaultTaskScheduleCoordinator.InitializeConstraintsSolutionPlans(..)",
					"task.id=>" + task.getId());
		PlanGraphItem outNode = new PlanGraphItem();
		outNode.setTask(task);
		if (!task.isLocked()) {

			IRuleBuilder constraintsRuleBuilder = this.componentsFactory.create(IRuleBuilder.class, task,
					task.getParentSchedulingSet());
			List<DateRule> dateRules = CollectionUtil.getInstance().filterByType(task.getConstraintRules(),
					DateRule.class);
			for (DateRule rule : dateRules) {
				IDatePlanSolver businessCompo = this.componentsFactory.create(IDatePlanSolver.class, rule,
						task.getParentSchedulingSet());
				DateChoice solvingPlan = businessCompo.createPlan(rule, rule.getTargetTask().getParentSchedulingSet(),
						direction);
				outNode.getPlans().add(solvingPlan);
			}
			List<MaterialRule> materialConstraintRules = CollectionUtil.getInstance()
					.filterByType(task.getConstraintRules(), MaterialRule.class);
			for (MaterialRule rule : materialConstraintRules) {
				IMaterialPlanSolver businessCompo = this.componentsFactory.create(IMaterialPlanSolver.class, rule,
						task.getParentSchedulingSet());
				MaterialChoice solvingPlan = businessCompo.createPlan(rule,
						rule.getTargetTask().getParentSchedulingSet(), direction);
				outNode.getPlans().add(solvingPlan);
			}
			List<EquipmentRule> equipmentAllocationConstraintRules = CollectionUtil.getInstance()
					.filterByType(task.getConstraintRules(), EquipmentRule.class);
			for (EquipmentRule rule : equipmentAllocationConstraintRules) {
				IEquipmentPlanSolver businessCompo = this.componentsFactory.create(IEquipmentPlanSolver.class, rule,
						task.getParentSchedulingSet());
				EquipmentChoice solvingPlan = businessCompo.createPlan(rule,
						rule.getTargetTask().getParentSchedulingSet(), direction);
				outNode.getPlans().add(solvingPlan);
			}
			List<TasksRelationRule> tasksRelationConstraintRules = CollectionUtil.getInstance()
					.filterByType(task.getConstraintRules(), TasksRelationRule.class);
			for (TasksRelationRule rule : tasksRelationConstraintRules) {
				ITasksRelationsPlanSolver businessCompo = this.componentsFactory.create(ITasksRelationsPlanSolver.class,
						rule, task.getParentSchedulingSet());
				TasksRelationChoice solvingPlan = businessCompo.createPlan(rule,
						rule.getTargetTask().getParentSchedulingSet(), direction);
				outNode.getPlans().add(solvingPlan);
			}

		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End DefaultTaskScheduleCoordinator.InitializeConstraintsSolutionPlans(..)",
					"task.id=>" + task.getId());
		return outNode;
	}

	@Autowired
	IMachineDao machineDao;

	@Override
	public PlanGraphItem doProductionSupervision(Task task, ApsSchedulingSet schedulingSet,
			IRuleSolutionListener constraintSolutionListener, ApsLogicDirection direction) {
		if (!ProductionMonitoringUtil.isUnderProduction(task)) {
			throw new IllegalStateException("The task =>" + task.getCode()
					+ " is not in under production state so it can't be managed by " + this.getClass().getName());
		}
		TaskStatus actualStatus = task.getStatus();
		Date startSetupDateTime = task.getAcquiredStartSetup();
		Date endSetupDateTime = task.getAcquiredEndSetup();
		Date startWorkDateTime = task.getAcquiredStartWork();
		Date endWorkDateTime = task.getAcquiredEndWork();
		double toBeProduced = task.getQtyResidual();
		String usedMachineCode = task.getAcquiredMachineCode();
		if (usedMachineCode == null)
			throw new IllegalStateException("The task =>" + task.getCode()
					+ " is under production state but it does not carry a machineCode!!");
		Machine usedMachine = null;
		try {
			usedMachine = machineDao.findByCode(usedMachineCode, task.getContext());
		} catch (DataModelDaoException e) {
			String msg = "Problem in IMachineDao use for task " + task.getCode();
			LOGGER.error(msg, e);
			throw new OpenI40Exception(msg, e);
		}
		TimeSegmentRequirement setupRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
		setupRequirement.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
		setupRequirement.setStartDateTime(startSetupDateTime);
		setupRequirement.setEndDateTime(endSetupDateTime);
		TimeSegmentRequirement workRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
		workRequirement.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
		workRequirement.setStartDateTime(startWorkDateTime);
		workRequirement.setEndDateTime(endWorkDateTime);

		TaskEquipmentModelInfo taskEquipmentModel = null;

		if (task.getSampledTaskEquipmentInfo() != null) {
			taskEquipmentModel = task.getSampledTaskEquipmentInfo().getMetaInfo();
		} else {
			for (TaskEquipmentModelInfo option : task.getMetaInfo().getEquipmentModelOptions().getEquipmentModels()) {
				List<Machine> machines = option.getPreparationModel().getResource().getGroup().getResources();
				for (Machine machine : machines) {
					if (machine == usedMachine) {
						taskEquipmentModel = option;
					}
				}
			}
		}
		if (taskEquipmentModel == null) {
			throw new OpenI40Exception("No taskEquiment metaInfo in treatment of task=>" + task.getCode());
		}
		IEquipmentConfigurator equipmentConfigurator = this.componentsFactory.create(IEquipmentConfigurator.class,
				task.getMetaInfo().getEquipmentModelOptions(), task.getContext());
		List<TaskEquipmentInfo> potentialEquipments = equipmentConfigurator.calculateConfigurations(taskEquipmentModel,
				usedMachine, schedulingSet.getOptions(), task, task.getContext(), task.getSampledTaskEquipmentInfo(),
				task.getAcquiredSetupUsedResources(), task.getAcquiredWorkUsedResources());
		for (TaskEquipmentInfo taskEquipmentInfo : potentialEquipments) {
			ISetupTimeLogic setupTimeLogic = this.componentsFactory.create(ISetupTimeLogic.class, taskEquipmentInfo,
					schedulingSet.getContext());
			IWorkTimeLogic workTimeLogic = this.componentsFactory.create(IWorkTimeLogic.class, taskEquipmentInfo,
					schedulingSet.getContext());
			switch (actualStatus) {
			case EXECUTING_SETUP: {
				// using startSetupDateTime and calculate setup time
				double setupTime = setupTimeLogic.calculateSetupTime(taskEquipmentInfo, task);
				// first calculate potential period of setupTime
				// use the setupStartDateTime+setupTime as setupRequirement if not in realtime
				// mode
				// use the maximum between setupStartDateTime+setupTime and actualDateTime as
				// maximum setup bound

			}
				break;
			case SETUP_DONE: {
			}
				break;
			case EXECUTING_WORK: {
			}
				break;
			case EXECUTED: {

			}
				break;
			case ABORTED: {
			}
				break;
			case PAUSED: {
			}
				break;
			}
		}
		return null;
	}
}