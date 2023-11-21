package com.openi40.ignite.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.scheduler.ignite.dataset")
@Data
public class HACachedApsDataSetConfig {
	String dataSourceName = null;
	String dataSetName = null;
	String dataSetVariant = null;
	String dataSourceDescription = null;
	boolean productionControlEnabled=false;
	boolean realtime=false;
	String sourceDataSourceName = null;
	String sourceDataSetName = null;
	String sourceDataSetVariant = null;
	public HACachedApsDataSetConfig() {

	}

}
