package com.openi40.mes.integration.config;

import java.util.HashMap;
import java.util.Map;

import com.openi40.mes.integration.ifaces.IntegrationEndpointInfo;


public class ManualChannelEndpointInfo implements IntegrationEndpointInfo {
	String readUri = null, writeUri = null;
	Map<String, Object> attributes = new HashMap<String, Object>();
	boolean canRead = false, canWrite = false;
	String protocolType = null;

	public ManualChannelEndpointInfo() {

	}

	public String getReadUri() {
		return readUri;
	}

	public void setReadUri(String readUri) {
		this.readUri = readUri;
	}

	public String getWriteUri() {
		return writeUri;
	}

	public void setWriteUri(String writeUri) {
		this.writeUri = writeUri;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public boolean isCanRead() {
		return canRead;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

}
