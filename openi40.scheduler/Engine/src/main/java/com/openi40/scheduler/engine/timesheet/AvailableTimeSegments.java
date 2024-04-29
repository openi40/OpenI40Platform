package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.time.WorkingTimeSegment;
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

public class AvailableTimeSegments {
	ITimesheetAllocableObject resource = null;
	List<WorkingTimeSegment> availableTimeSegments = new ArrayList<>();
	public ITimesheetAllocableObject getResource() {
		return resource;
	}
	public void setResource(ITimesheetAllocableObject resource) {
		this.resource = resource;
	}
	public List<WorkingTimeSegment> getAvailableTimeSegments() {
		return availableTimeSegments;
	}
	public void setAvailableTimeSegments(List<WorkingTimeSegment> availableTimeSegments) {
		this.availableTimeSegments = availableTimeSegments;
	}

	
}
