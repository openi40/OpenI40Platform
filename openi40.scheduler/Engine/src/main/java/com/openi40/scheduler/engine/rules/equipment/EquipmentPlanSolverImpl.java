package com.openi40.scheduler.engine.rules.equipment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.equipment.allocation.EquipmentAllocation;
import com.openi40.scheduler.engine.equipment.allocation.IEquipmentAllocator;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.equipment.EquipmentEvaluatedChoice;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
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
@DefaultImplementation(implemented = IEquipmentPlanSolver.class, entityClass = EquipmentRule.class)
public class EquipmentPlanSolverImpl extends BusinessLogic<EquipmentRule> implements IEquipmentPlanSolver {
	static Logger LOGGER = LoggerFactory.getLogger(EquipmentPlanSolverImpl.class);

	public EquipmentPlanSolverImpl() {

	}

	@Override
	public EquipmentChoice createPlan(EquipmentRule rule, ApsSchedulingSet action, ApsLogicDirection direction) {
		EquipmentChoice satisfactionPlan = new EquipmentChoice(rule);

		return satisfactionPlan;

	}

	@Override
	public List<EquipmentEvaluatedChoice> generateChoices(EquipmentChoice plan, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Begin EquipmentAllocationConstraintHandler.ElaborateSolvingPlanOptions(..)", "evaluatin plan for period SetupTimeRange=" + SetupTimeRange + " WorkTimeRange=" + WorkTimeRange);
		// Use the IEquipmentAllocator to plan all feaseable constraint satisfaction
		// options in the passed time ranges
		List<EquipmentEvaluatedChoice> equipmentOptions = new ArrayList<EquipmentEvaluatedChoice>();
		EquipmentRule constraintRule = (EquipmentRule) plan.getConstraint();
		Task task = constraintRule.getTargetTask();
		IEquipmentAllocator equipmentAllocator = this.componentsFactory.create(IEquipmentAllocator.class, constraintRule, constraintRule.getTargetTask().getParentSchedulingSet());
		List<EquipmentAllocation> equipmentAllocation = equipmentAllocator.calculateAllocations(constraintRule, SetupTimeRange, WorkTimeRange, constraintRule.getTargetTask().getParentSchedulingSet().getOptions(), constraintRule.getTargetTask(), constraintRule.getContext());
		for (EquipmentAllocation allocation : equipmentAllocation) {
			// TODO: MANAGE PARALLEL EQUIPMENT, NOW IMPLEMENT JUST ONE ENTRY
			TaskEquipmentInfo equipmentEntry = allocation.getChoosedTaskEquipment();
			TimeSegmentRequirement setupRequirement = new TimeSegmentRequirement(equipmentEntry.getPreparation().getEquipmentEventsGroup());
			TimeSegmentRequirement workRequirement = new TimeSegmentRequirement(equipmentEntry.getExecution().getEquipmentEventsGroup());
			double setupTime = setupRequirement.getDurationMinutes();
			double workTime = workRequirement.getDurationMinutes();
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Evaluated setupRequirement minutes => " + setupTime + " workRequirement in minutes => " + workTime, "");
			EquipmentEvaluatedChoice option = new EquipmentEvaluatedChoice(plan, setupRequirement, workRequirement, allocation.getChoosedTaskEquipment(),
					allocation.getOperations());
			equipmentOptions.add(option);
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("End EquipmentAllocationConstraintHandler.ElaborateSolvingPlanOptions(..)", "equipmentOptions.Count=" + equipmentOptions.size());
		constraintRule.setCurrentlySatisfied(!equipmentOptions.isEmpty());
		return equipmentOptions;

	}

	@Override
	public boolean isRuleOk(EquipmentRule constraint) {
		return !constraint.getTaskEquipmentInfos().isEmpty() && constraint.getTargetTask().getEquipment() != null;
	}

}