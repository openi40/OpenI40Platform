package com.openi40.scheduler.input.model;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;

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
public class ScheduledWorkOrderInputDto extends InputDto {
	String workOrderCode=null;
	String apsSchedulingSetCode=null;
	public ScheduledWorkOrderInputDto() {
		
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkOrderInputDto.class, nullable = false)
	public String getWorkOrderCode() {
		return workOrderCode;
	}
	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ApsSchedulingSetInputDto.class, nullable = false)
	public String getApsSchedulingSetCode() {
		return apsSchedulingSetCode;
	}
	public void setApsSchedulingSetCode(String apsSchedulingSetCode) {
		this.apsSchedulingSetCode = apsSchedulingSetCode;
	}

}
