package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;

@Service
public class WorkOrderOutputDto2WorkOrderInputDtoMapperService
		extends AbstractOutputDto2InputDtoMapper<WorkOrderOutputDto, WorkOrderInputDto> {
	public WorkOrderOutputDto2WorkOrderInputDtoMapperService(
			@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(WorkOrderOutputDto.class, WorkOrderInputDto.class, autowireCapableFactory);
	}
}