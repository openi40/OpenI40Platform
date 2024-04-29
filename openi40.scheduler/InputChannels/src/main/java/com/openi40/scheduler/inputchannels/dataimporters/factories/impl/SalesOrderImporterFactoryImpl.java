package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ISalesOrderImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ISalesOrderDao;
import com.openi40.scheduler.model.orders.SalesOrder;
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
@Service
public class SalesOrderImporterFactoryImpl extends AbstractDataImporterFactory<SalesOrderInputDto, SalesOrder> implements ISalesOrderImporterFactory {
	@Autowired
	public SalesOrderImporterFactoryImpl(IMapperFactory mapperFactory, ISalesOrderDao apsDataModelDao) {
		super(SalesOrderInputDto.class, SalesOrder.class, mapperFactory, apsDataModelDao);

	}

}
