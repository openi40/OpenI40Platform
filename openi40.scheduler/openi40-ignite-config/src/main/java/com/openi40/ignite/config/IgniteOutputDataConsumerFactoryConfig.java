package com.openi40.ignite.config;

import javax.inject.Singleton;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.openi40.ignite.datastreamfactories.IgniteOutputDataConsumerFactory;
import com.openi40.ignite.datastreamfactories.handlers.IgniteExtendedConsumerHandlerFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
@Configuration
public class IgniteOutputDataConsumerFactoryConfig {

	private HACachedApsDataSetConfig config;
	private Ignite ignite;
	private IgniteExtendedConsumerHandlerFactory consumerFactory;
	@Autowired
	public IgniteOutputDataConsumerFactoryConfig(HACachedApsDataSetConfig config,Ignite ignite,IgniteExtendedConsumerHandlerFactory consumerFactory) {
		this.ignite=ignite;
		this.config=config;
		this.consumerFactory=consumerFactory;
		
	}
	@Bean
	@Singleton
	@Qualifier("haOutputDataConsumerFactory")
	public IOutputDataConsumerFactory igniteOutputDataConsumerFactory() {
		return new IgniteOutputDataConsumerFactory(this.config,this.consumerFactory,this.ignite);
	}
}
