package com.openi40.mes.io.mqtt.generic.config;

import com.openi40.mes.integration.ifaces.ManuallyConfiguredIntegrationHandlerImpl;


public class IntegratedChannelsConfig {
	private String channelId = null, integrationHandlerId = ManuallyConfiguredIntegrationHandlerImpl.HANDLER_ID;
	private MqttBrokerConfig brokerConfig = null;

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

	public MqttBrokerConfig getBrokerConfig() {
		return brokerConfig;
	}

	public void setBrokerConfig(MqttBrokerConfig brokerConfig) {
		this.brokerConfig = brokerConfig;
	}

}
