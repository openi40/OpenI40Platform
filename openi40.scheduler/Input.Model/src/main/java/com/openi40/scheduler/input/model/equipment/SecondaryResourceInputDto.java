package com.openi40.scheduler.input.model.equipment;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.BaseTimesheetManagedInputDTO;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
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
public class SecondaryResourceInputDto extends BaseTimesheetManagedInputDTO {
	private boolean disabled=false;
	private String resourceGroupCode = null;
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ResourceGroupInputDto.class, nullable = false)
	public String getResourceGroupCode() {
		return resourceGroupCode;
	}

	public void setResourceGroupCode(String resourceGroupCode) {
		this.resourceGroupCode = resourceGroupCode;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
