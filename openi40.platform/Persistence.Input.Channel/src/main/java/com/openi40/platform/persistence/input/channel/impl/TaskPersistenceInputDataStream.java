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
package com.openi40.platform.persistence.input.channel.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.JpaStreamLoader;
import com.openi40.platform.persistence.input.channel.model.Task;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;

@Service
public class TaskPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<Task, TaskInputDto, TaskPersistenceInputDataStream.TaskStreamLoadRelated> {
	@Service
	public static class TaskStreamLoadRelated extends JpaStreamLoader<TaskInputDto, Task> {

		TaskStreamLoadRelated(EntityManager entityManager) {
			super(Task.class, TaskInputDto.class, entityManager, "workOrderCode,sequenceCode");

		}

	};

	public TaskPersistenceInputDataStream(TaskStreamLoadRelated repository) {
		super(Task.class, TaskInputDto.class, repository);

	}

}
