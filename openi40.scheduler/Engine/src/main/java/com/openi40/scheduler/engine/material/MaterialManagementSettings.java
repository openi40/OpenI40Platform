package com.openi40.scheduler.engine.material;

import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.material.SupplyReservation;
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
public class MaterialManagementSettings {
	public MaterialManagementSettings(List<IOperation> Operations, List<SupplyReservation> Reservations) {
		this.Operations = Operations;
		this.Reservations = Reservations;
	}

	private List<IOperation> Operations;

	public final List<IOperation> getOperations() {
		return Operations;
	}

	private List<SupplyReservation> Reservations;

	public final List<SupplyReservation> getReservations() {
		return Reservations;
	}
}