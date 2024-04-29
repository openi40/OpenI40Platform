package com.openi40.scheduler.model.material;

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
public class StockSupply extends AbstractSupply {
	protected double QtyTotal = 0.0;
	private Boolean infiniteCapacity;
	public Boolean getInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(Boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	public double getQtyTotal() {
		return QtyTotal;
	}

	public void setQtyTotal(double qtyTotal) {
		QtyTotal = qtyTotal;
	}

	public StockSupply(ApsData context) {
		super(context);
	}

	

	

}