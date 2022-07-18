package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class WorkOrderInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<WorkOrderInputDto, WorkOrder> {

	public WorkOrderInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(WorkOrderInputDto.class, WorkOrder.class, beanFactory, businessModelFactory);

	}
	@Override
	public void copyValue(WorkOrderInputDto originalObject, WorkOrder targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		
	}

}
