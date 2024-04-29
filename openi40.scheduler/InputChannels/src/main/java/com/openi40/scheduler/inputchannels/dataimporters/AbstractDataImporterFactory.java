package com.openi40.scheduler.inputchannels.dataimporters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.customsupport.ICustomObjectSupportHandler;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.IContextAwareObjext;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsDataModelDao;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public abstract class AbstractDataImporterFactory<ImportedData extends InputDto, MatchingData extends IApsObject>
		implements IDataImporterFactory<ImportedData, MatchingData> {
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	protected Class<ImportedData> importedType = null;
	protected Class<MatchingData> apsType = null;
	protected IApsDataModelDao<MatchingData> apsDataModelDao = null;
	protected IMapperFactory mapperFactory = null;
	protected @Autowired ObjectMapper omapper;
	@Autowired
	protected IContextualBusinessLogicFactory componentsFactory;

	protected AbstractDataImporterFactory(Class<ImportedData> importedType, Class<MatchingData> apsType,
			IMapperFactory mapperFactory, IApsDataModelDao<MatchingData> apsDataModelDao) {
		this.importedType = importedType;
		this.apsType = apsType;
		this.apsDataModelDao = apsDataModelDao;
		this.mapperFactory = mapperFactory;
	}

	@Override
	public Class<ImportedData> getManagedType() {

		return importedType;
	}

	@Override
	public Class<MatchingData> getMappedType() {

		return apsType;
	}

	@Override
	public IDataImporterConsumer<ImportedData> create(ApsData apsData) throws DataModelDaoException, MapperException {
		final IMapper<ImportedData, MatchingData> mapper = mapperFactory.createMapper(getManagedType(),
				getMappedType());
		final Map<String, MatchingData> map = new HashMap<String, MatchingData>();
		final Map environment = new HashMap();
		environment.put("context", apsData);
		final boolean recursive = true;
		Consumer<MatchingData> consumer = new Consumer<MatchingData>() {
			@Override
			public void accept(MatchingData apsData) {
				map.put(apsData.getCode(), apsData);
			}
		};

		ApsEntitiesFactory factory = new ApsEntitiesFactory(apsData);
		this.apsDataModelDao.consumeAll(consumer, apsData);
		IDataImporterConsumer<ImportedData> updatingConsumer = new IDataImporterConsumer<ImportedData>() {
			ICustomObjectSupportHandler customObjectHandler = null;
			boolean customObjectSupportedType = false;
			boolean customObjectBatchInitialization = false;
			int batchInitializationSize = 0;
			List<MatchingData> currentBatch = new ArrayList<MatchingData>();

			@Override
			public void accept(ImportedData importedData) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Begin accept('" + importedData.getCode() + "')");
					try {
						LOGGER.debug("Recvd: " + omapper.writeValueAsString(importedData));
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					if (map.containsKey(importedData.getCode())) {
						if (importedData.getRemoved() != null && importedData.getRemoved()) {
							deleteMatchingEntry(importedData, map.get(importedData.getCode()), apsData);
						} else {
							MatchingData model = map.get(importedData.getCode());
							mapper.copyValue(importedData, model, factory, environment, recursive);
						}
					} else {
						if (importedData.getRemoved() == null || !importedData.getRemoved()) {
							MatchingData item = mapper.mapInstance(importedData, apsType, factory, environment,
									recursive);
							map.put(item.getCode(), item);
							apsDataModelDao.insert(item, apsData);
							if (customObjectHandler == null) {
								customObjectHandler = componentsFactory.create(ICustomObjectSupportHandler.class, item,
										apsData);
								customObjectSupportedType = customObjectHandler
										.isEntityWithCustomObjectSupport(apsType);
								if (customObjectSupportedType) {
									customObjectBatchInitialization = customObjectHandler
											.isBatchEntryInitializationEntity(apsType);
									if (customObjectBatchInitialization)
										batchInitializationSize = customObjectHandler.getBatchEntriesSize(apsType);
								}
							}
							if (customObjectSupportedType) {
								if (customObjectBatchInitialization) {
									currentBatch.add(item);
									if ((currentBatch.size() % batchInitializationSize) == 0) {
										customObjectHandler.initializeEntries(apsType, currentBatch);
										currentBatch.clear();
									}
								} else {
									customObjectHandler.initializeEntry(apsType, item);
								}
							}
						}
					}
				} catch (MapperException | DataModelDaoException e) {
					String msg = "mapping error from " + importedType.getName() + " and " + apsType.getName();
					LOGGER.error(msg);
					throw new IllegalStateException(msg, e);
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("End accept('" + importedData.getCode() + "')");
				}
			}

			@Override
			public void endOfStream() {
				if (customObjectBatchInitialization && !currentBatch.isEmpty()) {
					customObjectHandler.initializeEntries(apsType, currentBatch);
					currentBatch.clear();
				}
				
			}

		};
		return updatingConsumer;
	}

	protected static class ApsEntitiesFactory implements IEntitiesFactory, IContextAwareObjext {
		ApsData apsData = null;

		ApsEntitiesFactory(ApsData apsData) {
			this.apsData = apsData;
		}

		@Override
		public <EntityType> EntityType create(Class<EntityType> type, Map environment) throws MapperException {
			EntityType out = null;
			try {
				Constructor<EntityType> constructor = type.getConstructor(ApsData.class);
				out = constructor.newInstance(apsData);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				out = DefaultEntitiesFactory.Instance.create(type, environment);

			}
			return out;
		}

		@Override
		public ApsData getContext() {

			return apsData;
		}

	}

	protected void deleteMatchingEntry(ImportedData importedData, MatchingData matchingData, ApsData context) {
		try {
			apsDataModelDao.delete(matchingData, context);
		} catch (DataModelDaoException e) {
			throw new OpenI40Exception("Cannot delete object " + matchingData.toString(), e);
		}
	}
}
