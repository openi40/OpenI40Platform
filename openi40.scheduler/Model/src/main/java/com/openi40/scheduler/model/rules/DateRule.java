package com.openi40.scheduler.model.rules;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
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
public class DateRule extends Rule {
	private java.util.Date Date = null;
	private EndDateTimeAlignment endAlignment = null;
	private StartDateTimeAlignment startAlignment = null;
	private long DeltaMillisecs = 0L;

	private TimeSegmentType TimeRangeType = com.openi40.scheduler.model.time.TimeSegmentType.TASK_WHOLE_TIME;

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

}