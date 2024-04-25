package com.openi40.scheduler.input.model.orders;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.material.ProductInputDto;
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
public class WorkOrderInputDto extends InputDto {
	private Date minProductionDateConstraint=null;
	private Date maxProductionDateConstraint=null;
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
	protected Boolean rootSalesOrderWorkOrder=false;
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = SalesOrderLineInputDto.class, nullable = true)
	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}

	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
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

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ProductInputDto.class, nullable = false)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = PlantInputDto.class, nullable = true)
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
