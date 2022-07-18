package com.openi40.scheduler.engine.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.dao.IPurchaseSupplyDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyConsumer;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.timeline.AbstractMaterialMovement;
import com.openi40.scheduler.model.material.timeline.MaterialAddiction;
import com.openi40.scheduler.model.material.timeline.MaterialSubtraction;
import com.openi40.scheduler.model.material.timeline.TimeLineException;
import com.openi40.scheduler.model.material.timeline.WarehouseProductMaterialTimeLine;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = IMaterialTimeLineManager.class, entityClass = ApsData.class)
public class MaterialTimeLineManagerImpl extends BusinessLogic<ApsData> implements IMaterialTimeLineManager {

	private static final String SINGLE_WAREHOUSE = "singleWarehouse";
	private static final String DEFAULT_SINGLE_WAREHOUSE = "defaultSingleWarehouse";

	public MaterialTimeLineManagerImpl() {

	}

	@Autowired
	IWarehouseDao warehouseDao;
	@Autowired
	IProductDao productDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IPurchaseSupplyDao purchaseSuppliesDao;
	@Autowired
	IProductionSupplyDao productionSupplyDao;

	private WarehouseProductMaterialTimeLine initializeMaterialTimeLine(Warehouse warehouse, Product product,
			ApsData context) throws TimeLineException {
		List<StockSupply> entries;
		try {
			entries = stockSupplyDao.findByItem(product, warehouse);
			WarehouseProductMaterialTimeLine materialTimeLine = new WarehouseProductMaterialTimeLine(context,
					entries != null && !entries.isEmpty() ? entries.get(0) : null);
			materialTimeLine.setProductCode(product.getCode());
			materialTimeLine.setWarehouseCode(warehouse.getCode());
			materialTimeLine.setCode(warehouse.getCode() + "-" + product.getCode());
			List<PurchaseSupply> purchases = purchaseSuppliesDao.findByItem(product, warehouse.getParent());
			for (PurchaseSupply purchaseSupply : purchases) {
				if (purchaseSupply.getWarehouseCode() != null
						&& purchaseSupply.getWarehouseCode().equals(warehouse.getCode())) {
					AbstractMaterialMovement<?> movement = new MaterialAddiction<ISupply>(purchaseSupply);
					materialTimeLine.addMovement(movement);
				}
			}
			return materialTimeLine;
		} catch (DataModelDaoException e) {
			throw new OpenI40Exception("problem searching for stockSupply", e);
		}

	}

	@Override
	public WarehouseProductMaterialTimeLine getMaterialTimeLine(Warehouse warehouse, Product product, ApsData context)
			throws TimeLineException {
		if (!warehouse.getProductsMaterialTimeLines().containsKey(product.getCode())) {
			warehouse.getProductsMaterialTimeLines().put(product.getCode(),
					initializeMaterialTimeLine(warehouse, product, context));
		}
		return warehouse.getProductsMaterialTimeLines().get(product.getCode());
	}

	@Override
	public void addSupply(ApsData context, ISupply supply) throws TimeLineException {
		List<ISupply> supplies = new ArrayList<>();
		supplies.add(supply);
		add(context, new ArrayList<>(), supplies);
	}

	@Override
	public void addSupplyConsumer(ApsData context, ISupplyConsumer consumer) throws TimeLineException {
		List<ISupplyConsumer> consumers = new ArrayList<>();
		consumers.add(consumer);
		add(context, consumers, new ArrayList<>());
	}

	@Override
	public void addSupply(ApsData context, List<ISupply> supplies) throws TimeLineException {
		add(context, new ArrayList<>(), supplies);

	}

	@Override
	public void addSupplyConsumer(ApsData context, List<ISupplyConsumer> consumers) throws TimeLineException {
		add(context, consumers, new ArrayList<>());
	}

	@Override
	public void addScheduledTask(Task task, ApsData context) throws TimeLineException {
		if (task.isSuccessfullyScheduled()) {
			List<ISupplyConsumer> consumers = new ArrayList<>();
			List<ISupply> supplies = new ArrayList<>();
			if (task.getProduction() != null) {
				supplies.add(task.getProduction());
			}
			for (ItemConsumed consumption : task.getMaterialConsumptions()) {
				consumers.add(consumption);
			}
			add(context, consumers, supplies);
		}
	}

	@Override
	public void add(ApsData context, List<ISupplyConsumer> consumers, List<ISupply> supplies) throws TimeLineException {
		Map<String, Map<String, List<AbstractMaterialMovement<?>>>> movements = new HashMap<String, Map<String, List<AbstractMaterialMovement<?>>>>();
		if (supplies != null) {
			for (ISupply iSupply : supplies) {
				if (!movements.containsKey(iSupply.getWarehouseCode())) {
					movements.put(iSupply.getWarehouseCode(), new HashMap<>());
				}
				if (!movements.get(iSupply.getWarehouseCode()).containsKey(iSupply.getSuppliedItem().getCode())) {
					movements.get(iSupply.getWarehouseCode()).put(iSupply.getSuppliedItem().getCode(),
							new ArrayList<>());
				}
				movements.get(iSupply.getWarehouseCode()).get(iSupply.getSuppliedItem().getCode())
						.add(new MaterialAddiction<ISupply>(iSupply));
			}
		}
		if (consumers != null) {
			for (ISupplyConsumer iSupplyConsumer : consumers) {
				if (!movements.containsKey(iSupplyConsumer.getWarehouseCode())) {
					movements.put(iSupplyConsumer.getWarehouseCode(), new HashMap<>());
				}
				if (!movements.get(iSupplyConsumer.getWarehouseCode())
						.containsKey(iSupplyConsumer.getProduct().getCode())) {
					movements.get(iSupplyConsumer.getWarehouseCode()).put(iSupplyConsumer.getProduct().getCode(),
							new ArrayList<>());
				}
				movements.get(iSupplyConsumer.getWarehouseCode()).get(iSupplyConsumer.getProduct().getCode())
						.add(new MaterialSubtraction<ISupplyConsumer>(iSupplyConsumer));

			}
		}
		try {
			for (String warehousesCode : movements.keySet()) {
				if (warehousesCode == null) {
					Boolean singleWarehouse = context.getCache(SINGLE_WAREHOUSE, Boolean.class);
					if (singleWarehouse == null) {
						List<Warehouse> warehouses = warehouseDao.findAll(context);
						context.addCache(SINGLE_WAREHOUSE, warehouses.size() == 1);
						context.addCache(DEFAULT_SINGLE_WAREHOUSE,
								warehouses.size() == 1 ? warehouses.get(0).getCode() : "N/A");
					}
					if (context.getCache(SINGLE_WAREHOUSE, Boolean.class)) {
						warehousesCode = context.getCache(DEFAULT_SINGLE_WAREHOUSE, String.class);
					} else
						throw new OpenI40Exception("Error, warehouseCode is null!!");
				}
				Warehouse _warehouse;

				_warehouse = warehouseDao.findByCode(warehousesCode, context);
				if (movements.containsKey(warehousesCode)) {
					for (String product : movements.get(warehousesCode).keySet()) {
						if (product == null) {
							throw new OpenI40Exception("Error, productCode is null!!");
						}
						Product _product = productDao.findByCode(product, context);
						getMaterialTimeLine(_warehouse, _product, context)
								.addMovement(movements.get(warehousesCode).get(product));
					}
				}
			}
		} catch (DataModelDaoException e) {
			throw new OpenI40Exception("Error accessing data model", e);
		}
	}
}
