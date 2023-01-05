package com.openi40.scheduler.engine.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
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
 */
@DefaultImplementation(implemented = ISimulatedProductionManager.class, entityClass = MaterialRule.class)
public class SimulatedProductionManagerImpl extends BusinessLogic<MaterialRule> implements ISimulatedProductionManager {
	

	@Override
	public List<SimulatedItemProduced> generateSimulatedProductions(MaterialRule materialConstraint, Task targetTask,
			ApsSchedulingSet parentSchedulingAction, Date fromDateTime, Date toDateTime, ApsData context) {
		return new ArrayList<SimulatedItemProduced>();
	}
}