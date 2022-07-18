package com.openi40.scheduler.model.planning.dates;

import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.time.TimeSegment;
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
public class DateEvaluatedChoice extends PlanChoice {
	public DateEvaluatedChoice(DateChoice dateConstraintSatisfactionPlan, TimeSegment setupTimeRange, TimeSegment workTimeRange, List<IOperation> reversibleOperations) {
		super(dateConstraintSatisfactionPlan, setupTimeRange, workTimeRange, reversibleOperations);
	}
}