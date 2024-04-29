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

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.engine.timesheet.AvailableTimeSegments;
import com.openi40.scheduler.engine.timesheet.IAvailableTimeRangeGenerator;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetReservation;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.WorkingTimeSegment;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
//@ComponentScan("com.openi40.scheduler")
public class TestCalendarManager {
	static int TESTS_ITERATIONS = 1;
	static Logger LOGGER = LoggerFactory.getLogger(TestCalendarManager.class);
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;
	@Autowired
	TestsApsDataSourceUncachedFactory uncachedAccessor;
	@BeforeClass
	public static void Initialize() {
		System.setProperty("log4j.logger.com.productionscheduler.engine.calendar", "DEBUG");
	}
	
	@Test
	public final void TestResourceCalendarManagerForwardAsap() {
		for (int idx = 0; idx < TESTS_ITERATIONS; idx++) {
			ApsData context = new ApsData();
			Date startDate = DateUtil.discretize(new Date());
			long d = DateUtil.dateTimeLongDiscreteRappresentation(startDate);
			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			Machine machine = new Machine(context);

			ITimesheetLogic timesheetLogic = ComponentFactory.create(ITimesheetLogic.class, machine, context);
			TimeSegmentRequirement startRequirement = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
			startRequirement.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
			startRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			Map<Date, Date> edges = datesEdgesOnSlotsForward(context, machine);
			TimesheetReservation previusReservation = null;
			Task sTask = new Task(context);
			for (int i = 0; i < 100; i++) {
				int REQUIRED_TIME = (int) (Math.random() > 0.5 ? Math.random() * 800.0 : Math.random() * 30);
				if (REQUIRED_TIME == 0)
					REQUIRED_TIME = 5;
				startRequirement.setAvailabilityDuration(REQUIRED_TIME);
				System.out.println("Requirement=>" + startRequirement);
				TimesheetReservation reservation = timesheetLogic.addReservation(machine, startRequirement, sTask);
				System.out.println(reservation);
				Assert.assertNotNull(reservation);
				Assert.assertEquals(REQUIRED_TIME, reservation.getReservedTime());

				if (previusReservation != null) {
					if (edges.containsKey(previusReservation.getEndDateTime())) {
						Assert.assertEquals(reservation.getStartDateTime(),
								edges.get(previusReservation.getEndDateTime()));
					} else {
						Assert.assertEquals(reservation.getStartDateTime(), (previusReservation.getEndDateTime()));
					}
				}
				previusReservation = reservation;
			}
		}

	}

	Map<Date, Date> datesEdgesOnSlotsForward(ApsData context, ITimesheetAllocableObject machine) {
		HashMap<Date, Date> datesEdges = new HashMap<>();
		IAvailableTimeRangeGenerator availableTimeRangeGen = ComponentFactory.create(IAvailableTimeRangeGenerator.class,
				machine, context);
		List<TimesheetAvailableTimeRange> workTimeRanges = availableTimeRangeGen.generateAvailableTimeRanges(machine,
				context.getSchedulingWindow());
		for (int idx = 0; idx < workTimeRanges.size() - 1; idx++) {
			TimesheetAvailableTimeRange timesheetAvailableTimeRange = (TimesheetAvailableTimeRange) workTimeRanges
					.get(idx);
			TimesheetAvailableTimeRange nextCalendarAvailableTimeRange = (TimesheetAvailableTimeRange) workTimeRanges
					.get(idx + 1);
			datesEdges.put(timesheetAvailableTimeRange.getEndDateTime(),
					nextCalendarAvailableTimeRange.getStartDateTime());
		}
		return datesEdges;
	}

	Map<Date, Date> datesEdgesOnSlotsBackward(ApsData context, ITimesheetAllocableObject machine) {
		HashMap<Date, Date> datesEdges = new HashMap<>();
		IAvailableTimeRangeGenerator availableTimeRangeGen = ComponentFactory.create(IAvailableTimeRangeGenerator.class,
				machine, context);
		List<TimesheetAvailableTimeRange> workTimeRanges = availableTimeRangeGen.generateAvailableTimeRanges(machine,
				context.getSchedulingWindow());
		for (int idx = workTimeRanges.size() - 1; idx > 0; idx--) {
			TimesheetAvailableTimeRange timesheetAvailableTimeRange = (TimesheetAvailableTimeRange) workTimeRanges
					.get(idx);
			TimesheetAvailableTimeRange nextCalendarAvailableTimeRange = (TimesheetAvailableTimeRange) workTimeRanges
					.get(idx - 1);
			datesEdges.put(nextCalendarAvailableTimeRange.getEndDateTime(),
					timesheetAvailableTimeRange.getStartDateTime());
		}
		return datesEdges;
	}

