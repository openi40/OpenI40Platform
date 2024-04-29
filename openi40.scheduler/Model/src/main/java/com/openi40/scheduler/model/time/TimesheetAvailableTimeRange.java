package com.openi40.scheduler.model.time;

import com.openi40.scheduler.common.aps.IApsObject;
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
public class TimesheetAvailableTimeRange extends TimeSegment {
	
	private int AvailableQty = 0;

	// Duration without productivity coefficients transformation
	private double Duration = 0L;
	private double efficencyPercent=100.0;
	public TimesheetAvailableTimeRange(IApsObject ownerObject) {
		super(TimeSegmentType.UNKNOWN, ownerObject);
	}

	public TimesheetAvailableTimeRange(TimeSegment range) {
		super(range, TimeSegmentType.UNKNOWN, null);
	}

	public final int getAvailableQty() {
		return AvailableQty;
	}

	public  double getDuration() {
		return Duration;
	}

	public final void setAvailableQty(int value) {
		AvailableQty = value;
	}

	public  void setDuration(double value) {
		Duration = value;
	}

	@Override
	public String toString() {
		String v = "{";
		v += getStartDateTime() != null && getStartDateTime() != null ? "StartDateTime=" + getStartDateTime() : "StartDateTime=null";
		v += ",";
		v += getEndDateTime() != null && getEndDateTime() != null ? "EndDateTime=" + getEndDateTime() : "EndDateTime=null";
		v += ",AvailableQty=" + getAvailableQty();
		v += ",Duration=" + getDuration();
		v += ",Type=" + getType();

		v += "}";
		return v;
	}

	public double getEfficencyPercent() {
		return efficencyPercent;
	}

	public void setEfficencyPercent(double efficencyPercent) {
		this.efficencyPercent = efficencyPercent;
	}
}