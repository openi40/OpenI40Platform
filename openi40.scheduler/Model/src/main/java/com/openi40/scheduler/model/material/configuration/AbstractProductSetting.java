package com.openi40.scheduler.model.material.configuration;

import com.openi40.scheduler.model.AbstractApsObject;
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

public abstract class AbstractProductSetting extends AbstractApsObject {
	
	public AbstractProductSetting(ApsData context) {
		super(context);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421117442167029109L;
	
	protected boolean produced = false;
	protected boolean purchased = false;
	
	protected boolean productionBySchedulerEnabled = false;
	protected boolean purchaseBySchedulerEnabled = false;
	
	protected String productCode=null;

	public boolean isProduced() {
		return produced;
	}

	public void setProduced(boolean produced) {
		this.produced = produced;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public boolean isProductionBySchedulerEnabled() {
		return productionBySchedulerEnabled;
	}

	public void setProductionBySchedulerEnabled(boolean productionBySchedulerEnabled) {
		this.productionBySchedulerEnabled = productionBySchedulerEnabled;
	}

	public boolean isPurchaseBySchedulerEnabled() {
		return purchaseBySchedulerEnabled;
	}

	public void setPurchaseBySchedulerEnabled(boolean purchaseBySchedulerEnabled) {
		this.purchaseBySchedulerEnabled = purchaseBySchedulerEnabled;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	

}
