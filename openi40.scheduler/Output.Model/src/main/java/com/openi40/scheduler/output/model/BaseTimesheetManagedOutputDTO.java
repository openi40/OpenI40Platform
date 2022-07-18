package com.openi40.scheduler.output.model;

import java.io.Serializable;

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
public class BaseTimesheetManagedOutputDTO extends OutputDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5358807647577542974L;
	
	private String timesheetMetaInfoCode = null;
	private boolean infiniteCapacity=false;
	
	

}
