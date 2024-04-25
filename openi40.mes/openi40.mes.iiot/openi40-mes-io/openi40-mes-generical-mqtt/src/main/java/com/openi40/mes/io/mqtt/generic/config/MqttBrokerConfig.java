package com.openi40.mes.io.mqtt.generic.config;

public class MqttBrokerConfig {
	String brokerUrl=null;
	MqttOptions options=null;
	public MqttBrokerConfig() {
		
	}
	public String getBrokerUrl() {
		return brokerUrl;
	}
	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}
	public MqttOptions getOptions() {
		return options;
	}
	public void setOptions(MqttOptions options) {
		this.options = options;
	}

}
