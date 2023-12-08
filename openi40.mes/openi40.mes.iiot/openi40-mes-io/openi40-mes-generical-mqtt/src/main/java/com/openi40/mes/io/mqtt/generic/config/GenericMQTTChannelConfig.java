package com.openi40.mes.io.mqtt.generic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.io.mqtt.generic.config")

public class GenericMQTTChannelConfig {
	boolean avoidInitializeOnStartup=false;
	List<IntegratedChannelsConfig> integrations = new ArrayList<IntegratedChannelsConfig>();

	public GenericMQTTChannelConfig() {

	}

	public boolean isAvoidInitializeOnStartup() {
		return avoidInitializeOnStartup;
	}

	public void setAvoidInitializeOnStartup(boolean avoidInitializeOnStartup) {
		this.avoidInitializeOnStartup = avoidInitializeOnStartup;
	}

	public List<IntegratedChannelsConfig> getIntegrations() {
		return integrations;
	}

	public void setIntegrations(List<IntegratedChannelsConfig> integrations) {
		this.integrations = integrations;
	}

}
