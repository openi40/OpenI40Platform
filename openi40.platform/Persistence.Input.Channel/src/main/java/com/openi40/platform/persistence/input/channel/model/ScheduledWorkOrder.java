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

import com.openi40.scheduler.input.model.ScheduledWorkOrderInputDto;

@Entity
@Table(name = "scheduled_wo")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "position", column = @Column(name = "position")),
		@AttributeOverride(name = "workOrderCode", column = @Column(name = "work_order_code")),
		@AttributeOverride(name = "apsSchedulingSetCode", column = @Column(name = "sched_set_code")) })

public class ScheduledWorkOrder extends ScheduledWorkOrderInputDto {

	public ScheduledWorkOrder() {

	}

}
