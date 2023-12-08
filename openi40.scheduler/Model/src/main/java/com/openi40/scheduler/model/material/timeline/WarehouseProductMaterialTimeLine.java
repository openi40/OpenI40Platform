package com.openi40.scheduler.model.material.timeline;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.material.configuration.ProductiveCompanyProductSetting;
import com.openi40.scheduler.model.material.configuration.WarehouseProductSetting;
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

public class WarehouseProductMaterialTimeLine extends AbstractMaterialTimeLine {
	protected String productCode = null;
	protected String warehouseCode = null;
	protected WarehouseProductSetting setting = null;
	protected PlantProductSetting plantSetting = null;
	protected ProductiveCompanyProductSetting companySetting = null;

	public WarehouseProductMaterialTimeLine(ApsData context, StockSupply initialStockPosition) {
		super(context, initialStockPosition);

	}

	public String toString() {
		return super.toString();
	}

	@Override
	public void debugLogging() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("warehouse:" + warehouseCode + " product:" + productCode);
			super.debugLogging();
		}
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public WarehouseProductSetting getSetting() {
		return setting;
	}

	public void setSetting(WarehouseProductSetting setting) {
		this.setting = setting;
	}

	public PlantProductSetting getPlantSetting() {
		return plantSetting;
	}

	public void setPlantSetting(PlantProductSetting plantSetting) {
		this.plantSetting = plantSetting;
	}

	public ProductiveCompanyProductSetting getCompanySetting() {
		return companySetting;
	}

	public void setCompanySetting(ProductiveCompanyProductSetting companySetting) {
		this.companySetting = companySetting;
	}
}
