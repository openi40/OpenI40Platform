package com.openi40.scheduler.engine.apsdata;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@BusinessInterface(entityClass = ApsData.class)
public interface IApsDataManager extends IBusinessLogic<ApsData> {
	/**
	 * Initializes data structures of ApsData
	 * 
	 * @param context
	 */
	void initialize(ApsData context);
	void beforeScheduling(ApsData context);
}