package com.openi40.scheduler.model.rules;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.tasks.Task;

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
public abstract class Rule extends AbstractApsObject {
	public enum ConstraintOrigin {
		DATALOAD, SCHEDULING;

	}

	public enum ConstraintPriority {
		WARNS, // Nice to have but something not met the rule is still schedulable
		MANDATORY, // Must be met otherwise not schedulable
	}

	private boolean currentlySatisfied = false;
	private ConstraintOrigin Origin = ConstraintOrigin.SCHEDULING;
	private ConstraintPriority Priority = ConstraintPriority.MANDATORY;
	private Task TargetTask = null;
	private ApsMessage unmetConstraintMessage = null;

	public Rule(ApsData context) {
		super(context);
	}

	public Rule(Task target, ConstraintOrigin origin, ConstraintPriority priority) {
		super(target.getContext());
		setOrigin(origin);
		setPriority(priority);
		setTargetTask(target);
	}

}