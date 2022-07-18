package com.openi40.scheduler.model.material;

import com.openi40.scheduler.model.aps.ApsData;
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
public class SimulatedItemProduced extends ProductionSupply {
	public SimulatedItemProduced(ApsData context, Task task) {
		super(context, task);
		simulatedItem = true;
	}
}