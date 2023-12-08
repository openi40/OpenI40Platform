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

@Configuration
@ConfigurationProperties(prefix = "com.openi40.platform.dbchannels")
public class PersistenceChannelsConfig {
	
	public static class PersistenceChannelConfig {
		String dataSourceName = null;
		String dataSetName = null;
		String dataSetVariant = null;
		String dataSourceDescription = null;
		Boolean useJpaStreaming = false;
		Integer batchingSize = 10000;
		boolean realtime = false;
		boolean productionControlEnabled = false;
		public String getDataSourceName() {
			return dataSourceName;
		}
		public void setDataSourceName(String dataSourceName) {
			this.dataSourceName = dataSourceName;
		}
		public String getDataSetName() {
			return dataSetName;
		}
		public void setDataSetName(String dataSetName) {
			this.dataSetName = dataSetName;
		}
		public String getDataSetVariant() {
			return dataSetVariant;
		}
		public void setDataSetVariant(String dataSetVariant) {
			this.dataSetVariant = dataSetVariant;
		}
		public String getDataSourceDescription() {
			return dataSourceDescription;
		}
		public void setDataSourceDescription(String dataSourceDescription) {
			this.dataSourceDescription = dataSourceDescription;
		}
		public Boolean getUseJpaStreaming() {
			return useJpaStreaming;
		}
		public void setUseJpaStreaming(Boolean useJpaStreaming) {
			this.useJpaStreaming = useJpaStreaming;
		}
		public Integer getBatchingSize() {
			return batchingSize;
		}
		public void setBatchingSize(Integer batchingSize) {
			this.batchingSize = batchingSize;
		}
		public boolean isRealtime() {
			return realtime;
		}
		public void setRealtime(boolean realtime) {
			this.realtime = realtime;
		}
		public boolean isProductionControlEnabled() {
			return productionControlEnabled;
		}
		public void setProductionControlEnabled(boolean productionControlEnabled) {
			this.productionControlEnabled = productionControlEnabled;
		}
	}

	List<PersistenceChannelConfig> configs = new ArrayList<>();

	public List<PersistenceChannelConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<PersistenceChannelConfig> configs) {
		this.configs = configs;
	}

}
