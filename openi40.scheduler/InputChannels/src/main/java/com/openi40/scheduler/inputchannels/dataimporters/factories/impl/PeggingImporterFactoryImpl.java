package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IPeggingImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IPlantImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.dao.IPeggingDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.orders.Pegging;
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
public class PeggingImporterFactoryImpl extends AbstractDataImporterFactory<PeggingInputDto, Pegging>
		implements IPeggingImporterFactory {
	@Autowired
	public PeggingImporterFactoryImpl(IMapperFactory mapperFactory, IPeggingDao apsDataModelDao) {
		super(PeggingInputDto.class, Pegging.class, mapperFactory, apsDataModelDao);

	}

}
