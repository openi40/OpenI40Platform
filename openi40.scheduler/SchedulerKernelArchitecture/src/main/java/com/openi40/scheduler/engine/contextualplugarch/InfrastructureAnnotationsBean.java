package com.openi40.scheduler.engine.contextualplugarch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
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
@Component
public class InfrastructureAnnotationsBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructureAnnotationsBean.class);

	

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("Begin InfrastructureAnnotationsBean.onApplicationEvent(...)");
		
		LOGGER.info("End InfrastructureAnnotationsBean.onApplicationEvent(...)");
	}
}