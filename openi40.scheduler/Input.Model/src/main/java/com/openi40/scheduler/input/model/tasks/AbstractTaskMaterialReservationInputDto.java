package com.openi40.scheduler.input.model.tasks;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;
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
@MappedSuperclass

public class AbstractTaskMaterialReservationInputDto extends InputDto {
	String productCode=null;
	String warehouseCode = null;
	String taskCode = null;
	String machineCode = null;
	String workCenterCode = null;	
	Date startTransfer = null;
	Date endTransfer = null;
	String transferType = null;
	double batchQty = 0;
	double nrTransfers = 0;
	double qtyReserved = 0;
	public AbstractTaskMaterialReservationInputDto() {
		
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getWorkCenterCode() {
		return workCenterCode;
	}
	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}
	public Date getStartTransfer() {
		return startTransfer;
	}
	public void setStartTransfer(Date startTransfer) {
		this.startTransfer = startTransfer;
	}
	public Date getEndTransfer() {
		return endTransfer;
	}
	public void setEndTransfer(Date endTransfer) {
		this.endTransfer = endTransfer;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public double getBatchQty() {
		return batchQty;
	}
	public void setBatchQty(double batchQty) {
		this.batchQty = batchQty;
	}
	public double getNrTransfers() {
		return nrTransfers;
	}
	public void setNrTransfers(double nrTransfers) {
		this.nrTransfers = nrTransfers;
	}
	public double getQtyReserved() {
		return qtyReserved;
	}
	public void setQtyReserved(double qtyReserved) {
		this.qtyReserved = qtyReserved;
	}

}
