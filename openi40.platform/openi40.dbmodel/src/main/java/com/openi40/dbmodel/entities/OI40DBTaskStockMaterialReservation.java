package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "whouse_picklist")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
		@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
		@AttributeOverride(name = "taskCode", column = @Column(name = "task_code")),
		@AttributeOverride(name = "machineCode", column = @Column(name = "mac_code")),
		@AttributeOverride(name = "workCenterCode", column = @Column(name = "work_center_code")),
		@AttributeOverride(name = "startTransfer", column = @Column(name = "start_transfer")),
		@AttributeOverride(name = "endTransfer", column = @Column(name = "end_transfer")),
		@AttributeOverride(name = "transferType", column = @Column(name = "transfer_type")),
		@AttributeOverride(name = "batchQty", column = @Column(name = "batch_qty")),
		@AttributeOverride(name = "nrTransfers", column = @Column(name = "nr_transfers")),
		@AttributeOverride(name = "qtyReserved", column = @Column(name = "qty_reserved")) ,
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})

public class OI40DBTaskStockMaterialReservation extends OI40DBBaseEntity implements Serializable {

	String productCode=null;
	String warehouseCode = null;
	String taskCode = null;
	String machineCode = null;
	String workCenterCode = null;	
	Date startTransfer = null;
	Date endTransfer = null;
	String transferType = null;
	Double batchQty = null;
	Double nrTransfers = null;
	Double qtyReserved = null;
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
	public Double getBatchQty() {
		return batchQty;
	}
	public void setBatchQty(Double batchQty) {
		this.batchQty = batchQty;
	}
	public Double getNrTransfers() {
		return nrTransfers;
	}
	public void setNrTransfers(Double nrTransfers) {
		this.nrTransfers = nrTransfers;
	}
	public Double getQtyReserved() {
		return qtyReserved;
	}
	public void setQtyReserved(Double qtyReserved) {
		this.qtyReserved = qtyReserved;
	}

}
