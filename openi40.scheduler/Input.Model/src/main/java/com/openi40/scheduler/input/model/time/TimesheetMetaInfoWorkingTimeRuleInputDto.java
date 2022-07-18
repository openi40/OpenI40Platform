package com.openi40.scheduler.input.model.time;

import java.sql.Time;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openi40.scheduler.input.model.InputDto;

import lombok.Data;
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
public class TimesheetMetaInfoWorkingTimeRuleInputDto extends InputDto {
	
	private Time startTime = null;
	
	private Time endTime = null;
	private int dayOfWeek=-1;
	private String timesheetMetaCode=null;
	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss.SSSZ")
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		if (startTime!=null) {
			startTime=new Time(startTime.getTime());
		}
		this.startTime = startTime;
	}
	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss.SSSZ")
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		if (endTime!=null) {
			endTime=new Time(endTime.getHours(),endTime.getMinutes(),endTime.getSeconds());
		}
		this.endTime = endTime;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTimesheetMetaCode() {
		return timesheetMetaCode;
	}
	public void setTimesheetMetaCode(String timesheetMetaCode) {
		this.timesheetMetaCode = timesheetMetaCode;
	}
	
}
