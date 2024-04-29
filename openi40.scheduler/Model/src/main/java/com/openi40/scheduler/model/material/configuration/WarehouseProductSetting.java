package com.openi40.scheduler.model.material.configuration;

import com.openi40.scheduler.model.aps.ApsData;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public int getAverageleadTimeDays() {
		return averageleadTimeDays;
	}
	public void setAverageleadTimeDays(int averageleadTimeDays) {
		this.averageleadTimeDays = averageleadTimeDays;
	}
	public int getAverageProductionDays() {
		return averageProductionDays;
	}
	public void setAverageProductionDays(int averageProductionDays) {
		this.averageProductionDays = averageProductionDays;
	}
	public double getSecurityStock() {
		return securityStock;
	}
	public void setSecurityStock(double securityStock) {
		this.securityStock = securityStock;
	}
	public boolean isProduceAccordingToReorderLevel() {
		return produceAccordingToReorderLevel;
	}
	public void setProduceAccordingToReorderLevel(boolean produceAccordingToReorderLevel) {
		this.produceAccordingToReorderLevel = produceAccordingToReorderLevel;
	}
	public boolean isPurchaseAccordingToReorderLevel() {
		return purchaseAccordingToReorderLevel;
	}
	public void setPurchaseAccordingToReorderLevel(boolean purchaseAccordingToReorderLevel) {
		this.purchaseAccordingToReorderLevel = purchaseAccordingToReorderLevel;
	}
	public double getAverageMinPurchaseQty() {
		return averageMinPurchaseQty;
	}
	public void setAverageMinPurchaseQty(double averageMinPurchaseQty) {
		this.averageMinPurchaseQty = averageMinPurchaseQty;
	}

}
