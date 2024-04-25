package com.openi40.scheduler.apsdatacache.config;

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

public class ApsDataCacheConfig {
	String dataSourceName = null;
	boolean cacheDisabled=false;

	public ApsDataCacheConfig() {

	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public boolean isCacheDisabled() {
		return cacheDisabled;
	}

	public void setCacheDisabled(boolean cacheDisabled) {
		this.cacheDisabled = cacheDisabled;
	}

}
