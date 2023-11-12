package com.openi40.mes.integration.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.mes.integration")
@Data
public class IntegrationConfig {
	private List<ManualChannelConfig> channels=new ArrayList<ManualChannelConfig>();
	public IntegrationConfig() {
		
	}

}
