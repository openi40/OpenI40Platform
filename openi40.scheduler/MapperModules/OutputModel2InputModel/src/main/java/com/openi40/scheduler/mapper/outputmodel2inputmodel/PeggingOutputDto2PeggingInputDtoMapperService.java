package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;

@Service
public class PeggingOutputDto2PeggingInputDtoMapperService
		extends AbstractOutputDto2InputDtoMapper<PeggingOutputDto, PeggingInputDto> {
	public PeggingOutputDto2PeggingInputDtoMapperService(
			@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(PeggingOutputDto.class, PeggingInputDto.class, autowireCapableFactory);
	}
}