package com.openi40.scheduler.engine.productionmonitoring;

import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.PlanGraphItem;
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
 *         This interface calculates task status/times using the received message data
 *         reported on the task state to reflect production monitoring data
 */
@BusinessInterface(entityClass = Task.class)
public interface IProductionMonitoringScheduler extends IBusinessLogic<Task> {
	public PlanGraphItem schedule(Task task, ApsSchedulingSet schedulingSet, ApsLogicNotifiedObjects observer);
}
