package com.openi40.scheduler.model.material.timeline;

import java.util.Date;

import com.openi40.scheduler.model.material.ISupplyConsumer;
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
public class MaterialSubtraction<SupplyType extends ISupplyConsumer> extends AbstractMaterialMovement<SupplyType> {

	public MaterialSubtraction(SupplyType originType) {
		super(originType);

	}

	@Override
	public double getMovementQty() {
		return -getMovimentCause().getRequiredQty();
	}

	@Override
	public Date getMovementDate() {
		return getMovimentCause().getSatisfiedDateTime();
	}

}
