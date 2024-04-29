package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ICycleModelImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.dao.ICycleModelDao;
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
public class CycleModelImporterFactoryImpl extends AbstractDataImporterFactory<CycleModelInputDto, CycleModel>
		implements ICycleModelImporterFactory {

	public CycleModelImporterFactoryImpl(IMapperFactory mapperFactory, ICycleModelDao apsDataModelDao) {
		super(CycleModelInputDto.class, CycleModel.class, mapperFactory, apsDataModelDao);

	}

}
