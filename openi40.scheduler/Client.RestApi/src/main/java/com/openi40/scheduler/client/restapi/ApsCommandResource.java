package com.openi40.scheduler.client.restapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SchedulingPriorityOptionDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SortOptionDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SupplyResolutionStrategyHolder;
import com.openi40.scheduler.client.model.aps.ApsSchedulingSetDto;
import com.openi40.scheduler.client.restapi.configuration.RestApiConfig;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractApsCommandResource;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractApsLogicNotifiedObjectsFactory;
import com.openi40.scheduler.client.websocketproto.model.WSScheduleTaskResponseItem;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SchedulingPriorities;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SortDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SortOption;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.rules.MaterialRule.SupplyResolutionStrategy;
import com.openi40.scheduler.model.tasks.Task;

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
@RequestMapping("schedulerClient/ApsCommand")
@Api
public class ApsCommandResource extends AbstractApsCommandResource<ApsDataDto, ApsSchedulingSetDto> {
	static HashMap<Class, Class> dataMap = new HashMap<Class, Class>();
	static {
		dataMap.put(Task.class, WSScheduleTaskResponseItem.class);
	}

	static class ApsLogicNotifiedObjectsFactory extends AbstractApsLogicNotifiedObjectsFactory {

		protected ApsLogicNotifiedObjectsFactory(SimpMessagingTemplate messagingTemplate) {
			super(com.openi40.commons.webconfigs.WebSocketChannels.COMMON_SCHEDULING_TOPIC_CHANNEL_PREFIX, dataMap, messagingTemplate);

		}

	}

	public ApsCommandResource(@Autowired IApsDataCacheAggregator apsDataCache, @Autowired IMapperFactory mapperFactory,
			@Autowired ILazyContextualBusinessLogicFactoryLoader lazyAutowire,
			@Autowired SimpMessagingTemplate simpMessagingTemplate, @Autowired RestApiConfig config) {
		super(ApsDataDto.class, ApsSchedulingSetDto.class, apsDataCache, mapperFactory, lazyAutowire,
				new ApsLogicNotifiedObjectsFactory(simpMessagingTemplate), config);

	}
    @Autowired IWorkOrderDao workOrderDao;
	protected void syncronizeAction(ApsSchedulingSet data, ApsSchedulingSetDto actionDto) {
		if (actionDto.getAlgorithmType() != null)
			data.setAlgorithmType(actionDto.getAlgorithmType());
		if (actionDto.getOptions() != null) {
			if (actionDto.getOptions().getSchedulingPrioritiesOptions() != null) {
				data.getOptions().getSchedulingPrioritiesOptions().clear();

				for (SchedulingPriorityOptionDto pOption : actionDto.getOptions().getSchedulingPrioritiesOptions()) {
					SchedulingPriorities value = SchedulingPriorities.valueOf(SchedulingPriorities.class,
							pOption.getCode());
					data.getOptions().getSchedulingPrioritiesOptions().add(value);
				}
			}
			if (actionDto.getOptions().getSortOptions() != null) {
				data.getOptions().getSortOptions().clear();
				for (SortOptionDto so : actionDto.getOptions().getSortOptions()) {
					SortDirection sod = so.getSortDirection() != null ? SortDirection.valueOf(so.getSortDirection())
							: SortDirection.ASC;
					data.getOptions().getSortOptions()
							.add(new SortOption(so.getPropertyName(), so.getDescription(), so.getDescription(), sod));
				}
			}
			if (actionDto.getOptions().getSupplyResolutionStrategies() != null) {
				data.getOptions().getSupplyResolutionStrategies().clear();
				List<SupplyResolutionStrategy> availableOptions = ApsLogicOptions.SUPPLY_RESOLUTION_STRATEGY_LIST;
				for (SupplyResolutionStrategy ao : availableOptions) {
					for (SupplyResolutionStrategyHolder aoDto : actionDto.getOptions()
							.getSupplyResolutionStrategies()) {
						if (aoDto.getStrategy()!=null && aoDto.getStrategy().getCode()!=null && aoDto.getStrategy().getCode().equals(ao.getCode()) ) {
							 if (!ao.isUserCanDisable() || aoDto.isEnabled()) {
								 data.getOptions().getSupplyResolutionStrategies().add(ao);
							 }
						}
					}
				}
			}
			if (actionDto.getWorkOrders()==null) {
				actionDto.setWorkOrders(new ArrayList<>());				
			}
			data.removeWorkOrders();
			for(ClientDto wo:actionDto.getWorkOrders()) {
				try {
					WorkOrder order = workOrderDao.findById(wo.getId(),data.getContext());
					data.addWorkOrder(order);
				} catch (DataModelDaoException e) {
					LOGGER.error("error in work order access in memory model",e);
					throw new OpenI40Exception("error in work order access in memory model",e);
				}
			}
		}
	}

	@Override
	protected void updateActions(ApsData data, List<ApsSchedulingSetDto> actions) {
		Map<String, ApsSchedulingSetDto> map = new HashMap();
		for (ApsSchedulingSetDto action : actions) {
			if (action.getId() != null) {
				map.put(action.getId(), action);
			}
		}
		if (data.getSchedulingSets() != null) {
			for (ApsSchedulingSet action : data.getSchedulingSets()) {
				if (map.containsKey(action.getId())) {
					syncronizeAction(action, map.remove(action.getId()));
				}
			}
		}
		if (!map.isEmpty()) {
			for(ApsSchedulingSetDto entry:map.values()) {
				addAction(data, entry);
			}
		}

	}

	@Override
	protected void addAction(ApsData data, ApsSchedulingSetDto mappedAction) {
		ApsSchedulingSet apsAction = new ApsSchedulingSet(data);
		apsAction.setOptions(new ApsLogicOptions());
		data.getSchedulingSets().add(apsAction);
		syncronizeAction(apsAction, mappedAction);

	}

}
