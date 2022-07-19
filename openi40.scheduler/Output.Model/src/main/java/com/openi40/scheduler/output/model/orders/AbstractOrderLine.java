package com.openi40.scheduler.output.model.orders;

import java.util.Date;

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
public abstract class AbstractOrderLine extends OutputDto {
	
	protected String plantCode = null;
	protected Integer customPriority = 0;
	protected String color = null;
	protected String departmentCode = null;
	protected String orderCode = null;
	protected String orderType = null;
	protected String warehouseCode=null;
	protected String productCode = null;
	protected double totalQty = 0.0;
	protected double residualQty = 0.0;
	protected double completedQty = 0.0;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
	protected String lineStatus = null;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
}
