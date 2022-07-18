package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsSchedulingSetDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
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
@Service
public class ApsSchedulingSetDataModelDao extends AbstractApsDataModelDao<ApsSchedulingSet>
		implements IApsSchedulingSetDao {

	public ApsSchedulingSetDataModelDao() {
		super(ApsSchedulingSet.class,
				new DataPathCfg(ApsSchedulingSet.class).withPath(ApsData.class, "ApsData/SchedulingSets"));

	}

	@Override
	public void insert(ApsSchedulingSet entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getSchedulingSets().add(entry);
		}
	}

	@Override
	public void delete(ApsSchedulingSet entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getSchedulingSets().remove(entry);
		}

	}

}