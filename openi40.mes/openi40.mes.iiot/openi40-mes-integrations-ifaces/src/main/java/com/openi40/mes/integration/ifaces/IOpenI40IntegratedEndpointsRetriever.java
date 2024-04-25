package com.openi40.mes.integration.ifaces;

import java.util.List;

public interface IOpenI40IntegratedEndpointsRetriever {
	public List<ConfiguredEndpointInfo> getConfiguredEndpoints(String integrationId, String channelId)
			throws IntegrationHandlerException;
}
