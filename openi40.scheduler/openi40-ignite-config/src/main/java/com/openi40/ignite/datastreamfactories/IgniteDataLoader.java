package com.openi40.ignite.datastreamfactories;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.inject.Singleton;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteBinary;
import org.apache.ignite.IgniteCache;
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

@Singleton
@Component
public class IgniteDataLoader {
	IImportedClassListProvider inputClassesListProvider = null;
	HACachedApsDataSetConfig dataSetConfig = null;
	Ignite ignite = null;
	IInputDataStreamFactoryRepository iStreamFactoryRepository = null;
	static Logger LOGGER=LoggerFactory.getLogger(IgniteDataLoader.class);
	public IgniteDataLoader(@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig, @Autowired Ignite ignite,
			@Autowired IInputDataStreamFactoryRepository iStreamFactoryRepository) {
		this.inputClassesListProvider = inputClassesListProvider;
		this.dataSetConfig = dataSetConfig;
		this.ignite = ignite;
		this.iStreamFactoryRepository = iStreamFactoryRepository;
	}
	class MaxHolder {
		long utcTs=0l;
		long nREAD=0L;
	}
	@EventListener(value = org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void initialize(org.springframework.boot.context.event.ApplicationReadyEvent ready) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin igniteDataLoader.initialize(...)");
		}
		if (this.dataSetConfig != null && this.dataSetConfig.isDataLoaderNode()) {
			List<IInputDataStreamFactory> ifactories = iStreamFactoryRepository.getDataImporterStreamFactories();
			IInputDataStreamFactory sfactory = null;
			for (IInputDataStreamFactory sf : ifactories) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Analizing: "+sf.getDataSourceName()+"/"+sf.getDataSetName()+"/"+sf.getDataSetVariant()+" description="+sf.getDataSourceDescription());
				}
				if (sf.getDataSetName().equals(this.dataSetConfig.getSourceDataSetName())
						&& sf.getDataSourceName().equals(this.dataSetConfig.getSourceDataSourceName())
						&& sf.getDataSetVariant().equals(this.dataSetConfig.getSourceDataSetVariant())) {
					sfactory=sf;
				}
			}
			if (sfactory!=null) {
				List<Class<? extends InputDto>> classesList = inputClassesListProvider.getClassesList();
				for (Class<? extends InputDto> classType : classesList) {
					try {
						final Stream<? extends InputDto> stream = sfactory.getStream(classType);
						final IgniteCache<Object, Object> cache = ignite.getOrCreateCache(classType.getSimpleName());						
						final MaxHolder holder=new MaxHolder();
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("Begin filling cache:"+classType.getSimpleName());
						}
						Consumer<InputDto> readerConsumer=new Consumer<InputDto>() {
							@Override
							public void accept(InputDto t) {
								
								cache.put(t.getCode(), t);
								Date modTs = t.getModifiedTimestamp();
								holder.nREAD++;
								if (modTs!=null) {
									holder.utcTs=Math.max(holder.utcTs, modTs.getTime());
								}
							}
						};
						stream.forEach(readerConsumer);
						
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("End filling cache:"+classType.getSimpleName()+" nr items read: "+holder.nREAD);
						}
					} catch (InputDataStreamException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End igniteDataLoader.initialize(...)");
		}
	}

}
