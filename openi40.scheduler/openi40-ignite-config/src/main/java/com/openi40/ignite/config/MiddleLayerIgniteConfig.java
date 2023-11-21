package com.openi40.ignite.config;

import java.util.ArrayList;
import java.util.List;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import javax.inject.Singleton;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactoryRepository;

@Configuration
public class MiddleLayerIgniteConfig {
	public static final String OPENI40MIDDLE_HA_CACHE = "OPENI40-MIDDLE-LAYER";
	IImportedClassListProvider inputClassesListProvider = null;
	HACachedApsDataSetConfig dataSetConfig = null;

	public MiddleLayerIgniteConfig(@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig) {
		this.inputClassesListProvider = inputClassesListProvider;
		this.dataSetConfig = dataSetConfig;

	}
	
	@Singleton
	@Bean
	public IgniteConfiguration igniteConfiguration() {
		// If you provide a whole ClientConfiguration bean then configuration properties
		// will not be used.
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setIgniteInstanceName(OPENI40MIDDLE_HA_CACHE);
		//cfg.setActiveOnStart(true);
		//cfg.setAutoActivationEnabled(true);
		List<Class<? extends InputDto>> inputTypes = this.inputClassesListProvider.getClassesList();
		List<CacheConfiguration> cachesConfig = new ArrayList<>();
		for (final Class<? extends InputDto> type : inputTypes) {
			CacheConfiguration _cfg = new CacheConfiguration(type.getSimpleName());
			_cfg.setTypes(String.class, type);
			_cfg.setCopyOnRead(true);
			_cfg.setCacheMode(CacheMode.REPLICATED);
			_cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
			_cfg.setSqlSchema("INPUT");
			_cfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);

			cachesConfig.add(_cfg);
		}
		cfg.setCacheConfiguration(cachesConfig.toArray(new CacheConfiguration[0]));
		return cfg;
	}

}
