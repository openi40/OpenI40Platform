package com.openi40.scheduler.engine.timesheet;

import java.util.TreeMap;

import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;

class ATRangesCapableMap extends TreeMap<Long, ATRangeNode> {

	ATSlot createSlot(TimesheetAvailableTimeRange ca) {
		ATSlot mySlot = new ATSlot(ca);
		if (!containsKey(mySlot.start)) {
			put(mySlot.start, new ATRangeNode());
		}
		if (!containsKey(mySlot.end)) {
			put(mySlot.end, new ATRangeNode());
		}
		ATRangeNode startNode = get(mySlot.start);
		ATRangeNode endNode = get(mySlot.end);
		startNode.startingSlot = mySlot;
		endNode.endingSlot = mySlot;
		return mySlot;
	}
}