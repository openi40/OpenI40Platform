package com.openi40.scheduler.model.time;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.AbstractApsObject;
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
public class TimeSegment extends AbstractApsObject implements Cloneable, ITimeSegment {

	private TimeSegmentType Type = TimeSegmentType.values()[0];

	public final TimeSegmentType getType() {
		return Type;
	}

	private IApsObject OwnerObject = null;

	public final IApsObject getOwnerObject() {
		return OwnerObject;
	}

	public final void setOwnerObject(IApsObject value) {
		OwnerObject = value;
	}

	public TimeSegment(TimeSegment tr) {
		super(null);
		setStartDateTime(tr.getStartDateTime());
		setEndDateTime(tr.getEndDateTime());
		Type = tr.getType();
		this.setOwnerObject(tr.getOwnerObject());

	}

	public TimeSegment(TimeSegment tr, TimeSegmentType type, IApsObject ownerObject) {
		super(null);
		setStartDateTime(tr.getStartDateTime());
		setEndDateTime(tr.getEndDateTime());
		Type = type;
		this.setOwnerObject(ownerObject);

	}

	public TimeSegment(TimeSegmentType type, IApsObject ownerObject) {
		super(null);
		Type = type;
		setOwnerObject(ownerObject);
	}

	protected Date StartDateTime = null;

	public Date getStartDateTime() {
		return StartDateTime;
	}

	public void setStartDateTime(Date value) {
		StartDateTime = value;
	}

	protected Date EndDateTime = null;

	public Date getEndDateTime() {
		return EndDateTime;
	}

	public void setEndDateTime(Date value) {
		EndDateTime = value;
	}

	public boolean isValid() {
		return getStartDateTime() != null && getEndDateTime() != null
				&& getEndDateTime().compareTo(getStartDateTime()) >= 0;
	}

	public boolean isInRange(Date dateTime) {
		boolean inRange = false;
		if (isValid()) {
			inRange = getStartDateTime().compareTo(dateTime) <= 0 && getEndDateTime().compareTo(dateTime) >= 0;
		} else if (isUpperLimited()) {
			inRange = getEndDateTime().compareTo(dateTime) >= 0;
		} else if (isLowerLimited()) {
			inRange = getStartDateTime().compareTo(dateTime) <= 0;
		}

		return inRange;
	}

	public boolean isInRange(ITimeSegment range) {
		boolean inRange = false;
		if (range.isValid() && isValid()) {
			inRange = isInRange(range.getStartDateTime()) && isInRange(range.getEndDateTime());
		} else if (range.isLowerLimited() && isLowerLimited() && !isUpperLimited()) {
			inRange = isInRange(range.getStartDateTime());
		} else if (range.isUpperLimited() && isUpperLimited() && !isLowerLimited()) {
			inRange = isInRange(range.getEndDateTime());
		}

		return inRange;
	}

	public boolean isContiguous(ITimeSegment range) {
		boolean contiguous = range.isLowerLimited() && isUpperLimited()
				&& getEndDateTime().compareTo(range.getStartDateTime()) == 0;
		contiguous = contiguous || (isLowerLimited() && range.isUpperLimited()
				&& range.getEndDateTime().compareTo(getStartDateTime()) == 0);
		return contiguous;
	}

	public static <Tr1 extends TimeSegment, Tr2 extends TimeSegment> boolean IsContiguous(Tr1 range, List<Tr2> ranges) {
		boolean rv = false;
		for (Tr2 r : ranges) {
			if (r != range) {
				rv = rv || r.isContiguous(range);
			}
		}
		return rv;
	}

	public static <Tr1 extends TimeSegment, Tr2 extends TimeSegment> boolean IsOverlapping(Tr1 range,
			List<Tr2> ranges) {
		boolean rv = false;
		for (Tr2 r : ranges) {
			if (r != range) {
				rv = rv || r.IsOverlapping(range);
			}
		}
		return rv;
	}

