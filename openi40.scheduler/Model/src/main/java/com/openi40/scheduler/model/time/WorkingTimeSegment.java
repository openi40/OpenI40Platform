package com.openi40.scheduler.model.time;

import com.openi40.scheduler.common.aps.IApsObject;
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
public class WorkingTimeSegment extends TimeSegment {
	int workingTime=0;
	public WorkingTimeSegment() {
		super(TimeSegmentType.CALENDAR_TIME, null);
	}
	public WorkingTimeSegment(TimeSegment tr) {
		super(tr);
		
	}

	public WorkingTimeSegment(TimeSegment tr, TimeSegmentType type, IApsObject ownerObject) {
		super(tr, type, ownerObject);
		
	}

	public WorkingTimeSegment(TimeSegmentType type, IApsObject ownerObject) {
		super(type, ownerObject);
		
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

}
