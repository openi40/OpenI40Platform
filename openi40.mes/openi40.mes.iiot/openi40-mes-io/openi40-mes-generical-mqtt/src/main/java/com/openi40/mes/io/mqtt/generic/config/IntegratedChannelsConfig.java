package com.openi40.mes.io.mqtt.generic.config;

import com.openi40.mes.integration.ifaces.ManuallyConfiguredIntegrationHandlerImpl;

import lombok.Data;

@Data
public class IntegratedChannelsConfig {
	private String channelId = null, integrationHandlerId = ManuallyConfiguredIntegrationHandlerImpl.HANDLER_ID;
	private MqttBrokerConfig brokerConfig = null;

	public IntegratedChannelsConfig() {

	}

}
