package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IWarehouseImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.IWarehouseDao;
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
public class WarehouseImporterFactoryImpl extends AbstractDataImporterFactory<WarehouseInputDto, Warehouse> implements IWarehouseImporterFactory{
	@Autowired
	public WarehouseImporterFactoryImpl(IMapperFactory mapperFactory, IWarehouseDao apsDataModelDao) {
		super(WarehouseInputDto.class, Warehouse.class, mapperFactory, apsDataModelDao);

	}

}
