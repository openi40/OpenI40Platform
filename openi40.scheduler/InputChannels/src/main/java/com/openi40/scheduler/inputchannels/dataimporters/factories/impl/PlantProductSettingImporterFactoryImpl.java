package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.material.configuration.PlantProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.ProductiveCompanyProductSettingInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ICalendarModelImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IPlantProductSettingImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IProductiveCompanyProductSettingImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IPlantProductSettingDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyProductSettingDao;
import com.openi40.scheduler.model.dao.ITimesheetMetaInfoDao;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.material.configuration.ProductiveCompanyProductSetting;
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
public class PlantProductSettingImporterFactoryImpl extends
		com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory<PlantProductSettingInputDto, PlantProductSetting>
		implements IPlantProductSettingImporterFactory {
	@Autowired
	public PlantProductSettingImporterFactoryImpl(IMapperFactory mapperFactory,
			IPlantProductSettingDao apsDataModelDao) {
		super(PlantProductSettingInputDto.class, PlantProductSetting.class, mapperFactory, apsDataModelDao);

	}

}
