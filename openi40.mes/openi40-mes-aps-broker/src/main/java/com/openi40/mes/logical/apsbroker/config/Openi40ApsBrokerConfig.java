package com.openi40.mes.logical.apsbroker.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Data;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.logical.apsbroker.config")
@Data
public class Openi40ApsBrokerConfig {
	private WebInterfaceConfig apsRestInterfaceConfig;
	private Boolean enabled = false;
	private String dataSourceName = null;
	private String dataSetName = null;
	private String dataSetVariant = null;
	private Integer maxRetry = 4;
	private Integer retryDelay=20000;
	private Boolean waitIfOnline = true;
	private Integer retryDelayOffline=60000;

	public Openi40ApsBrokerConfig() {

	}

}
