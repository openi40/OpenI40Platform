package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;

import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
/*WorkOrderOutputDto.class, TaskOutputDto.class, TaskRelationOutputDto.class,
			PeggingOutputDto.class,ApsSchedulingSetOutputDto.class */
public class WorkOrderOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<WorkOrderOutputDto> {

	public WorkOrderOutputIgniteExtendedConsumer( Ignite ignite) {
		super(WorkOrderOutputDto.class, ignite);
		
	}

	@Override
	protected void initializeInputLayerModificationSupport() {
		

	}

	@Override
	protected void modifyInputLayer(WorkOrderOutputDto t) {
		

	}

}
