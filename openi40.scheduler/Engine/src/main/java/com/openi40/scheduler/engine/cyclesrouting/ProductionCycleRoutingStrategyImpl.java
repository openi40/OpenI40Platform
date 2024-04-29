package com.openi40.scheduler.engine.cyclesrouting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ICycleModelDao;
import com.openi40.scheduler.model.dao.IPlantDao;
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
@DefaultImplementation(implemented = IProductionCycleRoutingStrategy.class, entityClass = WorkOrder.class)
public class ProductionCycleRoutingStrategyImpl extends BusinessLogic<WorkOrder>
		implements IProductionCycleRoutingStrategy {
	static Logger LOGGER = LoggerFactory.getLogger(ProductionCycleRoutingStrategyImpl.class);
	@Autowired
	IPlantDao plantDao;
	@Autowired
	ICycleModelDao cycleModelDao;

	public ProductionCycleRoutingStrategyImpl() {

	}

	@Override
	public CycleModel findAppliableCycleModel(WorkOrder workOrder, Product product, ApsData context) {
		if (workOrder.getPlantCode() != null) {
			try {
				Plant plant = plantDao.findByCode(workOrder.getPlantCode(), context);
				return findAppliableCycleModel(plant, product, context);
			} catch (DataModelDaoException e) {
				throw new OpenI40Exception("Problem searching plant with code " + workOrder.getPlantCode(), e);
			}

		}
		throw new OpenI40Exception("Work order without plant code ! work order code:" + workOrder.getCode());
	}

	@Override
	public CycleModel findAppliableCycleModel(Plant plant, Product product, ApsData context) {

		CycleModel _cycle;
		try {
			_cycle = cycleModelDao.findDefaultCycleByProducingItem(product, plant);
		} catch (DataModelDaoException e) {
			String msg = "EXCEPTION IN ACCESSING MEMORY MODEL";
			LOGGER.error(msg, e);
			throw new OpenI40Exception(msg, e);
		}

		return _cycle;
	}

}
