/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.scheduler.channels.runtime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.apsdatacache.ApsDataCacheAggregatorImpl;
import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.ApsDataCacheImpl;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.apsdatacache.IApsDataSource;
import com.openi40.scheduler.apsdatacache.config.ApsDataCacheConfig;
import com.openi40.scheduler.apsdatacache.config.ApsDataCachesConfig;
import com.openi40.scheduler.apsdatacache.config.ApsDataSourceConfig;
import com.openi40.scheduler.apsdatacache.config.ApsDataSourceMixerConfig;
import com.openi40.scheduler.apsdatacache.config.ApsDataSourcesConfig;
import com.openi40.scheduler.apsdatacache.config.ApsDataSourceMixerConfig.ApsDataSourceId;
import com.openi40.scheduler.common.datamodel.IDataInputValidator;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.inputchannels.dataimporters.DataImporterStreamFactoryRepositoryImpl;
import com.openi40.scheduler.inputchannels.dataimporters.IConfiguredApsDataSourcesRepository;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterAgent;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.config.ApsInputChannelsConfig;
import com.openi40.scheduler.inputchannels.streaminputs.handlers.ApsInputMixerDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.handlers.HttpClientInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.handlers.JsonFilesInputDataStreamFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.outputchannels.dataexporters.IDataExporterAgent;
import com.openi40.scheduler.outputchannels.dataexporters.IDataExporterFactoryRepository;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactoryRepository;
import com.openi40.scheduler.outputchannels.streamoutputs.config.ApsOutputChannelsConfig;
import com.openi40.scheduler.outputchannels.streamoutputs.config.HttpClientOutputDataConfig;
import com.openi40.scheduler.outputchannels.streamoutputs.handlers.RestHttpConfigurableOutputDataConsumerFactory;

@Configuration
public class RuntimeChannelsConfiguration {
	private ApsDataCachesConfig dataCachesConfig = null;
	private ApsDataSourcesConfig config = null;
	private IDataInputValidator validator = null;
	private ObjectMapper objectMapper = null;
	private IDataImporterAgent dataImporterAgent = null;
	private IDataImporterFactoryRepository dataImporterFactoryRepository = null;
	private IApsDataCacheAggregator apsDataCacheAggregator = null;
	private List<IApsDataSource> _apsDataSources = new ArrayList<IApsDataSource>();
	private List<IApsDataSource> configuredApsDataSources = new ArrayList<>();
	private List<IApsDataCache> _apsDataCaches = new ArrayList<IApsDataCache>();
	private List<IInputDataStreamFactory> _inputDataStreamFactories = new ArrayList<IInputDataStreamFactory>();
	private ApsInputChannelsConfig inputChannels = null;
	private ApsOutputChannelsConfig outputChannels = null;
	private IDataExporterAgent dataExporterAgent = null;
	private List<IOutputDataConsumerFactory> dataExporters = null;
	private RestTemplate restTemplate = null;
	private IDataExporterFactoryRepository dataExporterFactoryRepository = null;
	private List<IInputDataStreamFactory> inputDataStreamFactories = null;
	private TransactionalWrapper transactionalWrapper = null;

	public RuntimeChannelsConfiguration(@Autowired(required = false) ApsDataSourcesConfig config,
			@Autowired(required = false) ApsInputChannelsConfig inputChannels,
			@Autowired(required = false) ApsOutputChannelsConfig outputChannels,
			@Autowired(required = false) ApsDataCachesConfig dataCachesConfig, @Autowired IDataInputValidator validator,
			@Autowired ObjectMapper objectMapper, @Autowired IDataImporterAgent dataImporterAgent,
			@Autowired IDataImporterFactoryRepository dataImporterFactoryRepository,
			@Autowired(required = false) List<IApsDataSource> alreadyBoundDataSources,
			@Autowired(required = false) List<IApsDataCache> alreadyBoundDataCaches,
			@Autowired IDataExporterAgent dataExporterAgent,
			@Autowired IDataExporterFactoryRepository dataExporterFactoryRepository,
			@Autowired(required = false) @Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> dataStreamFactories,
			@Autowired(required = false) List<IOutputDataConsumerFactory> dataExporters,
			@Autowired(required = false) @Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> dataStreamExporters,
			@Autowired RestTemplate restTemplate, @Autowired TransactionalWrapper transactionalWrapper) {
		this.transactionalWrapper = transactionalWrapper;
		this.config = config;
		this.inputDataStreamFactories = dataStreamFactories;
		if (this.config == null)
			this.config = new ApsDataSourcesConfig();
		this.dataCachesConfig = dataCachesConfig;
		if (this.dataCachesConfig == null)
			this.dataCachesConfig = new ApsDataCachesConfig();
		this.dataExporterAgent = dataExporterAgent;
		this.inputChannels = inputChannels;
		this.outputChannels = outputChannels;
		this.objectMapper = objectMapper;
		this.validator = validator;
		this.dataImporterAgent = dataImporterAgent;
		this.dataImporterFactoryRepository = dataImporterFactoryRepository;
		this.dataExporterFactoryRepository = dataExporterFactoryRepository;
		if (alreadyBoundDataCaches != null) {
			this._apsDataCaches.addAll(alreadyBoundDataCaches);
		}
		if (alreadyBoundDataSources != null) {
			this._apsDataSources.addAll(alreadyBoundDataSources);
			this.configuredApsDataSources.addAll(alreadyBoundDataSources);
		}

		this.dataExporters = dataExporters;
		if (this.dataExporters == null) {
			this.dataExporters = new ArrayList<>();
		}
		if (dataStreamExporters != null) {
			this.dataExporters.addAll(dataStreamExporters);
		}
		if (this.outputChannels == null) {
			this.outputChannels = new ApsOutputChannelsConfig();
		}
		this.restTemplate = restTemplate;
		this.initializeSystemObjects();
	}

