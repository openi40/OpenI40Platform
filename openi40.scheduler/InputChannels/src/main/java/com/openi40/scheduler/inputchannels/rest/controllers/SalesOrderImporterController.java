package com.openi40.scheduler.inputchannels.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ISalesOrderImporterFactory;
import com.openi40.scheduler.inputchannels.rest.AbstractDataImporterController;
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
 * @author architectures@openi40.org
 *
 */
@Profile(AbstractDataImporterController.RESTINPUTPROFILE)
@RestController
@RequestMapping(path = "/engine/salesOrderImporterController")
public class SalesOrderImporterController extends
		AbstractDataImporterController<SalesOrderInputDto, SalesOrder, ISalesOrderImporterFactory, ISalesOrderDao> {
	@Autowired
	public SalesOrderImporterController(IApsDataCacheAggregator apsDataCacheAggregator,
			ISalesOrderImporterFactory importerFactory, IMapperFactory mapperFactory, ISalesOrderDao dao) {
		super(apsDataCacheAggregator, importerFactory, mapperFactory, dao);

	}

}
