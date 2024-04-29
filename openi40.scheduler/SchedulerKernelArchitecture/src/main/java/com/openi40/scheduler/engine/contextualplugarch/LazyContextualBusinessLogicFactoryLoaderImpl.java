package com.openi40.scheduler.engine.contextualplugarch;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LazyContextualBusinessLogicFactoryLoaderImpl implements ILazyContextualBusinessLogicFactoryLoader {
	AutowireCapableBeanFactory beanFactory;

	public LazyContextualBusinessLogicFactoryLoaderImpl(@Autowired AutowireCapableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public  class AutowiredLazyReference {
		@Autowired
		IContextualBusinessLogicFactory componentFactory;

		public IContextualBusinessLogicFactory getComponentFactory() {
			return componentFactory;
		}

		public void setComponentFactory(IContextualBusinessLogicFactory componentFactory) {
			this.componentFactory = componentFactory;
		}
	}

	@Override
	public IContextualBusinessLogicFactory getComponentFactory() {
		AutowiredLazyReference awr = new AutowiredLazyReference();
		beanFactory.autowireBean(awr);
		return awr.getComponentFactory();
	}

}
