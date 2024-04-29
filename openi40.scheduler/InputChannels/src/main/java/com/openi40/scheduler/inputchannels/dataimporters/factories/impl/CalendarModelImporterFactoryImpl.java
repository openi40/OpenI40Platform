package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ICalendarModelImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ITimesheetMetaInfoDao;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
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
public class CalendarModelImporterFactoryImpl extends
		com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory<TimesheetMetaInfoInputDto, TimesheetMetaInfo>
		implements ICalendarModelImporterFactory {
	@Autowired
	public CalendarModelImporterFactoryImpl(IMapperFactory mapperFactory, ITimesheetMetaInfoDao apsDataModelDao) {
		super(TimesheetMetaInfoInputDto.class, TimesheetMetaInfo.class, mapperFactory, apsDataModelDao);

	}

}
