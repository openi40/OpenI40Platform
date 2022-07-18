package com.openi40.scheduler.model.equipment;

import com.openi40.scheduler.model.AbstractApsReservableObject;
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
public class Resource extends AbstractApsReservableObject {
	private String resourcesGroupCode=null;
	public Resource(ApsData context) {
		super(context);

	}
	public String getResourcesGroupCode() {
		return resourcesGroupCode;
	}
	public void setResourcesGroupCode(String resourcesGroupCode) {
		this.resourcesGroupCode = resourcesGroupCode;
	}

}