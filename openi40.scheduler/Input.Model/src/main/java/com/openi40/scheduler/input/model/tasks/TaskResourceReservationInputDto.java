package com.openi40.scheduler.input.model.tasks;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;
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
@MappedSuperclass

public class TaskResourceReservationInputDto extends InputDto {
	String taskCode=null;
	String resourceCode = null;
	String secondaryResourceGroupCode = null;
	String slotType = null;
	Date start = null;
	Date end = null;
	public TaskResourceReservationInputDto() {
		
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getSecondaryResourceGroupCode() {
		return secondaryResourceGroupCode;
	}
	public void setSecondaryResourceGroupCode(String secondaryResourceGroupCode) {
		this.secondaryResourceGroupCode = secondaryResourceGroupCode;
	}
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

}
