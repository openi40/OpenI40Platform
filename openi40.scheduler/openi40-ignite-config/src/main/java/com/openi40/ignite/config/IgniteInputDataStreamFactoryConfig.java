package com.openi40.ignite.config;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openi40.ignite.datastreamfactories.IgniteInputDataStreamFactory;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IImportedClassListProvider;

@Configuration
public class IgniteInputDataStreamFactoryConfig {
	private HACachedApsDataSetConfig dataSetConfig = null;
	
	private IImportedClassListProvider inputClassesListProvider = null;
	private BeanFactory beanFactory;

	public IgniteInputDataStreamFactoryConfig(@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig,
			@Autowired IImportedClassListProvider inputClassesListProvider,
			@Autowired BeanFactory beanFactory) {
		this.dataSetConfig = dataSetConfig;
	
		this.inputClassesListProvider = inputClassesListProvider;
		this.beanFactory = beanFactory;
	}

	@Bean
	@Qualifier("persistenceInputDataStreamFactories")
	public IgniteInputDataStreamFactory igniteInputDataStreamFactory() {
		List<Class<? extends InputDto>> types = inputClassesListProvider.getClassesList();
		
		IgniteInputDataStreamFactory iidsf = new IgniteInputDataStreamFactory(dataSetConfig, beanFactory);
		return iidsf;

	}

}
