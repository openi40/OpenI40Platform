package com.openi40.ignite.datastreamfactories;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.inject.Singleton;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.transactions.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.ignite.model.SharedConfigurationInfos;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.mapper.MapperException;

@Singleton
@Component
public class IgniteDataLoader {
	IImportedClassListProvider inputClassesListProvider = null;
	HACachedApsDataSetConfig dataSetConfig = null;

	IInputDataStreamFactoryRepository iStreamFactoryRepository = null;
	static Logger LOGGER = LoggerFactory.getLogger(IgniteDataLoader.class);
	BeanFactory beanFactory;

	public IgniteDataLoader(@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig,
			@Autowired IInputDataStreamFactoryRepository iStreamFactoryRepository, @Autowired BeanFactory beanFactory) {
		this.inputClassesListProvider = inputClassesListProvider;
		this.dataSetConfig = dataSetConfig;
		this.iStreamFactoryRepository = iStreamFactoryRepository;
		this.beanFactory = beanFactory;
	}

	class MaxHolder {
		long utcTs = 0l;
		long nREAD = 0L;
	}

	private <T extends InputDto> void cacheStreamingContentUsingDataStreamer(final MaxHolder holder, Class<T> classType,
			Ignite ignite, IInputDataStreamFactory sfactory) throws MapperException, InputDataStreamException {
		final Stream<T> stream = sfactory.getStream(classType);
		try (final IgniteDataStreamer<String, T> streamer = ignite.dataStreamer(classType.getName())) {
			streamer.deployClass(classType);
			streamer.allowOverwrite(true);
			Consumer<T> readerConsumer = new Consumer<T>() {
				@Override
				public void accept(T t) {
					streamer.addData(t.getCode(), t);
					Date modTs = t.getModifiedTimestamp();
					holder.nREAD++;
					if (modTs != null) {
						holder.utcTs = Math.max(holder.utcTs, modTs.getTime());
					}
				}

			};
			stream.forEach(readerConsumer);
			streamer.flush();
		}

	}

	@EventListener(value = org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void initialize(org.springframework.boot.context.event.ApplicationReadyEvent ready)
			throws MapperException, InputDataStreamException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin igniteDataLoader.initialize(...)");
		}
		Ignite ignite = beanFactory.getBean(Ignite.class);
		if (ignite == null)
			throw new RuntimeException("Ignite not yet injectable");
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

				IgniteTransactions transactions = ignite.transactions();
				IgniteCache<String, SharedConfigurationInfos> cache = ignite
						.cache(SharedConfigurationInfos.class.getName());
				SharedConfigurationInfos sharedConfig = cache.get(SharedConfigurationInfos.SHARED_CONFIG_ID);
				boolean runInitialSync = false;
				if (sharedConfig == null) {
					runInitialSync = true;
					sharedConfig = new SharedConfigurationInfos();
					sharedConfig.setDataAreLoading(true);
					sharedConfig.setDataLoaded(false);
					try (Transaction tx = transactions.txStart()) {
						cache.put(SharedConfigurationInfos.SHARED_CONFIG_ID, sharedConfig);
						tx.commit();
					}
				}
				if (runInitialSync) {
					LOGGER.info("Node with loader configuration and verified non existent shared state, executing initial data loading");
					try (Transaction tx = transactions.txStart()) {

						List<Class<? extends InputDto>> classesList = inputClassesListProvider.getClassesList();
						for (Class<? extends InputDto> classType : classesList) {
							final MaxHolder holder = new MaxHolder();
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Begin filling cache:" + classType.getSimpleName());
							}
							cacheStreamingContentUsingDataStreamer(holder, classType, ignite, sfactory);
							if (holder.utcTs>0l) {
								sharedConfig.getMaximumTimestamps().put(classType.getName(), new Timestamp(holder.utcTs));
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("End filling cache:" + classType.getSimpleName() + " nr items read: "
										+ holder.nREAD);
							}
						}
						sharedConfig.setDataAreLoading(false);
						sharedConfig.setDataLoaded(true);
						cache.put(SharedConfigurationInfos.SHARED_CONFIG_ID, sharedConfig);
						tx.commit();
					}
					LOGGER.info("Data resident inside Ignite system");
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End igniteDataLoader.initialize(...)");
		}
	}

}
