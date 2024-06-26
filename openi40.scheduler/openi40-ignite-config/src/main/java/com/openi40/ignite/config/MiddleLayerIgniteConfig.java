package com.openi40.ignite.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openi40.ignite.model.SharedConfigurationInfos;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.dataexporters.IExportedClassListProvider;

@Configuration
public class MiddleLayerIgniteConfig {
	public static final String OPENI40MIDDLE_HA_CACHE = "OPENI40-MIDDLE-LAYER";
	IImportedClassListProvider inputClassesListProvider = null;
	HACachedApsDataSetConfig dataSetConfig = null;
	IExportedClassListProvider exportedClassListProvider = null;

	public MiddleLayerIgniteConfig(@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired IExportedClassListProvider exportedClassListProvider,
			@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig) {
		this.inputClassesListProvider = inputClassesListProvider;
		this.dataSetConfig = dataSetConfig;
		this.exportedClassListProvider = exportedClassListProvider;
	}

	@Singleton
	@Bean
	public IgniteConfiguration igniteConfiguration() {
		// If you provide a whole ClientConfiguration bean then configuration properties
		// will not be used.
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setIgniteInstanceName(OPENI40MIDDLE_HA_CACHE);
		// cfg.setActiveOnStart(true);
		// cfg.setAutoActivationEnabled(true);
		List<Class<? extends InputDto>> inputTypes = this.inputClassesListProvider.getClassesList();
		List<Class<? extends OutputDto>> outputTypes = this.exportedClassListProvider.getClassesList();
		List<CacheConfiguration> cachesConfig = new ArrayList<>();
		for (final Class<? extends InputDto> type : inputTypes) {
			CacheConfiguration _cfg = new CacheConfiguration(type.getName());
			_cfg.setTypes(String.class, type);
			_cfg.setCopyOnRead(true);
			_cfg.setCacheMode(CacheMode.REPLICATED);
			_cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
			_cfg.setSqlSchema("INPUT");
			_cfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
			cachesConfig.add(_cfg);
		}
		for (final Class<? extends OutputDto> type : outputTypes) {
			CacheConfiguration _cfg = new CacheConfiguration(type.getName());
			_cfg.setTypes(String.class, type);
			_cfg.setCopyOnRead(true);
			_cfg.setCacheMode(CacheMode.REPLICATED);
			_cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
			_cfg.setSqlSchema("INPUT");
			_cfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
			cachesConfig.add(_cfg);
		}
		CacheConfiguration _cfg = new CacheConfiguration(SharedConfigurationInfos.class.getName());
		_cfg.setTypes(String.class, SharedConfigurationInfos.class);
		_cfg.setCopyOnRead(true);
		_cfg.setCacheMode(CacheMode.REPLICATED);
		_cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
		_cfg.setSqlSchema("INPUT");
		_cfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
		cachesConfig.add(_cfg);
		cfg.setCacheConfiguration(cachesConfig.toArray(new CacheConfiguration[0]));
		return cfg;
	}

}
