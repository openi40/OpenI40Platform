package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IPlantImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.dao.IPlantDao;
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
public class PlantImporterFactoryImpl extends AbstractDataImporterFactory<PlantInputDto, Plant> implements IPlantImporterFactory{
	@Autowired
	public PlantImporterFactoryImpl(IMapperFactory mapperFactory, IPlantDao apsDataModelDao) {
		super(PlantInputDto.class, Plant.class, mapperFactory, apsDataModelDao);
		
	}

}
