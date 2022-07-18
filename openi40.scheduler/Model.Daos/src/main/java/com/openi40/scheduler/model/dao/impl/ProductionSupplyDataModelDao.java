package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class ProductionSupplyDataModelDao extends AbstractSupplyDao<ProductionSupply> implements IProductionSupplyDao {

	public ProductionSupplyDataModelDao() {
		super(ProductionSupply.class, new DataPathCfg(ProductionSupply.class).withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ProductionSupply")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ProductionSupply").withPath(Plant.class, "Plant/WorkOrders/WorkOrder/ProductionSupply").withPath(WorkOrder.class, "WorkOrder/ProductionSupply"));

	}

	@Override
	public void insert(ProductionSupply entry, ApsData context) throws DataModelDaoException {
		
		
	}

	@Override
	public void delete(ProductionSupply entry, ApsData context) throws DataModelDaoException {
		// TODO Auto-generated method stub
		
	}

}