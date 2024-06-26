package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "tsheet_meta_working_time_rule")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "dayOfWeek", column = @Column(name = "day_of_week")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "endTime", column = @Column(name = "end_time")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "startTime", column = @Column(name = "start_time")),
		@AttributeOverride(name = "timesheetMetaCode", column = @Column(name = "tsheet_meta_code")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })

public class OI40DBTimesheetMetaInfoWorkingTimeRule extends OI40DBBaseEntity implements Serializable {
	private Time startTime = null;
	private Time endTime = null;
	private Integer dayOfWeek = null;
	private String timesheetMetaCode = null;
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTimesheetMetaCode() {
		return timesheetMetaCode;
	}
	public void setTimesheetMetaCode(String timesheetMetaCode) {
		this.timesheetMetaCode = timesheetMetaCode;
	}

}
