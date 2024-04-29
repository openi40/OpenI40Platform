package com.openi40.scheduler.engine.timesheet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
import com.openi40.scheduler.model.time.TimesheetMetaInfoExceptionRule;
import com.openi40.scheduler.model.time.TimesheetMetaInfoWorkingTimeRule;
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
@DefaultImplementation(implemented = ITimesheetMetaInfoUnwrapper.class, entityClass = TimesheetMetaInfo.class)
public class TimesheetMetaInfoUnwrapperImpl extends BusinessLogic<TimesheetMetaInfo> implements ITimesheetMetaInfoUnwrapper {

	public TimesheetMetaInfoUnwrapperImpl() {

	}

	@Override
	public List<TimesheetAvailableTimeRange> unwrapCalendar(ITimesheetAllocableObject ewObject, ApsWindow timeWindow,
			TimesheetMetaInfo timesheetMetaInfo) {
		Date startDate = timeWindow.getStartDateTime();
		Date endDate = timeWindow.getEndDateTime();
		List<TimesheetAvailableTimeRange> _calendarSlots = new ArrayList<TimesheetAvailableTimeRange>();
		List<TimesheetMetaInfoWorkingTimeRule> workingTimeRules = timesheetMetaInfo.getWorkingTimeRules();
		List<TimesheetMetaInfoExceptionRule> exceptions = timesheetMetaInfo.getExceptionRules();
		List<TimeSegment> workExceptionSegments = new ArrayList<TimeSegment>();
		List<TimeSegment> restExceptionSegments = new ArrayList<TimeSegment>();
		if (exceptions != null) {
			for (TimesheetMetaInfoExceptionRule timesheetMetaInfoExceptionRule : exceptions) {
				TimeSegment segment = new TimeSegment(TimeSegmentType.CALENDAR_TIME, ewObject);
				segment.setStartDateTime(timesheetMetaInfoExceptionRule.getStartPeriod());
				segment.setEndDateTime(timesheetMetaInfoExceptionRule.getEndPeriod());
				if (timesheetMetaInfoExceptionRule.isWorking()) {
					workExceptionSegments.add(segment);
				} else {
					restExceptionSegments.add(segment);
				}
			}
		}
		if (workingTimeRules != null) {
			GregorianCalendar gregorian = new GregorianCalendar();
			gregorian.setTime(startDate);
			gregorian.set(GregorianCalendar.HOUR, 0);
			gregorian.set(GregorianCalendar.MINUTE, 0);
			gregorian.set(GregorianCalendar.SECOND, 0);
			gregorian.set(GregorianCalendar.MILLISECOND, 0);
			TreeMap<Date, TimesheetAvailableTimeRange> orderedPeriods = new TreeMap<Date, TimesheetAvailableTimeRange>();
			GregorianCalendar endGregorian = new GregorianCalendar();
			endGregorian.setTime(endDate);
			while (gregorian.before(endGregorian) || gregorian.equals(endGregorian)) {
				for (TimesheetMetaInfoWorkingTimeRule timesheetMetaInfoWorkingTimeRule : workingTimeRules) {

					int dow = timesheetMetaInfoWorkingTimeRule.getDayOfWeek();
					gregorian.set(GregorianCalendar.DAY_OF_WEEK, dow);

					Time startTime = timesheetMetaInfoWorkingTimeRule.getStartTime();
					setTime(gregorian, startTime);

					TimesheetAvailableTimeRange timeRange = new TimesheetAvailableTimeRange(ewObject);
					timeRange.setStartDateTime(gregorian.getTime());
					Time endTime = timesheetMetaInfoWorkingTimeRule.getEndTime();

					setTime(gregorian, endTime);

					timeRange.setEndDateTime(gregorian.getTime());
					if (timeRange.getStartDateTime().after(startDate)
							|| timeRange.getStartDateTime().equals(startDate)) {
						boolean isOnException = false;
						for (TimeSegment timeSegment : restExceptionSegments) {
							isOnException = isOnException || (timeSegment.isInRange(timeRange));
						}
						if (!isOnException) {
							orderedPeriods.put(timeRange.getStartDateTime(), timeRange);
						}
					}
				}
				gregorian.add(GregorianCalendar.WEEK_OF_YEAR, 1);
			}
			_calendarSlots.addAll(orderedPeriods.values());
		}
		return _calendarSlots;
	}

	private void setTime(GregorianCalendar gregorian, Time time) {
		
		gregorian.set(GregorianCalendar.HOUR_OF_DAY, 0);
		gregorian.set(GregorianCalendar.MINUTE, 0);
		gregorian.set(GregorianCalendar.SECOND, 0);
		gregorian.set(GregorianCalendar.MILLISECOND, 0);

		int hours = time.getHours();
		int minutes = time.getMinutes();
		int seconds = time.getSeconds();
		gregorian.set(GregorianCalendar.HOUR_OF_DAY, hours);
		gregorian.set(GregorianCalendar.MINUTE, minutes);
		gregorian.set(GregorianCalendar.SECOND, seconds);
		gregorian.set(GregorianCalendar.MILLISECOND, 0);
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTimeInMillis(time.getTime());
		if (gc.get(GregorianCalendar.DAY_OF_YEAR)>1) {
			gregorian.add(GregorianCalendar.DAY_OF_YEAR, gc.get(GregorianCalendar.DAY_OF_YEAR)-1);
		}

	}
}
