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
public class WorkOrderOutputDto extends OutputDto {
	private String salesOrderLineCode = null;
	private Date startExecutionDate = null;
	private Date endExecutionDate = null;
	private Date deliveryDate = null;
	private int Idx = 0;
	private double totalQty = 0.0;
	private String productCode = null;
	private String plantCode = null;
	protected String color = null;
	private String cycleCode = null;
	private boolean rootSalesOrderWorkOrder = false;

	public WorkOrderOutputDto() {

	}

}
