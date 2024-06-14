package com.openi40.scheduler.engine.timesheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ATPotentialSlotRange  implements Serializable{
	List<ATSlotIndex> slots = new ArrayList<ATSlotIndex>();

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

	public List<ATSlotIndex> getSlots() {
		return slots;
	}

	public void setSlots(List<ATSlotIndex> slots) {
		this.slots = slots;
	}
}