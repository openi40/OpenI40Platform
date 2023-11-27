package com.openi40.ignite.datastreamfactories.handlers;

import org.apache.ignite.Ignite;

import com.openi40.scheduler.output.model.orders.PeggingOutputDto;

public class PeggingOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<PeggingOutputDto> {

	public PeggingOutputIgniteExtendedConsumer( Ignite ignite) {
		super(PeggingOutputDto.class, ignite);
		
	}

	@Override
	protected void initializeInputLayerModificationSupport() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void modifyInputLayer(PeggingOutputDto t) {
		// TODO Auto-generated method stub

	}

}
