package com.openi40.scheduler.engine.productionmonitoring;

import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.tasks.Task;

@DefaultImplementation(implemented = IProductionMonitoringScheduler.class, entityClass = Task.class)
public class ProductionMonitoringSchedulerImpl extends BusinessLogic<Task> implements IProductionMonitoringScheduler {

	public ProductionMonitoringSchedulerImpl() {

	}

	@Override
	public PlanGraphItem schedule(Task task, ApsSchedulingSet schedulingSet, ApsLogicNotifiedObjects observer) {

		return null;
	}

}
