package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.ISalesOrderDao;
import com.openi40.scheduler.model.orders.SalesOrder;
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
public class SalesOrderDataModelDao extends AbstractOrderDao<SalesOrder,SalesOrderLine> implements ISalesOrderDao {

	public SalesOrderDataModelDao() {
		super(SalesOrder.class, new DataPathCfg(SalesOrder.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/SalesOrders")
				.withPath(Plant.class, "Plant/SalesOrders")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/SalesOrders"));
	}
	@Autowired
	IPlantDao plantDao;
	@Override
	public void insert(SalesOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getSalesOrders().add(entry);
		}
	}

	@Override
	public void delete(SalesOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getSalesOrders().remove(entry);
		}
		
	}

}
