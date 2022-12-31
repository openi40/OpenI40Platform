package com.openi40.scheduler.engine.material;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderLineDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;

@Service
public class PurchaseOrderSimulationBuilderImpl implements IPurchaseOrderSimulationBuilder {
	static Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderSimulationBuilderImpl.class);
	@Autowired
	IPurchaseOrderDao purchaseOrderDao;
	@Autowired
	IPurchaseOrderLineDao purchaseOrderLineDao;
	@Autowired
	IWarehouseDao warehouseDao;
	@Autowired
	IPlantDao plantDao;
	private String newPurchaseOrderCode() {
		return UUID.randomUUID().toString();
	}
	private String newPurchaseOrderLineCode() {
		return UUID.randomUUID().toString();
	}
	@Override
	public PurchaseOrderLine integrateSimulatedPurchases(SimulatedPurchaseSupply supply, ApsData context) {
		PurchaseOrderLine line=null;
		try {
			Warehouse warehouse = warehouseDao.findByCode(supply.getWarehouseCode(), context);
			String plantCode = warehouse.getPlantCode();
			Plant plant = plantDao.findByCode(plantCode, context);
			PurchaseOrder purchaseOrder=new PurchaseOrder(context);
			purchaseOrder.setCode(newPurchaseOrderCode());
			purchaseOrder.setCompanyCode(plant.getProductiveCompanyCode());
			purchaseOrder.setPlannedDeliveryDate(supply.getAvailabilityDateTime());
			purchaseOrder.setAskedDeliveryDate(supply.getAvailabilityDateTime());
			purchaseOrder.setPlantCode(plantCode);
			line=new PurchaseOrderLine(context);
			line.setCode(newPurchaseOrderLineCode());
			line.setAskedDeliveryDate(supply.getAvailabilityDateTime());
			line.setPlannedDeliveryDate(supply.getAvailabilityDateTime());
			line.setCompanyCode(plant.getProductiveCompanyCode());
			line.setPlantCode(plantCode);
			line.setProduct(supply.getSuppliedItem());
			line.setTotalQty(supply.getQtyTotal());
			line.setSupply(supply);
			line.setOrderCode(purchaseOrder.getCode());
			purchaseOrderDao.insert(purchaseOrder, context);
		} catch (DataModelDaoException e) {
			String msg = "Data model manipulation problem";
			LOGGER.error(msg, e);
			throw new OpenI40Exception(msg, e);
		}
		return line;
	}

}
