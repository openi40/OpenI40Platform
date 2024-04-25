package com.openi40.scheduler.engine.timesheet;

class ATFreeSegment {
	String SlotId = null;
	int endIndex = 0;
	int position = 0;
	int startIndex = 0;

	ATFreeSegment() {

	}

	ATFreeSegment(ATFreeSegment f) {
		this.SlotId = f.SlotId;
		this.startIndex = f.startIndex;
		this.endIndex = f.endIndex;
		this.position = f.position;
	}

	public String toString() {
		return "FreeSegment{[" + startIndex + "<-->" + endIndex + "],position=" + position + ",SlotId=>" + SlotId + "}";
	}
}