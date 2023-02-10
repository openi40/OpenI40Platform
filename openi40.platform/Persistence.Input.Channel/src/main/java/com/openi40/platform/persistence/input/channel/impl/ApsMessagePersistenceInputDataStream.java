package com.openi40.platform.persistence.input.channel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.InputChannelStreamProvider;
import com.openi40.platform.persistence.input.channel.model.ApsMessage;
import com.openi40.platform.persistence.input.channel.providers.ApsMessageInputChannelRepository;
import com.openi40.scheduler.input.model.tasks.ApsMessageInputDto;

@Service
public class ApsMessagePersistenceInputDataStream
		extends AbstractPersistenceInputDataStream<ApsMessage, ApsMessageInputDto, ApsMessageInputChannelRepository> {
	@Autowired
	public ApsMessagePersistenceInputDataStream(ApsMessageInputChannelRepository repository) {
		super(ApsMessage.class, ApsMessageInputDto.class, repository);

	}

}
