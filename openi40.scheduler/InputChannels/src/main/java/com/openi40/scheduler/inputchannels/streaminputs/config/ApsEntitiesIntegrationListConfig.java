package com.openi40.scheduler.inputchannels.streaminputs.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "com.openi40.scheduler.entities")

public class ApsEntitiesIntegrationListConfig {

	public ApsEntitiesIntegrationListConfig() {

	}

	List<String> additionalEntities = new ArrayList<>();

	public List<String> getAdditionalEntities() {
		return additionalEntities;
	}

	public void setAdditionalEntities(List<String> additionalEntities) {
		this.additionalEntities = additionalEntities;
	}

}
