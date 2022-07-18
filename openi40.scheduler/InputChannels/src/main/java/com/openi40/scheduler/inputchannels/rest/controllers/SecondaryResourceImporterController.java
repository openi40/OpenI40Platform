package com.openi40.scheduler.inputchannels.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ISecondaryResourceImporterFactory;
import com.openi40.scheduler.inputchannels.rest.AbstractDataImporterController;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ISecondaryResourceDao;
import com.openi40.scheduler.model.equipment.Resource;
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
@RequestMapping(path = "/engine/secondaryResourceImporterController")
public class SecondaryResourceImporterController extends
		AbstractDataImporterController<SecondaryResourceInputDto, Resource, ISecondaryResourceImporterFactory, ISecondaryResourceDao> {
	@Autowired
	public SecondaryResourceImporterController(IApsDataCacheAggregator apsDataCacheAggregator,
			ISecondaryResourceImporterFactory importerFactory, IMapperFactory mapperFactory,
			ISecondaryResourceDao dao) {
		super(apsDataCacheAggregator, importerFactory, mapperFactory, dao);

	}

}
