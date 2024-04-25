package com.openi40.scheduler.engine.aps;

import com.openi40.commons.multithreading.config.OpenI40MultithreadingConfig;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
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
@DefaultImplementation(implemented = IApsLogic.class, entityClass = ApsSchedulingSet.class)
public class DefaultApsLogicImpl extends BackwardApsLogicImpl {

	public DefaultApsLogicImpl(OpenI40MultithreadingConfig multithreadingConfig, AsyncDelegateService delegateService) {
		super(multithreadingConfig, delegateService);
		
	}

}
