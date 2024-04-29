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

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
import com.openi40.scheduler.input.model.ScheduledWorkOrderInputDto;

@Entity
@Table(name = "scheduling_set")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "position", column = @Column(name = "position")),
		@AttributeOverride(name = "options", column = @Column(name = "options")),
		@AttributeOverride(name = "algorithmDirection", column = @Column(name = "algo_dir")),
		@AttributeOverride(name = "algorithmType", column = @Column(name = "algo_type"))})

public class ApsSchedulingSet extends ApsSchedulingSetInputDto {

	public ApsSchedulingSet() {

	}

	@StreamLoadRelated(overriddenType = ScheduledWorkOrderInputDto.class, loadType = ScheduledWorkOrder.class, relationType = RelationType.ONE2MANY, joinProperty = "apsSchedulingSetCode",orderOptions = "position")
	@Override
	public void setScheduledWorkOrders(List<ScheduledWorkOrderInputDto> scheduledWorkOrders) {
		super.setScheduledWorkOrders(scheduledWorkOrders);
	}

}
