package com.openi40.scheduler.model.aps;

import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class ApsWindow extends TimeSegment {
	protected Integer realtimePlanningDays = null;
	protected Integer realtimePlanningPastDays = null;

	public ApsWindow(ApsData ownerContext) {
		super(TimeSegmentType.SCHEDULING_WINDOW, ownerContext);
	}

	public Integer getRealtimePlanningDays() {
		return realtimePlanningDays;
	}

	public void setRealtimePlanningDays(Integer realtimePlanningDays) {
		this.realtimePlanningDays = realtimePlanningDays;
	}

	public Integer getRealtimePlanningPastDays() {
		return realtimePlanningPastDays;
	}

	public void setRealtimePlanningPastDays(Integer realtimePlanningPastDays) {
		this.realtimePlanningPastDays = realtimePlanningPastDays;
	}
}