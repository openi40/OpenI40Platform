package com.openi40.scheduler.engine.material;

import java.util.Date;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.tasks.Task;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 *
 *         This componente is able to create needed purchase orders to solve a
 *         material constraint
 */
@BusinessInterface(entityClass = MaterialRule.class)
public interface ISimulatedPurchaseOrderManager extends IBusinessLogic<MaterialRule> {
	final static String SIMULATED_SUPPLY_ORDER_CODE = "[SIMULATED-SUPPLY]-[000000]";

	/**
	 * This method tries to retrieve and modify existing simulated purchases to
	 * satisfy the passed materialConstraint, if necessary, creates a new one. Adds
	 * simuated purchases to existing supplies in the context passed to be coherent
	 * with supply lifecycle.
	 * 
	 * @param materialConstraint
	 * @param targetTask
	 * @param parentSchedulingAction
	 * @param toDateTime 
	 * @param fromDateTime 
	 * @param context
	 * @return
	 */
	List<SimulatedPurchaseSupply> generateSimulatedPurchases(MaterialRule materialConstraint, Task targetTask,
			ApsSchedulingSet parentSchedulingAction, Date fromDateTime, Date toDateTime, ApsData context);
	
}