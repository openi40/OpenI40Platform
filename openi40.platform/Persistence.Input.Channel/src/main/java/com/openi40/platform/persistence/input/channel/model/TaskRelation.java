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
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
@Entity
@Table(name = "task_relation")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "consumerTaskCode", column = @Column(name = "consmr_task_code")),
@AttributeOverride(name = "consumerWorkOrderCode", column = @Column(name = "consmr_worder_code")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "supplierTaskCode", column = @Column(name = "suplr_task_code")),
@AttributeOverride(name = "supplierWorkOrderCode", column = @Column(name = "suplr_worder_code")),
@AttributeOverride(name = "alignmentType", column = @Column(name = "alignment_type")),
@AttributeOverride(name = "timeRangeType", column = @Column(name = "time_range_type")),
@AttributeOverride(name = "peggingCode", column = @Column(name = "pegging_code")),
@AttributeOverride(name = "peggingEdge", column = @Column(name = "pegging_edge")),
@AttributeOverride(name = "offsetMillisecs", column = @Column(name = "offset_millisecs")),
@AttributeOverride(name = "bomItemCode", column = @Column(name = "bom_item_code")),
@AttributeOverride(name = "consumptionTransferType", column = @Column(name = "cons_transfer_type")),
@AttributeOverride(name = "consumptionBatchQty", column = @Column(name = "cons_batch_qty"))
})
public class TaskRelation extends TaskRelationInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
}
