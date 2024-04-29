package com.openi40.scheduler.engine.cyclesrouting;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.WorkOrder;
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
@BusinessInterface(entityClass = WorkOrder.class)
public interface IProductionCycleRoutingStrategy extends IBusinessLogic<WorkOrder> {
	public CycleModel findAppliableCycleModel(WorkOrder workOrder, Product product, ApsData context);

	public CycleModel findAppliableCycleModel(Plant plant, Product product, ApsData context);
}
