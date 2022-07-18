package com.openi40.scheduler.input.model.companystructure;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;
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
public class ResourceGroupInputDto extends AbstractGroupInputDto<SecondaryResourceInputDto> {
	
	private String departmentCode=null;	
	private int resourcesNumber=0;
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = DepartmentInputDto.class,nullable = false)
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getResourcesNumber() {
		return resourcesNumber;
	}

	public void setResourcesNumber(int resourcesNumber) {
		this.resourcesNumber = resourcesNumber;
	}
}
