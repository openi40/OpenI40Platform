package com.openi40.scheduler.model.orders;

import java.util.Date;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.cloudfoundry.AccessLevel;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.companystructure.Company;

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

public abstract class AbstractOrder<OrderLines extends AbstractOrderLine> extends AbstractPlantRelatedApsObject {

	private static final String ORDER_LINES = "OrderLines";

	public AbstractOrder(ApsData context,Class<OrderLines> lineType) {
		super(context);
		this.orderLines=createCleanChild(this, ORDER_LINES, lineType);
	}
	protected Integer customPriority=0;
	protected Company partner = null;
	protected String departmentCode = null;
	protected String orderType = null;
	protected String orderStatus = null;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;

	protected List<OrderLines> orderLines = null;

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		for (OrderLines orderLine : orderLines) {
			orderLine.resetSchedulingData();
		}
	}

	public Integer getCustomPriority() {
		return customPriority;
	}

	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
	}

	public Company getPartner() {
		return partner;
	}

	public void setPartner(Company partner) {
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
}
