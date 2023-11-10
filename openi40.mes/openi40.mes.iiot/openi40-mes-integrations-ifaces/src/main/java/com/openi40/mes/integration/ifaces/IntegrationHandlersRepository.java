package com.openi40.mes.integration.ifaces;

public interface IntegrationHandlersRepository {
	public IntegrationHandler getIntegrationHandler(String integrationId, String channelId) throws IntegrationHandlerException;
}
