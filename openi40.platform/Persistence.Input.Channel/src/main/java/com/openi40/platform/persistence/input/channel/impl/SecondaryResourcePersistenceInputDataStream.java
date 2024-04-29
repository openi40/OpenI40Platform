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
package com.openi40.platform.persistence.input.channel.impl;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.model.SecondaryResource;
import com.openi40.platform.persistence.input.channel.providers.SecondaryResourceInputChannelRepository;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;
@Service
public class SecondaryResourcePersistenceInputDataStream
		extends AbstractPersistenceInputDataStream<SecondaryResource, SecondaryResourceInputDto, SecondaryResourceInputChannelRepository> {

	public SecondaryResourcePersistenceInputDataStream(SecondaryResourceInputChannelRepository repository) {
		super(SecondaryResource.class, SecondaryResourceInputDto.class, repository);

	}

}
 