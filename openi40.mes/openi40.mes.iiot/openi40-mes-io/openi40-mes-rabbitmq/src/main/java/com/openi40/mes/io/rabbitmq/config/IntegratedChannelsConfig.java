package com.openi40.mes.io.rabbitmq.config;

import com.openi40.mes.integration.ifaces.ManuallyConfiguredIntegrationHandlerImpl;

public class IntegratedChannelsConfig {
	private String channelId = null, integrationHandlerId = ManuallyConfiguredIntegrationHandlerImpl.HANDLER_ID;

	public IntegratedChannelsConfig() {

	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIntegrationHandlerId() {
		return integrationHandlerId;
	}

	public void setIntegrationHandlerId(String integrationHandlerId) {
		this.integrationHandlerId = integrationHandlerId;
	}

}
