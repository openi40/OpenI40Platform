package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsReservableObjectDao;
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
public class ApsReservableObjectDao extends AbstractApsDataModelDao<ITimesheetAllocableObject> implements IApsReservableObjectDao {

	public ApsReservableObjectDao() {
		super(ITimesheetAllocableObject.class,
				new DataPathCfg(ITimesheetAllocableObject.class).withPath(ApsData.class, "ApsData/ProductiveCompanies").withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants").withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Warehouses")
						.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters").withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters/Workcenter/Resources")
						.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/SecondaryResourceGroups/SecondaryResource"));

	}

	@Override
	public void insert(ITimesheetAllocableObject entry, ApsData context) throws DataModelDaoException {
		
		
	}

	@Override
	public void delete(ITimesheetAllocableObject entry, ApsData context) throws DataModelDaoException {
		
		
	}

}