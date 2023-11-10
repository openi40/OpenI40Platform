package com.openi40.mes.io.mqtt.generic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.io.mqtt.generic.config")
@Data
public class GenericMQTTChannelConfig {
	List<IntegratedChannelsConfig> integrations = new ArrayList<IntegratedChannelsConfig>();

	public GenericMQTTChannelConfig() {

	}

}
