package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.companystructure.ResourceGroupDto;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.model.equipment.ResourceGroup;
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
public class ResourceGroup2ResourceGroupDtoService
		extends BaseGroup2GroupDtoService<ResourceGroup, ResourceGroupDto>
		implements IMapper<ResourceGroup, ResourceGroupDto> {

	public ResourceGroup2ResourceGroupDtoService(@Autowired AutowireCapableBeanFactory beanFactory,ILazyContextualBusinessLogicFactoryLoader lcbFLoader) {
		super(beanFactory, ResourceGroup.class, ResourceGroupDto.class,lcbFLoader);
	}

}