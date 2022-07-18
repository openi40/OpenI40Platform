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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
@Entity
@Table(name = "work_order")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "color", column = @Column(name = "color")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "deliveryDate", column = @Column(name = "del_date")),
@AttributeOverride(name = "endExecutionDate", column = @Column(name = "end_execution_date")),
@AttributeOverride(name = "idx", column = @Column(name = "idx")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "cycleCode", column = @Column(name = "cycle_code")),
@AttributeOverride(name = "salesOrderLineCode", column = @Column(name = "sales_order_line_code")),
@AttributeOverride(name = "startExecutionDate", column = @Column(name = "start_execution_date")),
@AttributeOverride(name = "rootSalesOrderWorkOrder", column = @Column(name = "root_task")),
@AttributeOverride(name = "totalQty", column = @Column(name = "total_qty"))
})
public class WorkOrder extends WorkOrderInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
}
