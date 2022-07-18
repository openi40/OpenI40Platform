package com.openi40.scheduler.model.material.configuration;

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
public class WarehouseProductSetting extends AbstractProductSetting {
	public WarehouseProductSetting(ApsData context) {
		super(context);
		
	}
	protected String warehouseCode=null;
	protected int averageleadTimeDays = 0;
	protected int averageProductionDays=0;
	protected double securityStock = 0.0;
	protected boolean produceAccordingToReorderLevel=false;
	protected boolean purchaseAccordingToReorderLevel=false;
	protected double averageMinPurchaseQty = 0.0;

}
