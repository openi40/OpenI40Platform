package com.openi40.mes.integration.ifaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrationHandlersRepositoryImpl implements IntegrationHandlersRepository {
	List<IntegrationHandler> handlers = null;
	HashMap<String, IntegrationHandler> handlersMap = new HashMap<String, IntegrationHandler>();
	static Logger LOGGER = LoggerFactory.getLogger(IntegrationHandlersRepositoryImpl.class);

	public IntegrationHandlersRepositoryImpl(@Autowired(required = false) List<IntegrationHandler> handlers) {
		if (handlers == null) {
			handlers = new ArrayList<IntegrationHandler>();
		}
		this.handlers = handlers;
		for (IntegrationHandler integrationHandler : handlers) {
			String key = integrationHandler.getIntegrationHandlerId();
			if (handlersMap.containsKey(key))
				throw new RuntimeException(
						"Duplicated integration handler:" + integrationHandler.getIntegrationHandlerId());
			this.handlersMap.put(key, integrationHandler);
		}
	}

	@Override
	public IntegrationHandler getIntegrationHandler(String integrationId, String channelId)
			throws IntegrationHandlerException {
		if (!handlersMap.containsKey(integrationId))
			throw new IntegrationHandlerException("Integration handler:" + integrationId + " is not loaded");
		IntegrationHandler handler = handlersMap.get(integrationId);
		if (handler == null || !handler.hasChannel(channelId)) {
			LOGGER.warn(
					"Integration handler:" + integrationId + " does not have " + channelId + " configured or setup");
		} else if (!handler.isOnline(channelId)) {
			handler.tryReconnect(channelId);
		}
		return handler;
	}

}
