package com.openi40.ignite.datastreamfactories;

import org.apache.ignite.Ignite;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.ignite.datastreamfactories.handlers.AbstractIgniteExtendedConsumer;
import com.openi40.ignite.datastreamfactories.handlers.IgniteExtendedConsumerHandlerFactory;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public class IgniteOutputDataConsumerFactory implements IOutputDataConsumerFactory {
	HACachedApsDataSetConfig config=null;
	IgniteExtendedConsumerHandlerFactory consumerFactory=null;
	Ignite ignite=null;
	public IgniteOutputDataConsumerFactory(HACachedApsDataSetConfig config,IgniteExtendedConsumerHandlerFactory consumerFactory,Ignite ignite) {
		this.config=config;
		this.consumerFactory=consumerFactory;
		this.ignite=ignite;
	}
	

	@Override
	public String getDataSourceName() {
		
		return config.getDataSourceName();
	}

	@Override
	public String getDataSetName() {
		return config.getDataSetName();
	}

	@Override
	public String getDataSetVariant() {
		return config.getDataSetVariant();
	}

	@Override
	public <DtoEntityType extends OutputDto> IExtendedConsumer<DtoEntityType> getConsumer(
			Class<DtoEntityType> requiredType) throws OutputDataStreamException {
		AbstractIgniteExtendedConsumer<DtoEntityType>  extendedConsumer=this.consumerFactory.create(requiredType,ignite);
		extendedConsumer.initialize();
		return extendedConsumer;
	}

	@Override
	public String getDataSourceDescription() {
		return config.getDataSourceDescription();
	}

}
