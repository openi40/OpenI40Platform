package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.cycle.BatchTransferType;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
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
public class TaskEdge2TaskRelationOutputService
		extends AbstractApsModel2OutputModelService<TaskEdge, TaskRelationOutputDto> {

	public TaskEdge2TaskRelationOutputService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, TaskEdge.class, TaskRelationOutputDto.class);

	}

	@Override
	public void copyValue(TaskEdge originalObject, TaskRelationOutputDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setCode(originalObject.getId());
		targetObject.setConsumerTaskCode(originalObject.getConsumerTask().getCode());
		targetObject.setSupplierTaskCode(originalObject.getProducerTask().getCode());
		targetObject.setConsumerWorkOrder(originalObject.getConsumerTask().getWorkOrderCode());
		targetObject.setSupplierWorkOrder(originalObject.getProducerTask().getWorkOrderCode());
		targetObject
				.setAlignmentType(originalObject.getAlignmentType() != null ? originalObject.getAlignmentType().name()
						: PeriodsAlignmentType.START_AFTER_END.name());
		targetObject
				.setTimeRangeType(originalObject.getTimeRangeType() != null ? originalObject.getTimeRangeType().name()
						: TimeSegmentType.WORK_TIME.name());
		targetObject.setPeggingCode(
				originalObject.getRappresentedPegging() != null ? originalObject.getRappresentedPegging().getId()
						: null);
		targetObject.setPeggingEdge(originalObject.getRappresentedPegging() != null ? true : false);
		targetObject.setBomItemCode(
				originalObject.getBomItemModel() != null ? originalObject.getBomItemModel().getCode() : null);
		targetObject.setConsumptionTransferType(originalObject.getConsumingBatchInfo() != null
				&& originalObject.getConsumingBatchInfo().getBatchTransferType() != null
						? originalObject.getConsumingBatchInfo().getBatchTransferType().name()
						: BatchTransferType.TRANSFER_ALL.name());
		targetObject.setConsumptionBatchQty(
				originalObject.getConsumingBatchInfo() != null ? originalObject.getConsumingBatchInfo().getBatchQty()
						: 1.0);
		targetObject.setOffsetMillisecs(originalObject.getOffsetMillisecs());
	}

}
