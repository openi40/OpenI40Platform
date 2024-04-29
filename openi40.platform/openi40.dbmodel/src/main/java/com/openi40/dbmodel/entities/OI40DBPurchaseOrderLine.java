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
@Table(name = "purch_order_line")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "askedDeliveryDate", column = @Column(name = "asked_del_date")),
@AttributeOverride(name = "color", column = @Column(name = "color")),
@AttributeOverride(name = "completedQty", column = @Column(name = "completed_qty")),
@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
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
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
@AttributeOverride(name="minProductionDateConstraint",column=@Column(name="min_prd_date")),
@AttributeOverride(name="maxProductionDateConstraint",column=@Column(name="max_prd_date"))
})

public class OI40DBPurchaseOrderLine extends OI40DBBaseEntity implements Serializable{
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
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	protected String lineStatus = null;
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	public Integer getCustomPriority() {
		return customPriority;
	}
	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public Double getResidualQty() {
		return residualQty;
	}
	public void setResidualQty(Double residualQty) {
		this.residualQty = residualQty;
	}
	public Double getCompletedQty() {
		return completedQty;
	}
	public void setCompletedQty(Double completedQty) {
		this.completedQty = completedQty;
	}
	public Date getAskedDeliveryDate() {
		return askedDeliveryDate;
	}
	public void setAskedDeliveryDate(Date askedDeliveryDate) {
		this.askedDeliveryDate = askedDeliveryDate;
	}
	public Date getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
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
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
}
