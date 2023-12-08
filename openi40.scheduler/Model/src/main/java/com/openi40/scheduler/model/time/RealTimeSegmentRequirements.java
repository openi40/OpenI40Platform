package com.openi40.scheduler.model.time;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;


public class RealTimeSegmentRequirements extends AbstractApsObject {
	public RealTimeSegmentRequirements(ApsData context) {
		super(context);
	}

	boolean invalidTaskConditions = false;
	List<String> invalidTaskConditionsMessages = new ArrayList<String>();
	TimeSegmentRequirement preparationRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
	TimeSegmentRequirement executionRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
	public boolean isInvalidTaskConditions() {
		return invalidTaskConditions;
	}
	public void setInvalidTaskConditions(boolean invalidTaskConditions) {
		this.invalidTaskConditions = invalidTaskConditions;
	}
	public List<String> getInvalidTaskConditionsMessages() {
		return invalidTaskConditionsMessages;
	}
	public void setInvalidTaskConditionsMessages(List<String> invalidTaskConditionsMessages) {
		this.invalidTaskConditionsMessages = invalidTaskConditionsMessages;
	}
	public TimeSegmentRequirement getPreparationRequirement() {
		return preparationRequirement;
	}
	public void setPreparationRequirement(TimeSegmentRequirement preparationRequirement) {
		this.preparationRequirement = preparationRequirement;
	}
	public TimeSegmentRequirement getExecutionRequirement() {
		return executionRequirement;
	}
	public void setExecutionRequirement(TimeSegmentRequirement executionRequirement) {
		this.executionRequirement = executionRequirement;
	}
}
