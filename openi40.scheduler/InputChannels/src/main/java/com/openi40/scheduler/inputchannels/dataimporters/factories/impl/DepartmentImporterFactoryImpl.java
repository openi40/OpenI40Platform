package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IDepartmentImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.dao.IDepartmentDao;
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
public class DepartmentImporterFactoryImpl extends
		com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory<DepartmentInputDto, Department>
		implements IDepartmentImporterFactory {
	@Autowired
	public DepartmentImporterFactoryImpl(IMapperFactory mapperFactory, IDepartmentDao apsDataModelDao) {
		super(DepartmentInputDto.class, Department.class, mapperFactory, apsDataModelDao);

	}

}
