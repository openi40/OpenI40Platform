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
package com.openi40.scheduler.tests;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimesheetReservation;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;

/**
 * Summary description for TimeRangesTests
 */

public class TimeRangesTests {
	public TimeRangesTests() {

		// iii

	}

	@Test
	public final void TestCalendarGroups() {
		ApsData c = new ApsData();
		IApsObject ownerObject = new Task(c);
		TimesheetReservation reservation = new TimesheetReservation(TimeSegmentType.MATERIAL_ARRIVING_TIME, ownerObject);
		reservation.setStartDateTime(new Date(Long.MAX_VALUE));
		reservation.setEndDateTime(new Date(0l));
		Assert.assertFalse("The CalendarReservation has wrong IsValid method", reservation.isValid());
		reservation.setEndDateTime(new Date(Long.MAX_VALUE));
		reservation.setStartDateTime(new Date(0l));
		Assert.assertTrue("The CalendarReservation has wrong IsValid method", reservation.isValid());
		reservation.setStartDateTime(new Date());
		reservation.setEndDateTime(DateUtil.add(reservation.getStartDateTime(),GregorianCalendar.MILLISECOND,10000));
		TimeSegmentsGroup timeSegmentsGroup = new TimeSegmentsGroup(TimeSegmentType.MATERIAL_ARRIVING_TIME,
				ownerObject);
		Assert.assertFalse("The CalendarReservation has wrong IsValid method with null bounds",
				timeSegmentsGroup.isValid());

		timeSegmentsGroup.Add(reservation);
		Assert.assertEquals("Calculation of startdatetime of calendargroup is wrong!",
				timeSegmentsGroup.getStartDateTime(), reservation.getStartDateTime());
		Assert.assertEquals("Calculation of enddatetime of calendargroup is wrong!", timeSegmentsGroup.getEndDateTime(),
				reservation.getEndDateTime());
		TimesheetReservation reservation1 = new TimesheetReservation(TimeSegmentType.MATERIAL_ARRIVING_TIME, ownerObject);
		reservation1.setStartDateTime(DateUtil.addMinutes(new Date(),100));
		reservation1.setEndDateTime(DateUtil.addMinutes(reservation1.getStartDateTime(),100));
		timeSegmentsGroup.Add(reservation1);
		Assert.assertEquals("wrong group calculation on StartDateTime", timeSegmentsGroup.getStartDateTime(),
				reservation.getStartDateTime());
		Assert.assertEquals("wrong group calculation on EndDateTime", timeSegmentsGroup.getEndDateTime(),
				reservation1.getEndDateTime());
		TimeSegmentsGroup timeRangeGroup1 = new TimeSegmentsGroup(TimeSegmentType.MATERIAL_ARRIVING_TIME, ownerObject);
		timeRangeGroup1.Add(timeSegmentsGroup);
		Assert.assertEquals("wrong group calculation on StartDateTime", timeRangeGroup1.getStartDateTime(),
				timeSegmentsGroup.getStartDateTime());
		Assert.assertEquals("wrong group calculation on EndDateTime", timeRangeGroup1.getEndDateTime(),
				timeSegmentsGroup.getEndDateTime());
		TimeSegment timeSegment = new TimeSegment(TimeSegmentType.SETUP_TIME, null);
		timeSegment.setStartDateTime(new Date());
		timeSegment.setEndDateTime(DateUtil.addMinutes(timeSegment.getStartDateTime(), 100));
		assert timeSegment.IsOverlapping(timeSegment);
		TimeSegment timeRange1 = new TimeSegment(TimeSegmentType.SETUP_TIME, null);
		timeRange1.setStartDateTime(timeSegment.getEndDateTime());
		timeRange1.setEndDateTime(DateUtil.addMinutes(timeRange1.getStartDateTime(),100));
		assert !timeSegment.IsOverlapping(timeRange1);
	}

	@Test
	public final void TestContigusAndOverlap() {
		TimeSegment tr = new TimeSegment(TimeSegmentType.CALENDAR_TIME, null);
		tr.setStartDateTime(new Date());
		tr.setEndDateTime(DateUtil.addDays(tr.getStartDateTime(),5));
		TimeSegment tr1 = new TimeSegment(TimeSegmentType.CALENDAR_TIME, null);
		tr1.setStartDateTime(tr.getEndDateTime());
		tr1.setEndDateTime(DateUtil.addDays(tr1.getStartDateTime(),5));
		assert tr.isContiguous(tr1);
		assert tr1.isContiguous(tr);
		assert tr1.IsOverlapping(tr1);
		tr1.setEndDateTime(null);
		assert tr.isContiguous(tr1);
		assert tr1.isContiguous(tr);
		assert tr1.IsOverlapping(tr1);
		tr.setStartDateTime(null);
		assert tr.isContiguous(tr1);
		assert tr1.isContiguous(tr);
		assert tr1.IsOverlapping(tr1);
		tr1.setStartDateTime(DateUtil.addDays(tr1.getStartDateTime(),-2));
		assert tr.IsOverlapping(tr1);
		assert tr1.IsOverlapping(tr);
		assert tr1.IsOverlapping(tr1);
		tr = new TimeSegment(TimeSegmentType.CALENDAR_TIME, null);
		tr.setStartDateTime(new Date());
		tr.setEndDateTime(DateUtil.addDays(tr.getStartDateTime(),5));
		tr1 = new TimeSegment(TimeSegmentType.CALENDAR_TIME, null);
		tr1.setStartDateTime(tr.getEndDateTime());
		tr1.setEndDateTime(DateUtil.addDays(tr1.getStartDateTime(),5));
		tr1.setStartDateTime(DateUtil.addDays(tr1.getStartDateTime(),-2));
		assert tr.IsOverlapping(tr1);
		assert tr1.IsOverlapping(tr);
		assert tr1.IsOverlapping(tr1);

	}
}