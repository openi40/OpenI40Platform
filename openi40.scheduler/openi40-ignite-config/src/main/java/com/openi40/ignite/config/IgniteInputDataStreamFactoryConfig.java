package com.openi40.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.openi40.ignite.datastreamfactories.IgniteInputDataStreamFactory;
@Configuration
public class IgniteInputDataStreamFactoryConfig {
	HACachedApsDataSetConfig dataSetConfig = null;
	
	private Ignite ignite;

	public IgniteInputDataStreamFactoryConfig(@Autowired(required = false) HACachedApsDataSetConfig dataSetConfig,@Autowired Ignite ignite) {
		this.dataSetConfig = dataSetConfig;
		this.ignite=ignite;
	}

	@Bean
	@Qualifier("persistenceInputDataStreamFactories")
	public IgniteInputDataStreamFactory igniteInputDataStreamFactory() {
		IgniteInputDataStreamFactory iidsf = new IgniteInputDataStreamFactory(dataSetConfig, ignite);
		return iidsf;

	}

}
