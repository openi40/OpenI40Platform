package com.openi40.scheduler.engine.timesheet;

import java.util.Date;
import java.util.GregorianCalendar;

class ATSlotIndex {
	ATSlot slot = null;
	ATFreeSegment usedSegment = null;
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

	public ATSlot getSlot() {
		return slot;
	}

	public void setSlot(ATSlot slot) {
		this.slot = slot;
	}

	public ATFreeSegment getUsedSegment() {
		return usedSegment;
	}

	public void setUsedSegment(ATFreeSegment usedSegment) {
		this.usedSegment = usedSegment;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}