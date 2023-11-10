package com.openi40.mes.io.mqtt.generic.config;

import lombok.Data;

@Data
public class MqttBrokerConfig {
	String username=null,password=null,brokerUrl=null;;
	public MqttBrokerConfig() {
		
	}

}
