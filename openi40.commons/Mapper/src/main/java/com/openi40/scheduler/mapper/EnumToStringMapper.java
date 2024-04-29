package com.openi40.scheduler.mapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
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
public class EnumToStringMapper extends AbstractReflectorMapper<Enum, String> {

	protected EnumToStringMapper(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Enum.class, String.class);

	}

	@Override
	public String mapInstance(Enum object, Class<String> targetType, IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {

		return object != null ? object.toString() : null;
	}

}
