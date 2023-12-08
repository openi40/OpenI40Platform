package com.openi40.scheduler.engine.apsdata;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.timesheet.TimeSheetsInitializer;
import com.openi40.scheduler.engine.workordergeneration.IWorkOrderGenerator;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IApsDataManager.class, entityClass = ApsData.class)
public class ApsDataManagerImpl extends BusinessLogic<ApsData> implements IApsDataManager {
	static Logger LOGGER = LoggerFactory.getLogger(ApsDataManagerImpl.class);
	@Autowired
	TimeSheetsInitializer timeSheetsInitializer;

	public void initialize(ApsData context) {
		LOGGER.debug("Begin initialize(context);");
		timeSheetsInitializer.initializeCalendars(context);
		this.beforeScheduling(context);
		context.setInitialized(true);
		// if actualDateTime is null initialize it at beginning of scheduling window
		if (context.getActualDateTime() == null && context.getSchedulingWindow() != null
				&& context.getSchedulingWindow().getStartDateTime() != null) {
			context.setActualDateTime(context.getSchedulingWindow().getStartDateTime());
		}
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
											workOrders = workOrderGenerator.createWorkOrder(orderLine, plant);
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