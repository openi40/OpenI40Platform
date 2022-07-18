package com.openi40.scheduler.outputchannels.streamoutputs.config;

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
public class EntityOutputSetting {
	private boolean relativePath = true;
	private boolean noContent=false;
	private String entityName = null;
	private int nEntriesGrouping=-1;
	private String path = null;
}
