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

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.model.AcquiredSetupSecondaryResourceInfo;
import com.openi40.platform.persistence.input.channel.model.Machine;
import com.openi40.platform.persistence.input.channel.providers.AcquiredSetupSecondaryResourceInfoRepository;
import com.openi40.platform.persistence.input.channel.providers.MachineInputChannelRepository;
import com.openi40.scheduler.input.model.equipment.MachineInputDto;
import com.openi40.scheduler.input.model.tasks.UsedSecondaryResourcesInfoInputDto;

@Service
public class AcquiredSetupSecondaryResourceInfoInputDataStream extends
		AbstractPersistenceInputDataStream<AcquiredSetupSecondaryResourceInfo, UsedSecondaryResourcesInfoInputDto, AcquiredSetupSecondaryResourceInfoRepository> {

	public AcquiredSetupSecondaryResourceInfoInputDataStream(AcquiredSetupSecondaryResourceInfoRepository repository) {
		super(AcquiredSetupSecondaryResourceInfo.class, UsedSecondaryResourcesInfoInputDto.class, repository);

	}

}
