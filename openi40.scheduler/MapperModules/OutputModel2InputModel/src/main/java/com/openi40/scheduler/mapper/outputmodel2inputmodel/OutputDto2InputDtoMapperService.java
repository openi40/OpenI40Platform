package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.output.model.OutputDto;

@Service
public class OutputDto2InputDtoMapperService extends AbstractOutputDto2InputDtoMapper<OutputDto, InputDto> {
	public OutputDto2InputDtoMapperService(@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(OutputDto.class, InputDto.class, autowireCapableFactory);
	}
}