package com.openi40.scheduler.engine.timesheet;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimesheetReservation;

class ATCalendarReservation extends TimesheetReservation {
	ATPotentialSlotRange slotRange = null;
	TimeSegmentRequirement requirement = null;

	ATCalendarReservation(TimeSegmentType type, IApsObject ownerObject, ATPotentialSlotRange slotRange, ITimesheetAllocableObject reservable, TimeSegmentRequirement req) {
		super(type, ownerObject);
		this.setReservedObject(reservable);
		this.slotRange = slotRange;
		this.StartDateTime = slotRange.getStartDateTime();
		this.EndDateTime = slotRange.getEndDateTime();
		this.requirement = req;
	}

	public String toString() {
		return "AdvancedCalendar.AdvancedCalendarReservation{[" + DateUtil.toString(getStartDateTime()) + "<-->" + DateUtil.toString(getEndDateTime()) + "],\n reservable{code=" + getReservedObject().getCode() + ",id=" + getReservedObject().getId() + "},\n owner{code="
				+ getReservedFromObject().getCode() + ",id=" + getReservedFromObject().getId() + "},slotRange{" + slotRange + "},\n requirement{" + requirement + "}}";
	}

}