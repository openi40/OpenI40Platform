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
import com.openi40.platform.persistence.input.channel.model.ApsSchedulingSet;
import com.openi40.platform.persistence.input.channel.model.CycleModel;
import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;

@Service
public class ApsSchedulingSetPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<ApsSchedulingSet, ApsSchedulingSetInputDto, ApsSchedulingSetPersistenceInputDataStream.ApsSchedulingSetStreamLoadRelated> {
	@Service
	public static class ApsSchedulingSetStreamLoadRelated
			extends JpaStreamLoader<ApsSchedulingSetInputDto, ApsSchedulingSet> {

		ApsSchedulingSetStreamLoadRelated(EntityManager entityManager) {
			super(ApsSchedulingSet.class, ApsSchedulingSetInputDto.class, entityManager, "position");

		}

	};

	public ApsSchedulingSetPersistenceInputDataStream(ApsSchedulingSetStreamLoadRelated repository) {
		super(ApsSchedulingSet.class, ApsSchedulingSetInputDto.class, repository);

	}

}