	@Test
	public final void TestResourceCalendarManagerForwardAsap7MinStep() {
		for (int idx = 0; idx < TESTS_ITERATIONS; idx++) {
			ApsData context = new ApsData();
			Date startDate = DateUtil.discretize(new Date());
			long d = DateUtil.dateTimeLongDiscreteRappresentation(startDate);
			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			Machine machine = new Machine(context);
			Map<Date, Date> edges = datesEdgesOnSlotsForward(context, machine);
			ITimesheetLogic timesheetLogic = ComponentFactory.create(ITimesheetLogic.class, machine, context);
			TimesheetReservation previusReservation = null;
			TimeSegmentRequirement startRequirement = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
			startRequirement.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
			startRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			Task sTask = new Task(context);
			for (int i = 0; i < 50; i++) {
				int REQUIRED_TIME = 700;
				startRequirement.setAvailabilityDuration(REQUIRED_TIME);
				System.out.println("Requirement=>" + startRequirement);
				TimesheetReservation reservation = timesheetLogic.addReservation(machine, startRequirement, sTask);
				System.out.println(reservation);
				Assert.assertNotNull(reservation);
				Assert.assertEquals(REQUIRED_TIME, reservation.getReservedTime());
				if (previusReservation != null) {
					if (edges.containsKey(previusReservation.getEndDateTime())) {
						Assert.assertEquals(reservation.getStartDateTime(),
								edges.get(previusReservation.getEndDateTime()));
					} else {
						Assert.assertEquals(reservation.getStartDateTime(), (previusReservation.getEndDateTime()));
					}
				}
				previusReservation = reservation;
			}
		}

	}

	@Test
	public final void TestResourceCalendarManagerBackwardAsap() {
		for (int idx = 0; idx < TESTS_ITERATIONS; idx++) {
			ApsData context = new ApsData();
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, 2020);
			gc.set(GregorianCalendar.MONTH, GregorianCalendar.APRIL);
			gc.set(GregorianCalendar.DAY_OF_MONTH, 6);
			gc.set(GregorianCalendar.HOUR, 8);
			gc.set(GregorianCalendar.MINUTE, 0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			Date startDate = DateUtil.discretize(gc.getTime());
			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			gc.set(GregorianCalendar.HOUR, 8);
			startDate = DateUtil.discretize(gc.getTime());

			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			Machine machine = new Machine(context);

			ITimesheetLogic timesheetLogic = ComponentFactory.create(ITimesheetLogic.class, machine, context);
			TimeSegmentRequirement startRequirement = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
			startRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
			startRequirement.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
			Map<Date, Date> edges = datesEdgesOnSlotsBackward(context, machine);
			TimesheetReservation previusReservation = null;
			Task sTask = new Task(context);
			for (int i = 0; i < 100; i++) {
				int REQUIRED_TIME = (int) (Math.random() > 0.5 ? Math.random() * 800.0 : Math.random() * 30);
				if (REQUIRED_TIME == 0)
					REQUIRED_TIME = 5;
				startRequirement.setAvailabilityDuration(REQUIRED_TIME);
				LOGGER.info("Requirement=>" + startRequirement);
				TimesheetReservation reservation = timesheetLogic.addReservation(machine, startRequirement, sTask);
				Assert.assertNotNull(reservation);
				Assert.assertEquals(reservation.getReservedTime(), REQUIRED_TIME);
				LOGGER.info("Reserved:" + reservation);
				if (previusReservation != null) {
					if (edges.containsKey(reservation.getEndDateTime())) {
						LOGGER.info("Assert.assertEquals(" + previusReservation.getStartDateTime() + ","
								+ edges.get(reservation.getEndDateTime()) + ");");
						Assert.assertEquals(previusReservation.getStartDateTime(),
								edges.get(reservation.getEndDateTime()));
					} else {
						LOGGER.info("Assert.assertEquals(" + reservation.getEndDateTime() + ","
								+ (previusReservation.getStartDateTime()) + ");");
						Assert.assertEquals(reservation.getEndDateTime(), (previusReservation.getStartDateTime()));
					}
				}
				previusReservation = reservation;
			}
		}
	}

