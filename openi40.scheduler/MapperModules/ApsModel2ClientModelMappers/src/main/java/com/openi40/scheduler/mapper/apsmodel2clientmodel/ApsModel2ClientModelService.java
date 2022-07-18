package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IMapper;
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
@Service
public class ApsModel2ClientModelService extends AbstractReflectorMapper<IApsObject, ClientDto>
		implements IMapper<IApsObject, ClientDto> {

	public ApsModel2ClientModelService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, IApsObject.class, ClientDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

}