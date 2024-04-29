package com.openi40.scheduler.engine.timesheet;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
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
@BusinessInterface(entityClass = ITimesheetAllocableObject.class)
public interface IAvailableTimeRangeGenerator extends IBusinessLogic<ITimesheetAllocableObject> {
	/*************************************************************************************************************************
	 * Return the working available time ranges for the reservable object in the passed time window, can cache if required
	 * @param ewObject
	 * @param timeWindow
	 * @return
	 */
	public List<TimesheetAvailableTimeRange> generateAvailableTimeRanges(ITimesheetAllocableObject ewObject, ApsWindow timeWindow);
	
	
}
