package com.openi40.scheduler.model.time;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;

@Data
public class RealTimeSegmentRequirements extends AbstractApsObject {
	public RealTimeSegmentRequirements(ApsData context) {
		super(context);
	}

	boolean invalidTaskConditions = false;
	List<String> invalidTaskConditionsMessages = new ArrayList<String>();
	TimeSegmentRequirement preparationRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
	TimeSegmentRequirement executionRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
}
