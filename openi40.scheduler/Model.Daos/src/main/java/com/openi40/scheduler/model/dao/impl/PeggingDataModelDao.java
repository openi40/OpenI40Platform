package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPeggingDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
import com.openi40.scheduler.model.orders.Pegging;
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
@Service
public class PeggingDataModelDao extends AbstractApsDataModelDao<Pegging> implements IPeggingDao {

	public PeggingDataModelDao() {
		super(Pegging.class, new DataPathCfg(Pegging.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/Peggings")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/Peggings")
				.withPath(Plant.class, "Plant/WorkOrders/WorkOrder/Peggings"));

	}

	
	@Override
	public void insert(Pegging entry, ApsData context) throws DataModelDaoException {
		
	}

	@Override
	public void delete(Pegging entry, ApsData context) throws DataModelDaoException {
		
		
	}
}