	public boolean IsOverlapping(ITimeSegment range) {
		if (isContiguous(range)) {
			return false;
		}
		boolean isOverlapping = isInRange(range) || range.isInRange(this);
		// Particular case of non overlapping => when ranges have Superior and Inferior
		// Bounds equals each other
		isOverlapping = isOverlapping || (range.isLowerLimited() && isInRange(range.getStartDateTime()));
		isOverlapping = isOverlapping || (range.isUpperLimited() && isInRange(range.getEndDateTime()));
		isOverlapping = isOverlapping || (isLowerLimited() && range.isInRange(getStartDateTime()));
		isOverlapping = isOverlapping || (isUpperLimited() && range.isInRange(getEndDateTime()));
		if (isOverlapping) {
			return true;
		}
		if ((isLowerLimited() && range.isUpperLimited() && getStartDateTime().compareTo(range.getEndDateTime()) >= 0)
				|| (isUpperLimited() && range.isLowerLimited() && getEndDateTime().equals(range.getStartDateTime()))) {
			return false;
		}

		if (!isOverlapping && isLowUpLimited()) {
			isOverlapping = isOverlapping || (range.isLowerLimited() && isInRange(range.getStartDateTime()))
					|| (range.isUpperLimited() && isInRange(range.getEndDateTime()));
		}
		if (!isOverlapping && isLowerLimited() && !isUpperLimited()) {
			isOverlapping = isOverlapping || range.isInRange(getStartDateTime());

		}
		if (!isOverlapping && !isLowerLimited() && isUpperLimited()) {
			isOverlapping = isOverlapping || range.isInRange(getEndDateTime());
		}
		if (!isOverlapping && !isUpperLimited() && !isLowerLimited()) {
			isOverlapping = true;
		}
		return isOverlapping;
	}

	public boolean equals(ITimeSegment range) {
		boolean e = false;
		e = isValid() && range.isValid() && getStartDateTime().equals(range.getStartDateTime())
				&& getEndDateTime().equals(range.getEndDateTime());
		return e;
	}

	public static TimeSegment Intersection(List<TimeSegment> range, TimeSegmentType resultType,
			IApsObject objectOwner) {
		TimeSegment tr = new TimeSegment(resultType, objectOwner);
		Date minDateTime = null;
		Date maxDateTime = null;
		for (TimeSegment r : range) {
			if (minDateTime == null) {
				minDateTime = r.getStartDateTime();
			} else if (r.isLowerLimited()) {
				minDateTime = minDateTime.compareTo(r.getStartDateTime()) > 0 ? minDateTime : r.getStartDateTime();
			}

			if (maxDateTime == null) {
				maxDateTime = r.getEndDateTime();
			} else if (r.isUpperLimited()) {
				maxDateTime = maxDateTime.compareTo(r.getEndDateTime()) > 0 ? maxDateTime : r.getEndDateTime();
			}
		}

		tr.setStartDateTime(minDateTime);
		tr.setEndDateTime(maxDateTime);
		return tr;
	}

	public Object clone() {
		TimeSegment tr = new TimeSegment(this, getType(), getOwnerObject());
		tr.id = id;
		tr.setOwnerObject(getOwnerObject());
		return tr;
	}

	public final boolean isLowerLimited() {
		return getStartDateTime() != null;
	}

	public final boolean isUpperLimited() {
		return getEndDateTime() != null;
	}

	public final boolean isLowUpLimited() {
		return isLowerLimited() && isUpperLimited() && isValid();
	}

	public final boolean IsSameLimits(ITimeSegment tr) {

		return (AreEqual(tr.getStartDateTime(), getStartDateTime()) && AreEqual(tr.getEndDateTime(), getEndDateTime()));
	}

	private boolean AreEqual(Date startDateTime1, Date startDateTime2) {
		if (startDateTime1 == null && startDateTime2 == null) {
			return true;
		}
		return startDateTime1 != null && startDateTime2 != null ? startDateTime1.equals(startDateTime2) : false;
	}

	public double getDurationMinutes() {
		double duration = -1;
		if (isValid()) {
			duration = getStartDateTime().toInstant().until(getEndDateTime().toInstant(), ChronoUnit.MINUTES);

		}
		return duration;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof TimeSegment) {
			TimeSegment comparison = (TimeSegment) obj;
			boolean _eq = comparison.IsSameLimits(this);
			_eq = _eq && comparison.Type == this.Type;
			_eq = _eq && (code == null && comparison.code == null
					|| (code != null && comparison.code != null && code.equals(comparison.code)));
			_eq = _eq && (description == null && comparison.description == null || (description != null
					&& comparison.description != null && description.equals(comparison.description)));
			_eq = _eq && OwnerObject == comparison.OwnerObject;
			return _eq;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		String v = "TimeSegment{[";
		v += DateUtil.toString(getStartDateTime());
		v += "<-->";
		v += DateUtil.toString(getEndDateTime());
		v += "]";
		v += "Type=" + getType() + "}";

		return v;
	}
}