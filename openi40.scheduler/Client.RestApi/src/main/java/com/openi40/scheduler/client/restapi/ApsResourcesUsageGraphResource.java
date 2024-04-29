package com.openi40.scheduler.client.restapi;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractBaseResource;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;

import io.swagger.annotations.Api;
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
@RestController
@RequestMapping("schedulerClient/ApsResourcesUsageGraph")
@Api
public class ApsResourcesUsageGraphResource extends AbstractBaseResource {
	static Logger LOGGER = LoggerFactory.getLogger(ApsResourcesUsageGraphResource.class);

	@Autowired
	public ApsResourcesUsageGraphResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire) {
		super(apsDataCache, mapperFactory, lazyAutowire);
	}

	@GetMapping("{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ResourcesUsageGraphDto> get(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin get(" + dataSetId + "," + variantId + ")");
		}
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IMapper<ApsData, ResourcesUsageGraphDto> resourcesUsageMapper = mapperFactory.createMapper(ApsData.class,
					ResourcesUsageGraphDto.class);
			ResourcesUsageGraphDto resourcesUsageDto = resourcesUsageMapper.mapInstance(apsData,
					ResourcesUsageGraphDto.class, DefaultEntitiesFactory.Instance, new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End get(" + dataSetId + "," + variantId + ")");
			}
			return ResponseEntity.ok().body(resourcesUsageDto);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}
}
