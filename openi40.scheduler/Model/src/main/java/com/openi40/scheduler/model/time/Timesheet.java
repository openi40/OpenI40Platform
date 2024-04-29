package com.openi40.scheduler.model.time;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
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
public abstract class Timesheet extends AbstractApsObject implements IReferencingMetaInfo<TimesheetMetaInfo> {
	TimesheetMetaInfo metaInfo = null;

	public Timesheet(ApsData context) {
		super(context);

	}

	private TimeSegmentsGroup MainRange = new TimeSegmentsGroup(TimeSegmentType.CALENDAR_TIME, null);

	public TimeSegmentsGroup getMainRange() {
		return MainRange;
	}

	public void resetSchedulingData() {
		MainRange.resetSchedulingData();
	}

	@Override
	public TimesheetMetaInfo getMetaInfo() {

		return metaInfo;
	}

	public void setMetaInfo(TimesheetMetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}

}