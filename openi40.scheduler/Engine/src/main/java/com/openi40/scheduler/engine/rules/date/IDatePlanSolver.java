package com.openi40.scheduler.engine.rules.date;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.rules.IRulePlanSolver;
import com.openi40.scheduler.model.planning.dates.DateChoice;
import com.openi40.scheduler.model.planning.dates.DateEvaluatedChoice;
import com.openi40.scheduler.model.rules.DateRule;
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
@BusinessInterface(entityClass = DateRule.class)
public interface IDatePlanSolver extends IRulePlanSolver<DateRule, DateChoice, DateEvaluatedChoice> {

}
