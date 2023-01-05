package com.openi40.scheduler.engine.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.tasks.Task;

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
@DefaultImplementation(implemented = ISimulatedPurchaseOrderManager.class, entityClass = MaterialRule.class)
public class SimulatedPurchaseOrderManagerImpl extends BusinessLogic<MaterialRule>
		implements ISimulatedPurchaseOrderManager {
	static Logger LOGGER = LoggerFactory.getLogger(SimulatedPurchaseOrderManagerImpl.class);

	@Override
	public List<SimulatedPurchaseSupply> generateSimulatedPurchases(MaterialRule materialConstraint, Task targetTask,
			ApsSchedulingSet parentSchedulingAction, Date fromDateTime, Date toDateTime, ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin generateSimulatedPurchases(....)");
		}
		List<SimulatedPurchaseSupply> purchaseList = new ArrayList<SimulatedPurchaseSupply>();
		Product requiredProduct = materialConstraint.getConsumer().getProduct();
		Date requiredDateTime = materialConstraint.getConsumer().getRequiredDateTime();
		double unsatisfiedQty = materialConstraint.getConsumer().getUnsatisfiedQty();
		String warehouseCode = materialConstraint.getConsumer().getWarehouseCode();
		Date minDateTime = context.getSchedulingWindow().getStartDateTime();
		Date availableDateTime = requiredDateTime;
		// TODO: FIX WITH REALTIME DATE EVALUATIONS
		if (context.isRealtime()) {
			minDateTime = context.getActualDateTime();
		}
		if (minDateTime != null && requiredDateTime != null) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(minDateTime);
			calendar.add(GregorianCalendar.DAY_OF_YEAR, requiredProduct.getLeadTimeDays());
			Date calculated = calendar.getTime();
			if (calculated.after(requiredDateTime)) {
				availableDateTime = calculated;
			}
		}
		SimulatedPurchaseSupply simulatedPurchaseSupply = new SimulatedPurchaseSupply(context);
		simulatedPurchaseSupply.setSimulatedItem(true);
		simulatedPurchaseSupply.setAvailabilityDateTime(availableDateTime);
		simulatedPurchaseSupply.setQtyTotal(unsatisfiedQty);
		simulatedPurchaseSupply.setSuppliedItem(requiredProduct);
		simulatedPurchaseSupply.setWarehouseCode(warehouseCode);
		simulatedPurchaseSupply.setCode(ISimulatedPurchaseOrderManager.SIMULATED_SUPPLY_ORDER_CODE);
		simulatedPurchaseSupply.setOrderCode(ISimulatedPurchaseOrderManager.SIMULATED_SUPPLY_ORDER_CODE);
		purchaseList.add(simulatedPurchaseSupply);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End generateSimulatedPurchases(....)");
		}
		return purchaseList;
	}

}