package com.openi40.scheduler.model.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.rules.MaterialRule;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
public class AbstractSupplyConsumer extends AbstractApsObject implements ISupplyConsumer {
	private MaterialRule.ConsumptionMode consumptionMode = MaterialRule.ConsumptionMode.CONTINUOUS;
	private double lotQty = 0;
	private Date requiredDateTime = null;
	private Date satisfiedDateTime = null;
	private Product product;
	private double requiredQty = 0.0;
	private String warehouseCode=null;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private List<SupplyReservation> reservations = new ArrayList<SupplyReservation>();

	public AbstractSupplyConsumer(ApsData context) {
		super(context);
	}

	public void addMaterialReservation(SupplyReservation reservation) {
		reservations.add(reservation);
	}

	public double getSatisfiedQty() {
		double qty = 0;
		for (SupplyReservation res : reservations) {
			qty += res.getQtyProvided();
		}

		return qty;
	}

	public double getUnsatisfiedQty() {
		return getRequiredQty() - getSatisfiedQty();
	}

	public void removeMaterialReservation(SupplyReservation reservation) {
		reservations.remove(reservation);
	}

	public List<SupplyReservation> getOwnedReservations() {
		return new ArrayList<SupplyReservation>(reservations);
	}

	@Override
	public void resetSchedulingData() {
		reservations.clear();
	}
}