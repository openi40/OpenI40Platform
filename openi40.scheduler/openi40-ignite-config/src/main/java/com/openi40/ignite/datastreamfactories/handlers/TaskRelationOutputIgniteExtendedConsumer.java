package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;

import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;

public class TaskRelationOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<TaskRelationOutputDto> {

	public TaskRelationOutputIgniteExtendedConsumer( Ignite ignite) {
		super(TaskRelationOutputDto.class, ignite);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeInputLayerModificationSupport() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void modifyInputLayer(TaskRelationOutputDto t) {
		// TODO Auto-generated method stub

	}

}
