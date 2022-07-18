package com.openi40.scheduler.client.restapi;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.cycles.CycleModelDto;
import com.openi40.scheduler.client.model.cycles.OperationModelDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractBaseResource;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ICycleModelDao;
import com.openi40.scheduler.model.dao.IOperationModelDao;

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
@RequestMapping("schedulerClient/ApsCycleModel")
@Api
public class ApsCycleModelResource extends AbstractBaseResource {
	@Autowired
	public ApsCycleModelResource(IApsDataCacheAggregator apsDataCacheAggregator, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire) {
		super(apsDataCacheAggregator, mapperFactory, lazyAutowire);
	}

	@Autowired
	IOperationModelDao operationModelDao;
	@Autowired
	ICycleModelDao cycleModelDao;

	@GetMapping(value = "cycle/{dataSourceName}/{dataSetId}/{variantId}/{cycleModel}/")
	public CycleModelDto getCycle(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("cycleModel") String cycleModel)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		CycleModelDto cycle = null;
		IApsDataCache datacache = this.apsDataCacheAggregator.getDataCache(dataSourceName);
		ApsData apsdata = datacache.getCachedData(dataSetId, variantId);
		CycleModel model = cycleModelDao.findByCode(cycleModel, apsdata);

		IMapper<CycleModel, CycleModelDto> mapper = this.mapperFactory.createMapper(CycleModel.class,
				CycleModelDto.class);
		cycle = mapper.mapInstance(model, CycleModelDto.class, DefaultEntitiesFactory.Instance, new HashMap<>(), true);
		return cycle;
	}

	@GetMapping(value = "operation/{dataSourceName}/{dataSetId}/{variantId}/{operationCode}/")
	public OperationModelDto getOperation(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("operationCode") String operationCode)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		OperationModelDto operation = null;
		IApsDataCache datacache = this.apsDataCacheAggregator.getDataCache(dataSourceName);
		ApsData apsdata = datacache.getCachedData(dataSetId, variantId);
		OperationModel item = operationModelDao.findByCode(operationCode, apsdata);
		IMapper<OperationModel, OperationModelDto> mapper = this.mapperFactory.createMapper(OperationModel.class,
				OperationModelDto.class);
		operation = mapper.mapInstance(item, OperationModelDto.class, DefaultEntitiesFactory.Instance, new HashMap<>(),
				true);
		return operation;
	}

}
