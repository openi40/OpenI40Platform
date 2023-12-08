package com.openi40.scheduler.model.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ApsModelException;
import com.openi40.scheduler.model.aps.ApsData;
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
public abstract class AbstractSupply extends AbstractApsObject implements ISupply {
	private Date availabilityDateTime = null;
	protected String warehouseCode=null; 
	protected List<SupplyReservation> reservations = new ArrayList<SupplyReservation>();

	private Product suppliedItem = null;

	public AbstractSupply(ApsData context) {
		super(context);
	}

	public SupplyReservation AddReservation(ISupplyConsumer consumer) {
		return AddReservation(consumer, null);
	}

	public SupplyReservation AddReservation(ISupplyConsumer consumer, Date instant) {
		ISupplyReservationOperation operation = PlanReservation(consumer, instant);
		operation.apply(null);
		sortReservations();
		return operation.getCreatedReservation();
	}

	public void AddReservation(SupplyReservation reservation) {
		reservations.add(reservation);
	}

	public Date getAvailabilityDateTime() {
		return availabilityDateTime;
	}

	public Date getEndDateTime() {
		return getAvailabilityDateTime();
	}

	public double getQtyAvailable() {
		return getQtyTotal() - getQtyReserved();
	}

	public double getQtyReserved() {
		double total = 0;
		for (SupplyReservation reservation : reservations) {
			total += reservation.getQtyProvided();
		}
		return total;
	}

	public abstract double getQtyTotal();

	public List<SupplyReservation> getReservations() {
		return new ArrayList<SupplyReservation>(reservations);
	}

	public Date getStartDateTime() {
		return getAvailabilityDateTime();
	}

	public Product getSuppliedItem() {
		return suppliedItem;
	}

	public ISupplyReservationOperation PlanReservation(ISupplyConsumer consumer, Date instant) {
		double avail = getQtyAvailable();
		if (!consumer.getProduct().equals(getSuppliedItem())) {
			throw new ApsModelException("Doing reservation on wrong item type");
		}
		SupplyReservation reservation = new SupplyReservation();
		reservation.setConsumer(consumer);
		reservation.setSupply(this);
		reservation.setMovementDate(instant != null && instant != null ? instant : this.getAvailabilityDateTime());
		if (avail < consumer.getUnsatisfiedQty()) {
			reservation.setQtyProvided(avail);
		} else {
			reservation.setQtyProvided(consumer.getUnsatisfiedQty());
		}
		SupplyReservationOperation Operation = new SupplyReservationOperation();
		Operation.setCreatedReservation(reservation);
		reservation.setReversibleOperation(Operation);
		return Operation;
	}

	public void RemoveReservation(SupplyReservation reservation) {
		reservations.remove(reservation);
		reservation.setQtyProvided(0);
		reservation.getConsumer().removeMaterialReservation(reservation);
	}

	@Override
	public void resetSchedulingData() {
		reservations.clear();
	}

	public void setAvailabilityDateTime(Date value) {
		availabilityDateTime = value;
	}

	public void setSuppliedItem(Product value) {
		suppliedItem = value;
	}

	private void sortReservations() {
		List<SupplyReservation> _reservations = new ArrayList<SupplyReservation>();
		TreeMap<Date, List<SupplyReservation>> sortedList = new TreeMap<Date, List<SupplyReservation>>();
		for (SupplyReservation reservation : this.reservations) {
			if (reservation.getMovementDate() != null && reservation.getMovementDate() != null) {
				if (!sortedList.containsKey(reservation.getMovementDate())) {
					sortedList.put(reservation.getMovementDate(), new ArrayList<SupplyReservation>());
				}

				List<SupplyReservation> container = sortedList.get(reservation.getMovementDate());
				container.add(reservation);
			} else {
				_reservations.add(reservation);
			}

		}

		for (Map.Entry<Date, List<SupplyReservation>> item : sortedList.entrySet()) { 
			CollectionUtil.getInstance().AddCollection(_reservations, item.getValue());
		}

		reservations = _reservations;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}