	private void initializeSystemObjects() {

		List<ApsDataCacheImpl> apsDataCaches = new ArrayList<ApsDataCacheImpl>();
		List<ApsDataSourceConfig> sources = this.config.getSources();
		if (sources != null) {
			for (ApsDataSourceConfig apsDataSourceConfig : sources) {
				if (!apsDataSourceConfig.isDisableUserAccess()) {
					ApsDataCacheImpl apsDataCache = new ApsDataCacheImpl(apsDataSourceConfig.getDataSourceName());
					apsDataCaches.add(apsDataCache);
				}
			}
		}
		if (!this._apsDataSources.isEmpty()) {

			this._apsDataSources.forEach((dataSource) -> {
				if (dataSource.getDataSourceName() == null || dataSource.getDataSourceName().trim().length() == 0)
					throw new IllegalStateException("Injected IApsDataSource with dataSourceName null!");
				List<IApsDataCache> matchingDataCache = new ArrayList<IApsDataCache>();
				this._apsDataCaches.forEach((dataCache) -> {
					if (dataSource.getDataSourceName() != null && dataCache.getDataSourceName() != null
							&& dataSource.getDataSourceName().equals(dataCache.getDataSourceName())) {
						dataSource.setDisableUserAccess(dataSource.isDisableUserAccess());
						matchingDataCache.add(dataCache);
					}
				});
				if (matchingDataCache.isEmpty()) {
					if (!dataSource.isDisableUserAccess()) {
						ApsDataCacheImpl apsDataCache = new ApsDataCacheImpl(dataSource.getDataSourceName());
						apsDataCaches.add(apsDataCache);
						matchingDataCache.add(apsDataCache);
					}
				}
				if (matchingDataCache.size() > 1)
					throw new IllegalStateException(
							"we have double dataCache with dataSourceName=" + dataSource.getDataSourceName());
				if (matchingDataCache.get(0).getDataSource() != null) {
					throw new IllegalStateException(
							"There is already a dataSource assigned to the dataCache with dataSourceName="
									+ dataSource.getDataSourceName());
				}
				matchingDataCache.get(0).setDataSource(dataSource);
			});
		}
		if (this.outputChannels != null) {
			for (HttpClientOutputDataConfig cfg : this.outputChannels.getRestConfigs()) {
				RestHttpConfigurableOutputDataConsumerFactory consumerFactory = new RestHttpConfigurableOutputDataConsumerFactory(
						cfg, this.restTemplate);
				this.dataExporters.add(consumerFactory);
			}
		}
		if (this.inputChannels != null) {
			if (this.inputChannels.getJsonFileInputs() != null) {
				this.inputChannels.getJsonFileInputs().forEach((inputDataStreamFactoryConfig) -> {
					JsonFilesInputDataStreamFactory inputDataStreamFactory = new JsonFilesInputDataStreamFactory(
							inputDataStreamFactoryConfig, objectMapper, validator);
					_inputDataStreamFactories.add(inputDataStreamFactory);

				});
			}
			if (this.inputChannels.getHttpClientInputs() != null) {
				this.inputChannels.getHttpClientInputs().forEach((inputDataStreamFactoryConfig) -> {
					HttpClientInputDataStreamFactory inputDataStreamFactory = new HttpClientInputDataStreamFactory(
							inputDataStreamFactoryConfig, objectMapper, validator);
					_inputDataStreamFactories.add(inputDataStreamFactory);
				});
			}
		}
		if (this.inputDataStreamFactories != null) {
			_inputDataStreamFactories.addAll(this.inputDataStreamFactories);
		}
		for (ApsDataSourceMixerConfig sourceMixer : this.config.getSourceMixers()) {
			List<IInputDataStreamFactory> streamFactories = new ArrayList<>();
			for (ApsDataSourceId srcCfg : sourceMixer.getJoined()) {
				this._inputDataStreamFactories.forEach((sf) -> {
					if (sf.getDataSourceName() != null && srcCfg.getDataSourceName() != null
							&& sf.getDataSourceName().equals(srcCfg.getDataSourceName()) && sf.getDataSetName() != null
							&& srcCfg.getDataSetName() != null && sf.getDataSetName().equals(srcCfg.getDataSetName())
							&& sf.getDataSetVariant() != null && srcCfg.getDataSetVariant() != null
							&& sf.getDataSetVariant().equals(srcCfg.getDataSetVariant())) {
						streamFactories.add(sf);

					}
				});
			}
			ApsInputMixerDataStreamFactory mixer = new ApsInputMixerDataStreamFactory(streamFactories);
			mixer.setDataSourceName(sourceMixer.getDataSourceName());
			mixer.setDataSetName(sourceMixer.getDataSetName());
			mixer.setDataSetVariant(sourceMixer.getDataSetVariant());
			mixer.setDataSourceDescription(sourceMixer.getDescription());
			_inputDataStreamFactories.add(mixer);
		}
		_inputDataStreamFactories.forEach((streamFactory) -> {
			List<ApsDataCacheImpl> matchingCaches = new ArrayList<ApsDataCacheImpl>();
			apsDataCaches.forEach((source) -> {
				if (streamFactory.getDataSourceName() != null && source.getDataSourceName() != null
						&& streamFactory.getDataSourceName().equals(source.getDataSourceName())) {
					matchingCaches.add(source);
				}
			});
			boolean userAccessible = true;
			for (ApsDataSourceConfig src : config.getSources()) {
				if (src.getDataSourceName() != null
						&& src.getDataSourceName().equals(streamFactory.getDataSourceName())) {
					userAccessible = !src.isDisableUserAccess();
				}
			}
			ApsDataSourceAdapter _dataSource = new ApsDataSourceAdapter(transactionalWrapper, dataImporterAgent,
					streamFactory, dataImporterFactoryRepository);
			_dataSource.setDisableUserAccess(!userAccessible);
			this.configuredApsDataSources.add(_dataSource);
			if (userAccessible) {
				if (!matchingCaches.isEmpty()) {
					matchingCaches.forEach((source) -> {
						if (source.getDataSource() != null)
							throw new IllegalStateException(
									"There is already a dataSource assigned to the dataCache with dataSourceName="
											+ source.getDataSourceName());
						source.setDataSource(_dataSource);
					});
				} else {
					ApsDataCacheImpl apsDataCache = new ApsDataCacheImpl(_dataSource, new ArrayList<>());
					apsDataCaches.add(apsDataCache);
				}
			}
		});
		_apsDataCaches.addAll(apsDataCaches);

		for (ApsDataCacheConfig cfg : this.dataCachesConfig.getConfigs()) {
			this._apsDataCaches.forEach((dataCache) -> {
				if (cfg.getDataSourceName() != null && dataCache.getDataSourceName() != null
						&& dataCache.getDataSourceName().equals(cfg.getDataSourceName())) {
					dataCache.setCacheDisabled(cfg.isCacheDisabled());
				}
			});
		}
		for (IApsDataSource cfgDataSource : configuredApsDataSources) {
			if (cfgDataSource instanceof ApsDataSourceAdapter) {
				ApsDataSourceAdapter adapter = (ApsDataSourceAdapter) cfgDataSource;
				this.dataExporters.forEach((exporter) -> {
					if (exporter.getDataSourceName() != null
							&& exporter.getDataSourceName().equals(adapter.getDataSourceName())) {
						adapter.setDataExporterAgent(dataExporterAgent);
						adapter.setDeFactoryRepository(dataExporterFactoryRepository);
						adapter.setOutputDataConsumerFactory(exporter);
					}
				});
			}
		}
		apsDataCacheAggregator = new ApsDataCacheAggregatorImpl(_apsDataCaches);

	}

