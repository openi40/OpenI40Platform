package com.openi40.scheduler.input.model.cycles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;

import lombok.Data;
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
@Data
@MappedSuperclass
public class EquipmentSpecification extends InputDto {
	public EquipmentSpecification() {
		this.code = UUID.randomUUID().toString();
	}

	private String operationCode = null;
	private String setupGroupCode = null;
	private double setupTime = 0.0;
	private List<SecondaryResourceUseSpecificationInputDto> secondaryResourcesSpecs = new ArrayList<SecondaryResourceUseSpecificationInputDto>();
	private double machineTime = 0.0;
	private String machineTimeSpec = OperationTimeCalculationSpec.TIME4OPERATION.name();
	private double minNextPhaseDelay=0.0;
	private double maxNextPhaseDelay=0.0;

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = OperationModelInputDto.class, nullable = true)
	public String getOperationCode() {
		return operationCode;
	}

	@Transient
	public List<SecondaryResourceUseSpecificationInputDto> getSecondaryResourcesSpecs() {
		return secondaryResourcesSpecs;
	}

	public void setSecondaryResourcesSpecs(List<SecondaryResourceUseSpecificationInputDto> secondaryResourcesSpecs) {
		this.secondaryResourcesSpecs = secondaryResourcesSpecs;
	}

}
