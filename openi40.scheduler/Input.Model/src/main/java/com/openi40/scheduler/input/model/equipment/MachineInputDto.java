package com.openi40.scheduler.input.model.equipment;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.BaseTimesheetManagedInputDTO;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;
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
public class MachineInputDto extends BaseTimesheetManagedInputDTO {
	
	private String workCenterCode=null;
	private boolean disabled=false;
	private String availability = null;
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = WorkCenterInputDto.class,nullable = false)
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workcenterCode) {
		this.workCenterCode = workcenterCode;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
}
