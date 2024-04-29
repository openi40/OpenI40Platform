package com.openi40.scheduler.model.material;

import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public interface ISupply extends IApsObject {

	List<SupplyReservation> getReservations();

	ISupplyReservationOperation PlanReservation(ISupplyConsumer consumer, Date dateTime);

	void AddReservation(SupplyReservation reservation);

	SupplyReservation AddReservation(ISupplyConsumer consumer);

	SupplyReservation AddReservation(ISupplyConsumer consumer, Date dateTime);

	void RemoveReservation(SupplyReservation reservation);

	Date getAvailabilityDateTime();

	Product getSuppliedItem();

	double getQtyTotal();

	double getQtyReserved();

	double getQtyAvailable();
	String getWarehouseCode();

}