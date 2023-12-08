package com.openi40.scheduler.model.orders;

import java.util.Date;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.material.Product;
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

public abstract class AbstractOrderLine extends AbstractPlantRelatedApsObject {

	public AbstractOrderLine(ApsData context) {
		super(context);

	}
	protected Integer customPriority=0;
	protected String departmentCode = null;
	protected String orderCode = null;
	protected String orderType = null;
	protected Product product = null;
	protected String warehouseCode=null;
	protected double totalQty = 0.0;
	protected double doneQty = 0.0;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
	protected String lineStatus = null;
	protected String color = null;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	public final double getResidualQty() {
		return (getTotalQty() > getDoneQty() ? getTotalQty() - getDoneQty() : 0.0);
	}
	public Integer getCustomPriority() {
		return customPriority;
	}
	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public double getDoneQty() {
		return doneQty;
	}
	public void setDoneQty(double doneQty) {
		this.doneQty = doneQty;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
