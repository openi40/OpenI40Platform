package com.openi40.scheduler.model.orders;

import java.time.LocalDateTime;
import java.util.Date;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.material.Product;

import lombok.Data;
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
@Data
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

	public final double getResidualQty() {
		return (getTotalQty() > getDoneQty() ? getTotalQty() - getDoneQty() : 0.0);
	}
}
