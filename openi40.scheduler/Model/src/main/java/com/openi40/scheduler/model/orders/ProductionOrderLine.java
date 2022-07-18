package com.openi40.scheduler.model.orders;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;

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
public class ProductionOrderLine extends AbstractOrderLine {

	public ProductionOrderLine(ApsData context) {
		super(context);

	}

	protected List<WorkOrder> WorkOrders = new ArrayList<WorkOrder>();

}
