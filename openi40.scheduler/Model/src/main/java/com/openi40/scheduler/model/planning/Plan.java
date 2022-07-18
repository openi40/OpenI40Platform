package com.openi40.scheduler.model.planning;

import com.openi40.scheduler.model.rules.Rule;

import lombok.Data;
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
@Data
public abstract class Plan<ConstraintRuleType extends Rule> {
	public Plan(ConstraintRuleType constraint) {
		this.constraint = constraint;

	}
	private ConstraintRuleType constraint;
	private boolean satisfactory;
	private PlanChoice choosed = null;

}