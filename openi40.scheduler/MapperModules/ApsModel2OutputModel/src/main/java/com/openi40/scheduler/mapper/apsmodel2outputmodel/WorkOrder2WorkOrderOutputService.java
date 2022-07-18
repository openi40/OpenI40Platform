package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
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
public class WorkOrder2WorkOrderOutputService
		extends AbstractApsModel2OutputModelService<WorkOrder, WorkOrderOutputDto> {

	public WorkOrder2WorkOrderOutputService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, WorkOrder.class, WorkOrderOutputDto.class);

	}

	public void copyValue(WorkOrder originalObject, WorkOrderOutputDto targetObject,
			com.openi40.scheduler.mapper.IEntitiesFactory entityFactory, java.util.Map environment, boolean recursive)
			throws com.openi40.scheduler.mapper.MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setStartExecutionDate(originalObject.getOrderExecution() != null
				&& originalObject.getOrderExecution().getStartDateTime() != null
						? originalObject.getOrderExecution().getStartDateTime()
						: null);
		targetObject.setEndExecutionDate(originalObject.getOrderExecution() != null
				&& originalObject.getOrderExecution().getEndDateTime() != null
						? originalObject.getOrderExecution().getEndDateTime()
						: null);
		targetObject.setPlantCode(originalObject.getPlantCode());
		targetObject.setProductCode(
				originalObject.getProducedPart() != null && originalObject.getProducedPart().getSuppliedItem() != null
						? originalObject.getProducedPart().getSuppliedItem().getCode()
						: null);
		String cycleModel = originalObject.getCycleModel() != null ? originalObject.getCycleModel().getCode() : null;
		targetObject.setCycleCode(cycleModel);
	};

}
