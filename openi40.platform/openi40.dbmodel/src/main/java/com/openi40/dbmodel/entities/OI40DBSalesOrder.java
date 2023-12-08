package com.openi40.dbmodel.entities;

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
 * @author architectures@openi40.org
 *
 */
@Entity
@Table(name = "sales_order")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "askedDeliveryDate", column = @Column(name = "asked_del_date")),
		@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "orderStatus", column = @Column(name = "order_status")),
		@AttributeOverride(name = "orderType", column = @Column(name = "order_type")),
		@AttributeOverride(name = "partner", column = @Column(name = "partner")),
		@AttributeOverride(name = "plannedDeliveryDate", column = @Column(name = "pld_del_date")),
		@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")) ,
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})

public class OI40DBSalesOrder extends OI40DBBaseEntity {
	protected String plantCode=null;
	protected Integer customPriority=null;
	protected String partner = null;	
	protected String departmentCode = null;
	protected String orderType = null;
	protected String orderStatus = null;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
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
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
}
