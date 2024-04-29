package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPurchaseSupplyDao;
import com.openi40.scheduler.model.material.PurchaseSupply;
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
public class PurchaseSupplyDataModelDao extends AbstractSupplyDao<PurchaseSupply> implements IPurchaseSupplyDao {

	public PurchaseSupplyDataModelDao() {
		super(PurchaseSupply.class, new DataPathCfg(PurchaseSupply.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply")
				.withPath(Plant.class, "Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply")
				.withPath(PurchaseOrder.class, "PurchaseOrder/OrderLines/PurchaseOrderLine/Supply")
				.withPath(PurchaseOrderLine.class, "PurchaseOrderLine/Supply"));

	}

	@Override
	public void insert(PurchaseSupply entry, ApsData context) throws DataModelDaoException {
		

	}

	@Override
	public void delete(PurchaseSupply entry, ApsData context) throws DataModelDaoException {
		// TODO Auto-generated method stub
		
	}

	

}