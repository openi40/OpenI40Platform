package com.openi40.scheduler.model.time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.CollectionUtil;
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
public class TimeSegmentsGroup extends TimeSegment {

	public TimeSegmentsGroup(TimeSegmentType type, IApsObject ownerObject) {
		super(type, ownerObject);
	}

	private List<TimeSegment> calendarEvents = new ArrayList<TimeSegment>();

	public final void Add(TimeSegment reservation) {
		calendarEvents.add(reservation);
		Update();
	}

	public final void Remove(TimeSegment reservation) {
		calendarEvents.remove(reservation);
		Update();
	}

	public final void Clear() {
		calendarEvents.clear();
		StartDateTime = null;
		EndDateTime = null;
	}

	private static boolean IsFiltered(TimeSegment calendarEvent, TimeSegmentType type, IApsObject owner) {
		boolean selected = type == null && owner == null;
		if (type != null && owner != null && calendarEvent.getOwnerObject() != null) {
			selected = calendarEvent.getType().equals(type) && calendarEvent.getOwnerObject().equals(owner);
		} else if (type != null) {
			selected = calendarEvent.getType().equals(type);
		} else if (owner != null && calendarEvent.getOwnerObject() != null) {
			selected = calendarEvent.getOwnerObject().equals(owner);
		}

		return selected;
	}

	public final List<TimeSegment> FindByTypeAndOwner(TimeSegmentType type, IApsObject owner) {
		List<TimeSegment> outRanges = new ArrayList<TimeSegment>();
		if (IsFiltered(this, type, owner)) {
			outRanges.add(this);
		}
		for (TimeSegment calendarEvent : calendarEvents) {

			if (IsFiltered(calendarEvent, type, owner)) {
				outRanges.add(calendarEvent);
			}
			boolean tempVar = calendarEvent instanceof TimeSegmentsGroup;
			TimeSegmentsGroup group = tempVar ? (TimeSegmentsGroup) calendarEvent : null;
			if (tempVar) {
				CollectionUtil.getInstance().AddCollection(outRanges, group.FindByTypeAndOwner(type, owner));
			}
		}

		return OrderByStartDate(outRanges);
	}

	public static List<TimeSegment> OrderByStartDate(List<TimeSegment> ranges) {
		List<TimeSegment> outr = new ArrayList<TimeSegment>();
		TreeMap<Date, List<TimeSegment>> sd = new TreeMap<Date, List<TimeSegment>>();
		for (TimeSegment trs : ranges) {
			if (trs.getStartDateTime() != null) {
				if (!sd.containsKey(trs.getStartDateTime())) {
					sd.put(trs.getStartDateTime(), new ArrayList<TimeSegment>());
				}
				List<TimeSegment> entry = sd.get(trs.getStartDateTime());
				entry.add(trs);
			} else if (!outr.contains(trs)) {
				outr.add(trs);
			}
		}

		for (Map.Entry<Date, List<TimeSegment>> dictionaryEntry : sd.entrySet()) {

			for (TimeSegment v : dictionaryEntry.getValue()) {
				if (!outr.contains(v)) {
					outr.add(v);
				}
			}
		}
		return outr;
	}

	public final List<TimeSegment> FindByType(TimeSegmentType type) {
		return FindByTypeAndOwner(type, null);
	}

	public final List<TimeSegment> FindByOwner(IApsObject objectOwner) {
		return FindByTypeAndOwner(null, objectOwner);
	}

	public final List<TimeSegment> getCalendarEvents() {
		return new ArrayList<TimeSegment>(calendarEvents);
	}

	public final void Update() {
		Date minBound = INFINITEDATE, maxBound = ZERODATE;
		for (TimeSegment timeSegment : calendarEvents) {
			if (timeSegment instanceof TimeSegmentsGroup) {
				TimeSegmentsGroup cg = (TimeSegmentsGroup) timeSegment;
				cg.Update();
			}
			if (timeSegment.isValid()) {
				minBound = minBound.compareTo(timeSegment.getStartDateTime()) > 0 ? timeSegment.getStartDateTime() : minBound;
				maxBound = maxBound.compareTo(timeSegment.getEndDateTime()) < 0 ? timeSegment.getEndDateTime() : maxBound;
			}
		}
		StartDateTime = minBound.compareTo(INFINITEDATE) == 0 ? null : minBound;
		EndDateTime = maxBound.compareTo(ZERODATE) == 0 ? null : maxBound;
	}

	@Override
	public Date getStartDateTime() {
		return getMinStartDateTime();
	}

	static final Date INFINITEDATE = new Date(Long.MAX_VALUE);

	private Date getMinStartDateTime() {
		Date minBound = INFINITEDATE;
		for (TimeSegment timeSegment : calendarEvents) {
			if (timeSegment.isLowerLimited()) {
				minBound = minBound.compareTo(timeSegment.getStartDateTime()) > 0 ? timeSegment.getStartDateTime() : minBound;
			}
		}

		return StartDateTime = minBound.compareTo(INFINITEDATE) == 0 ? null : minBound;
	}

	static final Date ZERODATE = new Date(0l);

	private Date getMaxEndDateTime() {
		Date maxBound = new Date(0l);
		for (TimeSegment timeSegment : calendarEvents) {

			if (timeSegment.isUpperLimited()) {
				maxBound = maxBound.compareTo(timeSegment.getEndDateTime()) < 0 ? timeSegment.getEndDateTime() : maxBound;
			}
		}

		return EndDateTime = maxBound.compareTo(ZERODATE) == 0 ? null : maxBound;
	}

	@Override
	public Date getEndDateTime() {
		return getMaxEndDateTime();
	}
	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		
	}
	@Override
	public Object clone() {
		TimeSegmentsGroup ceg = new TimeSegmentsGroup(getType(), getOwnerObject());
		ceg.id = id;
		for (TimeSegment Event : calendarEvents) {
			TimeSegment r = (TimeSegment) Event.clone();
			ceg.Add(r);
		}

		return ceg;
	}

	public static TimeSegmentsGroup Intersection(List<TimeSegmentsGroup> calendarEvents) {
		return null;
	}
}