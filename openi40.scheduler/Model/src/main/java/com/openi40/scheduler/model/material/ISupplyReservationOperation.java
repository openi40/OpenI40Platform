package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IOperation;
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
public interface ISupplyReservationOperation extends IOperation {
	SupplyReservation getCreatedReservation();

	void setCreatedReservation(SupplyReservation value);
}