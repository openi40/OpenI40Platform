package com.openi40.scheduler.output.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class OutputDto implements Serializable {
	protected String code = null;
	protected String description = null;
	protected Date modifiedTimestamp = null;
	protected Map<String, Object> attributesMap = new HashMap<>();
	protected boolean deleted = false;

}
