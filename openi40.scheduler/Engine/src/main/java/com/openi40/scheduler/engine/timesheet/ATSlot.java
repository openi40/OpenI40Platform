package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetReservation;

class ATSlot extends com.openi40.scheduler.model.time.TimeSegment {
	public ATSlot beforeSlot = null, afterSlot = null;
	long end = 0l;
	public List<ATFreeSegment> freeSegments = new ArrayList<>();
	int gridLength = 0;
	public List<TimesheetReservation> reservations = new ArrayList<TimesheetReservation>();
	long start = 0l;
	double efficiencyPercent = 100.0;

	ATSlot(TimesheetAvailableTimeRange tr) {
		this(tr.getStartDateTime(), tr.getEndDateTime());
		this.efficiencyPercent = tr.getEfficencyPercent();
	}

	private ATSlot(Date start, Date end) {
		super(TimeSegmentType.CALENDAR_TIME, null);
		setStartDateTime(DateUtil.discretize(start));
		setEndDateTime(DateUtil.discretize(end));
		this.start = DateUtil.dateTimeLongDiscreteRappresentation(start);
		this.end = DateUtil.dateTimeLongDiscreteRappresentation(end);
		int size = DateUtil.calculateDiscreteStepsNr(getStartDateTime(), getEndDateTime());
		gridLength = size;
		ATFreeSegment free = new ATFreeSegment();
		free.SlotId = id;
		free.startIndex = 0;
		free.endIndex = gridLength;
		this.freeSegments.add(free);
	}

	public ATFreeSegment getFreeSegmentWichContains(int index) {
		for (ATFreeSegment seg : freeSegments) {
			if (seg.startIndex <= index && index <= seg.endIndex)
				return new ATFreeSegment(seg);
		}
		return null;
	}

	public List<ATFreeSegment> getSegmentsFromOffset(int index, ApsLogicDirection direction) {
		List<ATFreeSegment> segments = new ArrayList<>();
		switch (direction) {
		case FORWARD:
			for (int i = 0; i < freeSegments.size(); i++) {
				ATFreeSegment segment = freeSegments.get(i);
				if (segment.endIndex >= index)
					segments.add(new ATFreeSegment(segment));
			}
			break;
		case BACKWARD:
			for (int i = freeSegments.size() - 1; i >= 0; i--) {
				ATFreeSegment segment = freeSegments.get(i);
				if (segment.startIndex <= index)
					segments.add(new ATFreeSegment(segment));
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