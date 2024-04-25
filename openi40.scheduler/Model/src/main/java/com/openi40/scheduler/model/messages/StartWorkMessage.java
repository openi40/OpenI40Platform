package com.openi40.scheduler.model.messages;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
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

public class StartWorkMessage extends TaskContextMessage {
	private List<UsedSecondaryResourcesInfo> usedResources=new ArrayList<UsedSecondaryResourcesInfo>();
	public StartWorkMessage(ApsData context) {
		super(context);
		
	}
	public List<UsedSecondaryResourcesInfo> getUsedResources() {
		return usedResources;
	}
	public void setUsedResources(List<UsedSecondaryResourcesInfo> usedResources) {
		this.usedResources = usedResources;
	}

}
