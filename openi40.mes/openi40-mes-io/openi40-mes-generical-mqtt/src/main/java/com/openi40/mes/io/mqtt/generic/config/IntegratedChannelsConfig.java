package com.openi40.mes.io.mqtt.generic.config;

import lombok.Data;

@Data
public class IntegratedChannelsConfig {
	private String channelId = null, integrationHandlerId = null;
	private MqttBrokerConfig brokerConfig = null;

	public IntegratedChannelsConfig() {

	}

}
