package com.openi40.scheduler.model;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.time.Timesheet;

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
public abstract class AbstractApsReservableObject extends AbstractApsObject implements ITimesheetAllocableObject, ICloneable {

	private Timesheet Timesheet = null;
	private boolean infiniteCapacity=false;
	private boolean disabled=false;
	private String timesheetMetaInfoCode = "DEFAULT";

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
}