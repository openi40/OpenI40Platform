package com.openi40.scheduler.input.model.cycles;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;
import com.openi40.scheduler.input.model.equipment.MachineInputDto;
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
public class ChangeOverMatrixItemInputDto extends InputDto {
	protected String setupGroupCodeFrom = null;
	protected String setupGroupCodeTo = null;
	protected String machineCode = null;
	protected String workCenterCode=null;
	protected double setupTime = 0.0;

	public ChangeOverMatrixItemInputDto() {

	}

	public String getSetupGroupCodeFrom() {
		return setupGroupCodeFrom;
	}

	public void setSetupGroupCodeFrom(String setupGroupCodeFrom) {
		this.setupGroupCodeFrom = setupGroupCodeFrom;
	}

	public String getSetupGroupCodeTo() {
		return setupGroupCodeTo;
	}

	public void setSetupGroupCodeTo(String setupGroupCodeTo) {
		this.setupGroupCodeTo = setupGroupCodeTo;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = MachineInputDto.class, nullable = true)
	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public double getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(double setupTime) {
		this.setupTime = setupTime;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkCenterInputDto.class, nullable = false)
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}

}
