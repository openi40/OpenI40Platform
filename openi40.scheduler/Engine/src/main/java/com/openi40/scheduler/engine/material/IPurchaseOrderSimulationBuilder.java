package com.openi40.scheduler.engine.material;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;

public interface IPurchaseOrderSimulationBuilder {
	/**
	 * If the simulated purchase passed is not yet integrate into a simulated purchase order creates it
	 * @param supply
	 * @param targetTask
	 * @param parentSchedulingAction
	 * @param context
	 * @return
	 */
	PurchaseOrderLine integrateSimulatedPurchases(SimulatedPurchaseSupply supply, ApsData context);
}
