package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.OutputDto;
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
public abstract class AbstractApsModel2OutputModelService<ApsType extends IApsObject, OutType extends OutputDto>
		extends AbstractReflectorMapper<ApsType, OutType> implements IMapper<ApsType, OutType> {

	protected AbstractApsModel2OutputModelService(AutowireCapableBeanFactory beanFactory, Class<ApsType> originalType,
			Class<OutType> targetType) {
		super(beanFactory, originalType, targetType);

	}

}
