package com.openi40.scheduler.model.material.timeline;

import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
public class InventoryTimeSegment extends TimeSegment {
	@Setter(value = AccessLevel.PACKAGE)
	protected double inventoryQty = 0.0;
	@Setter(value = AccessLevel.PACKAGE)
	protected double reservationsBalance = 0.0;

	public InventoryTimeSegment() {
		super(TimeSegmentType.CALENDAR_TIME, null);

	}

	InventoryTimeNode startNode = null, endNode = null;

	public String toString() {
		return super.toString();
	}
}
