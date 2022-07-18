package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IOperationActuator;
import com.openi40.scheduler.common.aps.IOperationActuatorFactory;

import lombok.Data;
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
@Data
public class SupplyReservationOperation implements ISupplyReservationOperation {
	private boolean applied = false;

	private boolean reversed = false;

	

	private SupplyReservation CreatedReservation = null;

	public  void apply(IOperationActuatorFactory contextualBLFactory) {
		
		IOperationActuator<SupplyReservationOperation> actuator =contextualBLFactory.create(SupplyReservationOperation.class);
		actuator.apply(this);
		setApplied(true);
		setReversed(false);
	}

	public  void reverse(IOperationActuatorFactory ctxBusinessLogicFactory) {
		IOperationActuator<SupplyReservationOperation> actuator = ctxBusinessLogicFactory.create(SupplyReservationOperation.class);
		actuator.reverse(this);
		setApplied(false);
		setReversed(true);
	}
}