package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "work_order")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
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
		@AttributeOverride(name = "totalQty", column = @Column(name = "total_qty")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
		@AttributeOverride(name="minProductionDateConstraint",column=@Column(name="min_prd_date")),
		@AttributeOverride(name="maxProductionDateConstraint",column=@Column(name="max_prd_date"))})

public class OI40DBWorkOrder extends OI40DBBaseEntity implements Serializable {
	private String salesOrderLineCode = null;
	private Date startExecutionDate = null;
	private Date endExecutionDate = null;
	private Date deliveryDate = null;
	private Integer idx = null;
	private Double totalQty = null;
	private String productCode = null;
	private String plantCode = null;
	protected String color = null;
	private String cycleCode = null;
	protected Boolean rootSalesOrderWorkOrder = null;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}
	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
	}
	public Date getStartExecutionDate() {
		return startExecutionDate;
	}
	public void setStartExecutionDate(Date startExecutionDate) {
		this.startExecutionDate = startExecutionDate;
	}
	public Date getEndExecutionDate() {
		return endExecutionDate;
	}
	public void setEndExecutionDate(Date endExecutionDate) {
		this.endExecutionDate = endExecutionDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCycleCode() {
		return cycleCode;
	}
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}
	public Boolean getRootSalesOrderWorkOrder() {
		return rootSalesOrderWorkOrder;
	}
	public void setRootSalesOrderWorkOrder(Boolean rootSalesOrderWorkOrder) {
		this.rootSalesOrderWorkOrder = rootSalesOrderWorkOrder;
	}
	public Date getMinProductionDateConstraint() {
		return minProductionDateConstraint;
	}
	public void setMinProductionDateConstraint(Date minProductionDateConstraint) {
		this.minProductionDateConstraint = minProductionDateConstraint;
	}
	public Date getMaxProductionDateConstraint() {
		return maxProductionDateConstraint;
	}
	public void setMaxProductionDateConstraint(Date maxProductionDateConstraint) {
		this.maxProductionDateConstraint = maxProductionDateConstraint;
	}
}
