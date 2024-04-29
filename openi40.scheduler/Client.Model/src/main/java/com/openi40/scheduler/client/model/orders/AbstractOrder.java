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
package com.openi40.scheduler.client.model.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;



public abstract class AbstractOrder<OrderLines extends AbstractOrderLine> extends ClientDto {
	
	protected String plantCode=null;
	protected Integer customPriority=0;
	protected String partner = null;
	
	protected String departmentCode = null;
	protected String orderType = null;
	protected String orderStatus = null;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
	
	protected List<OrderLines> orderLines = new ArrayList<OrderLines>();

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

	public List<OrderLines> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLines> orderLines) {
		this.orderLines = orderLines;
	}
	

}
