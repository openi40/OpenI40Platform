package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.aps.ApsSchedulingSetDto;
import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
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
@Service
public class ApsData2ApsDataDtoService extends AbstractReflectorMapper<ApsData, ApsDataDto>
		implements IMapper<ApsData, ApsDataDto> {

	public ApsData2ApsDataDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ApsData.class, ApsDataDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(ApsData originalObject, ApsDataDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.getSchedulingSets().clear();
		IMapper<ApsSchedulingSet, ApsSchedulingSetDto> mapper = this.getMapperFactory().createMapper(ApsSchedulingSet.class,
				ApsSchedulingSetDto.class);
		for (ApsSchedulingSet action : originalObject.getSchedulingSets()) {
			ApsSchedulingSetDto result = mapper.mapInstance(action, ApsSchedulingSetDto.class, entityFactory, environment,
					recursive);
			targetObject.getSchedulingSets().add(result);
		}
	}
}