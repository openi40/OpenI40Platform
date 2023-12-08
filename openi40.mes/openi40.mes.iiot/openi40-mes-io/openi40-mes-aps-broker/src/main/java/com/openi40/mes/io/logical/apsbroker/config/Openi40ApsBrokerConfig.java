package com.openi40.mes.io.logical.apsbroker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.logical.apsbroker.config")

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

	public WebInterfaceConfig getApsRestInterfaceConfig() {
		return apsRestInterfaceConfig;
	}

	public void setApsRestInterfaceConfig(WebInterfaceConfig apsRestInterfaceConfig) {
		this.apsRestInterfaceConfig = apsRestInterfaceConfig;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Integer getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(Integer maxRetry) {
		this.maxRetry = maxRetry;
	}

	public Integer getRetryDelay() {
		return retryDelay;
	}

	public void setRetryDelay(Integer retryDelay) {
		this.retryDelay = retryDelay;
	}

	public Boolean getWaitIfOnline() {
		return waitIfOnline;
	}

	public void setWaitIfOnline(Boolean waitIfOnline) {
		this.waitIfOnline = waitIfOnline;
	}

	public Integer getRetryDelayOffline() {
		return retryDelayOffline;
	}

	public void setRetryDelayOffline(Integer retryDelayOffline) {
		this.retryDelayOffline = retryDelayOffline;
	}

}
