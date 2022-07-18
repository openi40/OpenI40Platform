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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.orders.PeggingInputDto;
@Entity
@Table(name = "pegging")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "consumerWorkOrderCode", column = @Column(name = "cons_worder_code")),
@AttributeOverride(name = "consumerTaskCode", column = @Column(name = "cons_task_code")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "peggingQty", column = @Column(name = "pegging_qty")),
@AttributeOverride(name = "producerWorkOrderCode", column = @Column(name = "prdcr_worder_code")),
@AttributeOverride(name = "producerTaskCode", column = @Column(name = "prdcr_task_code"))
})
public class Pegging extends PeggingInputDto{
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
