package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IResourceGroupImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IApsDataModelDao;
import com.openi40.scheduler.model.equipment.ResourceGroup;
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
public class ResourceGroupImporterFactoryImpl extends AbstractDataImporterFactory<ResourceGroupInputDto, ResourceGroup>
		implements IResourceGroupImporterFactory {
	@Autowired
	public ResourceGroupImporterFactoryImpl(IMapperFactory mapperFactory,
			IApsDataModelDao<ResourceGroup> apsDataModelDao) {
		super(ResourceGroupInputDto.class, ResourceGroup.class, mapperFactory, apsDataModelDao);

	}

}
