package com.openi40.scheduler.model.aps;

import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;

import lombok.Data;
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
public class ApsWindow extends TimeSegment {
	protected Integer realtimePlanningDays=null;
	public ApsWindow(ApsData ownerContext) {
		super(TimeSegmentType.SCHEDULING_WINDOW, ownerContext);
	}
}