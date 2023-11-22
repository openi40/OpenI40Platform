package com.openi40.ignite.datastreamfactories;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.inject.Singleton;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteBinary;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;

@Singleton
@Component
public class IgniteDataLoader {
	IImportedClassListProvider inputClassesListProvider = null;
	HACachedApsDataSetConfig dataSetConfig = null;
	Ignite ignite = null;
	IInputDataStreamFactoryRepository iStreamFactoryRepository = null;
	static Logger LOGGER = LoggerFactory.getLogger(IgniteDataLoader.class);
	IMapperFactory mapperFactory = null;

	public IgniteDataLoader(@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig, @Autowired Ignite ignite,
			@Autowired IInputDataStreamFactoryRepository iStreamFactoryRepository,
			@Autowired IMapperFactory mapperFactory) {
		this.inputClassesListProvider = inputClassesListProvider;
		this.dataSetConfig = dataSetConfig;
		this.ignite = ignite;
		this.iStreamFactoryRepository = iStreamFactoryRepository;
		this.mapperFactory = mapperFactory;
	}

	class MaxHolder {
		long utcTs = 0l;
		long nREAD = 0L;
	}

	private <T extends InputDto> void cacheStreamingContent(final MaxHolder holder, Class<T> classType,
			IInputDataStreamFactory sfactory) throws MapperException, InputDataStreamException {
		final Stream<T> stream = sfactory.getStream(classType);

		final IgniteCache<String, BinaryObject> cache = ignite.getOrCreateCache(classType.getName()).withKeepBinary();
		final IMapper<T, BinaryObjectBuilder> mapper = mapperFactory.createMapper(classType, BinaryObjectBuilder.class);
		Consumer<T> readerConsumer = new Consumer<T>() {
			@Override
			public void accept(T t) {
				BinaryObjectBuilder ret;
				try {
					ret = mapper.mapInstance(t, BinaryObjectBuilder.class, DefaultEntitiesFactory.Instance,
							new HashMap<>(), true);
				} catch (MapperException e) {
					throw new RuntimeException("Cannot map object correctly for type " + classType.getName(), e);
				}
				cache.put(t.getCode(), ret.build());
				Date modTs = t.getModifiedTimestamp();
				holder.nREAD++;
				if (modTs != null) {
					holder.utcTs = Math.max(holder.utcTs, modTs.getTime());
				}
			}
		};

		stream.forEach(readerConsumer);
	}

	@EventListener(value = org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void initialize(org.springframework.boot.context.event.ApplicationReadyEvent ready)
			throws MapperException, InputDataStreamException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin igniteDataLoader.initialize(...)");
		}
		if (this.dataSetConfig != null && this.dataSetConfig.isDataLoaderNode()) {
			List<IInputDataStreamFactory> ifactories = iStreamFactoryRepository.getDataImporterStreamFactories();
			IInputDataStreamFactory sfactory = null;
			for (IInputDataStreamFactory sf : ifactories) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Analizing: " + sf.getDataSourceName() + "/" + sf.getDataSetName() + "/"
							+ sf.getDataSetVariant() + " description=" + sf.getDataSourceDescription());
				}
				if (sf.getDataSetName().equals(this.dataSetConfig.getSourceDataSetName())
						&& sf.getDataSourceName().equals(this.dataSetConfig.getSourceDataSourceName())
						&& sf.getDataSetVariant().equals(this.dataSetConfig.getSourceDataSetVariant())) {
					sfactory = sf;
				}
			}
			if (sfactory != null) {
				List<Class<? extends InputDto>> classesList = inputClassesListProvider.getClassesList();
				for (Class<? extends InputDto> classType : classesList) {

					final MaxHolder holder = new MaxHolder();
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Begin filling cache:" + classType.getSimpleName());
					}

					cacheStreamingContent(holder, classType, sfactory);

					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(
								"End filling cache:" + classType.getSimpleName() + " nr items read: " + holder.nREAD);
					}

				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End igniteDataLoader.initialize(...)");
		}
	}

}
