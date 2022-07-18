package com.openi40.scheduler.apsdatacache;

import java.util.List;
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
public interface IApsDataCacheAggregator {
	public List<IApsDataCache> getDataCaches();
	public IApsDataCache getDataCache(String dataSourceName);
	public void addApsDataCache(IApsDataCache dataCache);
}
