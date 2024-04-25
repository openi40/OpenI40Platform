package com.openi40.scheduler.input.model.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

public class TimesheetMetaInfoInputDto extends InputDto implements Serializable {
	protected List<TimesheetMetaInfoWorkingTimeRuleInputDto> workingTimeRules = new ArrayList<TimesheetMetaInfoWorkingTimeRuleInputDto>();
	protected List<TimesheetMetaInfoExceptionRuleInputDto> exceptionRules = new ArrayList<TimesheetMetaInfoExceptionRuleInputDto>();
	public List<TimesheetMetaInfoWorkingTimeRuleInputDto> getWorkingTimeRules() {
		return workingTimeRules;
	}
	public void setWorkingTimeRules(List<TimesheetMetaInfoWorkingTimeRuleInputDto> workingTimeRules) {
		this.workingTimeRules = workingTimeRules;
	}
	public List<TimesheetMetaInfoExceptionRuleInputDto> getExceptionRules() {
		return exceptionRules;
	}
	public void setExceptionRules(List<TimesheetMetaInfoExceptionRuleInputDto> exceptionRules) {
		this.exceptionRules = exceptionRules;
	}
}
