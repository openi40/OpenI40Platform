package com.openi40.scheduler.engine.apsdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.workordergeneration.IWorkOrderGenerator;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.SalesOrder;
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
 */
@DefaultImplementation(implemented = IApsDataManager.class, entityClass = ApsData.class)
public class ApsDataManagerImpl extends BusinessLogic<ApsData> implements IApsDataManager {
	static Logger LOGGER = LoggerFactory.getLogger(ApsDataManagerImpl.class);

	public void initialize(ApsData context) {
		LOGGER.debug("Begin initialize(context);");
		List<ITimesheetAllocableObject> list = new ArrayList<>();
		list.add(context);
		list.addAll(context.getProductiveCompanies());
		for (ProductiveCompany pc : context.getProductiveCompanies()) {
			list.addAll(pc.getPlants());
			for (Plant plant : pc.getPlants()) {
				list.addAll(plant.getDepartments());
				for (Department dep : plant.getDepartments()) {
					list.addAll(dep.getWorkCenters());
					for (WorkCenter wc : dep.getWorkCenters()) {
						list.addAll(wc.getResources());
					}
					for (ResourceGroup grp : dep.getSecondaryResourceGroups()) {
						list.addAll(grp.getResources());
					}
				}
				list.addAll(plant.getWarehouses());
			}
		}
		for (ITimesheetAllocableObject r : list) {
			ITimesheetLogic timesheetLogic = this.componentsFactory.create(ITimesheetLogic.class, r, context);
			r.setTimesheet(timesheetLogic.createCleanCalendar(r));
		}
		this.beforeScheduling(context);
		context.setInitialized(true);
		LOGGER.debug("End initialize(context);");
	}

	@Autowired
	IWorkOrderDao workOrderDao;

	@Override
	public void beforeScheduling(ApsData context) {
		for (ProductiveCompany pc : context.getProductiveCompanies()) {

			for (Plant plant : pc.getPlants()) {
				if (plant.getSalesOrders() != null) {
					for (SalesOrder saleOrder : plant.getSalesOrders()) {
						if (saleOrder.getOrderLines() != null) {
							for (SalesOrderLine orderLine : saleOrder.getOrderLines()) {
								if (orderLine.isExplodeWorkOrders()) {
									try {
										List<WorkOrder> workOrders = workOrderDao
												.findBySalesOrderLineCode(orderLine.getCode(), context);
										if (workOrders == null || workOrders.isEmpty()) {
											IWorkOrderGenerator workOrderGenerator = this.componentsFactory
													.create(IWorkOrderGenerator.class, context, context);
											Product productToProduce = orderLine.getProduct();
											String orderCode = orderLine.getCode();
											double qty = orderLine.getResidualQty();
											Date deliveryDateTime = orderLine.getAskedDeliveryDate();
											boolean createDependencyWorkOrders = true;
											boolean simulated = false;
											List<WorkOrder> wos = workOrderGenerator.createWorkOrder(productToProduce,
													plant, orderCode, orderLine.getCode(), true, qty,
													deliveryDateTime, createDependencyWorkOrders, simulated,
													orderLine.getColor(), context);
											
											plant.getWorkOrders().addAll(wos);
										}
									} catch (DataModelDaoException e) {
										throw new RuntimeException("Problem accessing workOrders search", e);
									}
								}
							}
						}
					}
				}
			}
		}

	}
}