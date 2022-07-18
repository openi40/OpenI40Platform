package com.openi40.dbmodel.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
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
@Data
public class OI40DBOperationModel extends OI40DBBaseEntity {
	String cycleCode=null;
	String plantCode = null;
	String sequenceCode = null;
	String consumingBatchTransferType  = null;
	String producingBatchTransferType = null;
	Double consumingBatchQty = null;
	Double producingBatchQty = null;
	

}
