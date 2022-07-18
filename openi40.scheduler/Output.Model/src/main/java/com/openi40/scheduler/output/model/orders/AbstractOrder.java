package com.openi40.scheduler.output.model.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.output.model.OutputDto;

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
public abstract class AbstractOrder<OrderLines extends AbstractOrderLine> extends OutputDto {
	
	protected String plantCode=null;
	protected Integer customPriority=0;
	protected String partner = null;
	
	protected String departmentCode = null;
	protected String orderType = null;
	protected String orderStatus = null;
	protected Date askedDeliveryDate = null;
	protected Date plannedDeliveryDate = null;
	
	protected List<OrderLines> orderLines = new ArrayList<OrderLines>();
	

}
