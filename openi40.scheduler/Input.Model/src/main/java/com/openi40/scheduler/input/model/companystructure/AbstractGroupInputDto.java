package com.openi40.scheduler.input.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.input.model.BaseTimesheetManagedInputDTO;
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
@MappedSuperclass
public class AbstractGroupInputDto<ResourceType extends InputDto> extends BaseTimesheetManagedInputDTO {
	
	protected List<ResourceType> resources = new ArrayList<ResourceType>();
	@Transient
	public List<ResourceType> getResources() {
		return resources;
	}

	public void setResources(List<ResourceType> resources) {
		this.resources = resources;
	}	

}
