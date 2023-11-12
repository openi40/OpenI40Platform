package com.openi40.mes.integration.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ManualChannelConfig {
	String channelId = null;
	List<ManualChannelEndpointInfo> endpoints = new ArrayList<ManualChannelEndpointInfo>();

	public ManualChannelConfig() {

	}

}
