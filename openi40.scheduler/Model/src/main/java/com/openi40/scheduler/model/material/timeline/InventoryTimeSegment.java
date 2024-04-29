package com.openi40.scheduler.model.material.timeline;

import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;
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

public class InventoryTimeSegment extends TimeSegment {
	
	protected double inventoryQty = 0.0;
	
	protected double reservationsBalance = 0.0;

	public InventoryTimeSegment() {
		super(TimeSegmentType.CALENDAR_TIME, null);

	}

	InventoryTimeNode startNode = null, endNode = null;

	public String toString() {
		return super.toString();
	}

	public double getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(double inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	public double getReservationsBalance() {
		return reservationsBalance;
	}

	public void setReservationsBalance(double reservationsBalance) {
		this.reservationsBalance = reservationsBalance;
	}

	public InventoryTimeNode getStartNode() {
		return startNode;
	}

	public void setStartNode(InventoryTimeNode startNode) {
		this.startNode = startNode;
	}

	public InventoryTimeNode getEndNode() {
		return endNode;
	}

	public void setEndNode(InventoryTimeNode endNode) {
		this.endNode = endNode;
	}
}
