package com.openi40.scheduler.model;

import com.openi40.scheduler.common.aps.IEnvironment;
import com.openi40.scheduler.model.aps.ApsData;
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
public class AbstractSchedulingEnvironmentNode extends AbstractApsObject implements IEnvironment {
	public AbstractSchedulingEnvironmentNode(ApsData context) {
		super(context);
	}

	protected IEnvironment parentNode;

	public final IEnvironment getParentNode() {
		return parentNode;
	}

	public final void setParentNode(IEnvironment value) {
		parentNode = value;
	}

	protected void finalize() throws Throwable {
		parentNode = null;
	}
}