	Date randomTimeBetween(Date start, Date end) {
		long time = Math.min(start.getTime(), end.getTime());
		long delta = Math.abs(DateUtil.calculateDiscreteStepsNr(start, end)) * 60 * 1000;
		double _doubleDelta = Math.random() * ((double) delta);
		delta = Math.round(_doubleDelta);
		return DateUtil.discretize(new Date(time + delta));
	}

	@Test
	public final void TestResourceCalendarManagerClosedSegment() {
		for (int idx = 0; idx < TESTS_ITERATIONS; idx++) {
			ApsData context = new ApsData();
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, 2020);
			gc.set(GregorianCalendar.MONTH, GregorianCalendar.APRIL);
			gc.set(GregorianCalendar.DAY_OF_MONTH, 6);
			gc.set(GregorianCalendar.HOUR, 8);
			gc.set(GregorianCalendar.MINUTE, 0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			Date startDate = DateUtil.discretize(gc.getTime());
			long d = DateUtil.dateTimeLongDiscreteRappresentation(startDate);
			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			Machine machine = new Machine(context);
			Task sTask = new Task(context);
			ITimesheetLogic timesheetLogic = ComponentFactory.create(ITimesheetLogic.class, machine, context);
			IAvailableTimeRangeGenerator availableTimeRangeGen = ComponentFactory
					.create(IAvailableTimeRangeGenerator.class, machine, context);
			List<TimesheetAvailableTimeRange> ranges = availableTimeRangeGen.generateAvailableTimeRanges(machine,
					context.getSchedulingWindow());
			for (int i = 0; i < ranges.size(); i++) {
				TimesheetAvailableTimeRange rangeStart = ranges.get(i);
				double slotsQty = Math.random() > 0.5 ? Math.round(Math.random() * 20.0) : 0.0;
				i += slotsQty;
				if (i < ranges.size()) {
					TimesheetAvailableTimeRange rangeEnd = ranges.get(i);
					Date start = randomTimeBetween(rangeStart.getStartDateTime(), rangeStart.getEndDateTime());
					Date end = randomTimeBetween(rangeEnd.getStartDateTime(), rangeEnd.getEndDateTime());
					if (!start.before(end)) {
						Date third4Swap = end;
						end = start;
						start = third4Swap;
					}
					TimeSegmentRequirement requirement = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
					requirement.setStartDateTime(start);
					if (Math.random() > 0.5) {

						requirement.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
					} else {
						requirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
					}
					int REQUIRED_TIME = 0;
					requirement.setAvailabilityDuration(REQUIRED_TIME);
					requirement.setEndDateTime(end);
					LOGGER.info("Requirement=>" + requirement);
					TimesheetReservation reservation = timesheetLogic.addReservation(machine, requirement, sTask);
					LOGGER.info("Reserved=>" + reservation);
					Assert.assertNotNull(reservation);
					Assert.assertEquals(reservation.getStartDateTime(), requirement.getStartDateTime());
					Assert.assertEquals(reservation.getEndDateTime(), requirement.getEndDateTime());
				}
			}
		}
	}

