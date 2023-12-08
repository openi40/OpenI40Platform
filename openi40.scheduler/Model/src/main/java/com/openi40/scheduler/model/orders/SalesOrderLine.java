package com.openi40.scheduler.model.orders;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.Demand;
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

public class SalesOrderLine extends AbstractOrderLine {
	public SalesOrderLine(ApsData context) {
		super(context);
	}
	private boolean explodeWorkOrders=false; 
	private String explodeWithCycleCode=null;
	protected Demand demand = null;
	public boolean isExplodeWorkOrders() {
		return explodeWorkOrders;
	}
	public void setExplodeWorkOrders(boolean explodeWorkOrders) {
		this.explodeWorkOrders = explodeWorkOrders;
	}
	public String getExplodeWithCycleCode() {
		return explodeWithCycleCode;
	}
	public void setExplodeWithCycleCode(String explodeWithCycleCode) {
		this.explodeWithCycleCode = explodeWithCycleCode;
	}
	public Demand getDemand() {
		return demand;
	}
	public void setDemand(Demand demand) {
		this.demand = demand;
	}
}