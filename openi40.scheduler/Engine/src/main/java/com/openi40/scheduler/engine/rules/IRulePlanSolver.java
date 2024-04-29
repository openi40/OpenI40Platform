package com.openi40.scheduler.engine.rules;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.Plan;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
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
public interface IRulePlanSolver<ConstraintType extends Rule, ConstraintPlanType extends Plan, ConstraintPlanOptionType extends PlanChoice> extends IBusinessLogic<ConstraintType> {

	/**
	 * Creates and apply a plan to solve a constraint requirement
	 * 
	 * @param rule
	 * @param action
	 * @param direction TODO
	 * @param allRules
	 * @return
	 */
	ConstraintPlanType createPlan(ConstraintType rule, ApsSchedulingSet action, ApsLogicDirection direction);

	/**
	 * Creates solving plan options without creating resource allocations to be
	 * elaborated from TaskScheduleCoordinator
	 * 
	 * @param plan
	 * @param direction TODO
	 * @param Event TimeRangeRequirement SetupTimeRange = new
	 *              TimeRangeRequirement(TimeRangeType.SETUP_TIME);
	 */

	List<ConstraintPlanOptionType> generateChoices(ConstraintPlanType plan, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction);

	/**
	 * Returns true if the Constraint rule is satisfied in the actual scenary
	 * 
	 * @param EntityObject
	 * @return
	 */
	boolean isRuleOk(ConstraintType EntityObject);

}