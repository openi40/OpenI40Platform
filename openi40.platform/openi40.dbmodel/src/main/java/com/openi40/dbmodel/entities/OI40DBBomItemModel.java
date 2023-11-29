package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "bom_item_model")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
		@AttributeOverride(name = "requiredProductCode", column = @Column(name = "required_prd_code")),
		@AttributeOverride(name = "useCoefficient", column = @Column(name = "use_coefficient")),
		@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
		@AttributeOverride(name = "consumingBatchQty", column = @Column(name = "cons_batch_qty")),
		@AttributeOverride(name = "consumingBatchTransferType", column = @Column(name = "cons_transfer_type")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })
@Data
public class OI40DBBomItemModel extends OI40DBBaseEntity implements Serializable {
	protected String requiredProductCode = null;
	protected Double useCoefficient = null;
	protected String consumingBatchTransferType = null;
	protected Double consumingBatchQty = null;
	protected String warehouseCode = null;
	protected String operationCode = null;

}
