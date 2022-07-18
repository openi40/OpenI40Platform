package com.openi40.scheduler.model.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.companystructure.Company;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
	@Setter(value = AccessLevel.NONE)
	protected List<OrderLines> orderLines = null;

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		for (OrderLines orderLine : orderLines) {
			orderLine.resetSchedulingData();
		}
	}
}
