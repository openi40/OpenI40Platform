package com.openi40.scheduler.engine.timesheet;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
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
@BusinessInterface(entityClass = TimesheetMetaInfo.class)
public interface ITimesheetMetaInfoUnwrapper extends IBusinessLogic<TimesheetMetaInfo> {
	List<TimesheetAvailableTimeRange> unwrapCalendar(ITimesheetAllocableObject ewObject,ApsWindow timeWindow, TimesheetMetaInfo timesheetMetaInfo);
}
