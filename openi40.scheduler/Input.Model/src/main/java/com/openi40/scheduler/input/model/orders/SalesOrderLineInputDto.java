package com.openi40.scheduler.input.model.orders;

import javax.persistence.MappedSuperclass;
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

@MappedSuperclass
public class SalesOrderLineInputDto extends AbstractOrderLine {
	private boolean explodeWorkOrders=false; 
	private String explodeWithCycleCode=null;
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
}