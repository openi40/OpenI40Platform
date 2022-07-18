package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ITimesheetMetaInfoDao;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
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
@DefaultImplementation(implemented = IAvailableTimeRangeGenerator.class, entityClass = ITimesheetAllocableObject.class)
public class AvailableTimeRangeGeneratorImpl extends BusinessLogic<ITimesheetAllocableObject>
		implements IAvailableTimeRangeGenerator {
	static Logger LOGGER = LoggerFactory.getLogger(AvailableTimeRangeGeneratorImpl.class);
	@Autowired
	ITimesheetMetaInfoDao timesheetMetaInfoDao;

	public AvailableTimeRangeGeneratorImpl() {

	}

	Map<String, List<TimesheetAvailableTimeRange>> timeRangesCache = new HashMap<String, List<TimesheetAvailableTimeRange>>();

	private List<TimesheetAvailableTimeRange> unwrapCalendarModel(ITimesheetAllocableObject ewObject, ApsWindow timeWindow,
			TimesheetMetaInfo timesheetMetaInfo, String _key) {

		List<TimesheetAvailableTimeRange> _calendarSlots = timeRangesCache.get(_key);
		if (_calendarSlots == null) {
			ITimesheetMetaInfoUnwrapper calendarUnwrapper = this.componentsFactory.create(ITimesheetMetaInfoUnwrapper.class,
					timesheetMetaInfo, ewObject.getContext());
			_calendarSlots = calendarUnwrapper.unwrapCalendar(ewObject, timeWindow, timesheetMetaInfo);
			timeRangesCache.put(_key, _calendarSlots);

		}
		return _calendarSlots;
	}

	@Override
	public List<TimesheetAvailableTimeRange> generateAvailableTimeRanges(ITimesheetAllocableObject ewObject,
			ApsWindow timeWindow) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin generateAvailableTimeRanges(" + ewObject.getCode() + "," + timeWindow + ")");
		}
		Date startDate = timeWindow.getStartDateTime();

		Date endDate = timeWindow.getEndDateTime();
		String _key = ewObject.getContext().getId() + "-" + DateUtil.toString(startDate) + "-"
				+ DateUtil.toString(endDate) + "-"
				+ (ewObject.getTimesheetMetaInfoCode() != null ? ewObject.getTimesheetMetaInfoCode() : "DEFAULT");
		String calendarModelCode = ewObject.getTimesheetMetaInfoCode();
		if (calendarModelCode == null || calendarModelCode.trim().length() == 0)
			calendarModelCode = "DEFAULT";
		try {
			TimesheetMetaInfo timesheetMetaInfo = timesheetMetaInfoDao.findByCode(calendarModelCode, ewObject.getContext());
			if (timesheetMetaInfo == null && !calendarModelCode.equalsIgnoreCase("DEFAULT"))
				throw new RuntimeException("cannot find referred calendarModel");
			if (timesheetMetaInfo != null) {
				return this.unwrapCalendarModel(ewObject, timeWindow, timesheetMetaInfo, _key);
			}
		} catch (DataModelDaoException e) {
			String _msg = "unable to use calendarModelDao";
			LOGGER.error(_msg, e);
			throw new RuntimeException(_msg, e);
		}

		List<TimesheetAvailableTimeRange> _calendarSlots = timeRangesCache.get(_key);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Available Time ranges "
					+ (_calendarSlots != null ? "HIT USING ALREADY GENERATED RANGES!!" : "MISS CREATE RANGES!"));
		}
		if (_calendarSlots == null) {
			_calendarSlots = new ArrayList<TimesheetAvailableTimeRange>();
			timeRangesCache.put(_key, _calendarSlots);
			GregorianCalendar gregorian = new GregorianCalendar();
			gregorian.setTime(startDate);
			gregorian.set(GregorianCalendar.HOUR_OF_DAY, 8);
			gregorian.set(GregorianCalendar.MINUTE, 0);
			gregorian.set(GregorianCalendar.SECOND, 0);
			gregorian.set(GregorianCalendar.MILLISECOND, 0);
			GregorianCalendar arrivingTime = new GregorianCalendar();
			arrivingTime.setTime(endDate);
			arrivingTime.set(GregorianCalendar.HOUR_OF_DAY, 17);
			arrivingTime.set(GregorianCalendar.MINUTE, 0);
			arrivingTime.set(GregorianCalendar.SECOND, 0);
			arrivingTime.set(GregorianCalendar.MILLISECOND, 0);

			while (gregorian.before(arrivingTime)) {
				int dayOfWeek = gregorian.get(GregorianCalendar.DAY_OF_WEEK);
				if (dayOfWeek != GregorianCalendar.SATURDAY && dayOfWeek != GregorianCalendar.SUNDAY) {
					TimesheetAvailableTimeRange morningWorkingSlot = new TimesheetAvailableTimeRange(ewObject);
					Date start = gregorian.getTime();
					morningWorkingSlot.setStartDateTime(start);
					gregorian.set(GregorianCalendar.HOUR_OF_DAY, 12);
					Date end = gregorian.getTime();
					morningWorkingSlot.setEndDateTime(end);
					morningWorkingSlot.setAvailableQty(1);
					morningWorkingSlot.setDuration(4 * 60);
					_calendarSlots.add(morningWorkingSlot);
					gregorian.set(GregorianCalendar.HOUR_OF_DAY, 13);
					TimesheetAvailableTimeRange afternoonWorkingSlot = new TimesheetAvailableTimeRange(ewObject);
					afternoonWorkingSlot.setStartDateTime(gregorian.getTime());
					gregorian.set(GregorianCalendar.HOUR_OF_DAY, 17);
					afternoonWorkingSlot.setEndDateTime(gregorian.getTime());
					afternoonWorkingSlot.setAvailableQty(1);
					afternoonWorkingSlot.setDuration(4 * 60);
					_calendarSlots.add(afternoonWorkingSlot);
				}
				gregorian.add(GregorianCalendar.DAY_OF_YEAR, 1);
				gregorian.set(GregorianCalendar.HOUR_OF_DAY, 8);
				gregorian.set(GregorianCalendar.MINUTE, 0);
				gregorian.set(GregorianCalendar.SECOND, 0);
				gregorian.set(GregorianCalendar.MILLISECOND, 0);
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End generateAvailableTimeRanges(" + ewObject.getCode() + "," + timeWindow + ")");
		}
		return _calendarSlots;
	}

}
