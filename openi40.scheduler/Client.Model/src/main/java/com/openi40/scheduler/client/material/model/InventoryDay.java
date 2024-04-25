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
package com.openi40.scheduler.client.material.model;

import java.util.Date;

import com.openi40.scheduler.client.model.ClientDto;


public class InventoryDay extends ClientDto {

	public InventoryDay() {

	}

	double inventoryQty = 0.0;
	Date time = null;
	public double getInventoryQty() {
		return inventoryQty;
	}
	public void setInventoryQty(double inventoryQty) {
		this.inventoryQty = inventoryQty;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
