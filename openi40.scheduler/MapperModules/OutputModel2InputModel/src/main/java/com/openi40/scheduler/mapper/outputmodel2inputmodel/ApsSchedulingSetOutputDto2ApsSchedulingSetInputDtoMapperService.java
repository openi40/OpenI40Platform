package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
import com.openi40.scheduler.input.model.ScheduledWorkOrderInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;

@Service
public class ApsSchedulingSetOutputDto2ApsSchedulingSetInputDtoMapperService
		extends AbstractOutputDto2InputDtoMapper<ApsSchedulingSetOutputDto, ApsSchedulingSetInputDto> {
	public ApsSchedulingSetOutputDto2ApsSchedulingSetInputDtoMapperService(
			@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(ApsSchedulingSetOutputDto.class, ApsSchedulingSetInputDto.class, autowireCapableFactory);
	}

	@Override
	public void copyValue(ApsSchedulingSetOutputDto originalObject, ApsSchedulingSetInputDto targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (targetObject.getScheduledWorkOrders() != null && targetObject.getScheduledWorkOrders().isEmpty()) {
			if (originalObject.getWorkOrderCodes() != null) {
				List<String> woCodes = originalObject.getWorkOrderCodes();
				for (String woCode : woCodes) {
					ScheduledWorkOrderInputDto wo = new ScheduledWorkOrderInputDto();
					wo.setCode(originalObject.getCode() + "-" + woCode);
					wo.setApsSchedulingSetCode(originalObject.getCode());
					wo.setWorkOrderCode(woCode);
					targetObject.getScheduledWorkOrders().add(wo);
				}
			}
		}
	}
}