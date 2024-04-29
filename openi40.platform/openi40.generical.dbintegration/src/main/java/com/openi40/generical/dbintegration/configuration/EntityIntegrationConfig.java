package com.openi40.generical.dbintegration.configuration;

import java.util.HashMap;
import java.util.Map;
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

public class EntityIntegrationConfig {
	private String entityName = null;
	private boolean incrementalSync = false;
	private boolean handleDeletions = false;
	private String customDeletionHandlerClass = null;
	private Map<String, Object> customParameters = new HashMap<String, Object>();

	public EntityIntegrationConfig() {

	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public boolean isIncrementalSync() {
		return incrementalSync;
	}

	public void setIncrementalSync(boolean incrementalSync) {
		this.incrementalSync = incrementalSync;
	}

	public boolean isHandleDeletions() {
		return handleDeletions;
	}

	public void setHandleDeletions(boolean handleDeletions) {
		this.handleDeletions = handleDeletions;
	}

	public String getCustomDeletionHandlerClass() {
		return customDeletionHandlerClass;
	}

	public void setCustomDeletionHandlerClass(String customDeletionHandlerClass) {
		this.customDeletionHandlerClass = customDeletionHandlerClass;
	}

	public Map<String, Object> getCustomParameters() {
		return customParameters;
	}

	public void setCustomParameters(Map<String, Object> customParameters) {
		this.customParameters = customParameters;
	}

}
