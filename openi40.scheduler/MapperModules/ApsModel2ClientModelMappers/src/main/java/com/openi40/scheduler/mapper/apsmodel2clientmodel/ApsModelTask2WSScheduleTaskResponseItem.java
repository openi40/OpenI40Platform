package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.websocketproto.model.WSScheduleTaskResponseItem;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.tasks.Task;
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
public class ApsModelTask2WSScheduleTaskResponseItem
		extends AbstractReflectorMapper<Task, WSScheduleTaskResponseItem>
		implements IMapper<Task, WSScheduleTaskResponseItem> {

	public ApsModelTask2WSScheduleTaskResponseItem(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Task.class, WSScheduleTaskResponseItem.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(Task originalObject, WSScheduleTaskResponseItem targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IMapper<Task, TaskDto> mapper = this.getMapperFactory().createMapper(Task.class, TaskDto.class);
		TaskDto taskDto = mapper.mapInstance(originalObject, TaskDto.class, DefaultEntitiesFactory.Instance,
				environment, recursive);
		targetObject.setData(taskDto);
	}

}