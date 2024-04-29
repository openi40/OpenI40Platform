/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
package com.openi40.platform.persistence.input.channel.impl;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.model.StockSupply;
import com.openi40.platform.persistence.input.channel.providers.StockSupplyInputChannelRepository;
import com.openi40.scheduler.input.model.material.StockSupplyInputDto;

@Service
public class StockSupplyPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<StockSupply, StockSupplyInputDto, StockSupplyInputChannelRepository> {

	public StockSupplyPersistenceInputDataStream(StockSupplyInputChannelRepository repository) {
		super(StockSupply.class, StockSupplyInputDto.class, repository);

	}

}
