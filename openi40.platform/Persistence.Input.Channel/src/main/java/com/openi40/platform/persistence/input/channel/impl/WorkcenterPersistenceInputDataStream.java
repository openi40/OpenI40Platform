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

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.JpaStreamLoader;
import com.openi40.platform.persistence.input.channel.model.WorkCenter;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;

@Service
public class WorkcenterPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<WorkCenter, WorkCenterInputDto, WorkcenterPersistenceInputDataStream.WorkcenterStreamLoadRelated> {
	@Service
	public static class WorkcenterStreamLoadRelated extends JpaStreamLoader<WorkCenterInputDto, WorkCenter> {

		WorkcenterStreamLoadRelated(EntityManager entityManager) {
			super(WorkCenter.class, WorkCenterInputDto.class, entityManager, "code");

		}

	};

	public WorkcenterPersistenceInputDataStream(WorkcenterStreamLoadRelated repository) {
		super(WorkCenter.class, WorkCenterInputDto.class, repository);

	}

}
