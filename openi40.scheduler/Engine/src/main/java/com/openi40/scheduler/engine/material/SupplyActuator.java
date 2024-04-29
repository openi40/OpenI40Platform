package com.openi40.scheduler.engine.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IOperationActuator;
import com.openi40.scheduler.model.material.SimulatedPurchaseSupply;
import com.openi40.scheduler.model.material.SupplyReservationOperation;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Service
public class SupplyActuator implements IOperationActuator<SupplyReservationOperation> {
	IPurchaseOrderSimulationBuilder builder;

	public SupplyActuator(@Autowired IPurchaseOrderSimulationBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void apply(SupplyReservationOperation operation) {
		// Check if we are speaking about a simulated purchase operation
		if (operation.getCreatedReservation().getSupply() instanceof SimulatedPurchaseSupply
				&& operation.getCreatedReservation().getSupply().isSimulatedItem() && operation.getCreatedReservation()
						.getSupply().getCode().equals(ISimulatedPurchaseOrderManager.SIMULATED_SUPPLY_ORDER_CODE)) {
			SimulatedPurchaseSupply supply=(SimulatedPurchaseSupply) operation.getCreatedReservation().getSupply();
			PurchaseOrderLine purchaseOrderLine = builder.integrateSimulatedPurchases(supply, supply.getContext());
			supply.setOrderCode(purchaseOrderLine.getCode());
			
		}
		operation.getCreatedReservation().getSupply().AddReservation(operation.getCreatedReservation());
	}

	@Override
	public void reverse(SupplyReservationOperation operation) {
		operation.getCreatedReservation().getSupply().RemoveReservation(operation.getCreatedReservation());
	}

	@Override
	public Class<SupplyReservationOperation> getRootOperationType() {

		return SupplyReservationOperation.class;
	}

}
