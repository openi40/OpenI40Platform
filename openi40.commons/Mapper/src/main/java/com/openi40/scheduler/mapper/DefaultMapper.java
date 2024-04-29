package com.openi40.scheduler.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Lazy;
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
@Lazy
public class DefaultMapper extends AbstractReflectorMapper<Object, Object> implements IMapper<Object, Object> {

	public DefaultMapper(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Object.class, Object.class);

	}

}
