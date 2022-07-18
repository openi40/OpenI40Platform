package com.openi40.scheduler.model.material.timeline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;
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
@Data
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
}
