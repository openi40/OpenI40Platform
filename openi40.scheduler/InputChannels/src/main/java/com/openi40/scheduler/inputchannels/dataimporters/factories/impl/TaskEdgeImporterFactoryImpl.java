package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ITaskImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.ITaskRelationImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.ITaskEdgeDao;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
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
public class TaskEdgeImporterFactoryImpl extends AbstractDataImporterFactory<TaskRelationInputDto, TaskEdge>
		implements ITaskRelationImporterFactory {
	@Autowired
	public TaskEdgeImporterFactoryImpl(IMapperFactory mapperFactory, ITaskEdgeDao apsDataModelDao) {
		super(TaskRelationInputDto.class, TaskEdge.class, mapperFactory, apsDataModelDao);

	}

}
