package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderDao;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
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
public class PurchaseOrderDataModelDao extends AbstractOrderDao<PurchaseOrder,PurchaseOrderLine> implements IPurchaseOrderDao {

	public PurchaseOrderDataModelDao() {
		super(PurchaseOrder.class, new DataPathCfg(PurchaseOrder.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/PurchaseOrders")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/PurchaseOrders")
				.withPath(Plant.class, "Plant/PurchaseOrders"));

	}

	@Autowired
	IPlantDao plantDao;

	@Override
	public void insert(PurchaseOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getPurchaseOrders().add(entry);
		}

	}

	@Override
	public void delete(PurchaseOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getPurchaseOrders().remove(entry);
		}
		
	}

}
