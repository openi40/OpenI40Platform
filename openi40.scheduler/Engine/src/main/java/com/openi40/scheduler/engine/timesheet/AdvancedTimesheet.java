package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
 class AdvancedTimesheet extends Timesheet {
	static Logger LOGGER = LoggerFactory.getLogger(AdvancedTimesheet.class);
	long minutesTotalReserved=0l;
	List<TimesheetAvailableTimeRange> availableTimeSlots = null;

	ATRangesCapableMap rangesMap = new ATRangesCapableMap();

	public AdvancedTimesheet(ApsData context, List<TimesheetAvailableTimeRange> timeSlots) {
		super(context);
		availableTimeSlots = timeSlots;
		initializeSlots();

	}
	List<TimesheetReservation> infiniteCapacityReservations=new ArrayList<TimesheetReservation>();
	void addInfiniteCapacityReservation(TimesheetReservation res) {
		this.infiniteCapacityReservations.add(res);
	}
	List<TimesheetReservation> getReservations(ITimesheetAllocableObject reservable) {
		if (reservable.isInfiniteCapacity()) 
			return this.infiniteCapacityReservations;
		List<TimesheetReservation> cr = new ArrayList<>();
		Collection<ATRangeNode> slots = rangesMap.values();
		for (ATRangeNode rangeNode : slots) {
			if (rangeNode.startingSlot != null) {
				for (TimesheetReservation reserv : rangeNode.startingSlot.reservations) {
					if (reserv.getReservedObject() == reservable) {
						if (!cr.contains(reserv))
						cr.add(reserv);
					}
				}
			}
		}
		return cr;
	}

	List<TimesheetReservation> getReservations(ITimesheetAllocableObject ewcalendar, TimeSegmentRequirement range) {
		return null;
	}

	private void initializeSlots() {
		ATSlot lastSlot = null;
		for (TimesheetAvailableTimeRange timesheetAvailableTimeRange : availableTimeSlots) {
			ATSlot thisSlot = rangesMap.createSlot(timesheetAvailableTimeRange);
			thisSlot.beforeSlot = lastSlot;
			if (lastSlot != null)
				lastSlot.afterSlot = thisSlot;
			lastSlot = thisSlot;
		}
	}

	ATSlot getContainingOrNearestSlot(Date date, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled())
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Begin getContainingOrNearestSlot(" + date + "," + direction + ",...)");
		long _dateTime = DateUtil.dateTimeLongDiscreteRappresentation(date);
		if (rangesMap.containsKey(_dateTime)) {
			if (LOGGER.isDebugEnabled())
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Date exactly fits a margin");
			ATRangeNode node = this.rangesMap.get(_dateTime);
			if (node.startingSlot != null) {
				return node.startingSlot;
			}
			if (node.endingSlot != null) {
				return node.endingSlot;
			}
		}
		ATSlot foundSlot = null;
		switch (direction) {
		case FORWARD: {
			boolean go = true;
			Long key = this.rangesMap.floorKey(_dateTime);
			int nLoop = 0;
			while (foundSlot == null && go) {

				if (key == null)
					key = this.rangesMap.ceilingKey(_dateTime);
				if (LOGGER.isDebugEnabled())
					if (LOGGER.isDebugEnabled()) LOGGER.debug("search " + direction + " key=" + key);
				if (key != null) {
					ATRangeNode item = this.rangesMap.get(key);
					if (item.startingSlot != null) {
						long endDateTime = DateUtil.dateTimeLongRappresentation(item.startingSlot.getEndDateTime());
						if (endDateTime >= _dateTime) {
							foundSlot = item.startingSlot;
						}
					}
					if (foundSlot == null) {
						key = this.rangesMap.ceilingKey(_dateTime);
						if (key != null) {
							_dateTime = key + 1;
						}
					}
				}
				go = key != null;
			}
		}
			break;
		case BACKWARD: {
			boolean go = true;
			Long key = this.rangesMap.ceilingKey(_dateTime);
			int nLoop = 0;
			while (foundSlot == null && go) {

				if (key == null)
					key = this.rangesMap.floorKey(_dateTime);
				if (LOGGER.isDebugEnabled())
					if (LOGGER.isDebugEnabled()) LOGGER.debug("search " + direction + " key=" + key);
				if (key != null) {
					ATRangeNode item = this.rangesMap.get(key);
					if (item.endingSlot != null) {
						long startDateTime = DateUtil.dateTimeLongRappresentation(item.endingSlot.getStartDateTime());
						if (startDateTime <= _dateTime) {
							foundSlot = item.endingSlot;
						}
					}
					if (foundSlot == null) {
						key = this.rangesMap.floorKey(_dateTime);
						if (key != null) {
							_dateTime = key + 1;
						}
					}
				}
				go = key != null;
			}
		}
			break;
		}
		if (LOGGER.isDebugEnabled())
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Return getContainingOrNearestSlot(" + date + "," + direction + ",...)=>" + foundSlot);
		return foundSlot;
	}

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		this.minutesTotalReserved=0l;
		rangesMap.clear();
		initializeSlots();
	}

}
