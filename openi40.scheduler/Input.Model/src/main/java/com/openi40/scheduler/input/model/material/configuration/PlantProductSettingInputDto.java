package com.openi40.scheduler.input.model.material.configuration;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
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
@MappedSuperclass
public class PlantProductSettingInputDto extends AbstractProductSettingInputDto {
	protected String plantCode=null;
	public PlantProductSettingInputDto() {
		
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = PlantInputDto.class, nullable = false)
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

}
