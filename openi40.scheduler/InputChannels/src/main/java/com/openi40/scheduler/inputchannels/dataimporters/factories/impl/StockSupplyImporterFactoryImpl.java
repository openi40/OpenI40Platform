package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.material.StockSupplyInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IStockSupplyImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.material.StockSupply;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@Service
public class StockSupplyImporterFactoryImpl extends
		com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory<StockSupplyInputDto, StockSupply> implements IStockSupplyImporterFactory {
	@Autowired
	public StockSupplyImporterFactoryImpl(IMapperFactory mapperFactory, IStockSupplyDao apsDataModelDao) {
		super(StockSupplyInputDto.class, StockSupply.class, mapperFactory, apsDataModelDao);
	}

}
