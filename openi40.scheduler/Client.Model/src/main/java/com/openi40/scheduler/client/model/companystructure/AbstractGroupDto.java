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
package com.openi40.scheduler.client.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;


public class AbstractGroupDto<ResourceType extends ClientDto> extends ClientDto {
	protected List<ResourceType> resources = new ArrayList<ResourceType>();

	public List<ResourceType> getResources() {
		return resources;
	}

	public void setResources(List<ResourceType> resources) {
		this.resources = resources;
	}	

}
