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
package com.openi40.platform.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.platform.dbchannels")
public class PersistenceChannelsConfig {
	@Data
	public static class PersistenceChannelConfig {
		String dataSourceName = null;
		String dataSetName = null;
		String dataSetVariant = null;
		String dataSourceDescription = null;
		Boolean useJpaStreaming = false;
		Integer batchingSize = 10000;
		boolean realtime = false;
		boolean productionControlEnabled = false;
	}

	List<PersistenceChannelConfig> configs = new ArrayList<>();

	public List<PersistenceChannelConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<PersistenceChannelConfig> configs) {
		this.configs = configs;
	}

}
