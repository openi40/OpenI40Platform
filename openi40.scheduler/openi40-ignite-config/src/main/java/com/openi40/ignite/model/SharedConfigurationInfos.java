package com.openi40.ignite.model;

import java.sql.Timestamp;
import java.util.HashMap;

import lombok.Data;

@Data
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

}
