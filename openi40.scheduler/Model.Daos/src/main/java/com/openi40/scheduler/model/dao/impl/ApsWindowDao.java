package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsWindowDao;
import com.openi40.scheduler.model.dao.ITimesheetMetaInfoDao;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
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
@Service
public class ApsWindowDao extends AbstractApsDataModelDao<ApsWindow> implements IApsWindowDao {

	public ApsWindowDao() {
		super(ApsWindow.class,
				new DataPathCfg(ApsWindow.class).withPath(ApsData.class, "ApsData/SchedulingWindow"));

	}

	@Override
	public void insert(ApsWindow entry, ApsData context) throws DataModelDaoException {
		synchronized(context) {
			context.setSchedulingWindow(entry);
		}
	}

	@Override
	public void delete(ApsWindow entry, ApsData context) throws DataModelDaoException {
		synchronized(context) {
			context.setSchedulingWindow(null);
		}

	}

}