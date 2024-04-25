package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;

@Service
public class TaskRelationOutputDto2TaskRelationInputDtoMapperService
		extends AbstractOutputDto2InputDtoMapper<TaskRelationOutputDto, TaskRelationInputDto> {
	public TaskRelationOutputDto2TaskRelationInputDtoMapperService(
			@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(TaskRelationOutputDto.class, TaskRelationInputDto.class, autowireCapableFactory);
	}
}