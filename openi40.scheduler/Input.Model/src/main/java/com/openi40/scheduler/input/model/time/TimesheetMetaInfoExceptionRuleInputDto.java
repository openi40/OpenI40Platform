package com.openi40.scheduler.input.model.time;

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
 * @author architectures@openi40.org
 *
 */

@MappedSuperclass
public class TimesheetMetaInfoExceptionRuleInputDto extends InputDto {
	private Date startPeriod = null, endPeriod = null;
	private boolean working = false;
	private String timesheetMetaCode = null;
	public Date getStartPeriod() {
		return startPeriod;
	}
	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}
	public Date getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}
	public boolean isWorking() {
		return working;
	}
	public void setWorking(boolean working) {
		this.working = working;
	}
	public String getTimesheetMetaCode() {
		return timesheetMetaCode;
	}
	public void setTimesheetMetaCode(String timesheetMetaCode) {
		this.timesheetMetaCode = timesheetMetaCode;
	}


}
