package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.tasks.AbstractTaskMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskProductionMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskPurchaseMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskStockMaterialReservationInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.IContextAwareObjext;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPurchaseOrderLineDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyReservationOperation;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
import com.openi40.scheduler.model.tasks.Task;

import io.swagger.models.Operation;
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
@Service
public class AbstractTaskMaterialReservationModel2ApsModelService
		extends AbstractInputModel2ApsModelService<AbstractTaskMaterialReservationInputDto, SupplyReservation> {
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IPurchaseOrderLineDao purchaseOrderLineDao;
	@Autowired
	IWarehouseDao warehouseDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	ILazyContextualBusinessLogicFactoryLoader lazyContextualBLFactoryLoader;
	@Autowired
	ObjectMapper objectMapper;
	static Logger LOGGER = LoggerFactory.getLogger(AbstractTaskMaterialReservationModel2ApsModelService.class);

	@Autowired
	public AbstractTaskMaterialReservationModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(AbstractTaskMaterialReservationInputDto.class, SupplyReservation.class, beanFactory,
				businessModelFactory);

	}

	@Override
	public SupplyReservation mapInstance(AbstractTaskMaterialReservationInputDto object,
			Class<SupplyReservation> targetType, IEntitiesFactory entityFactory, Map environment, boolean recursive)
			throws MapperException {
		if (!(entityFactory instanceof IContextAwareObjext)) {
			throw new MapperException("The passed IEntitiesFactory does not implement IContextAwareObjext");
		}
		SupplyReservation reservation = null;
		try {
			IContextAwareObjext ctxAware = (IContextAwareObjext) entityFactory;
			ApsData context = ctxAware.getContext();
			Task task = taskDao.findByCode(object.getTaskCode(), context);
			for (ItemConsumed bom : task.getMaterialConsumptions()) {
				if (bom.getProduct() != null && object.getProductCode().equals(bom.getProduct().getCode())) {
					ISupply supply = null;
					if (object instanceof TaskProductionMaterialReservationInputDto) {
						TaskProductionMaterialReservationInputDto tp = (TaskProductionMaterialReservationInputDto) object;
						Task productionTask = taskDao.findByCode(tp.getSupplyTaskCode(), task.getContext());
						supply = productionTask.getProduction();
					} else if (object instanceof TaskPurchaseMaterialReservationInputDto) {
						TaskPurchaseMaterialReservationInputDto tp = (TaskPurchaseMaterialReservationInputDto) object;
						PurchaseOrderLine purchase = purchaseOrderLineDao.findByCode(tp.getPurchaseOrderLineCode(),
								task.getContext());
						supply = purchase.getSupply();
					} else if (object instanceof TaskStockMaterialReservationInputDto) {
						TaskStockMaterialReservationInputDto tp = (TaskStockMaterialReservationInputDto) object;
						Warehouse warehouse = warehouseDao.findByCode(tp.getWarehouseCode(), task.getContext());
						List<StockSupply> stockSupply = stockSupplyDao.findByItem(bom.getProduct(), warehouse);
						if (stockSupply != null && !stockSupply.isEmpty()) {
							supply = stockSupply.get(0);
						}
					}
					if (supply != null) {
						ISupplyReservationOperation operation = supply.PlanReservation(bom, object.getStartTransfer());
						operation.apply(lazyContextualBLFactoryLoader.getComponentFactory());
						reservation = operation.getCreatedReservation();
						bom.addMaterialReservation(reservation);
						break;
					} else {
						try {
							LOGGER.error("Cannot find supply for " + objectMapper.writeValueAsString(object));
						} catch (Throwable e) {

						}
					}
				}
			}
		} catch (DataModelDaoException exc) {
			throw new MapperException("Problem accessing datamodel", exc);
		}
		if (reservation==null) {
			try {
				LOGGER.error("Cannot create reservation for " + objectMapper.writeValueAsString(object));
			} catch (Throwable e) {

			}
		}
		return reservation;
	}

	@Override
	public void copyValue(AbstractTaskMaterialReservationInputDto originalObject, SupplyReservation targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {

	}
}