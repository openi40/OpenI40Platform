package com.openi40.scheduler.model.orders;

import java.util.Date;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
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
public abstract class AbstractWorkOrder extends AbstractPlantRelatedApsObject {
	private Date DeliveryDate = null;
	protected Integer customPriority=0;
	private int Idx = 0;

	double TotalQty = 0.0;

	public AbstractWorkOrder(ApsData context) {
		super(context);
	}

	public final Date getDeliveryDate() {
		return DeliveryDate;
	}

	public final int getIdx() {
		return Idx;
	}

	public double getTotalQty() {
		return TotalQty;
	}

	public final void setDeliveryDate(Date value) {
		DeliveryDate = value;
	}

	public final void setIdx(int value) {
		Idx = value;
	}

	public void setTotalQty(double totalQty) {
		TotalQty = totalQty;
	}

	public Integer getCustomPriority() {
		return customPriority;
	}

	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
	}
}