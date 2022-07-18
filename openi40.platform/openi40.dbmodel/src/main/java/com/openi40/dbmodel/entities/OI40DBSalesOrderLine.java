package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Entity
@Table(name = "sales_order_line")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "askedDeliveryDate", column = @Column(name = "asked_del_date")),
@AttributeOverride(name = "color", column = @Column(name = "color")),
@AttributeOverride(name = "completedQty", column = @Column(name = "completed_qty")),
@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
@AttributeOverride(name = "explodeWithCycleCode", column = @Column(name = "explode_with_cycle_code")),
@AttributeOverride(name = "explodeWorkOrders", column = @Column(name = "explode_work_orders")),
@AttributeOverride(name = "lineStatus", column = @Column(name = "line_status")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "orderCode", column = @Column(name = "order_code")),
@AttributeOverride(name = "orderType", column = @Column(name = "order_type")),
@AttributeOverride(name = "plannedDeliveryDate", column = @Column(name = "pld_del_date")),
@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "residualQty", column = @Column(name = "residual_qty")),
@AttributeOverride(name = "totalQty", column = @Column(name = "total_qty")),
@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})
@Data
public class OI40DBSalesOrderLine extends OI40DBBaseEntity implements Serializable{
	protected String plantCode = null;
	protected Integer customPriority = null;
	protected String color = null;
	protected String departmentCode = null;
	protected String orderCode = null;
	protected String orderType = null;
	protected String warehouseCode=null;
	protected String productCode = null;
	protected Double totalQty = null;
	protected Double residualQty = null;
	protected Double completedQty = null;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
	protected String lineStatus = null;
}
