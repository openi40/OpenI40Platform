package com.openi40.scheduler.model.material;

import java.util.Date;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class SupplyReservation extends AbstractApsObject{

	public SupplyReservation() {
		super(null);
	}
	public SupplyReservation(ApsData context) {
		super(context);
	}
	private ISupply Supply;

	public  ISupply getSupply() {
		return Supply;
	}

	public  void setSupply(ISupply value) {
		Supply = value;
	}

	private Date MovementDate = null;

	public  Date getMovementDate() {
		return MovementDate;
	}

	public  void setMovementDate(Date value) {
		MovementDate = value;
	}

	private IOperation ReversibleOperation = null;

	public  IOperation getReversibleOperation() {
		return ReversibleOperation;
	}

	public  void setReversibleOperation(IOperation value) {
		ReversibleOperation = value;
	}

	private ISupplyConsumer Consumer;

	public  ISupplyConsumer getConsumer() {
		return Consumer;
	}

	public  void setConsumer(ISupplyConsumer value) {
		Consumer = value;
	}

	private double QtyProvided = 0.0;

	public final double getQtyProvided() {
		return QtyProvided;
	}

	public final void setQtyProvided(double value) {
		QtyProvided = value;
	}

}