package com.openi40.scheduler.input.model.companystructure;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.BaseTimesheetManagedInputDTO;
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
public class PlantInputDto extends BaseTimesheetManagedInputDTO {
	
	private String productiveCompanyCode=null;
	
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = ProductiveCompanyInputDto.class,nullable = false)
	public String getProductiveCompanyCode() {
		return productiveCompanyCode;
	}

	public void setProductiveCompanyCode(String productiveCompanyCode) {
		this.productiveCompanyCode = productiveCompanyCode;
	}
}
