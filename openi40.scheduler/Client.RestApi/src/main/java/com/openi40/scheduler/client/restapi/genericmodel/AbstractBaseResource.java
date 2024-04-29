package com.openi40.scheduler.client.restapi.genericmodel;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapperFactory;
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
public class AbstractBaseResource {

	protected ILazyContextualBusinessLogicFactoryLoader lazyAutowire;

	protected IApsDataCacheAggregator apsDataCacheAggregator;

	protected IMapperFactory mapperFactory;

	protected AbstractBaseResource(IApsDataCacheAggregator apsDataCacheAggregator, IMapperFactory mapperFactory, ILazyContextualBusinessLogicFactoryLoader lazyAutowire) {
		this.lazyAutowire = lazyAutowire;
		this.apsDataCacheAggregator = apsDataCacheAggregator;
		this.mapperFactory = mapperFactory;

	}

	protected IContextualBusinessLogicFactory cf = null;

	protected IContextualBusinessLogicFactory getComponentFactory() {
		if (cf == null)
			cf = lazyAutowire.getComponentFactory();
		return cf;
	}

}
