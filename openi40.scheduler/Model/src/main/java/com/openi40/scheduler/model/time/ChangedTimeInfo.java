package com.openi40.scheduler.model.time;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class ChangedTimeInfo extends AbstractApsObject {
	public ChangedTimeInfo(ApsData context, TimeSegmentsGroup before, TimeSegmentsGroup after) {
		super(context);
		BeforeTime = before;
		AfterTime = after;

	}

	private TimeSegmentsGroup BeforeTime;

	public final TimeSegmentsGroup getBeforeTime() {
		return BeforeTime;
	}

	private TimeSegmentsGroup AfterTime;

	public final TimeSegmentsGroup getAfterTime() {
		return AfterTime;
	}
}