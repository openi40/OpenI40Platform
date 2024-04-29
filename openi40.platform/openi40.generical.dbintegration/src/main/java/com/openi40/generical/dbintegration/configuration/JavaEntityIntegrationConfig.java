package com.openi40.generical.dbintegration.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class JavaEntityIntegrationConfig extends EntityIntegrationConfig {
	String entityIntegrationFactory = null;
	List<String> params = new ArrayList<String>();
	List<HashMap<String,Object>> mappedEntries=new ArrayList<HashMap<String,Object>>();

	public JavaEntityIntegrationConfig() {

	}

	public String getEntityIntegrationFactory() {
		return entityIntegrationFactory;
	}

	public void setEntityIntegrationFactory(String entityIntegrationFactory) {
		this.entityIntegrationFactory = entityIntegrationFactory;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public List<HashMap<String, Object>> getMappedEntries() {
		return mappedEntries;
	}

	public void setMappedEntries(List<HashMap<String, Object>> mappedEntries) {
		this.mappedEntries = mappedEntries;
	}

}
