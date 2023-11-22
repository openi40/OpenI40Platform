package com.openi40.ignite.config;

import java.util.List;

import javax.inject.Singleton;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteBinary;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.openi40.ignite.datastreamfactories.IgniteInputDataStreamFactory;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;
@Configuration
public class IgniteInputDataStreamFactoryConfig {
	private HACachedApsDataSetConfig dataSetConfig = null;
	private Ignite ignite;
	private IImportedClassListProvider inputClassesListProvider=null;
	public IgniteInputDataStreamFactoryConfig(@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig,@Autowired Ignite ignite,@Autowired IImportedClassListProvider inputClassesListProvider) {
		this.dataSetConfig = dataSetConfig;
		this.ignite=ignite;
		this.inputClassesListProvider=inputClassesListProvider;
	}

	@Bean
	@Qualifier("persistenceInputDataStreamFactories")
	public IgniteInputDataStreamFactory igniteInputDataStreamFactory() {
		List<Class<? extends InputDto>> types = inputClassesListProvider.getClassesList();
		IgniteBinary binary = ignite.binary();
		for (Class<? extends InputDto> type : types) {
			binary.registerClass(type);
		}
		IgniteInputDataStreamFactory iidsf = new IgniteInputDataStreamFactory(dataSetConfig, ignite);
		return iidsf;

	}

}
