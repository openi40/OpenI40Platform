package com.openi40.scheduler.model.time;

import java.sql.Time;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

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
@Data
public class TimesheetMetaInfoWorkingTimeRule  extends AbstractApsObject{
	public TimesheetMetaInfoWorkingTimeRule(ApsData context) {
		super(context);		
	}
	private Time startTime = null;
	private Time endTime = null;
	private int dayOfWeek=-1;

}
