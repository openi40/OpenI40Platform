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
 * @author architectures@openi40.org
 *
 */
@MappedSuperclass
public class WarehouseInputDto extends BaseTimesheetManagedInputDTO {
	private String plantCode=null;
	private boolean defaultIncomingWarehouse=false;
	private boolean defaultOutgoingWarehouse=false;
	private boolean defaultProductionWarehouse=false;
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = PlantInputDto.class,nullable = false)
	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
}
