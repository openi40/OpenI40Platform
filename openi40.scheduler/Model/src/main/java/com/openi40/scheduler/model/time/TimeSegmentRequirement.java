package com.openi40.scheduler.model.time;

import org.springframework.web.bind.annotation.RequestMethod;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.IValidable;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 * Rappresent a time range requirement to be used as various arguments in method
 * and data structures, can specify a upper and/or limited time interval with
 * alignment specification.
 */
public class TimeSegmentRequirement extends TimeSegment implements IValidable {
	public TimeSegmentRequirement(TimeSegmentRequirement requirement) {
		super(requirement, requirement.getType(), requirement.getOwnerObject());
		this.startAlignment = requirement.getStartAlignment();
		this.endAlignment = requirement.getEndAlignment();
		setAvailabilityDuration(requirement.getAvailabilityDuration());
	}

	public TimeSegmentRequirement(TimeSegmentType type, StartDateTimeAlignment startAlignment,
			EndDateTimeAlignment endAlignment) {
		super(type, null);
		this.startAlignment = startAlignment;
		this.endAlignment = endAlignment;
	}

	public TimeSegmentRequirement(TimeSegmentType type) {
		super(type, null);
		// startAlignment=StartDateTimeAlignment.ALIGN_START_ASAP;
		// endAlignment=EndDateTimeAlignment.ALIGN_END_ASAP;
	}

	public TimeSegmentRequirement(TimeSegmentType type, TimeSegment toCopy) {
		super(toCopy, type, null);
		startAlignment = StartDateTimeAlignment.START_ON_START_PRECISELY;
		endAlignment = EndDateTimeAlignment.END_ON_END_PRECISELY;
	}

	public TimeSegmentRequirement(TimeSegment toCopy) {
		super(toCopy, toCopy.getType(), null);
		// startAlignment=StartDateTimeAlignment.ALIGN_START_ASAP;
		// endAlignment=EndDateTimeAlignment.ALIGN_END_ASAP;
	}

	private EndDateTimeAlignment endAlignment = null;
	private StartDateTimeAlignment startAlignment = null;

	private double AvailabilityDuration = 0L;

	public double getAvailabilityDuration() {
		return AvailabilityDuration;
	}

	public void setAvailabilityDuration(double value) {
		AvailabilityDuration = value;
	}

	@Override
	public Object clone() {
		return new TimeSegmentRequirement(this);
	}

	public boolean equals(TimeSegmentRequirement range) {
		boolean _superEq = super.equals(range);
		return _superEq && startAlignment == range.startAlignment && endAlignment == range.endAlignment
				&& AvailabilityDuration == range.AvailabilityDuration;
	}
	
	@Override
	public String toString() {
		String v = "TimeRangeRequirement{[";
		v += DateUtil.toString(getStartDateTime());
		v += "<-->";
		v += DateUtil.toString(getEndDateTime());
		v += "],";
		v += "Type=" + getType();
		v += ",";
		v += "startAlignment=" + getStartAlignment();
		v += ",";
		v += "endAlignment=" + getEndAlignment();
		v += ",";
		v += "AvailabilityDuration=" + getAvailabilityDuration();
		v += "}";
		return v;
	}

	public EndDateTimeAlignment getEndAlignment() {
		return endAlignment;
	}

	public void setEndAlignment(EndDateTimeAlignment endAlignment) {
		this.endAlignment = endAlignment;
	}

	public StartDateTimeAlignment getStartAlignment() {
		return startAlignment;
	}

	public void setStartAlignment(StartDateTimeAlignment startAlignment) {
		this.startAlignment = startAlignment;
	}

	@Override
	public boolean isValidState() {
		return (AvailabilityDuration > 0.0) ? (isLowerLimited() || isUpperLimited())
				: (isUpperLimited() && isLowerLimited());
	}

}