	@Bean
	@Scope("singleton")
	public IApsDataCacheAggregator getApsDataCacheAggregator() {
		return apsDataCacheAggregator;
	}

	@Bean
	@Scope("singleton")
	public List<IInputDataStreamFactory> getInputDataStreamFactories() {
		return _inputDataStreamFactories;
	}
	@Bean
	@Singleton
	public IInputDataStreamFactoryRepository getInputDataStreamFactoryRepository() {
		return new DataImporterStreamFactoryRepositoryImpl(_inputDataStreamFactories);
	}
	@Bean
	@Scope("singleton")
	public IOutputDataConsumerFactoryRepository getOutputDataConsumerFactoryRepository() {
		return new IOutputDataConsumerFactoryRepository() {

			@Override
			public List<IOutputDataConsumerFactory> getDataImporterStreamFactories() {

				return dataExporters;
			}
		};
	}

	@Bean
	@Scope("singleton")
	public IConfiguredApsDataSourcesRepository getConfiguredApsDataSourcesRepository() {
		return new IConfiguredApsDataSourcesRepository() {

			@Override
			public ApsData loadData(String dataSourceName, String dataSetName, String dataSetVariant)
					throws ApsDataCacheException {
				for (IApsDataSource apsDataSource : configuredApsDataSources) {
					if (apsDataSource.getDataSourceName().equals(dataSourceName)) {
						return apsDataSource.loadDataSet(dataSetName, dataSetVariant);
					}
				}
				throw new OpenI40Exception("Cannot find data set configuration for dataSourceName=" + dataSourceName
						+ " dataSetName=" + dataSetName + " dataSetVariant=" + dataSetVariant);
			}

			@Override
			public List<IApsDataSource> getApsDataSources() {

				return configuredApsDataSources;
			}
		};
	}

}
