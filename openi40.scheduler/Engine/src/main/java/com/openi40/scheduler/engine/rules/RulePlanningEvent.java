package com.openi40.scheduler.engine.rules;

import java.util.EventObject;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.rules.Rule;
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
public class RulePlanningEvent<ContextDataType> extends EventObject {
	Rule rule = null;
	Task task = null;
	ContextDataType contextData = null;
	RuleEventType eventType=null;
	public enum RuleEventType {
		CONSTRAINT_UNSOLVABLE, CONSTRAINT_SOLVED
	}
	public RulePlanningEvent(IBusinessLogic source, Task task, Rule rule, RuleEventType eventType, ContextDataType contextData) {
		super(source);
		this.rule = rule;
		this.task = task;
		this.contextData = contextData;
		this.eventType=eventType;
	}

}
