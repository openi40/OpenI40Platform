package com.openi40.ignite.datastreamfactories.handlers;

import java.util.HashMap;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;

import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;

public class TaskRelationOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<TaskRelationOutputDto> {
	private IMapper<TaskRelationOutputDto, TaskRelationInputDto> mapper = null;
	private HashMap pMap = new HashMap<>();
	protected IgniteDataStreamer<String, TaskRelationInputDto> taskRelationInputStreamer = null;

	public TaskRelationOutputIgniteExtendedConsumer(Ignite ignite, IMapperFactory mapperFactory) {
		super(TaskRelationOutputDto.class, ignite, mapperFactory);

	}

	@Override
	protected void initializeInputLayerModificationSupport() throws IgniteExtenderConsumerException {
		try {
			this.mapper = this.mapperFactory.createMapper(TaskRelationOutputDto.class, TaskRelationInputDto.class);
			this.taskRelationInputStreamer=this.ignite.dataStreamer(TaskRelationInputDto.class.getName());
			this.taskRelationInputStreamer.allowOverwrite(true);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in initializeInputLayerModificationSupport()", e);
		}

	}

	@Override
	protected void modifyInputLayer(TaskRelationOutputDto t) throws IgniteExtenderConsumerException {
		// TODO Auto-generated method stub

	}

}
