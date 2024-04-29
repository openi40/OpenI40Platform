package com.openi40.scheduler.engine.rules;

import java.util.EventObject;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
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
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public ContextDataType getContextData() {
		return contextData;
	}
	public void setContextData(ContextDataType contextData) {
		this.contextData = contextData;
	}
	public RuleEventType getEventType() {
		return eventType;
	}
	public void setEventType(RuleEventType eventType) {
		this.eventType = eventType;
	}

}
