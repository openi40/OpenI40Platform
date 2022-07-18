package com.openi40.scheduler.model.cycle;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.Product;
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
public class BomItemModel extends AbstractApsObject implements IMetaInfo {
	public BomItemModel(ApsData context) {
		super(context);

	}
	private BatchingInfo consumingBatchInfo = new BatchingInfo();
	private Product ConsumedItem = null;
	private String warehouseCode=null;
	public  Product getConsumedItem() {
		return ConsumedItem;
	}

	public  void setConsumedItem(Product value) {
		ConsumedItem = value;
	}

	private double Qty = 0.0;

	public  double getQty() {
		return Qty;
	}

	public  void setQty(double value) {
		Qty = value;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public BatchingInfo getConsumingBatchInfo() {
		return consumingBatchInfo;
	}

	public void setConsumingBatchInfo(BatchingInfo consumingBatchInfo) {
		this.consumingBatchInfo = consumingBatchInfo;
	}

}