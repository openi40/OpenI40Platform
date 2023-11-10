package com.openi40.mes.integration.ifaces;

import java.util.List;

public interface IntegrationHandler {
	public String getIntegrationHandlerId();

	public boolean hasChannel(String channelId) throws IntegrationHandlerException;

	public boolean isOnline(String channelId) throws IntegrationHandlerException;

	public boolean tryReconnect(String channelId) throws IntegrationHandlerException;

	public List<IntegrationEndpointInfo> getEndPoints(String channelId) throws IntegrationHandlerException;
}
