package com.openi40.scheduler.outputchannels.dataexporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.ITaskEdgeDao;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
import com.openi40.scheduler.outputchannels.dataexporters.AbstractDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.ITaskOutputDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.ITaskRelationOutputDataExporterFactory;
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
public class TaskRelationOutputDataExporterFactoryImpl
		extends AbstractDataExporterFactory<TaskEdge, TaskRelationOutputDto, ITaskEdgeDao>
		implements ITaskRelationOutputDataExporterFactory {
	@Autowired
	public TaskRelationOutputDataExporterFactoryImpl(ITaskEdgeDao dao, IMapperFactory mapperFactory) {
		super(TaskEdge.class, TaskRelationOutputDto.class, dao, mapperFactory);

	}

}
