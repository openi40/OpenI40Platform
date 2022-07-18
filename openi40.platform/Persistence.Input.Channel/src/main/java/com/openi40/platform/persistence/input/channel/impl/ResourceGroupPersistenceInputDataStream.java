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

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.JpaStreamLoader;
import com.openi40.platform.persistence.input.channel.model.ResourceGroup;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;

@Service
public class ResourceGroupPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<ResourceGroup, ResourceGroupInputDto, ResourceGroupPersistenceInputDataStream.ResourceGroupStreamLoadRelated> {
	@Service
	public static class ResourceGroupStreamLoadRelated extends JpaStreamLoader<ResourceGroupInputDto, ResourceGroup> {

		ResourceGroupStreamLoadRelated(EntityManager entityManager) {
			super(ResourceGroup.class, ResourceGroupInputDto.class, entityManager, "code");

		}

	};

	public ResourceGroupPersistenceInputDataStream(ResourceGroupStreamLoadRelated repository) {
		super(ResourceGroup.class, ResourceGroupInputDto.class, repository);

	}

}
