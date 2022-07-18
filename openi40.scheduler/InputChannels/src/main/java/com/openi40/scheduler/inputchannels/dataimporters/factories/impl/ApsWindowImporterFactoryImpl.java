package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.time.ApsWindowInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IApsWindowImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.dao.IApsWindowDao;
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
public class ApsWindowImporterFactoryImpl extends
		com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory<ApsWindowInputDto, ApsWindow>
		implements IApsWindowImporterFactory {
	@Autowired
	public ApsWindowImporterFactoryImpl(IMapperFactory mapperFactory, IApsWindowDao apsDataModelDao) {
		super(ApsWindowInputDto.class, ApsWindow.class, mapperFactory, apsDataModelDao);

	}

}
