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
package com.openi40.scheduler.client.model.cycles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.openi40.scheduler.client.model.ClientDto;


public class EquipmentSpecification extends ClientDto {
	public EquipmentSpecification() {
		this.code = UUID.randomUUID().toString();
	}

	private double setupTime = 0.0;
	private List<SecondaryResourceUseSpecification> secondaryResourcesSpecs = new ArrayList<SecondaryResourceUseSpecification>();
	private double machineTime = 0.0;
	private OperationTimeCalculationSpec machineTimeSpec = OperationTimeCalculationSpec.TIME4OPERATION;
	public double getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(double setupTime) {
		this.setupTime = setupTime;
	}
	public List<SecondaryResourceUseSpecification> getSecondaryResourcesSpecs() {
		return secondaryResourcesSpecs;
	}
	public void setSecondaryResourcesSpecs(List<SecondaryResourceUseSpecification> secondaryResourcesSpecs) {
		this.secondaryResourcesSpecs = secondaryResourcesSpecs;
	}
	public double getMachineTime() {
		return machineTime;
	}
	public void setMachineTime(double machineTime) {
		this.machineTime = machineTime;
	}
	public OperationTimeCalculationSpec getMachineTimeSpec() {
		return machineTimeSpec;
	}
	public void setMachineTimeSpec(OperationTimeCalculationSpec machineTimeSpec) {
		this.machineTimeSpec = machineTimeSpec;
	}
}