	@Test
	public final void TestResourceCalendarManagerPreciseForwardSegment() {
		for (int idx = 0; idx < TESTS_ITERATIONS; idx++) {
			ApsData context = new ApsData();
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, 2020);
			gc.set(GregorianCalendar.MONTH, GregorianCalendar.APRIL);
			gc.set(GregorianCalendar.DAY_OF_MONTH, 6);
			gc.set(GregorianCalendar.HOUR, 8);
			gc.set(GregorianCalendar.MINUTE, 0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			Date startDate = DateUtil.discretize(gc.getTime());
			long d = DateUtil.dateTimeLongDiscreteRappresentation(startDate);
			context.getSchedulingWindow().setStartDateTime(startDate);
			context.getSchedulingWindow().setEndDateTime(DateUtil.add(startDate, GregorianCalendar.DAY_OF_YEAR, 180));
			Machine machine = new Machine(context);
			Task sTask = new Task(context);
			ITimesheetLogic timesheetLogic = ComponentFactory.create(ITimesheetLogic.class, machine, context);
			IAvailableTimeRangeGenerator availableTimeRangeGen = ComponentFactory
					.create(IAvailableTimeRangeGenerator.class, machine, context);
			List<TimesheetAvailableTimeRange> ranges = availableTimeRangeGen.generateAvailableTimeRanges(machine,
					context.getSchedulingWindow());
			HashMap<Date, Date> edges = new HashMap<>();
			for (int index = 0; index < ranges.size(); index++) {
				TimesheetAvailableTimeRange timesheetAvailableTimeRange = ranges.get(index);
				if (index < ranges.size() - 1) {
					edges.put(timesheetAvailableTimeRange.getEndDateTime(), ranges.get(index + 1).getStartDateTime());
				}
			}
			TimesheetAvailableTimeRange rangeStart = ranges.get(5);
			Date start = randomTimeBetween(rangeStart.getStartDateTime(), rangeStart.getEndDateTime());
			TimesheetReservation lastReservation = null;
			for (int i = 0; i < 100; i++) {
				TimeSegmentRequirement requirement = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
				if (edges.containsKey(start)) {
					start = edges.get(start);
					lastReservation = null;
				}
				requirement.setStartDateTime(start);

				requirement.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				int REQUIRED_TIME = (int) (Math.random() > 0.5 ? Math.random() * 1000.0 : Math.random() * 10.0);
				if (REQUIRED_TIME == 0)
					REQUIRED_TIME = 7;
				requirement.setAvailabilityDuration(REQUIRED_TIME);
				System.out.println("Requirement=>" + requirement);
				TimesheetReservation reservation = timesheetLogic.addReservation(machine, requirement, sTask);
				System.out.println("Reserved=>" + reservation);
				Assert.assertNotNull(reservation);
				Assert.assertEquals(reservation.getStartDateTime(), requirement.getStartDateTime());
				if (lastReservation != null) {
					Assert.assertEquals(lastReservation.getEndDateTime(), reservation.getStartDateTime());
				}
				lastReservation = reservation;
				start = lastReservation.getEndDateTime();
			}
		}
	}
	@Autowired IMachineDao machineDao;
	@Test
	public void testFreeSegmentDiscoveryFunctions() throws DataModelDaoException, ApsDataCacheException {
		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData =uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		String machineCode="SS-LASERCUTMACHINE-01";
		Machine machine=machineDao.findByCode(machineCode, apsData);
		ITimesheetLogic timesheetLogic=ComponentFactory.create(ITimesheetLogic.class,machine,apsData);
		TimeSegmentRequirement fullTimeRequirement=new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
		fullTimeRequirement.setStartDateTime(apsData.getSchedulingWindow().getStartDateTime());
		fullTimeRequirement.setEndDateTime(apsData.getSchedulingWindow().getEndDateTime());
		AvailableTimeSegments result = timesheetLogic.getAvailableTimeSegments(machine, fullTimeRequirement);
		for(WorkingTimeSegment s:result.getAvailableTimeSegments()) {
			System.out.println(s);
		}
		//Alloco 
		TimeSegmentRequirement timeRequirement=new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
		timeRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
		timeRequirement.setStartDateTime(add(apsData.getSchedulingWindow().getStartDateTime(),0,1,0,0));
		timeRequirement.setEndDateTime(add(apsData.getSchedulingWindow().getStartDateTime(),0,6,2,0));
		//Alloco nel bel mezzo del calendario
		TimesheetReservation reservation=timesheetLogic.addReservation(machine, timeRequirement, timeRequirement);
		System.out.println("Allocated: "+reservation);
		result = timesheetLogic.getAvailableTimeSegments(machine, fullTimeRequirement);
		for(WorkingTimeSegment s:result.getAvailableTimeSegments()) {
			System.out.println(s);
		}
		
	}
	private Date add(Date date,int months,int days,int hours,int minutes) {
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		
		gc.add(GregorianCalendar.MONTH, months);
		gc.add(GregorianCalendar.DAY_OF_YEAR, days);
		gc.add(GregorianCalendar.HOUR_OF_DAY, hours);
		gc.add(GregorianCalendar.MINUTE, minutes);
		return gc.getTime();
		
	}
	public static void Cleanup() {

	}
}