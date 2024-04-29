package com.openi40.scheduler.engine.aps;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;

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
 * This component is the main scheduling algorithms launcher
 */
@BusinessInterface(entityClass = ApsData.class)
public interface IApsLogicComposer extends IBusinessLogic<ApsData> {

	/**
	 * Uses the scheduling algorithms to recalculate the output informations of the
	 * SchedulingContext passed as parameter
	 * 
	 * @param observer     TODO
	 * @param scheduleData
	 */
	void schedule(ApsData EntityObject, ApsLogicNotifiedObjects observer);

	List<ApsSchedulingSet> autoSetTasks(ApsData apsData, List<ApsSchedulingSet> schedulingSets);

}