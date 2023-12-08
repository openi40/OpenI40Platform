package com.openi40.mes.io.mqtt.generic.config;

public class MqttOptions {
	String username=null,password=null;
	boolean automaticReconnect=true;
	Integer connectionTimout=60;
	Integer keepAliveInterval=10;
	public MqttOptions() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAutomaticReconnect() {
		return automaticReconnect;
	}
	public void setAutomaticReconnect(boolean automaticReconnect) {
		this.automaticReconnect = automaticReconnect;
	}
	public Integer getConnectionTimout() {
		return connectionTimout;
	}
	public void setConnectionTimout(Integer connectionTimout) {
		this.connectionTimout = connectionTimout;
	}
	public Integer getKeepAliveInterval() {
		return keepAliveInterval;
	}
	public void setKeepAliveInterval(Integer keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

}
