package com.openi40.ignite.model;

import java.sql.Timestamp;
import java.util.HashMap;


public class SharedConfigurationInfos {
	public static final String SHARED_CONFIG_ID="SHARED-CONFIGURATION";
	private String id=SHARED_CONFIG_ID;
	private boolean dataLoaded=false;
	private boolean dataAreLoading=false;
	private Timestamp startup_time=null;
	private Timestamp latest_update_time=null;
	private HashMap<String, Timestamp> maximumTimestamps=new HashMap<String, Timestamp>();
	public SharedConfigurationInfos() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDataLoaded() {
		return dataLoaded;
	}
	public void setDataLoaded(boolean dataLoaded) {
		this.dataLoaded = dataLoaded;
	}
	public boolean isDataAreLoading() {
		return dataAreLoading;
	}
	public void setDataAreLoading(boolean dataAreLoading) {
		this.dataAreLoading = dataAreLoading;
	}
	public Timestamp getStartup_time() {
		return startup_time;
	}
	public void setStartup_time(Timestamp startup_time) {
		this.startup_time = startup_time;
	}
	public Timestamp getLatest_update_time() {
		return latest_update_time;
	}
	public void setLatest_update_time(Timestamp latest_update_time) {
		this.latest_update_time = latest_update_time;
	}
	public HashMap<String, Timestamp> getMaximumTimestamps() {
		return maximumTimestamps;
	}
	public void setMaximumTimestamps(HashMap<String, Timestamp> maximumTimestamps) {
		this.maximumTimestamps = maximumTimestamps;
	}

}
