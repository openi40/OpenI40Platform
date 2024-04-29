package com.openi40.scheduler.model.cycle;
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
public class BatchingInfo {
	BatchTransferType batchTransferType=BatchTransferType.TRANSFER_ALL;
	public double batchQty=1.0;

	public double getBatchQty() {
		return batchQty;
	}

	public void setBatchQty(double batchQty) {
		this.batchQty = batchQty;
	}

	public BatchTransferType getBatchTransferType() {
		return batchTransferType;
	}

	public void setBatchTransferType(BatchTransferType batchTransferType) {
		this.batchTransferType = batchTransferType;
	}
}
