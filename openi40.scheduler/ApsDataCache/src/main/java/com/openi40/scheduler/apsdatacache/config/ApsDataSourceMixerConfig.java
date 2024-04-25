package com.openi40.scheduler.apsdatacache.config;

import java.util.ArrayList;
import java.util.List;
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

public class ApsDataSourceMixerConfig {
	String dataSourceName = null;
	String dataSetName = null;
	String dataSetVariant = null;
	String description = null;

	public static class ApsDataSourceId {
		String dataSourceName = null;
		String dataSetName = null;
		String dataSetVariant = null;
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
	}

	public ApsDataSourceMixerConfig() {

	}

	List<ApsDataSourceId> joined = new ArrayList<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ApsDataSourceId> getJoined() {
		return joined;
	}

	public void setJoined(List<ApsDataSourceId> joined) {
		this.joined = joined;
	}

}
