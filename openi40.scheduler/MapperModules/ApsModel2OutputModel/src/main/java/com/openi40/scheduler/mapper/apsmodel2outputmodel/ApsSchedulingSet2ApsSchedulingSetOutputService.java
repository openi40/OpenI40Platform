package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
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
public class ApsSchedulingSet2ApsSchedulingSetOutputService
		extends AbstractApsModel2OutputModelService<ApsSchedulingSet, ApsSchedulingSetOutputDto> {
	ObjectMapper objectMapper = null;

	public ApsSchedulingSet2ApsSchedulingSetOutputService(AutowireCapableBeanFactory beanFactory,
			ObjectMapper objectMapper) {
		super(beanFactory, ApsSchedulingSet.class, ApsSchedulingSetOutputDto.class);
		this.objectMapper = objectMapper;

	}

	public void copyValue(ApsSchedulingSet originalObject, ApsSchedulingSetOutputDto targetObject,
			com.openi40.scheduler.mapper.IEntitiesFactory entityFactory, java.util.Map environment, boolean recursive)
			throws com.openi40.scheduler.mapper.MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (originalObject.getOptions() != null) {
			try {
				targetObject.setOptions(objectMapper.writeValueAsString(originalObject.getOptions()));
			} catch (JsonProcessingException e) {
				throw new MapperException("cannot encode scheduling options in json", e);
			}
		}
		for (WorkOrder wo : originalObject.getWorkOrders()) {
			targetObject.getWorkOrderCodes().add(wo.getCode());
		}
	};

}
