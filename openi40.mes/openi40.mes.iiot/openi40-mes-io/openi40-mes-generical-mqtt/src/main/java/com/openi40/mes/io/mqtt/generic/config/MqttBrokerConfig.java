package com.openi40.mes.io.mqtt.generic.config;

import lombok.Data;

@Data
public class MqttBrokerConfig {
	String brokerUrl=null;
	MqttOptions options=null;
	public MqttBrokerConfig() {
		
	}

}
