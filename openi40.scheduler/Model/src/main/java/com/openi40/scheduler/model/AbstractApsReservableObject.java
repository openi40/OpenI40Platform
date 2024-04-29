package com.openi40.scheduler.model;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.time.Timesheet;

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

public abstract class AbstractApsReservableObject extends AbstractApsObject
		implements ITimesheetAllocableObject, ICloneable {

	private Timesheet Timesheet = null;
	private boolean infiniteCapacity = false;
	private boolean disabled = false;
	private String timesheetMetaInfoCode = "DEFAULT";
	private ReservableObjectAvailability availability = ReservableObjectAvailability.AVAILABLE;

	public AbstractApsReservableObject(ApsData context) {
		super(context);

	}

	public ICloneable cleanClone() throws CloneNotSupportedException {
		AbstractApsReservableObject ewc = (AbstractApsReservableObject) clone();

		return ewc;
	}

	@Override
	public void resetSchedulingData() {

		if (Timesheet != null)
			Timesheet.resetSchedulingData();
	}

	protected void finalize() throws Throwable {

		setTimesheet(null);

	}

	public Timesheet getTimesheet() {
		return Timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		Timesheet = timesheet;
	}

	public boolean isInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}

	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}

	public ReservableObjectAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(ReservableObjectAvailability availability) {
		this.availability = availability;
	}
}