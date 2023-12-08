package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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

public class ItemProducedMetaInfo extends AbstractApsObject implements IMetaInfo {
	public ItemProducedMetaInfo(ApsData context) {
		super(context);
	}

	public ItemProducedMetaInfo(ApsData context, Product supplied, double qty) {
		super(context);
		this.setSuppliedItem(supplied);
		this.setQty(qty);
	}

	public ItemProducedMetaInfo(ApsData context, ItemProducedMetaInfo itemProducedModel) {
		super(context);
		this.suppliedItem = itemProducedModel.getSuppliedItem();
		this.qty = itemProducedModel.getQty();
		this.warehouseCode = itemProducedModel.getWarehouseCode();
	}

	private Product suppliedItem = null;
	private String warehouseCode = null;
	private double qty;
	public Product getSuppliedItem() {
		return suppliedItem;
	}

	public void setSuppliedItem(Product suppliedItem) {
		this.suppliedItem = suppliedItem;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

}