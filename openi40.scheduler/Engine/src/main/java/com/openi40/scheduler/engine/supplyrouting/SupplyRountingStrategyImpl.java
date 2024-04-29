package com.openi40.scheduler.engine.supplyrouting;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.dao.IPurchaseSupplyDao;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = ISupplyRountingStrategy.class, entityClass = Task.class)
public class SupplyRountingStrategyImpl extends BusinessLogic<Task> implements ISupplyRountingStrategy {
	static Logger LOGGER = LoggerFactory.getLogger(SupplyRountingStrategyImpl.class);
	@Autowired
	IPlantDao plantDao;
	@Autowired
	IProductionSupplyDao productionSupplyDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IPurchaseSupplyDao purchaseSupplyDao;

	public SupplyRountingStrategyImpl() {

	}

	class SupplySelector<S extends ISupply> implements ISelector<S> {
		Product searchedItem = null;

		SupplySelector(Product product) {
			searchedItem = product;
		}

		@Override
		public boolean isSelectable(S t) {
			if (t != null) {
				return t.getSuppliedItem().equals(searchedItem);
			}
			return false;
		}

	}

	@Override
	public List<ISupply> locateUsableSupplies(Task thisTask, Product product) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Start locateUsableSupplies(....)");
		List<ISupply> supplies = new ArrayList<ISupply>();
		if (thisTask.getPlantCode() == null || thisTask.getPlantCode().trim().length() == 0)
			throw new IllegalStateException("Task=>" + thisTask.getCode() + " does not have plantCode");
		try {
			String _key = thisTask.getPlantCode() + "-" + product.getCode();
			if (thisTask.getContext().isInCache(_key)) {
				supplies = thisTask.getContext().getCache(_key, List.class);
			} else {
				Plant plant = plantDao.findByCode(thisTask.getPlantCode(), thisTask.getContext());
				if (plant == null)
					throw new IllegalStateException("Task=>" + thisTask.getCode() + " with incoherent plantCode=" + thisTask.getPlantCode());

				supplies.addAll(stockSupplyDao.findBySelector(new SupplySelector<StockSupply>(product), plant));
				supplies.addAll(productionSupplyDao.findBySelector(new SupplySelector<ProductionSupply>(product), plant));
				supplies.addAll(purchaseSupplyDao.findBySelector(new SupplySelector<PurchaseSupply>(product), plant));
				thisTask.getContext().addCache(_key, supplies);
			}

		} catch (DataModelDaoException e) {
			throw new OpenI40Exception("Cannot search plant " + thisTask.getPlantCode(), e);
		}
		if (supplies.isEmpty()) {
			LOGGER.warn("Supply not found for " + product.getCode() + " and task:" + thisTask.getCode() + " work order:" + thisTask.getWorkOrderCode());
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("End locateUsableSupplies(....)");
		return supplies;
	}

}
