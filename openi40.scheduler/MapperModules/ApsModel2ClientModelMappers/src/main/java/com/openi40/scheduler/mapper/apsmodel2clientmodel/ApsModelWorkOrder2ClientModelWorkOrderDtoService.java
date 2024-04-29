package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.orders.WorkOrderDto;
import com.openi40.scheduler.client.model.orders.WorkOrderDto.PeggingDto;
import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Service
public class ApsModelWorkOrder2ClientModelWorkOrderDtoService extends AbstractReflectorMapper<WorkOrder, WorkOrderDto>
		implements IMapper<WorkOrder, WorkOrderDto> {
	public ApsModelWorkOrder2ClientModelWorkOrderDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, WorkOrder.class, WorkOrderDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	List<Task> getChildsOnSameWorkOrder(Task rootTask, WorkOrder wo) {
		List<Task> outValue = new ArrayList<Task>();
		if (rootTask.getWorkOrder() == null || rootTask.getWorkOrder() == wo) {
			for (TaskEdge child : rootTask.getChildTasks()) {
				outValue.addAll(getChildsOnSameWorkOrder(child.getProducerTask(), wo));
			}
			outValue.add(rootTask);
		}
		return outValue;
	}

	@Override
	public void copyValue(WorkOrder originalObject, WorkOrderDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (targetObject.getPeggings() != null && targetObject.getPeggings().isEmpty()) {
			IMapper<Pegging, PeggingDto> mapper = getMapperFactory().createMapper(Pegging.class, PeggingDto.class);
			for (Pegging pegging : originalObject.getPeggings()) {
				PeggingDto pegDto = mapper.mapInstance(pegging, PeggingDto.class, entityFactory, environment,
						recursive);
				pegDto.setConsumerWorkOrder(new ClientDto());
				pegDto.getConsumerWorkOrder()
						.setCode(pegging.getConsumer() != null ? pegging.getConsumer().getCode() : null);
				pegDto.getConsumerWorkOrder()
						.setId(pegging.getConsumer() != null ? pegging.getConsumer().getId() : null);
				pegDto.getConsumerWorkOrder()
						.setDescription(pegging.getConsumer() != null ? pegging.getConsumer().getDescription() : null);
				pegDto.setProducerWorkOrder(new ClientDto());
				pegDto.getProducerWorkOrder()
						.setId(pegging.getSupplier() != null ? pegging.getSupplier().getId() : null);
				pegDto.getProducerWorkOrder()
						.setCode(pegging.getSupplier() != null ? pegging.getSupplier().getCode() : null);
				pegDto.getProducerWorkOrder()
						.setDescription(pegging.getSupplier() != null ? pegging.getSupplier().getDescription() : null);
				targetObject.getPeggings().add(pegDto);
			}
		}
		long minUtc = 0l, maxUtc = 0l;
		Task rootTask = originalObject.getRootTask();
		List<Task> tasks = getChildsOnSameWorkOrder(rootTask, originalObject);
		IMapper<Task, TaskDto> mapper = getMapperFactory().createMapper(Task.class, TaskDto.class);
		List<TaskDto> outTasks = new ArrayList<>();
		for (Task task : tasks) {
			TaskDto tdto = mapper.mapInstance(task, TaskDto.class, entityFactory, environment, recursive);
			outTasks.add(tdto);
			long startUTC = tdto.getSetup() != null ? tdto.getSetup().getUtcStartDateTime() : 0l;
			long endUTC = tdto.getWork() != null ? tdto.getWork().getUtcEndDateTime() : 0l;
			if (startUTC > 0) {
				if (minUtc == 0)
					minUtc = startUTC;
				else
					minUtc = Math.min(minUtc, startUTC);
			}
			if (endUTC > 0) {
				if (maxUtc == 0)
					maxUtc = endUTC;
				else
					maxUtc = Math.max(maxUtc, endUTC);
			}
		}
		if (minUtc > 0l && maxUtc > 0l) {
			targetObject.setExecution(new TimeSegmentDto());
			targetObject.getExecution().setStartDateTime(new Date(minUtc));
			targetObject.getExecution().setEndDateTime(new Date(maxUtc));
		}
		targetObject.setTasks(outTasks);
	}
}