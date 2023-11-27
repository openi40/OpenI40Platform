package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.output.model.OutputDto;

@Service
public class IgniteExtendedConsumerHandlerFactory {

	public IgniteExtendedConsumerHandlerFactory() {

	}

	public <DtoEntityType extends OutputDto> AbstractIgniteExtendedConsumer<DtoEntityType> create(
			Class<DtoEntityType> requiredType, Ignite ignite) {

		switch (requiredType.getName()) {
		case "com.openi40.scheduler.output.model.tasks.TaskOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new TaskOutputIgniteExtendedConsumer(ignite);
		}
		case "com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new ApsSchedulingSetOutputIgniteExtendedConsumer(
					ignite);
		}
		case "com.openi40.scheduler.output.model.orders.PeggingOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new PeggingOutputIgniteExtendedConsumer(ignite);
		}
		case "com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new TaskRelationOutputIgniteExtendedConsumer(ignite);
		}
		case "com.openi40.scheduler.output.model.orders.WorkOrderOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new WorkOrderOutputIgniteExtendedConsumer(ignite);
		}
		}
		throw new RuntimeException("Output type:" + requiredType.getName() + " unmanaged");
	}

}
