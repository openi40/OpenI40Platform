package com.openi40.scheduler.engine.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.supplyrouting.ISupplyRountingStrategy;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.dao.IPurchaseSupplyDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyConsumer;
import com.openi40.scheduler.model.material.ISupplyReservationOperation;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.SimulatedItemProduced;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;

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
@DefaultImplementation(implemented = IMaterialManager.class, entityClass = MaterialRule.class)
public class MaterialManagerImpl extends BusinessLogic<MaterialRule> implements IMaterialManager {
	static Logger LOGGER = LoggerFactory.getLogger(MaterialManagerImpl.class);

	private <SType extends ISupply> List<SType> filterCompatibleSupplies(List<SType> candidates,
			ISupplyConsumer consumer, Date fromDateTime, Date toDateTime) {
		List<SType> compatibles = new ArrayList<SType>();
		for (SType candidate : candidates) {
			if (candidate.getQtyAvailable() > 0) {
				if (candidate.getAvailabilityDateTime() == null) {// || candidate.getAvailabilityDateTime() != null
					compatibles.add(candidate);
				} else {

					if (consumer.getRequiredDateTime() == null) {// || consumer.getRequiredDateTime() != null
						compatibles.add(candidate);
					} else {
						if (consumer.getRequiredDateTime() != null && consumer.getRequiredDateTime() != null
								&& consumer.getRequiredDateTime().compareTo(candidate.getAvailabilityDateTime()) >= 0) {
							compatibles.add(candidate);
						}
					}
				}
			}

		}
		return compatibles;
	}

	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IPurchaseSupplyDao purchaseSupplyDao;
	@Autowired
	IProductionSupplyDao productionSupplyDao;

	public List<MaterialManagementSettings> solveMaterialConstraint(MaterialRule materialConstraint,
			TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin MaterialManagerImpl.solveMaterialConstraint(" + materialConstraint + ","
					+ SetupTimeRange + "," + WorkTimeRange + ",context)");
		}
		if (!((SetupTimeRange != null && SetupTimeRange.isValidState())
				|| (WorkTimeRange != null && WorkTimeRange.isValidState()))) {
			LOGGER.warn("No time intervals check passed!!");

		}
		String requiredCode = materialConstraint != null && materialConstraint.getConsumer() != null
				&& materialConstraint.getConsumer().getProduct() != null
						? materialConstraint.getConsumer().getProduct().getCode()
						: "<?>";
		List<MaterialManagementSettings> outValue = new ArrayList<MaterialManagementSettings>();
		double requiredQty = materialConstraint.getConsumer().getRequiredQty();
		if (!materialConstraint.getConstraintGeneratedReservations().isEmpty()) {
			throw new OpenI40Exception(
					"Try to allocate material for already managed materialConstraint without cleaned reservations");
		}

		Date fromDateTime = WorkTimeRange.getStartDateTime();
		Date toDateTime = WorkTimeRange.getEndDateTime();
		if (fromDateTime != null && fromDateTime != null
				&& WorkTimeRange.getStartAlignment() == StartDateTimeAlignment.START_ON_START_PRECISELY) {
			materialConstraint.getConsumer().setRequiredDateTime(fromDateTime);
		} else {
			materialConstraint.getConsumer().setRequiredDateTime(null);
		}

		List<IOperation> operations = new ArrayList<IOperation>();
		MaterialRule constraint = materialConstraint;
		TimeSegmentsGroup timeRange = new TimeSegmentsGroup(TimeSegmentType.MATERIAL_ARRIVING_TIME,
				materialConstraint.getTargetTask().getWorkPhaseExecution());
		boolean constraintSatisfied = false;
		List<SupplyReservation> generatedReservations = new ArrayList<SupplyReservation>();
		ISupplyRountingStrategy supplyRoutingStrategy = componentsFactory.create(ISupplyRountingStrategy.class,
				constraint.getTargetTask(), context);
		List<ISupply> routedSupplies = supplyRoutingStrategy.locateUsableSupplies(constraint.getTargetTask(),
				constraint.getConsumer().getProduct());

