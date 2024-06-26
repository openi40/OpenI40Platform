package com.openi40.scheduler.apsdatacache.config;

import org.springframework.context.annotation.Configuration;
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

@Configuration
public class ApsDataSourceConfig {
	private String dataSourceName=null;
	private String dataImporterId=null;
	private boolean disableUserAccess=false;
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public String getDataImporterId() {
		return dataImporterId;
	}
	public void setDataImporterId(String dataImporterId) {
		this.dataImporterId = dataImporterId;
	}
	public boolean isDisableUserAccess() {
		return disableUserAccess;
	}
	public void setDisableUserAccess(boolean disableUserAccess) {
		this.disableUserAccess = disableUserAccess;
	}
	
}
