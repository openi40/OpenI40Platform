package com.openi40.dbmodel.entities;

import javax.persistence.MappedSuperclass;
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

public class OI40DBBaseTimesheetManaged extends OI40DBBaseEntity {
	private String timesheetMetaInfoCode = null;
	private Boolean infiniteCapacity=false;
	public OI40DBBaseTimesheetManaged() {
		
	}
	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}
	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}
	public Boolean getInfiniteCapacity() {
		return infiniteCapacity;
	}
	public void setInfiniteCapacity(Boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

}
