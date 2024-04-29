package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.cycles.ChangeOverMatrixItemInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IChangeOverMatrixItemImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.cycle.ChangeOverMatrixItem;
import com.openi40.scheduler.model.dao.IChangeOverMatrixItemDao;
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
public class ChangeOverMatrixItemImporterFactoryImpl extends AbstractDataImporterFactory<ChangeOverMatrixItemInputDto, ChangeOverMatrixItem>
		implements IChangeOverMatrixItemImporterFactory {

	public ChangeOverMatrixItemImporterFactoryImpl(IMapperFactory mapperFactory, IChangeOverMatrixItemDao apsDataModelDao) {
		super(ChangeOverMatrixItemInputDto.class, ChangeOverMatrixItem.class, mapperFactory, apsDataModelDao);

	}

}
