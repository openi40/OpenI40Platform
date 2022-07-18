package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ISalesOrderLineDataImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ISalesOrderLineDao;
import com.openi40.scheduler.model.orders.SalesOrderLine;
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
public class SalesOrderLineDataImporterFactoryImpl
		extends AbstractDataImporterFactory<SalesOrderLineInputDto, SalesOrderLine> implements ISalesOrderLineDataImporterFactory {
	@Autowired
	public SalesOrderLineDataImporterFactoryImpl(IMapperFactory mapperFactory, ISalesOrderLineDao apsDataModelDao) {
		super(SalesOrderLineInputDto.class, SalesOrderLine.class, mapperFactory, apsDataModelDao);
	}

}
