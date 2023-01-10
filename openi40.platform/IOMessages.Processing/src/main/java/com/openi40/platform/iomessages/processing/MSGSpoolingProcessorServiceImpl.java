package com.openi40.platform.iomessages.processing;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.iomessages.spooler.config.MSGElaborationConfig;
import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingProcessorService;
import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingService.MsgIngestionResult;
import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker.ApsMessageResponse;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.TypeMap.TypeMetaInfos;
import com.openi40.scheduler.mapper.iomessages2apsmodel.InputIOMessage2ApsMessageModelService;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;
@Singleton
@Service
public class MSGSpoolingProcessorServiceImpl implements IMSGSpoolingProcessorService {
	private @Autowired(required = false) MSGElaborationConfig elaborationConfig;
	private @Autowired(required = false) List<IIOMessageHandlingListener> ioMessageHandlingListeners;
	private @Autowired(required = true) IApsDataCacheAggregator apsDataCacheAggregator;
	private @Autowired(required = true) ObjectMapper mapper;
	private @Autowired IContextualBusinessLogicFactory ComponentFactory;
	private @Autowired IMapperFactory mapperFactory;

	protected static class ApsEntitiesFactory
			implements com.openi40.scheduler.mapper.IEntitiesFactory, com.openi40.scheduler.model.IContextAwareObjext {
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

	static Logger LOGGER = LoggerFactory.getLogger(MSGSpoolingProcessorServiceImpl.class);

	public MSGSpoolingProcessorServiceImpl() {

	}

	private AbstractBaseMessage translate(AbstractBaseIOMessage message, ApsData context) throws MapperException {
		TypeMetaInfos mappedType = InputIOMessage2ApsMessageModelService.typeMap.get(message.getClass());
		if (mappedType == null)
			throw new OpenI40Exception("Cannot map data type=>" + message.getClass().getName());
		HashMap environment = new HashMap<>();
		environment.put("context", context);
		IMapper<AbstractBaseIOMessage, AbstractBaseMessage> mapper = this.mapperFactory.createMapper(message.getClass(),
				mappedType.toType);
		return mapper.mapInstance(message, mappedType.toType, new ApsEntitiesFactory(context), environment, true);
	}

	private List<IIOMessageHandlingListener> getListeners() {
		if (ioMessageHandlingListeners == null)
			return new ArrayList<>();
		return ioMessageHandlingListeners;
	}

	private void notifyProcessedCorrectly(String dataSourceName, String dataSetName, String dataSetVariant,
			AbstractBaseMessage message) {
		getListeners().forEach(entry -> {
			try {
				entry.processedCorrectly(dataSourceName, dataSetName, dataSetVariant, message);
			} catch (Throwable th) {
				LOGGER.error("Error while notifying correct message processing", th);
			}
		});
	}

	private void notifyProcessedUncorrectly(String dataSourceName, String dataSetName, String dataSetVariant,
			AbstractBaseMessage message, List<MessageHandlingErrorMessage> errors) {
		getListeners().forEach(entry -> {
			try {
				entry.processedUncorrectly(dataSourceName, dataSetName, dataSetVariant, message, errors);
			} catch (Throwable th) {
				LOGGER.error("Error while notifying uncorrect message processing", th);
			}
		});
	}

	@Override
	public MsgIngestionResult apply(AbstractBaseIOMessage t) {
		if (t == null)
			throw new OpenI40Exception("Cannot be called with null message passed");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin apply(" + t.toString() + ")");
		}
		MsgIngestionResult result = new MsgIngestionResult();
		if (elaborationConfig == null) {
			throw new OpenI40Exception("MSGElaborationConfig not set or isEnableMessagesElaboration=false");
		}
		if (elaborationConfig.isEnableMessagesElaboration()) {
			IApsDataCache cache = this.apsDataCacheAggregator.getDataCache(elaborationConfig.getDataSourceName());
			ApsData apsData;
			try {

				apsData = cache.getCachedData(elaborationConfig.getDataSetName(),
						elaborationConfig.getDataSetVariant());
				IApsMessagesBroker broker = ComponentFactory.create(IApsMessagesBroker.class, apsData, apsData);
				AbstractBaseMessage message = translate(t, apsData);
				ApsMessageResponse response = broker.handleMessage(message, apsData, null);

				result.setSuccessfull(response.isMessageHandled());
				if (response.isMessageHandled()) {
					cache.save(elaborationConfig.getDataSetName(), elaborationConfig.getDataSetVariant());
					notifyProcessedCorrectly(elaborationConfig.getDataSourceName(), elaborationConfig.getDataSetName(),
							elaborationConfig.getDataSetVariant(), message);
				} else if (!response.isMessageHandled() && !response.getErrors().isEmpty()) {
					result.setErrorCode("ERROR_HANDLING_MESSAGE");
					try {
						result.setErrorMessage(mapper.writeValueAsString(response.getErrors()));
					} catch (JsonProcessingException e) {

					}
					notifyProcessedUncorrectly(elaborationConfig.getDataSourceName(),
							elaborationConfig.getDataSetName(), elaborationConfig.getDataSetVariant(), message,
							response.getErrors());

				}

			} catch (Throwable e) {
				String msg = "error in processing message";
				throw new OpenI40Exception(msg, e);
			}
		} else {
			result.setSuccessfull(false);
			result.setErrorCode("IOMESSAGES-PROCESSING-DISABLED");
			result.setErrorMessage("The handling of IOMEssages are disabled in this installation");
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End apply(" + t.toString() + ")");
		}
		return result;
	}

}
