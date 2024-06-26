package com.openi40.ignite.datastreamfactories.handlers;

import java.util.stream.Stream;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public abstract class AbstractIgniteExtendedConsumer<DtoEntityType extends OutputDto> {
	protected static ObjectMapper objectMapper = new ObjectMapper();
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	protected Class<DtoEntityType> type = null;
	protected Ignite ignite = null;
	protected IgniteDataStreamer<String, DtoEntityType> streamer = null;
	protected IMapperFactory mapperFactory = null;

	public AbstractIgniteExtendedConsumer(Class<DtoEntityType> type, Ignite ignite, IMapperFactory mapperFactory) {
		this.type = type;
		this.ignite = ignite;
		this.mapperFactory = mapperFactory;
	}

	public void initialize() throws IgniteExtenderConsumerException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin initialize()");
		}
		streamer = ignite.dataStreamer(type.getName());
		streamer.deployClass(type);
		streamer.allowOverwrite(true);
		this.initializeInputLayerModificationSupport();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End initialize()");
		}
	}

	public void consume(Stream<DtoEntityType> stream) {
		stream.forEach((t) -> {
			this.accept(t);
		});
	}

	public void accept(DtoEntityType t) {
		if (LOGGER.isDebugEnabled()) {

			try {
				LOGGER.debug("Begin accept(" + (t != null ? objectMapper.writeValueAsString(t) : "null") + ")");
			} catch (JsonProcessingException e) {
				LOGGER.warn("Problem logging in accept()", e);
			}
		}
		streamer.addData(t.getCode(), t);
		try {
			this.modifyInputLayer(t);
		} catch (IgniteExtenderConsumerException e) {
			LOGGER.error("Exception in modifyInputLayer(....)", e);
			throw new RuntimeException("Exception in modifyInputLayer(....)", e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End accept(....)");
		}
	}

	protected abstract void initializeInputLayerModificationSupport() throws IgniteExtenderConsumerException;

	protected abstract void modifyInputLayer(DtoEntityType t) throws IgniteExtenderConsumerException;

	public void endReached() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin endReached()");
		}
		try {
			streamer.close();
			streamer = null;
		} catch (Throwable th) {
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End endReached()");
		}
	}

}
