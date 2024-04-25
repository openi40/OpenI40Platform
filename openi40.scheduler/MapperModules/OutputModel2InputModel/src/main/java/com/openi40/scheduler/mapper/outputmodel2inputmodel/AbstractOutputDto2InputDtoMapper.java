package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.output.model.OutputDto;

public abstract class AbstractOutputDto2InputDtoMapper<OutputType extends OutputDto,InputType extends InputDto> extends AbstractReflectorMapper<OutputType,InputType > {
	
	public AbstractOutputDto2InputDtoMapper(Class<OutputType> outputClass,Class<InputType> inputClass, AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, outputClass,inputClass);
	}

}
