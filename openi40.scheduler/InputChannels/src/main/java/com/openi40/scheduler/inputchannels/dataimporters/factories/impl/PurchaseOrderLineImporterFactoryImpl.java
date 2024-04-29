package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.orders.PurchaseOrderLineInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IPurchaseOrderLineImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IPurchaseOrderLineDao;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
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
public class PurchaseOrderLineImporterFactoryImpl
		extends AbstractDataImporterFactory<PurchaseOrderLineInputDto, PurchaseOrderLine> implements IPurchaseOrderLineImporterFactory{
	@Autowired
	public PurchaseOrderLineImporterFactoryImpl(IMapperFactory mapperFactory,
			IPurchaseOrderLineDao apsDataModelDao) {
		super(PurchaseOrderLineInputDto.class, PurchaseOrderLine.class, mapperFactory, apsDataModelDao);

	}

}
