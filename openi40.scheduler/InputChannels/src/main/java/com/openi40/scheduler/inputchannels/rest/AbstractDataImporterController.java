package com.openi40.scheduler.inputchannels.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterFactory;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsDataModelDao;
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
public abstract class AbstractDataImporterController<ImportedData extends InputDto, ApsDataType extends IApsObject, IDataImporterFactoryType extends IDataImporterFactory<ImportedData, ApsDataType>, IDaoType extends IApsDataModelDao<ApsDataType>> {
	public static final String RESTINPUTPROFILE="restinput";
	protected IApsDataCacheAggregator apsDataCacheAggregator = null;
	protected IDataImporterFactoryType importerFactory = null;
	protected IDaoType dao = null;
	protected IMapperFactory mapperFactory = null;

	public AbstractDataImporterController(IApsDataCacheAggregator apsDataCacheAggregator,
			IDataImporterFactoryType importerFactory, IMapperFactory mapperFactory, IDaoType dao) {
		this.apsDataCacheAggregator = apsDataCacheAggregator;
		this.importerFactory = importerFactory;
		this.dao = dao;
		this.mapperFactory = mapperFactory;
	}

	protected void update(String dataSourceName, String dataSetName, String dataSetVariant, List<ImportedData> imported,
			boolean staging) throws ApsDataCacheException, DataModelDaoException, MapperException {
		IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
		ApsData apsData = staging ? apsDataCache.getStagedData(dataSetName, dataSetVariant)
				: apsDataCache.getCachedData(dataSetName, dataSetVariant);
		Consumer<ImportedData> consumer = importerFactory.create(apsData);
		imported.forEach(consumer);
	}

	protected List<ImportedData> get(String dataSourceName, String dataSetName, String dataSetVariant, boolean staging)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
		ApsData apsData = staging ? apsDataCache.getCachedData(dataSetName, dataSetVariant)
				: apsDataCache.getCachedData(dataSetName, dataSetVariant);
		List<ImportedData> outData = new ArrayList<ImportedData>();
		Class<ImportedData> importType = importerFactory.getManagedType();
		Class<ApsDataType> apsType = importerFactory.getMappedType();
		IMapper<ApsDataType, ImportedData> mapper = this.mapperFactory.createMapper(apsType, importType);
		Map environment = new HashMap();
		boolean recursive = true;
		Consumer<ApsDataType> consumer = new Consumer<ApsDataType>() {
			@Override
			public void accept(ApsDataType t) {

				try {
					outData.add(
							mapper.mapInstance(t, importType, DefaultEntitiesFactory.Instance, environment, recursive));
				} catch (MapperException e) {
					String msg = "MAPPER ERROR";
					throw new IllegalStateException(msg, e);
				}

			}

		};
		dao.consumeAll(consumer, apsData);
		return outData;
	}

	@PostMapping("update/{dataSourceName}/{dataSetId}/{dataSetVariant}/")
	public void update(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetName, @PathVariable("dataSetVariant") String dataSetVariant,
			@RequestBody List<ImportedData> imported)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		this.update(dataSourceName, dataSetName, dataSetVariant, imported, false);
	}

	@GetMapping("{dataSourceName}/{dataSetId}/{dataSetVariant}/")
	public List<ImportedData> get(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetName, @PathVariable("dataSetVariant") String dataSetVariant)
			throws ApsDataCacheException, DataModelDaoException, MapperException {

		return get(dataSourceName, dataSetName, dataSetVariant, false);
	}

	@PostMapping("staging/update/{dataSourceName}/{dataSetId}/{dataSetVariant}/")
	public void updateStaging(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetName, @PathVariable("dataSetVariant") String dataSetVariant,
			@RequestBody List<ImportedData> imported)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		this.update(dataSourceName, dataSetName, dataSetVariant, imported, true);
	}

	@GetMapping("staging/{dataSourceName}/{dataSetId}/{dataSetVariant}/")
	public List<ImportedData> getStaging(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetName, @PathVariable("dataSetVariant") String dataSetVariant)
			throws ApsDataCacheException, DataModelDaoException, MapperException {
		return get(dataSourceName, dataSetName, dataSetVariant, true);
	}
}
