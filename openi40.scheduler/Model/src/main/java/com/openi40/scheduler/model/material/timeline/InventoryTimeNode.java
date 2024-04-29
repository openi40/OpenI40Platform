package com.openi40.scheduler.model.material.timeline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class InventoryTimeNode {
	Date eventsTime = null;
	List<AbstractMaterialMovement<?>> movements = new ArrayList<>();
	double inventoryQty = 0;
	double reservationBalance=0;
	InventoryTimeSegment endingSegment = null;
	InventoryTimeSegment startingSegment = null;

	public String toString() {
		return super.toString();
	}

	public Date getEventsTime() {
		return eventsTime;
	}

	public void setEventsTime(Date eventsTime) {
		this.eventsTime = eventsTime;
	}

	public List<AbstractMaterialMovement<?>> getMovements() {
		return movements;
	}

	public void setMovements(List<AbstractMaterialMovement<?>> movements) {
		this.movements = movements;
	}

	public double getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(double inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	public double getReservationBalance() {
		return reservationBalance;
	}

	public void setReservationBalance(double reservationBalance) {
		this.reservationBalance = reservationBalance;
	}

	public InventoryTimeSegment getEndingSegment() {
		return endingSegment;
	}

	public void setEndingSegment(InventoryTimeSegment endingSegment) {
		this.endingSegment = endingSegment;
	}

	public InventoryTimeSegment getStartingSegment() {
		return startingSegment;
	}

	public void setStartingSegment(InventoryTimeSegment startingSegment) {
		this.startingSegment = startingSegment;
	}
}
