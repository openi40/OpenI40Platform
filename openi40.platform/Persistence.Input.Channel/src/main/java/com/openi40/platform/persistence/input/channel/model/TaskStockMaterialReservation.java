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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.tasks.TaskStockMaterialReservationInputDto;

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
		@AttributeOverride(name = "qtyReserved", column = @Column(name = "qty_reserved")) })
public class TaskStockMaterialReservation extends TaskStockMaterialReservationInputDto {

	public TaskStockMaterialReservation() {

	}

}
