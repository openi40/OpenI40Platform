package com.openi40.scheduler.input.model.cycles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
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
public class OperationEquipmentSpecificationInputDto extends EquipmentSpecification {
	
	String workCenterCode = null;
	String defaultMachineCode = null;
	List<MachinePriorityInputDto> machinePriorities=new ArrayList<>();
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = WorkCenterInputDto.class,nullable = false)
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}
	@Transient
	public List<MachinePriorityInputDto> getMachinePriorities() {
		return machinePriorities;
	}

	public void setMachinePriorities(List<MachinePriorityInputDto> machinePriorities) {
		this.machinePriorities = machinePriorities;
	}

	public String getDefaultMachineCode() {
		return defaultMachineCode;
	}

	public void setDefaultMachineCode(String defaultMachineCode) {
		this.defaultMachineCode = defaultMachineCode;
	}
}