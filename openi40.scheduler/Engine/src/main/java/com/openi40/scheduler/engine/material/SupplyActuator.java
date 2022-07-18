package com.openi40.scheduler.engine.material;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IOperationActuator;
import com.openi40.scheduler.model.material.SupplyReservationOperation;
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
public class SupplyActuator implements IOperationActuator<SupplyReservationOperation> {

	public SupplyActuator() {
		
	}

	@Override
	public void apply(SupplyReservationOperation operation) {
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
