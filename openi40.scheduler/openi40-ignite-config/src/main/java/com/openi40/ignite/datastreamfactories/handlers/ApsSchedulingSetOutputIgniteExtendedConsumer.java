package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;

import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;

public class ApsSchedulingSetOutputIgniteExtendedConsumer
		extends AbstractIgniteExtendedConsumer<ApsSchedulingSetOutputDto> {

	public ApsSchedulingSetOutputIgniteExtendedConsumer( Ignite ignite,IMapperFactory mapperFactory) {
		super(ApsSchedulingSetOutputDto.class, ignite,mapperFactory);
		
	}

	@Override
	protected void initializeInputLayerModificationSupport() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void modifyInputLayer(ApsSchedulingSetOutputDto t) {
		// TODO Auto-generated method stub

	}

}
