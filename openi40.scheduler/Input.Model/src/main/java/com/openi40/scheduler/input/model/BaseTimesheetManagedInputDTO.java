package com.openi40.scheduler.input.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@MappedSuperclass
public class BaseTimesheetManagedInputDTO extends InputDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5358807647577542974L;

	private String timesheetMetaInfoCode = null;
	
	private Boolean infiniteCapacity = false;

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = TimesheetMetaInfoInputDto.class, nullable = true)
	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}

	public void setTimesheetMetaInfoCode(String calendarCode) {
		this.timesheetMetaInfoCode = calendarCode;
	}

	public Boolean getInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(Boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity != null && infiniteCapacity ? true : false;
	}

	

}
