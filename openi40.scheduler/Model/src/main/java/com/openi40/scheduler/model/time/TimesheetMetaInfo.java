package com.openi40.scheduler.model.time;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
public class TimesheetMetaInfo extends AbstractApsObject implements IMetaInfo{

	public TimesheetMetaInfo(ApsData context) {
		super(context);		
	}
	@Setter(value = AccessLevel.NONE)
	List<TimesheetMetaInfoWorkingTimeRule> workingTimeRules = new ArrayList<TimesheetMetaInfoWorkingTimeRule>();
	@Setter(value = AccessLevel.NONE)
	List<TimesheetMetaInfoExceptionRule> exceptionRules = new ArrayList<TimesheetMetaInfoExceptionRule>();
}
