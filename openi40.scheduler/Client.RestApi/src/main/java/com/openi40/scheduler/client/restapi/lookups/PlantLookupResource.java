/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openi40.scheduler.client.restapi.lookups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.companystructure.PlantDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractApsLookupResource;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.dao.IPlantDao;

import io.swagger.annotations.Api;
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
@RestController
@RequestMapping("schedulerClient/PlantLookupResource")
@Api
public class PlantLookupResource extends AbstractApsLookupResource<Plant, IPlantDao, PlantDto> {
	@Autowired
	public PlantLookupResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire, IPlantDao dao) {
		super(apsDataCache, mapperFactory, lazyAutowire, dao, Plant.class, PlantDto.class);
	}
}
