package com.openi40.scheduler.model.companystructure;

import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.configuration.ProductiveCompanyProductSetting;
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

public class ProductiveCompany extends Company implements ICompanyStructureNode {
	private static final String PLANTS = "Plants";
	private static final String PRODUCT_SETTINGS = "ProductSettings";

	public ProductiveCompany(ApsData c) {
		super(c);
	}

	protected List<Plant> plants = createCleanChild(this, PLANTS, Plant.class);
	protected List<ProductiveCompanyProductSetting> productSettings = createCleanChild(this, PRODUCT_SETTINGS,
			ProductiveCompanyProductSetting.class);

	@Override
	public ICompanyStructureNode getParent() {

		return null;
	}

	@Override
	public void resetSchedulingData() {
		for (Plant plant : plants) {
			plant.resetSchedulingData();
		}
	}

	public List<Plant> getPlants() {
		return plants;
	}

	public List<ProductiveCompanyProductSetting> getProductSettings() {
		return productSettings;
	}

}