package com.openi40.scheduler.engine.aps;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.orders.WorkOrder;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *
 * This interface declares the signatures to be implemented from all the
 * scheduling algorithms implemented to be used from the ISchedulingEngine to
 * compose a complex scheduling scenario
 */
@BusinessInterface(entityClass = ApsSchedulingSet.class)
public interface IApsLogic extends IBusinessLogic<ApsSchedulingSet> {
	/**
	 * This method schedules the work orders listed in the schedulingAction passed
	 * as parameter with the custom parameters listed in the same entity
	 * 
	 * @param apsAction
	 * @param observer  TODO
	 */
	void schedule(ApsSchedulingSet apsAction, ApsLogicNotifiedObjects observer);

	/**
	 * Creates default scheduling options for the implemented algorithm
	 * 
	 * @param apsAction
	 */
	ApsLogicOptions createDefaultOptions(ApsSchedulingSet apsAction);

	ApsLogicDirection getDirection();

	ApsSchedulingSet autoSetTasks(ApsSchedulingSet apsSet, List<WorkOrder> unscheduled, ApsData apsData);
}