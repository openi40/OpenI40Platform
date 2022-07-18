package com.openi40.scheduler.input.model.orders;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
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
public abstract class AbstractOrderLine extends InputDto {
	
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
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = PlantInputDto.class, nullable = false)
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
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = DepartmentInputDto.class, nullable = true)
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
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ProductInputDto.class, nullable = false)
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public double getResidualQty() {
		return residualQty;
	}
	public void setResidualQty(double residualQty) {
		this.residualQty = residualQty;
	}
	public double getCompletedQty() {
		return completedQty;
	}
	public void setCompletedQty(double completedQty) {
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
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WarehouseInputDto.class, nullable = true)
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