		List<MaterialRule.SupplyResolutionStrategy> resolutionStrategies = materialConstraint.getResolutionStrategies();
		MaterialRule.SupplyResolutionStrategy strategy;
		try {
			for (int k = 0; k < resolutionStrategies.size() && !constraintSatisfied; k++) {
				strategy = resolutionStrategies.get(k);
				List<ISupply> availableSupplies = null;
				switch (strategy) {
				case USE_STOCK_IF_AVAILABLE: {
					// Find stock supply items (even if simulated or real)
					List<StockSupply> stockSupplies = stockSupplyDao.findByItem(
							materialConstraint.getConsumer().getProduct(), constraint.getContext(), StockSupply.class);
					stockSupplies = filterCompabileStockSupplies(stockSupplies, materialConstraint.getConsumer(),
							fromDateTime, toDateTime);
					availableSupplies = new ArrayList<ISupply>(stockSupplies);

				}
					break;
				case USE_PURCHASE_ORDER_IF_REQUIRED: {
					// Find purchase supply items (even if simulated or real)
					availableSupplies = new ArrayList<ISupply>(
							purchaseSupplyDao.findByItem(materialConstraint.getConsumer().getProduct(),
									constraint.getContext(), PurchaseSupply.class));
					availableSupplies = filterCompatibleSupplies(availableSupplies, materialConstraint.getConsumer(),
							fromDateTime, toDateTime);
				}
					break;
				case FOLLOW_PRODUCTION_LINK: {
					// By design when there is a production link we have to follow it in the order
					// it is evaluated
					availableSupplies = new ArrayList<ISupply>(
							CollectionUtil.getInstance().<ISupply, ProductionSupply>filterByType(
									materialConstraint.getMaterialSupplies(), ProductionSupply.class));
					availableSupplies = filterCompatibleSupplies(availableSupplies, materialConstraint.getConsumer(),
							fromDateTime, toDateTime);
				}
					break;
				case CREATE_ODL_IF_REQUIRED: {
					if (materialConstraint.getConsumer().getProduct().isCanBeProducedByScheduler()) {

						availableSupplies = new ArrayList<ISupply>(
								productionSupplyDao.findByItem(materialConstraint.getConsumer().getProduct(),
										constraint.getContext(), SimulatedItemProduced.class));
						availableSupplies = filterCompatibleSupplies(availableSupplies,
								materialConstraint.getConsumer(), fromDateTime, toDateTime);
						if (availableSupplies.isEmpty()) {
							ISimulatedProductionManager simulatedProductionManager = this.componentsFactory.create(
									ISimulatedProductionManager.class, materialConstraint,
									materialConstraint.getTargetTask().getParentSchedulingSet());
							List<SimulatedItemProduced> simulatedProduction = simulatedProductionManager
									.generateSimulatedProductions(materialConstraint,
											materialConstraint.getTargetTask(),
											materialConstraint.getTargetTask().getParentSchedulingSet(), context);
							for (SimulatedItemProduced supply : simulatedProduction) {
								availableSupplies.add(supply);
							}
						}
					}
				}
					break;
				case CREATE_PURCHASE_ORDER_IF_REQUIRED: {
					if (materialConstraint.getConsumer().getProduct().isCanBePurchasedByScheduler()) {
						availableSupplies = new ArrayList<ISupply>(
								purchaseSupplyDao.findByItem(materialConstraint.getConsumer().getProduct(),
										constraint.getContext(), SimulatedPurchaseSupply.class));
						availableSupplies = filterCompatibleSupplies(availableSupplies,
								materialConstraint.getConsumer(), fromDateTime, toDateTime);
						if (availableSupplies.isEmpty()) {
							ISimulatedPurchaseOrderManager simulatedPurchaseOrderManager = this.componentsFactory
									.create(ISimulatedPurchaseOrderManager.class, materialConstraint,
											materialConstraint.getTargetTask().getParentSchedulingSet());
							List<SimulatedPurchaseSupply> simulatedPurchases = simulatedPurchaseOrderManager
									.generateSimulatedPurchases(materialConstraint, materialConstraint.getTargetTask(),
											materialConstraint.getTargetTask().getParentSchedulingSet(), context);
							for (SimulatedPurchaseSupply supply : simulatedPurchases) {
								availableSupplies.add(supply);
							}
						}
					}
				}
					break;
				}

				if (availableSupplies != null && !availableSupplies.isEmpty()) {
					for (int i = 0; i < availableSupplies.size() && !constraintSatisfied; i++) {
						constraintSatisfied = requiredQty <= 0.0;
						if (!constraintSatisfied) {
							boolean isInfiniteCapacity = false;
							ISupply supply = availableSupplies.get(i);
							if (supply instanceof StockSupply) {
								// Special shortcut for infiniteCapacity stockSupply
								StockSupply stockSupply = (StockSupply) supply;
								isInfiniteCapacity = stockSupply.getInfiniteCapacity() != null
										&& stockSupply.getInfiniteCapacity();
							}
							if (isInfiniteCapacity) {
								// if entry has infiniteCapacity then create supply reservation
								// force residual required quantity to 0 value
								Date time = DateUtil.getInstance().Max(supply.getAvailabilityDateTime(),
										timeRange.getStartDateTime());
								ISupplyReservationOperation _operation = supply
										.PlanReservation(materialConstraint.getConsumer(), time);
								operations.add(_operation);
								SupplyReservation reservation = _operation.getCreatedReservation();
								//force reserved qty to the requiredQty
								reservation.setQtyProvided(requiredQty);
								generatedReservations.add(reservation);
								requiredQty = 0.0;
							} else if (supply.getQtyAvailable() > 0.0) {
								requiredQty = requiredQty <= supply.getQtyAvailable() ? 0.0
										: requiredQty - supply.getQtyAvailable();
								Date time = DateUtil.getInstance().Max(supply.getAvailabilityDateTime(),
										timeRange.getStartDateTime());
								ISupplyReservationOperation _operation = supply
										.PlanReservation(materialConstraint.getConsumer(), time);
								operations.add(_operation);
								generatedReservations.add(_operation.getCreatedReservation());
							}
						}
						constraintSatisfied = requiredQty <= 0.0;
					}
					if (constraintSatisfied) {
						MaterialManagementSettings materialManagementSetting = new MaterialManagementSettings(
								operations, generatedReservations);
						outValue.add(materialManagementSetting);
					}
				}

			}
		} catch (DataModelDaoException e) {
			String msg = "ERROR IN ACCESSING DATA MODEL ";
			LOGGER.error(msg, e);
			throw new OpenI40Exception(msg, e);
		}
		if (outValue.isEmpty()) {

			LOGGER.warn("Supplies not found! for:" + requiredCode);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End MaterialManagerImpl.solveMaterialConstraint(...) returning "
					+ (outValue != null ? outValue.size() : 0) + " supplies matching");
		}
		return outValue;
	}

	private List<StockSupply> filterCompabileStockSupplies(List<StockSupply> stockSupplies, ISupplyConsumer consumer,
			Date fromDateTime, Date toDateTime) {
		if (stockSupplies != null && !stockSupplies.isEmpty()) {
			for (Iterator iterator = stockSupplies.iterator(); iterator.hasNext();) {
				StockSupply stockSupply = (StockSupply) iterator.next();
				if (stockSupply.getInfiniteCapacity() != null && stockSupply.getInfiniteCapacity()) {
					List<StockSupply> ss = new ArrayList<StockSupply>();
					ss.add(stockSupply);
					return ss;
				}
			}
		}
		return filterCompatibleSupplies(stockSupplies, consumer, fromDateTime, toDateTime);
	}

}