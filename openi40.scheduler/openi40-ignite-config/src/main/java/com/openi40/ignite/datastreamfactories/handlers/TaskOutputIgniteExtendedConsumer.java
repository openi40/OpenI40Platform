package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;

import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
/*WorkOrderOutputDto.class, TaskOutputDto.class, TaskRelationOutputDto.class,
			PeggingOutputDto.class,ApsSchedulingSetOutputDto.class */
public class TaskOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<TaskOutputDto> {

	public TaskOutputIgniteExtendedConsumer(Ignite ignite) {
		super(TaskOutputDto.class, ignite);
		
	}

	@Override
	protected void initializeInputLayerModificationSupport() {
		

	}

	@Override
	protected void modifyInputLayer(TaskOutputDto t) {
		

	}

}
