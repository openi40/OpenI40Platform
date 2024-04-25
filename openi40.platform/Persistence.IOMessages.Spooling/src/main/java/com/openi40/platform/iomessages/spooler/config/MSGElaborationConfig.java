package com.openi40.platform.iomessages.spooler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.platform.iomsgelaboration")
@EnableScheduling

public class MSGElaborationConfig {
	private String dataSourceName = null;
	private String dataSetName = null;
	private String dataSetVariant = null;
	private boolean enableMessagesElaboration=false;

	public MSGElaborationConfig() {

	}

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

	public boolean isEnableMessagesElaboration() {
		return enableMessagesElaboration;
	}

	public void setEnableMessagesElaboration(boolean enableMessagesElaboration) {
		this.enableMessagesElaboration = enableMessagesElaboration;
	}

	
}
