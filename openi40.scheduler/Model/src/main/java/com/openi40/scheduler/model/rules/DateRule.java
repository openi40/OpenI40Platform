package com.openi40.scheduler.model.rules;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentType;
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

public class DateRule extends Rule {
	private java.util.Date date = null;
	private EndDateTimeAlignment endAlignment = null;
	private StartDateTimeAlignment startAlignment = null;
	private long deltaMillisecs = 0L;

	private TimeSegmentType timeRangeType = com.openi40.scheduler.model.time.TimeSegmentType.TASK_WHOLE_TIME;

	public DateRule(ApsData context) {
		super(context);
	}

	public DateRule(Task target, ConstraintOrigin origin, ConstraintPriority priority, java.util.Date date, StartDateTimeAlignment startAlignment,EndDateTimeAlignment endAlignment ) {
		super(target, origin, priority);
		setDate(date);
		this.startAlignment=startAlignment;
		this.endAlignment=endAlignment;
	}

	public DateRule(Task target, ConstraintOrigin origin, ConstraintPriority priority, java.util.Date date,  StartDateTimeAlignment startAlignment,EndDateTimeAlignment endAlignment, TimeSegmentType timeSegmentType) {
		super(target, origin, priority);
		setDate(date);
		this.startAlignment=startAlignment;
		this.endAlignment=endAlignment;
		setTimeRangeType(timeSegmentType);
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public EndDateTimeAlignment getEndAlignment() {
		return endAlignment;
	}

	public void setEndAlignment(EndDateTimeAlignment endAlignment) {
		this.endAlignment = endAlignment;
	}

	public StartDateTimeAlignment getStartAlignment() {
		return startAlignment;
	}

	public void setStartAlignment(StartDateTimeAlignment startAlignment) {
		this.startAlignment = startAlignment;
	}

	public long getDeltaMillisecs() {
		return deltaMillisecs;
	}

	public void setDeltaMillisecs(long deltaMillisecs) {
		this.deltaMillisecs = deltaMillisecs;
	}

	public TimeSegmentType getTimeRangeType() {
		return timeRangeType;
	}

	public void setTimeRangeType(TimeSegmentType timeRangeType) {
		this.timeRangeType = timeRangeType;
	}

}