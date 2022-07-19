package com.openi40.scheduler.client.restapi.genericmodel;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.workordergeneration.IWorkOrderGenerator;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Product;
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
public class AbstractAddSimulatedOrderResource extends AbstractBaseResource {
        static Logger LOGGER=LoggerFactory.getLogger(AbstractAddSimulatedOrderResource.class);
	public AbstractAddSimulatedOrderResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory, ILazyContextualBusinessLogicFactoryLoader lazyAutowire) {
		super(apsDataCache, mapperFactory, lazyAutowire);
	}

	@Autowired
	IPlantDao plantDao;
	@Autowired
	IProductDao productDao;

	protected void addOrder(String itemCode, String plantCode, String workOrderCode, double qty, Date deliveryDateTime, ApsData apsData, boolean schedule, int schedulingActionToApply, ApsLogicNotifiedObjects observer) throws DataModelDaoException {
		IWorkOrderGenerator workOrderGenerator = getComponentFactory().create(IWorkOrderGenerator.class, apsData, apsData);
		Plant plant = plantDao.findByCode(plantCode, apsData);
		Product product = productDao.findByCode(itemCode, apsData);
		List<WorkOrder> workOrders = workOrderGenerator.createWorkOrder(product, plant, workOrderCode, null, false, qty, deliveryDateTime, true, true, "CA00BF", apsData, null, null);
		plant.getWorkOrders().addAll(workOrders);
		if (schedule) {
			if (schedulingActionToApply < apsData.getSchedulingSets().size() && schedulingActionToApply >= 0) {
				ApsSchedulingSet action = apsData.getSchedulingSets().get(schedulingActionToApply);
				for (WorkOrder workOrder : workOrders) {
					action.addWorkOrder(workOrder);
				}
			}
			IApsLogicComposer composer = getComponentFactory().create(IApsLogicComposer.class, apsData, apsData);
			composer.schedule(apsData, observer);
		}
	}
	
}
