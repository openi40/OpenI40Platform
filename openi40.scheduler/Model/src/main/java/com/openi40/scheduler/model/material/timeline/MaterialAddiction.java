package com.openi40.scheduler.model.material.timeline;

import java.util.Date;

import com.openi40.scheduler.model.material.ISupply;
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
public class MaterialAddiction<SupplyType extends ISupply> extends AbstractMaterialMovement<SupplyType> {

	public MaterialAddiction(SupplyType originType) {
		super(originType);

	}

	@Override
	public double getMovementQty() {

		return this.movimentCause.getQtyTotal();
	}

	@Override
	public Date getMovementDate() {

		return this.movimentCause.getAvailabilityDateTime();
	}

}
