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
package com.openi40.scheduler.client.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;

public class DepartmentDto extends ClientDto {

	List<WorkCenterDto> WorkCenters = new ArrayList<WorkCenterDto>();
	List<ResourceGroupDto> secondaryResourceGroups = new ArrayList<ResourceGroupDto>();
	public List<WorkCenterDto> getWorkCenters() {
		return WorkCenters;
	}
	public void setWorkCenters(List<WorkCenterDto> workCenters) {
		WorkCenters = workCenters;
	}
	public List<ResourceGroupDto> getSecondaryResourceGroups() {
		return secondaryResourceGroups;
	}
	public void setSecondaryResourceGroups(List<ResourceGroupDto> secondaryResourceGroups) {
		this.secondaryResourceGroups = secondaryResourceGroups;
	}

}
