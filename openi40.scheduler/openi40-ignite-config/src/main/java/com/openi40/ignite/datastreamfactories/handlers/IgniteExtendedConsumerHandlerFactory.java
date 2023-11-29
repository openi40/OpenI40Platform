package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.output.model.OutputDto;

@Service
public class IgniteExtendedConsumerHandlerFactory {
	private IMapperFactory mapperFactory = null;

	public IgniteExtendedConsumerHandlerFactory(@Autowired IMapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

	public <DtoEntityType extends OutputDto> AbstractIgniteExtendedConsumer<DtoEntityType> create(
			Class<DtoEntityType> requiredType, Ignite ignite) throws IgniteExtenderConsumerException{

		switch (requiredType.getName()) {
		case "com.openi40.scheduler.output.model.tasks.TaskOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new TaskOutputIgniteExtendedConsumer(ignite,
					mapperFactory);
		}
		case "com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new ApsSchedulingSetOutputIgniteExtendedConsumer(
					ignite, mapperFactory);
		}
		case "com.openi40.scheduler.output.model.orders.PeggingOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new PeggingOutputIgniteExtendedConsumer(ignite,
					mapperFactory);
		}
		case "com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new TaskRelationOutputIgniteExtendedConsumer(ignite,
					mapperFactory);
		}
		case "com.openi40.scheduler.output.model.orders.WorkOrderOutputDto": {
			return (AbstractIgniteExtendedConsumer<DtoEntityType>) new WorkOrderOutputIgniteExtendedConsumer(ignite,
					mapperFactory);
		}
		}
		throw new IgniteExtenderConsumerException("Output type:" + requiredType.getName() + " unmanaged");
	}

}
