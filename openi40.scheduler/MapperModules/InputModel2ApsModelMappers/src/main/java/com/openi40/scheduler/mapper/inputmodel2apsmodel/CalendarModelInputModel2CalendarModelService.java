package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoExceptionRuleInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoWorkingTimeRuleInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
import com.openi40.scheduler.model.time.TimesheetMetaInfoExceptionRule;
import com.openi40.scheduler.model.time.TimesheetMetaInfoWorkingTimeRule;
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
@Service
public class CalendarModelInputModel2CalendarModelService
		extends AbstractInputModel2ApsModelService<TimesheetMetaInfoInputDto, TimesheetMetaInfo> {
	@Autowired 
	public CalendarModelInputModel2CalendarModelService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(TimesheetMetaInfoInputDto.class, TimesheetMetaInfo.class, beanFactory, businessModelFactory);

	}

	@Override
	public void copyValue(TimesheetMetaInfoInputDto originalObject, TimesheetMetaInfo targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.getExceptionRules().clear();
		targetObject.getWorkingTimeRules().clear();
		List<TimesheetMetaInfoExceptionRuleInputDto> exceptions = originalObject.getExceptionRules();
		IMapper<TimesheetMetaInfoExceptionRuleInputDto, TimesheetMetaInfoExceptionRule> cmexcMapper = this
				.getMapperFactory()
				.createMapper(TimesheetMetaInfoExceptionRuleInputDto.class, TimesheetMetaInfoExceptionRule.class);
		for (TimesheetMetaInfoExceptionRuleInputDto timesheetMetaInfoExceptionRuleInputDto : exceptions) {
			TimesheetMetaInfoExceptionRule object = cmexcMapper.mapInstance(timesheetMetaInfoExceptionRuleInputDto,
					TimesheetMetaInfoExceptionRule.class, entityFactory, environment, recursive);
			targetObject.getExceptionRules().add(object);

		}
		IMapper<TimesheetMetaInfoWorkingTimeRuleInputDto, TimesheetMetaInfoWorkingTimeRule> cmwtrMapper = this
				.getMapperFactory()
				.createMapper(TimesheetMetaInfoWorkingTimeRuleInputDto.class, TimesheetMetaInfoWorkingTimeRule.class);
		List<TimesheetMetaInfoWorkingTimeRuleInputDto> workingTimeRules = originalObject.getWorkingTimeRules();
		for (TimesheetMetaInfoWorkingTimeRuleInputDto timesheetMetaInfoWorkingTimeRuleInputDto : workingTimeRules) {
			TimesheetMetaInfoWorkingTimeRule object = cmwtrMapper.mapInstance(timesheetMetaInfoWorkingTimeRuleInputDto,
					TimesheetMetaInfoWorkingTimeRule.class, entityFactory, environment, recursive);
			targetObject.getWorkingTimeRules().add(object);
		}
	}

	@Override
	public TimesheetMetaInfo mapInstance(TimesheetMetaInfoInputDto object, Class<TimesheetMetaInfo> targetType,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		return super.mapInstance(object, targetType, entityFactory, environment, recursive);
	}
}