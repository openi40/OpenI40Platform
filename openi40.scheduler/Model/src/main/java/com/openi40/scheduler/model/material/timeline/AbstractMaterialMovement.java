package com.openi40.scheduler.model.material.timeline;

import java.util.Date;
import java.util.GregorianCalendar;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.common.utils.DateUtil.Week;

import lombok.Data;
import lombok.Getter;
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
@Getter
public abstract class AbstractMaterialMovement<MovementOriginType> {
	protected AbstractMaterialMovement(MovementOriginType originType) {
		this.movimentCause = originType;
	}

	protected MovementOriginType movimentCause = null;

	public abstract double getMovementQty();

	public abstract Date getMovementDate();

	public int getMovementWeek() {
		return DateUtil.getWeek(getMovementDate()).getPeriod();
	}

	public int getMovementMonth() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getMovementDate());
		return gc.get(GregorianCalendar.YEAR) * 100 + gc.get(GregorianCalendar.MONTH) + 1;
	}
	public String toString() {
		return super.toString();
	}
}
