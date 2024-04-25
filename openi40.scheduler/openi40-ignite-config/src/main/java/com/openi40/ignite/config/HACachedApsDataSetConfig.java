package com.openi40.ignite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.scheduler.ignite.dataset")
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
	boolean dataLoaderNode=System.getProperty("dataLoaderNode")!=null && System.getProperty("dataLoaderNode").equalsIgnoreCase("true");
	Integer dataLoadingFrequency=60000;
	public HACachedApsDataSetConfig() {

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
	public String getDataSourceDescription() {
		return dataSourceDescription;
	}
	public void setDataSourceDescription(String dataSourceDescription) {
		this.dataSourceDescription = dataSourceDescription;
	}
	public boolean isProductionControlEnabled() {
		return productionControlEnabled;
	}
	public void setProductionControlEnabled(boolean productionControlEnabled) {
		this.productionControlEnabled = productionControlEnabled;
	}
	public boolean isRealtime() {
		return realtime;
	}
	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}
	public String getSourceDataSourceName() {
		return sourceDataSourceName;
	}
	public void setSourceDataSourceName(String sourceDataSourceName) {
		this.sourceDataSourceName = sourceDataSourceName;
	}
	public String getSourceDataSetName() {
		return sourceDataSetName;
	}
	public void setSourceDataSetName(String sourceDataSetName) {
		this.sourceDataSetName = sourceDataSetName;
	}
	public String getSourceDataSetVariant() {
		return sourceDataSetVariant;
	}
	public void setSourceDataSetVariant(String sourceDataSetVariant) {
		this.sourceDataSetVariant = sourceDataSetVariant;
	}
	public boolean isDataLoaderNode() {
		return dataLoaderNode;
	}
	public void setDataLoaderNode(boolean dataLoaderNode) {
		this.dataLoaderNode = dataLoaderNode;
	}
	public Integer getDataLoadingFrequency() {
		return dataLoadingFrequency;
	}
	public void setDataLoadingFrequency(Integer dataLoadingFrequency) {
		this.dataLoadingFrequency = dataLoadingFrequency;
	}

}
