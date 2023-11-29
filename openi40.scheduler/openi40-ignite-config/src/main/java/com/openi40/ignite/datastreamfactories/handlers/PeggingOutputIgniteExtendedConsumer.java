package com.openi40.ignite.datastreamfactories.handlers;

import java.util.HashMap;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;

import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;

public class PeggingOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<PeggingOutputDto> {
	private IMapper<PeggingOutputDto, PeggingInputDto> mapper = null;
	private HashMap pMap = new HashMap<>();
	protected IgniteDataStreamer<String, PeggingInputDto> peggingInputStreamer = null;

	public PeggingOutputIgniteExtendedConsumer(Ignite ignite, IMapperFactory mapperFactory) {
		super(PeggingOutputDto.class, ignite, mapperFactory);

	}

	@Override
	protected void initializeInputLayerModificationSupport() throws IgniteExtenderConsumerException {
		this.peggingInputStreamer = this.ignite.dataStreamer(PeggingInputDto.class.getName());
		this.peggingInputStreamer.allowOverwrite(true);
		try {
			this.mapper = mapperFactory.createMapper(PeggingOutputDto.class, PeggingInputDto.class);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in mapping PeggingOutputDto to PeggingInputDto", e);
		}

	}

	@Override
	protected void modifyInputLayer(PeggingOutputDto t) throws IgniteExtenderConsumerException {
		try {
			PeggingInputDto inputDto = mapper.mapInstance(t, PeggingInputDto.class, DefaultEntitiesFactory.Instance,
					pMap, true);
			peggingInputStreamer.addData(inputDto.getCode(), inputDto);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in modifyInputLayer(..)", e);
		}

	}

	@Override
	public void endReached() {
		super.endReached();
		try {
			this.peggingInputStreamer.close();
		} catch (Throwable th) {
		}
	}

}
