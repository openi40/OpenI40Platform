package com.openi40.scheduler.model.orders;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.PurchaseSupply;
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

public class PurchaseOrderLine extends AbstractOrderLine {
	public PurchaseOrderLine(ApsData context) {
		super(context);
	}

	protected PurchaseSupply supply = null;

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		if (supply != null)
			supply.resetSchedulingData();
	}

	public PurchaseSupply getSupply() {
		return supply;
	}

	public void setSupply(PurchaseSupply supply) {
		this.supply = supply;
	}

}