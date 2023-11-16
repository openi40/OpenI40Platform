package com.openi40.mes.io.mqtt.generic.config;

import lombok.Data;

@Data
public class MqttOptions {
	String username=null,password=null;
	boolean automaticReconnect=true;
	Integer connectionTimout=60;
	Integer keepAliveInterval=10;
	public MqttOptions() {
		
	}

}
