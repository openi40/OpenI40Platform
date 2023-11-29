package com.openi40.ignite.datastreamfactories.handlers;

import java.util.HashMap;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;

import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;

/*WorkOrderOutputDto.class, TaskOutputDto.class, TaskRelationOutputDto.class,
			PeggingOutputDto.class,ApsSchedulingSetOutputDto.class */
public class WorkOrderOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<WorkOrderOutputDto> {
	private IMapper<WorkOrderOutputDto, WorkOrderInputDto> mapper = null;
	private HashMap pMap = new HashMap<>();
	private IgniteDataStreamer<String, WorkOrderInputDto> worderInputStreamer = null;

	public WorkOrderOutputIgniteExtendedConsumer(Ignite ignite, IMapperFactory mapperFactory) {
		super(WorkOrderOutputDto.class, ignite, mapperFactory);

	}

	@Override
	protected void initializeInputLayerModificationSupport() throws IgniteExtenderConsumerException {
		this.worderInputStreamer = this.ignite.dataStreamer(WorkOrderInputDto.class.getName());
		this.worderInputStreamer.allowOverwrite(true);
		try {
			this.mapper = this.mapperFactory.createMapper(WorkOrderOutputDto.class, WorkOrderInputDto.class);
			
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception creating mapper", e);
		}
	}

	@Override
	protected void modifyInputLayer(WorkOrderOutputDto t) throws IgniteExtenderConsumerException {
		try {
			WorkOrderInputDto input = mapper.mapInstance(t, WorkOrderInputDto.class, DefaultEntitiesFactory.Instance,
					pMap, true);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in modifyInputLayer(..)", e);
		}
	}

	@Override
	public void endReached() {

		super.endReached();
		try {
			this.worderInputStreamer.flush();
			this.worderInputStreamer.close();
		} catch (Throwable th) {
		}
	}

}
