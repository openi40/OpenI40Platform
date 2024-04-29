package com.openi40.scheduler.model.dao.impl;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.orders.AbstractOrder;
import com.openi40.scheduler.model.orders.AbstractOrderLine;
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
public abstract class AbstractOrderDao<AbstractOrderType extends AbstractOrder<AbstractOrderLineType>,AbstractOrderLineType extends AbstractOrderLine> extends AbstractApsDataModelDao<AbstractOrderType>{

	protected AbstractOrderDao(Class<AbstractOrderType> searchedType, DataPathCfg dataPathConfig) {
		super(searchedType, dataPathConfig);
		
	}

	

}
