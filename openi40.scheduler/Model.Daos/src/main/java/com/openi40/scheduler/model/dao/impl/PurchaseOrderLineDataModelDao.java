package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPurchaseOrderDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderLineDao;
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
public class PurchaseOrderLineDataModelDao extends AbstractOrderLineDao<PurchaseOrderLine>
		implements IPurchaseOrderLineDao {
	@Autowired
	public PurchaseOrderLineDataModelDao() {
		super(PurchaseOrderLine.class, new DataPathCfg(PurchaseOrderLine.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines")
				.withPath(Plant.class, "Plant/PurchaseOrders/PurchaseOrder/OrderLines"));

	}

	@Autowired
	IPurchaseOrderDao purchaseOrderDao;

	@Override
	public void insert(PurchaseOrderLine entry, ApsData context) throws DataModelDaoException {
		String orderCode = entry.getOrderCode();
		if (orderCode == null || orderCode.trim().length() == 0)
			throw new DataModelDaoException(
					"cannot insert purchase order line =>" + entry.getCode() + " with null orderCode");
		PurchaseOrder po = purchaseOrderDao.findByCode(orderCode, context);
		if (po == null)
			throw new DataModelDaoException("cannot insert purchase order line =>" + entry.getCode()
					+ " with orderCode=> " + orderCode + " this order cannot be found!");
		synchronized (context) {
			if (entry.getSupply() == null) {
				entry.setSupply(new PurchaseSupply(context));

			}
			po.getOrderLines().add(entry);
			entry.getSupply().setCode(po.getCode());
			entry.getSupply()
					.setAvailabilityDateTime(entry.getPlannedDeliveryDate() != null ? entry.getAskedDeliveryDate()
							: entry.getPlannedDeliveryDate());
			entry.getSupply().setQtyTotal(entry.getResidualQty());

		}
	}

	@Override
	public void delete(PurchaseOrderLine entry, ApsData context) throws DataModelDaoException {
		String orderCode = entry.getOrderCode();
		if (orderCode == null || orderCode.trim().length() == 0)
			throw new DataModelDaoException(
					"cannot insert purchase order line =>" + entry.getCode() + " with null orderCode");
		PurchaseOrder po = purchaseOrderDao.findByCode(orderCode, context);
		if (po == null)
			throw new DataModelDaoException("cannot insert purchase order line =>" + entry.getCode()
					+ " with orderCode=> " + orderCode + " this order cannot be found!");
		synchronized (context) {
			po.getOrderLines().remove(entry);			

		}
		
	}

}
