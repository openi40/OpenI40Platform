package com.openi40.scheduler.engine.workordergeneration;

import java.util.Date;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 *
 * This componente creates working orders (also in recursive way for required
 * semi-finished items)
 */
@BusinessInterface(entityClass = ApsData.class)
public interface IWorkOrderGenerator extends IBusinessLogic<ApsData> {
	
	/**
	 * Creates work order(s) doing automatic selection of TaskModel (Cycle) starting
	 * from an Item
	 * 
	 * @param productToProduce
	 * @param plant                      TODO
	 * @param orderCode
	 * @param salesOrderLineCode TODO
	 * @param rootSalesOrderWorkOrder TODO
	 * @param qty
	 * @param deliveryDateTime
	 * @param createDependencyWorkOrders
	 * @param simulated
	 * @param color TODO
	 * @param context
	 * @param minProductionDateConstraint TODO
	 * @param maxProductionDateConstraint TODO
	 * @return
	 */
	List<WorkOrder> createWorkOrder(Product productToProduce, Plant plant, String orderCode, String salesOrderLineCode, boolean rootSalesOrderWorkOrder, double qty, Date deliveryDateTime, boolean createDependencyWorkOrders, boolean simulated, String color, ApsData context, Date minProductionDateConstraint, Date maxProductionDateConstraint);

	/**
	 * Creates work order(s) using specified Cycle (TaskModel)
	 * 
	 * @param cycleModel
	 * @param orderCode
	 * @param salesOrderLineCode TODO
	 * @param rootSalesOrderWorkOrder TODO
	 * @param qty
	 * @param deliveryDateTime
	 * @param createDependencyWorkOrders
	 * @param simulated
	 * @param color TODO
	 * @param context
	 * @param minProductionDateConstraint TODO
	 * @param maxProductionDateConstraint TODO
	 * @return
	 */
	List<WorkOrder> createWorkOrder(CycleModel cycleModel, Plant plant, String orderCode, String salesOrderLineCode, boolean rootSalesOrderWorkOrder, double qty, Date deliveryDateTime, boolean createDependencyWorkOrders, boolean simulated, String color, ApsData context, Date minProductionDateConstraint, Date maxProductionDateConstraint);
	
	
	List<WorkOrder> createWorkOrder(SalesOrderLine line,Plant plant);
}