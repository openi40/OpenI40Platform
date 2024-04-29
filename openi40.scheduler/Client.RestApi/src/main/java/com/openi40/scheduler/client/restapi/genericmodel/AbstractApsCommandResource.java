package com.openi40.scheduler.client.restapi.genericmodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.restapi.configuration.RestApiConfig;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public abstract class AbstractApsCommandResource<ApsDataMappedType, ApsActionMappedType> extends AbstractBaseResource {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractApsCommandResource.class);

	Class<ApsDataMappedType> scenarioType = null;
	Class<ApsActionMappedType> actionType = null;
	IApsLogicNotifiedObjectsFactory stompNotifierFactory = null;
	RestApiConfig config = null;

	protected AbstractApsCommandResource(Class<ApsDataMappedType> scenarioType, Class<ApsActionMappedType> actionType,
			IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire,
			IApsLogicNotifiedObjectsFactory stompNotifierFactory, RestApiConfig config) {
		super(apsDataCache, mapperFactory, lazyAutowire);
		this.stompNotifierFactory = stompNotifierFactory;
		this.scenarioType = scenarioType;
		this.config = config;
		this.actionType = actionType;
	}

	protected abstract void updateActions(ApsData data, List<ApsActionMappedType> actions);

	protected abstract void addAction(ApsData data, ApsActionMappedType mappedAction);

	public static class StompOperationResult {
		boolean successfull = false;

		public boolean isSuccessfull() {
			return successfull;
		}

		public void setSuccessfull(boolean successfull) {
			this.successfull = successfull;
		}
	};

	@PostConstruct
	protected void Initialize() {
	}

	@GetMapping(value = "schedule/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsDataMappedType> schedule(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin schedule(" + dataSetId + "," + variantId + ")");
		}
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);

			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Remapping from ApsData to model: " + this.scenarioType.getName());
			}
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End schedule(" + dataSetId + "," + variantId + ")");
			}
			return ResponseEntity.ok().body(apsDataDto);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@GetMapping(value = "reload/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsDataMappedType> reload(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin reload(" + dataSetId + "," + variantId + ")");
		}
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.reload(dataSetId, variantId);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Remapping from ApsData to model: " + this.scenarioType.getName());
			}
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End reload(" + dataSetId + "," + variantId + ")");
			}
			return ResponseEntity.ok().body(apsDataDto);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	
	public static class ApsSaveResult {
		private boolean savedSuccessfully = false;
		private String errorMessage = null;
		public boolean isSavedSuccessfully() {
			return savedSuccessfully;
		}
		public void setSavedSuccessfully(boolean savedSuccessfully) {
			this.savedSuccessfully = savedSuccessfully;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	}

	@GetMapping(value = "save/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsSaveResult> save(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin save(" + dataSetId + "," + variantId + ")");
		}
		ApsSaveResult result = new ApsSaveResult();
		try {
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			apsDataCache.save(dataSetId, variantId);

			result.setSavedSuccessfully(true);

		} catch (ApsDataCacheException e) {
			LOGGER.error("Error saving", e);
			result.setErrorMessage("Error saving:" + e.getMessage());
			result.setSavedSuccessfully(false);
		} finally {
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End save(" + dataSetId + "," + variantId + ")");
		}
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "dropSchedulingSet/{dataSourceName}/{dataSetId}/{variantId}/{schedulingSetId}")
	public ResponseEntity<ApsDataMappedType> dropSchedulingSet(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("schedulingSetId") String schedulingSetId) {
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			apsData.removeSchedulingSetById(schedulingSetId);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Remapping from ApsData to model: " + this.scenarioType.getName());
			}
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End dropSchedulingSet(" + dataSetId + "," + variantId + "," + schedulingSetId + ")");
			}
			return ResponseEntity.ok().body(apsDataDto);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}

	}

	@GetMapping(value = "stompSchedule/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<StompOperationResult> stompSchedule(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		StompOperationResult result = new StompOperationResult();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin stompSchedule(" + dataSetId + "," + variantId + ")");
		}
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End stompSchedule(" + dataSetId + "," + variantId + ")");
			}
			return ResponseEntity.ok().body(result);
		} catch (ApsDataCacheException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@Autowired
	IWorkOrderDao workOrderDao;

	@GetMapping(value = "scheduleAll/{dataSourceName}/{dataSetId}/{variantId}/{algorithmType}/")
	public ResponseEntity<ApsDataMappedType> scheduleAll(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("algorithmType") String algorithmType) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Begin scheduleAll(" + dataSetId + "," + variantId + "," + algorithmType + ")");
			}
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			apsData.getSchedulingSets().clear();
			ApsSchedulingSet action = new ApsSchedulingSet(apsData);
			action.setAlgorithmType(algorithmType);
			List<WorkOrder> worders = workOrderDao.findAll(apsData);
			for (WorkOrder workOrder : worders) {
				action.addWorkOrder(workOrder);
			}
			apsData.getSchedulingSets().add(action);
			IApsLogic apsLogic = ComponentFactory.create(IApsLogic.class, algorithmType, action, apsData);
			action.setOptions(apsLogic.createDefaultOptions(action));
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Remapping from ApsData to model: " + this.scenarioType.getName());
			}
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End scheduleAll(" + dataSetId + "," + variantId + "," + algorithmType + ")");
			}
			return ResponseEntity.ok().body(apsDataDto);
		} catch (ApsDataCacheException | MapperException | DataModelDaoException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@GetMapping(value = "stompScheduleAll/{dataSourceName}/{dataSetId}/{variantId}/{algorithmType}/")
	public ResponseEntity<StompOperationResult> stompScheduleAll(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("algorithmType") String algorithmType) {
		try {
			StompOperationResult result = new StompOperationResult();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Begin stompScheduleAll(" + dataSetId + "," + variantId + "," + algorithmType + ")");
			}
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			apsData.getSchedulingSets().clear();
			ApsSchedulingSet action = new ApsSchedulingSet(apsData);
			action.setAlgorithmType(algorithmType);
			List<WorkOrder> worders = workOrderDao.findAll(apsData);
			for (WorkOrder workOrder : worders) {
				action.addWorkOrder(workOrder);
			}
			apsData.getSchedulingSets().add(action);
			IApsLogic apsLogic = ComponentFactory.create(IApsLogic.class, algorithmType, action, apsData);
			action.setOptions(apsLogic.createDefaultOptions(action));
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End stompScheduleAll(" + dataSetId + "," + variantId + "," + algorithmType + ")");
			}
			return ResponseEntity.ok().body(result);
		} catch (ApsDataCacheException | DataModelDaoException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@GetMapping(value = "refresh/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsDataMappedType> refresh(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin refresh(" + dataSetId + "," + variantId + ")");
		}
		try {
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Remapping from ApsData to model: " + this.scenarioType.getName());
			}
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End refresh(" + dataSetId + "," + variantId + ")");
			}
			return ResponseEntity.ok().body(apsDataDto);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to get from cache", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}

	}

	@PostMapping(value = "add/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsDataMappedType> addAction(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@RequestBody ApsActionMappedType action) {
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			this.addAction(apsData, action);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			return ResponseEntity.ok().body(apsDataDto);

		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@PostMapping(value = "updateActions/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsDataMappedType> updateActions(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@RequestBody List<ApsActionMappedType> actions) {
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			this.updateActions(apsData, actions);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);
			IMapper<ApsData, ApsDataMappedType> mapper = mapperFactory.createMapper(ApsData.class, scenarioType);
			ApsDataMappedType apsDataDto = mapper.mapInstance(apsData, scenarioType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			return ResponseEntity.ok().body(apsDataDto);

		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@PostMapping(value = "autoSetTasksActions/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<List<ApsActionMappedType>> autoSetTasks(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@RequestBody List<ApsActionMappedType> actions) {
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IEntitiesFactory reverseFactory = new IEntitiesFactory() {
				@Override
				public <EntityType> EntityType create(Class<EntityType> type, Map environment) throws MapperException {
					try {
						return type.getConstructor(ApsData.class).newInstance(apsData);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException e) {
						String msg = "Cannot instantiate " + type.getName()
								+ " with constructor with ApsData context parameter";
						LOGGER.error(msg, e);
						throw new MapperException(msg, e);
					} catch (NoSuchMethodException exc) {
						try {
							return type.newInstance();
						} catch (InstantiationException | IllegalAccessException e) {
							String msg = "Cannot instantiate " + type.getName() + " with default constructor";
							LOGGER.error(msg, e);
							throw new MapperException(msg, e);
						}
					}
				}
			};
			IMapper<ApsActionMappedType, ApsSchedulingSet> actionSetMapper = mapperFactory.createMapper(this.actionType,
					ApsSchedulingSet.class);

			List<ApsSchedulingSet> schedulingSets = new ArrayList<>();
			for (ApsActionMappedType apsActionMappedType : actions) {
				ApsSchedulingSet set = actionSetMapper.mapInstance(apsActionMappedType, ApsSchedulingSet.class,
						reverseFactory, new HashMap<>(), true);
				schedulingSets.add(set);
			}
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			
			List<ApsSchedulingSet> resultingSets = scheduler.autoSetTasks(apsData,schedulingSets);
			IMapper<ApsSchedulingSet, ApsActionMappedType> outMapper = mapperFactory
					.createMapper(ApsSchedulingSet.class, this.actionType);

			List<ApsActionMappedType> outList = new ArrayList<>();
			for (ApsSchedulingSet apsSchedulingSet : resultingSets) {
				ApsActionMappedType translatedSet = outMapper.mapInstance(apsSchedulingSet, this.actionType,
						DefaultEntitiesFactory.Instance, new HashMap(), true);
				outList.add(translatedSet);
			}

			return ResponseEntity.ok().body(outList);

		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@GetMapping(value = "newCleanApsAction/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<ApsActionMappedType> getCleanApsAction(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId) {
		try {
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			IMapper<ApsSchedulingSet, ApsActionMappedType> mapper = mapperFactory.createMapper(ApsSchedulingSet.class,
					actionType);
			ApsSchedulingSet apsAction = new ApsSchedulingSet(apsData);
			apsAction.setAlgorithmType(ApsLogics.FORWARD_APS);
			IApsLogic logic = ComponentFactory.create(IApsLogic.class, apsAction, apsData);
			apsAction.setOptions(logic.createDefaultOptions(apsAction));
			ApsActionMappedType outData = mapper.mapInstance(apsAction, actionType, DefaultEntitiesFactory.Instance,
					new HashMap(), true);
			return ResponseEntity.ok().body(outData);
		} catch (ApsDataCacheException | MapperException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}

	}

	@PostMapping(value = "stompAdd/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<StompOperationResult> stompAddAction(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@RequestBody ApsActionMappedType action) {
		try {
			StompOperationResult result = new StompOperationResult();
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			this.addAction(apsData, action);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);

			return ResponseEntity.ok().body(result);

		} catch (ApsDataCacheException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}

	@PostMapping(value = "stompUpdateActions/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<StompOperationResult> stompUpdateActions(
			@PathVariable("dataSourceName") String dataSourceName, @PathVariable("dataSetId") String dataSetId,
			@PathVariable("variantId") String variantId, @RequestBody List<ApsActionMappedType> actions) {
		try {
			StompOperationResult result = new StompOperationResult();
			IContextualBusinessLogicFactory ComponentFactory = getComponentFactory();
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData apsData = apsDataCache.getCachedData(dataSetId, variantId);
			this.updateActions(apsData, actions);
			IApsLogicComposer scheduler = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
			ApsLogicNotifiedObjects observer = this.stompNotifierFactory != null && config.isUseWebSocket()
					? this.stompNotifierFactory.create(dataSetId, variantId, mapperFactory)
					: null;
			scheduler.schedule(apsData, observer);

			return ResponseEntity.ok().body(result);

		} catch (ApsDataCacheException e) {
			LOGGER.error("Unable to schedule", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
		}
	}
}
