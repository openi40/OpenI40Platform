package com.openi40.generical.dbintegration.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
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
@Data
public class JavaEntityIntegrationConfig extends EntityIntegrationConfig {
	String entityIntegrationFactory = null;
	List<String> params = new ArrayList<String>();
	List<HashMap<String,Object>> mappedEntries=new ArrayList<HashMap<String,Object>>();

	public JavaEntityIntegrationConfig() {

	}

}
