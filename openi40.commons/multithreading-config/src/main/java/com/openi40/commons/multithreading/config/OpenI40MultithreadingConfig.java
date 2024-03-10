package com.openi40.commons.multithreading.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.commons.multithreading.config")
public class OpenI40MultithreadingConfig {
	private boolean useMultithreading=false;
	private Integer maxThreads=1;
	private Integer minThreads=1;
	private Integer executorQueueCapacity=100;
	public Integer getExecutorQueueCapacity() {
		return executorQueueCapacity;
	}

	public void setExecutorQueueCapacity(Integer executorQueueCapacity) {
		this.executorQueueCapacity = executorQueueCapacity;
	}

	public Integer getMinThreads() {
		return minThreads;
	}

	public void setMinThreads(Integer minThreads) {
		this.minThreads = minThreads;
	}

	public boolean isUseMultithreading() {
		return useMultithreading;
	}

	public void setUseMultithreading(boolean useMultithreading) {
		this.useMultithreading = useMultithreading;
	}

	

	public Integer getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}
}
