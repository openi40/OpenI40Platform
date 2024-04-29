package com.openi40.scheduler.client.restapi.genericmodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.IApsLogicObserver;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
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
public class AbstractApsLogicNotifiedObjectsFactory implements IApsLogicNotifiedObjectsFactory {

	Map<Class, Class> typesTranslationMap = null;
	SimpMessagingTemplate messagingTemplate = null;
	String channelPrefix = null;

	protected AbstractApsLogicNotifiedObjectsFactory(String channelPrefix, Map<Class, Class> typesTranslationMap, SimpMessagingTemplate messagingTemplate) {
		this.typesTranslationMap = typesTranslationMap;
		this.messagingTemplate = messagingTemplate;
		this.channelPrefix = channelPrefix;
	}

	protected class InternalApsLogicObserver implements IApsLogicObserver {
		IMapperFactory mapperFactory = null;
		String dataSetId, variantId;

		InternalApsLogicObserver(String dataSetId, String variantId, IMapperFactory mapperFactory) {
			this.mapperFactory = mapperFactory;
			this.dataSetId = dataSetId;
			this.variantId = variantId;
		}

		@Override
		public void startProcessingElement(IApsObject object) {

		}

		@Override
		public void processedElement(IApsObject object) {
			if (object != null) {
				Class wantedType = typesTranslationMap.get(object.getClass());
				try {
					IMapper mapper = this.mapperFactory.createMapper(object.getClass(), wantedType);
					Object dto = mapper.mapInstance(object, wantedType, DefaultEntitiesFactory.Instance, new HashMap(), true);
					messagingTemplate.convertAndSend(channelPrefix + "/" + dataSetId + "/" + variantId, dto);
				} catch (MapperException e) {

					e.printStackTrace();
				}
			}

		}

	}

	protected class InternalConstraintsSolutionListener implements IRuleSolutionListener {
		IMapperFactory mapperFactory = null;
		String dataSetId, variantId;

		InternalConstraintsSolutionListener(String dataSetId, String variantId, IMapperFactory mapperFactory) {
			this.mapperFactory = mapperFactory;
			this.dataSetId = dataSetId;
			this.variantId = variantId;
		}

		@Override
		public void onConstraintSolutionEvent(RulePlanningEvent event) {

		}

	}

	@Override
	public ApsLogicNotifiedObjects create(String dataSetId, String variantId, IMapperFactory mapperFactory) {
		ApsLogicNotifiedObjects returned = new ApsLogicNotifiedObjects();
		returned.setObserver(new InternalApsLogicObserver(dataSetId, variantId, mapperFactory));
		returned.setConstraintSolutionListener(new InternalConstraintsSolutionListener(dataSetId, variantId, mapperFactory));
		return returned;
	}

}
