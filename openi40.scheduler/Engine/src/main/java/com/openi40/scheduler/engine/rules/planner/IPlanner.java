package com.openi40.scheduler.engine.rules.planner;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.PlanGraphItem;
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
 *
 * Coordinates constraint solutions to make the task execution match an
 * identified time range coherently with scheduling policies (minimum
 * setup/minimum execution time......). Used from the scheduling algorithm to
 * try positioning a task in time.
 */
@BusinessInterface(entityClass = ApsSchedulingSet.class)
public interface IPlanner extends IBusinessLogic<ApsSchedulingSet> {
	/**
	 * Coordinates and chooses constraints solutions hipotesis to match passed time
	 * range and scheduling option policies.
	 * 
	 * @param task
	 * @param action
	 * @param constraintSolutionListener TODO
	 * @param SetupTimeRange
	 * @param WorkTimeRange
	 * @param direction TODO
	 */

	PlanGraphItem doPlanSupervision(Task task, ApsSchedulingSet action, IRuleSolutionListener constraintSolutionListener, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsLogicDirection direction);
}