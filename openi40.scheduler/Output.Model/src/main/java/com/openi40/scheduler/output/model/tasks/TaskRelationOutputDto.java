package com.openi40.scheduler.output.model.tasks;

import com.openi40.scheduler.output.model.OutputDto;

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
public class TaskRelationOutputDto extends OutputDto {

	String supplierTaskCode = null, consumerTaskCode = null;
	String supplierWorkOrder = null, consumerWorkOrder = null;
	String alignmentType = null, timeRangeType = null, peggingCode = null, bomItemCode = null,
			consumptionTransferType = null;
	Boolean peggingEdge = false;
	long offsetMillisecs = 0L;
	double consumptionBatchQty = 0.0;

}
