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
package com.openi40.scheduler.client.model.material;

import java.util.Date;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.common.utils.DateUtil.Week;


public class SupplyReservationDto extends ClientDto {
	protected Date movementDate = null;
	protected double qtyProvided = 0.0;
	protected String supplyType = null;
	protected String warehouseCode = null;
	protected String documentCode = null;
	protected String workOrderCode = null;
	protected String taskCode = null;

	public Integer getMovementWeek() {
		Week week = null;
		if (movementDate != null) {
			week = DateUtil.getWeek(movementDate);
		}

		return week == null ? null : week.getPeriod();
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public double getQtyProvided() {
		return qtyProvided;
	}

	public void setQtyProvided(double qtyProvided) {
		this.qtyProvided = qtyProvided;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
}
