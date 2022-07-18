package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;
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
@Data
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

}