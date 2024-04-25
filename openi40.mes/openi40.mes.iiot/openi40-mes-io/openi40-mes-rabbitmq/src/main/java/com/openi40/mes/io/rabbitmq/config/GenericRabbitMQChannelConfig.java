package com.openi40.mes.io.rabbitmq.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.io.rabbitmq")
public class GenericRabbitMQChannelConfig {
	private List<IntegratedChannelsConfig> channels=new ArrayList<IntegratedChannelsConfig>();
	public GenericRabbitMQChannelConfig() {
		
	}
	public List<IntegratedChannelsConfig> getChannels() {
		return channels;
	}
	public void setChannels(List<IntegratedChannelsConfig> channels) {
		this.channels = channels;
	}

}
