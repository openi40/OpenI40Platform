package com.openi40.scheduler.model.dao;

import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
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
 */
public interface IWorkOrderDao extends IApsDataModelDao<WorkOrder> {
	List<WorkOrder> findBySalesOrderLineCode(String code, ApsData context) throws DataModelDaoException;
}
