package com.openi40.scheduler.engine.rules.material;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.material.IMaterialManager;
import com.openi40.scheduler.engine.material.MaterialManagementSettings;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.planning.material.MaterialChoice;
import com.openi40.scheduler.model.planning.material.MaterialEvaluatedChoice;
import com.openi40.scheduler.model.rules.MaterialRule;
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
@DefaultImplementation(implemented = IMaterialPlanSolver.class, entityClass = MaterialRule.class)
public class MaterialPlanSolverImpl extends BusinessLogic<MaterialRule> implements IMaterialPlanSolver {
	static Logger LOGGER = LoggerFactory.getLogger(MaterialPlanSolverImpl.class);

	public MaterialPlanSolverImpl() {

	}

	@Override
	public MaterialChoice createPlan(MaterialRule rule, ApsSchedulingSet action, ApsLogicDirection direction) {
		MaterialChoice cleanPlan = new MaterialChoice(rule);

		return cleanPlan;
	}

	@Override
	public List<MaterialEvaluatedChoice> generateChoices(MaterialChoice plan, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction) {
		// Recreate a plan
		MaterialRule constraintRule = (MaterialRule) plan.getConstraint();
		Task task = constraintRule.getTargetTask();
		IMaterialManager materialManager = this.componentsFactory.create(IMaterialManager.class, constraintRule, task.getParentSchedulingSet());
		List<MaterialManagementSettings> planout = materialManager.solveMaterialConstraint(constraintRule, SetupTimeRange, WorkTimeRange, task.getContext());
		List<MaterialEvaluatedChoice> outList = new ArrayList<MaterialEvaluatedChoice>();
		for (MaterialManagementSettings mSettings : planout) {
			MaterialEvaluatedChoice option = new MaterialEvaluatedChoice(plan, SetupTimeRange, WorkTimeRange, mSettings.getOperations(), mSettings.getReservations());
			outList.add(option);
		}

		constraintRule.setCurrentlySatisfied(!outList.isEmpty());
		return outList;
	}

	@Override
	public boolean isRuleOk(MaterialRule materialConstraintRule) {
		boolean isOk = false;
		double globalQtyProvided = 0.0;
		for (SupplyReservation reservation : materialConstraintRule.getConstraintGeneratedReservations()) {
			globalQtyProvided += reservation.getQtyProvided();
		}

		if (globalQtyProvided >= materialConstraintRule.getConsumer().getRequiredQty()) {
			isOk = true;
		} else if (materialConstraintRule.getCoveringType() == MaterialRule.CoveringConstraintType.PARTIAL_COVERING_MANDATORY) {
			isOk = globalQtyProvided > 0;
		}

		return isOk;
	}

}