package com.openi40.mes.integration.config;

import java.util.HashMap;
import java.util.Map;

import com.openi40.mes.integration.ifaces.IntegrationEndpointInfo;

import lombok.Data;

@Data
public class ManualChannelEndpointInfo implements IntegrationEndpointInfo {
	String readUri = null, writeUri = null;
	Map<String, Object> attributes = new HashMap<String, Object>();
	boolean canRead = false, canWrite = false;
	String protocolType = null;

	public ManualChannelEndpointInfo() {

	}

}
