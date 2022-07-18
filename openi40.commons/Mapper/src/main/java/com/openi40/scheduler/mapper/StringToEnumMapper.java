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
 * @author architectures@openi40.org
 *
 */
@Service
public class StringToEnumMapper extends AbstractReflectorMapper<String, Enum> {

	protected StringToEnumMapper(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, String.class, Enum.class);

	}

	@Override
	public Enum mapInstance(String object, Class<Enum> targetType, IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		Object _constants[] = targetType.getEnumConstants();
		if (_constants != null) {
			for (Object object2 : _constants) {
				if (object2.toString().equals(object))
					return (Enum) object2;
			}
		}
		return null;
	}

}
