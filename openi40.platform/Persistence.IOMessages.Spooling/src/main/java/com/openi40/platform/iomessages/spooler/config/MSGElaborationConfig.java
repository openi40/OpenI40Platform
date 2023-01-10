package com.openi40.platform.iomessages.spooler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.platform.iomsgelaboration")
@EnableScheduling
@Data
public class MSGElaborationConfig {
	private String dataSourceName = null;
	private String dataSetName = null;
	private String dataSetVariant = null;
	private boolean enableMessagesElaboration=false;

	public MSGElaborationConfig() {

	}

	
}
