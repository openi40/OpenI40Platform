package com.openi40.scheduler.engine.material;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.material.SimulatedItemProduced;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.tasks.Task;

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
 * This component manages simulated production orders
 */
@BusinessInterface(entityClass = MaterialRule.class)
public interface ISimulatedProductionManager extends IBusinessLogic<MaterialRule> {
	/**
	 * This method tries to retrieve and modify existing simulated productions to
	 * satisfy the passed materialConstraint, if necessary, creates a new one. Adds
	 * simuated productions to existing supplies in the context passed to be
	 * coherent with supply lifecycle.
	 * 
	 * @param materialConstraint
	 * @param targetTask
	 * @param parentSchedulingAction
	 * @param context
	 * @return
	 */
	List<SimulatedItemProduced> generateSimulatedProductions(MaterialRule materialConstraint, Task targetTask, ApsSchedulingSet parentSchedulingAction, ApsData context);
}