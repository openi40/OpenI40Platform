package com.openi40.scheduler.engine.aps;

import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.IRuleStateListener;
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
public class ApsLogicNotifiedObjects {
	protected IApsLogicObserver observer = null;
	protected IRuleSolutionListener constraintSolutionListener=null;
	public IApsLogicObserver getObserver() {
		return observer;
	}

	public void setObserver(IApsLogicObserver observer) {
		this.observer = observer;
	}

	public IRuleStateListener getConstraintRuleListener() {
		return constraintRuleListener;
	}

	public void setConstraintRuleListener(IRuleStateListener constraintRuleListener) {
		this.constraintRuleListener = constraintRuleListener;
	}

	protected IRuleStateListener constraintRuleListener = null;

	public ApsLogicNotifiedObjects() {

	}

	public IRuleSolutionListener getConstraintSolutionListener() {
		return constraintSolutionListener;
	}

	public void setConstraintSolutionListener(IRuleSolutionListener constraintSolutionListener) {
		this.constraintSolutionListener = constraintSolutionListener;
	}

}
