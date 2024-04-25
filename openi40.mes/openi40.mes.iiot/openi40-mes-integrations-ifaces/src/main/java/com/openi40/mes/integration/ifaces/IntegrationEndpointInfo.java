package com.openi40.mes.integration.ifaces;

import java.util.Map;

public interface IntegrationEndpointInfo {
	

	public String getReadUri();
	public String getWriteUri();

	public String getProtocolType();

	public Map getAttributes();

	public boolean isCanWrite();

	public boolean isCanRead();
}
