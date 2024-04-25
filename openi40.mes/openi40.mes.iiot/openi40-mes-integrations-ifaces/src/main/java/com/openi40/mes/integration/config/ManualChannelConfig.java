package com.openi40.mes.integration.config;

import java.util.ArrayList;
import java.util.List;


public class ManualChannelConfig {
	String channelId = null;
	List<ManualChannelEndpointInfo> endpoints = new ArrayList<ManualChannelEndpointInfo>();

	public ManualChannelConfig() {

	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<ManualChannelEndpointInfo> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<ManualChannelEndpointInfo> endpoints) {
		this.endpoints = endpoints;
	}

}
