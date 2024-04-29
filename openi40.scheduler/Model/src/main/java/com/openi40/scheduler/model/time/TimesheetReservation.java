package com.openi40.scheduler.model.time;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.aps.IOperationActuator;
import com.openi40.scheduler.common.aps.IOperationActuatorFactory;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
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
public class TimesheetReservation extends TimeSegment implements IOperation {

	public TimesheetReservation(TimeSegmentType type, IApsObject ownerObject) {
		super(type, ownerObject);
		ReservedFromObject = ownerObject;
		setIsApplied(false);
	}

	public boolean available;

	private IApsObject ReservedFromObject;

	public final IApsObject getReservedFromObject() {
		return ReservedFromObject;
	}

	private ITimesheetAllocableObject ReservedObject;

	public final ITimesheetAllocableObject getReservedObject() {
		return ReservedObject;
	}

	public final void setReservedObject(ITimesheetAllocableObject value) {
		ReservedObject = value;
	}

	private long ReservedTime;

	public final long getReservedTime() {
		return ReservedTime;
	}

	public final void setReservedTime(long value) {
		ReservedTime = value;
	}

	

	private Timesheet Timesheet;

	public final Timesheet getCalendar() {
		return Timesheet;
	}

	public final void setCalendar(Timesheet value) {
		Timesheet = value;
	}

	private boolean IsApplied = false;

	public final boolean isApplied() {
		return IsApplied;
	}

	private void setIsApplied(boolean value) {
		IsApplied = value;
	}

	private boolean IsReversed = false;

	public final boolean isReversed() {
		return IsReversed;
	}

	private void setIsReversed(boolean value) {
		IsReversed = value;
	}

	public final void apply(IOperationActuatorFactory actuatorFactory) {
		if (!isApplied() && getReservedObject() != null) {
			IOperationActuator<TimesheetReservation> actuator = actuatorFactory.create(TimesheetReservation.class);
			actuator.apply(this);
		}

		setIsApplied(true);
		setIsReversed(false);
	}

	public final void reverse(IOperationActuatorFactory actuatorFactory) {
		if (isApplied() && getReservedObject() != null) {
			IOperationActuator<TimesheetReservation> actuator = actuatorFactory.create(TimesheetReservation.class);
			actuator.reverse(this);
		}
		setIsApplied(false);
		setIsReversed(true);
	}

	@Override
	public String toString() {
		String v = "CalendarReservation{";
		v += getStartDateTime() != null && getStartDateTime() != null ? "StartDateTime=" + getStartDateTime() : "StartDateTime=null";
		v += ",";
		v += getEndDateTime() != null && getEndDateTime() != null ? "EndDateTime=" + getEndDateTime() : "EndDateTime=null";
		v += ",";
		v += "Type=" + getType();
		v += "IsApplied=" + isApplied();
		
		v += ",ReservedTime=" + getReservedTime();
		v += ",ReservedFrom=" + getReservedFromObject();
		v += ",ReservedObject=" + getReservedObject();
		v += "}";
		return v;
	}
}