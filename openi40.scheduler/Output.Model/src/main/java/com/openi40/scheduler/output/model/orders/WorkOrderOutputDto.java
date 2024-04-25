package com.openi40.scheduler.output.model.orders;

import java.util.Date;

import com.openi40.scheduler.output.model.OutputDto;
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

public class WorkOrderOutputDto extends OutputDto {
	private String salesOrderLineCode = null;
	private Date startExecutionDate = null;
	private Date endExecutionDate = null;
	private Date deliveryDate = null;
	private Integer Idx = 0;
	private double totalQty = 0.0;
	private String productCode = null;
	private String plantCode = null;
	protected String color = null;
	private String cycleCode = null;
	private boolean rootSalesOrderWorkOrder = false;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	public WorkOrderOutputDto() {

	}
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
		return Idx;
	}
	public void setIdx(Integer idx) {
		Idx = idx;
	}
	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
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
	public boolean isRootSalesOrderWorkOrder() {
		return rootSalesOrderWorkOrder;
	}
	public void setRootSalesOrderWorkOrder(boolean rootSalesOrderWorkOrder) {
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
