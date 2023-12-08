package com.openi40.dbmodel.entities;

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
 * @author architectures@openi40.org
 *
 */
@Entity
@Table(name = "op_model")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "cycleCode", column = @Column(name = "cycle_code")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
		@AttributeOverride(name = "sequenceCode", column = @Column(name = "sequence_code")),
		@AttributeOverride(name = "consumingBatchQty", column = @Column(name = "cons_batch_qty")),
		@AttributeOverride(name = "producingBatchQty", column = @Column(name = "prd_batch_qty"))

		, @AttributeOverride(name = "consumingBatchTransferType", column = @Column(name = "cons_transfer_type")),

		@AttributeOverride(name = "producingBatchTransferType", column = @Column(name = "prd_transfer_type")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))

})

public class OI40DBOperationModel extends OI40DBBaseEntity {
	String cycleCode=null;
	String plantCode = null;
	String sequenceCode = null;
	String consumingBatchTransferType  = null;
	String producingBatchTransferType = null;
	Double consumingBatchQty = null;
	Double producingBatchQty = null;
	public String getCycleCode() {
		return cycleCode;
	}
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	public String getSequenceCode() {
		return sequenceCode;
	}
	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}
	public String getConsumingBatchTransferType() {
		return consumingBatchTransferType;
	}
	public void setConsumingBatchTransferType(String consumingBatchTransferType) {
		this.consumingBatchTransferType = consumingBatchTransferType;
	}
	public String getProducingBatchTransferType() {
		return producingBatchTransferType;
	}
	public void setProducingBatchTransferType(String producingBatchTransferType) {
		this.producingBatchTransferType = producingBatchTransferType;
	}
	public Double getConsumingBatchQty() {
		return consumingBatchQty;
	}
	public void setConsumingBatchQty(Double consumingBatchQty) {
		this.consumingBatchQty = consumingBatchQty;
	}
	public Double getProducingBatchQty() {
		return producingBatchQty;
	}
	public void setProducingBatchQty(Double producingBatchQty) {
		this.producingBatchQty = producingBatchQty;
	}
	

}
