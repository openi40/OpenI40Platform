package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetReservation;

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
 class AdvancedTimesheet extends Timesheet {
	static Logger LOGGER = LoggerFactory.getLogger(AdvancedTimesheet.class);
	long minutesTotalReserved=0l;
	static class FreeSegment {
		String SlotId = null;
		int endIndex = 0;
		int position = 0;
		int startIndex = 0;

		FreeSegment() {

		}

		FreeSegment(FreeSegment f) {
			this.SlotId = f.SlotId;
			this.startIndex = f.startIndex;
			this.endIndex = f.endIndex;
			this.position = f.position;
		}

		public String toString() {
			return "FreeSegment{[" + startIndex + "<-->" + endIndex + "],position=" + position + ",SlotId=>" + SlotId + "}";
		}
	}

	static class RangeNode {
		Slot endingSlot = null;
		Slot startingSlot = null;

	}

	static class RangesCapableMap extends TreeMap<Long, RangeNode> {

		Slot createSlot(TimesheetAvailableTimeRange ca) {
			Slot mySlot = new Slot(ca);
			if (!containsKey(mySlot.start)) {
				put(mySlot.start, new RangeNode());
			}
			if (!containsKey(mySlot.end)) {
				put(mySlot.end, new RangeNode());
			}
			RangeNode startNode = get(mySlot.start);
			RangeNode endNode = get(mySlot.end);
			startNode.startingSlot = mySlot;
			endNode.endingSlot = mySlot;
			return mySlot;
		}
	}

	static class Slot extends com.openi40.scheduler.model.time.TimeSegment {
		public Slot beforeSlot = null, afterSlot = null;
		long end = 0l;
		public List<FreeSegment> freeSegments = new ArrayList<>();
		int gridLength = 0;
		public List<TimesheetReservation> reservations = new ArrayList<TimesheetReservation>();
		long start = 0l;
		double efficiencyPercent = 100.0;

		Slot(TimesheetAvailableTimeRange tr) {
			this(tr.getStartDateTime(), tr.getEndDateTime());
			this.efficiencyPercent = tr.getEfficencyPercent();
		}

		private Slot(Date start, Date end) {
			super(TimeSegmentType.CALENDAR_TIME, null);
			setStartDateTime(DateUtil.discretize(start));
			setEndDateTime(DateUtil.discretize(end));
			this.start = DateUtil.dateTimeLongDiscreteRappresentation(start);
			this.end = DateUtil.dateTimeLongDiscreteRappresentation(end);
			int size = DateUtil.calculateDiscreteStepsNr(getStartDateTime(), getEndDateTime());
			gridLength = size;
			FreeSegment free = new FreeSegment();
			free.SlotId = id;
			free.startIndex = 0;
			free.endIndex = gridLength;
			this.freeSegments.add(free);
		}

		public FreeSegment getFreeSegmentWichContains(int index) {
			for (FreeSegment seg : freeSegments) {
				if (seg.startIndex <= index && index <= seg.endIndex)
					return new FreeSegment(seg);
			}
			return null;
		}

		public List<FreeSegment> getSegmentsFromOffset(int index, ApsLogicDirection direction) {
			List<FreeSegment> segments = new ArrayList<>();
			switch (direction) {
			case FORWARD:
				for (int i = 0; i < freeSegments.size(); i++) {
					FreeSegment segment = freeSegments.get(i);
					if (segment.endIndex >= index)
						segments.add(new FreeSegment(segment));
				}
				break;
			case BACKWARD:
				for (int i = freeSegments.size() - 1; i >= 0; i--) {
					FreeSegment segment = freeSegments.get(i);
					if (segment.startIndex <= index)
						segments.add(new FreeSegment(segment));
				}
				break;
			}
			return segments;
		}

		public String toString() {
			String v = "AdvancedCalendar.Slot{[" + DateUtil.toString(getStartDateTime()) + "<-->" + DateUtil.toString(getEndDateTime()) + "],freeSegments=" + freeSegments + "}";
			return v;
		}

		public Date getIndexedDateTime(int startIndex) {
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(StartDateTime);
			gc.add(GregorianCalendar.MINUTE, startIndex);
			return gc.getTime();
		}
	}

	@Data
	static class SlotIndex {
		Slot slot = null;
		FreeSegment usedSegment = null;
		int startIndex = 0, endIndex = 0;

		Date getStartDate() {
			if (slot.getStartDateTime() != null) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(slot.getStartDateTime());
				gc.add(GregorianCalendar.MINUTE, startIndex);
				return gc.getTime();
			}
			return null;
		}

		Date getEndDate() {
			if (slot.getStartDateTime() != null) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(slot.getStartDateTime());
				gc.add(GregorianCalendar.MINUTE, endIndex);
				return gc.getTime();
			}
			return null;
		}

		double getWorkTimeUsingEfficiency() {
			double delta = endIndex - startIndex;
			return delta * slot.efficiencyPercent / 100.0;
		}
		int getWorkTime() {
			return endIndex-startIndex;
		}
	}

	@Data
	static class PotentialSlotRange {
		List<SlotIndex> slots = new ArrayList<SlotIndex>();

		Date getStartDateTime() {
			if (slots.isEmpty())
				return null;
			return slots.get(0).getStartDate();
		}

		Date getEndDateTime() {
			if (slots.isEmpty())
				return null;
			return slots.get(slots.size() - 1).getEndDate();
		}
	}

	static class AdvancedCalendarReservation extends TimesheetReservation {
		PotentialSlotRange slotRange = null;
		TimeSegmentRequirement requirement = null;

		AdvancedCalendarReservation(TimeSegmentType type, IApsObject ownerObject, PotentialSlotRange slotRange, ITimesheetAllocableObject reservable, TimeSegmentRequirement req) {
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

	List<TimesheetAvailableTimeRange> availableTimeSlots = null;

	RangesCapableMap rangesMap = new RangesCapableMap();

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
		Collection<RangeNode> slots = rangesMap.values();
		for (RangeNode rangeNode : slots) {
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
		Slot lastSlot = null;
		for (TimesheetAvailableTimeRange timesheetAvailableTimeRange : availableTimeSlots) {
			Slot thisSlot = rangesMap.createSlot(timesheetAvailableTimeRange);
			thisSlot.beforeSlot = lastSlot;
			if (lastSlot != null)
				lastSlot.afterSlot = thisSlot;
			lastSlot = thisSlot;
		}
	}

	Slot getContainingOrNearestSlot(Date date, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled())
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Begin getContainingOrNearestSlot(" + date + "," + direction + ",...)");
		long _dateTime = DateUtil.dateTimeLongDiscreteRappresentation(date);
		if (rangesMap.containsKey(_dateTime)) {
			if (LOGGER.isDebugEnabled())
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Date exactly fits a margin");
			RangeNode node = this.rangesMap.get(_dateTime);
			if (node.startingSlot != null) {
				return node.startingSlot;
			}
			if (node.endingSlot != null) {
				return node.endingSlot;
			}
		}
		Slot foundSlot = null;
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
					RangeNode item = this.rangesMap.get(key);
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
					RangeNode item = this.rangesMap.get(key);
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
