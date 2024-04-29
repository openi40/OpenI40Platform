package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISalesOrderLineDao;
import com.openi40.scheduler.model.orders.SalesOrderLine;
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
public class SalesOrderLineDataModelDao extends AbstractOrderLineDao<SalesOrderLine> implements ISalesOrderLineDao {

	public SalesOrderLineDataModelDao() {
		super(SalesOrderLine.class, new DataPathCfg(SalesOrderLine.class)
				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/SalesOrders/SalesOrder/OrderLines")
				.withPath(Plant.class, "Plant/SalesOrders/SalesOrder/OrderLines")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/SalesOrders/SalesOrder/OrderLines"));
	}

	@Override
	public void insert(SalesOrderLine entry, ApsData context) throws DataModelDaoException {

	}

	@Override
	public void delete(SalesOrderLine entry, ApsData context) throws DataModelDaoException {
		// TODO Auto-generated method stub
		
	}

}
