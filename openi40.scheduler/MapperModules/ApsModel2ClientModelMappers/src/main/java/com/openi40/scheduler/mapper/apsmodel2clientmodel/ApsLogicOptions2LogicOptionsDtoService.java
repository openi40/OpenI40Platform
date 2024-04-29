package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SchedulingPriorityOptionDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SortOptionDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SupplyResolutionStrategyDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto.SupplyResolutionStrategyHolder;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SchedulingPriorities;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SortOption;
import com.openi40.scheduler.model.rules.MaterialRule.SupplyResolutionStrategy;
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
public class ApsLogicOptions2LogicOptionsDtoService
		extends AbstractReflectorMapper<ApsLogicOptions, ApsLogicOptionsDto>
		implements IMapper<ApsLogicOptions, ApsLogicOptionsDto> {

	protected ApsLogicOptions2LogicOptionsDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ApsLogicOptions.class, ApsLogicOptionsDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(ApsLogicOptions originalObject, ApsLogicOptionsDto targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (targetObject.getSchedulingPrioritiesOptions().isEmpty()) {
			if (!originalObject.getSchedulingPrioritiesOptions().isEmpty()) {
				for (SchedulingPriorities opt : originalObject.getSchedulingPrioritiesOptions()) {
					SchedulingPriorityOptionDto entry = new SchedulingPriorityOptionDto(opt.getCode(),
							opt.getDescription(), opt.getLongDescription());
					targetObject.getSchedulingPrioritiesOptions().add(entry);
				}
			}
		}
		if (targetObject.getSortOptions().isEmpty()) {
			for (SortOption sort : originalObject.getSortOptions()) {
				SortOptionDto sortO = new SortOptionDto(sort.getPropertyName(), sort.getDescription(),
						sort.getSortDirection().toString());
				targetObject.getSortOptions().add(sortO);
			}
		}
		if (targetObject.getSupplyResolutionStrategies().isEmpty()) {
			for (SupplyResolutionStrategy option : ApsLogicOptions.SUPPLY_RESOLUTION_STRATEGY_LIST) {
				SupplyResolutionStrategyDto value = new SupplyResolutionStrategyDto(option.getCode(),
						option.getDescription(), option.getLongDescription(), option.isUserCanDisable());
				SupplyResolutionStrategyHolder strategyDto = new SupplyResolutionStrategyHolder(value,
						originalObject.getSupplyResolutionStrategies().contains(option));
				targetObject.getSupplyResolutionStrategies().add(strategyDto);
			}
		}
	}
}