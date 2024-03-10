package com.openi40.commons.multithreading.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class OpenI40AsyncConfig {
	static Logger LOGGER = LoggerFactory.getLogger(OpenI40AsyncConfig.class);
	private OpenI40MultithreadingConfig config = null;

	public OpenI40AsyncConfig(OpenI40MultithreadingConfig config) {
		this.config = config;
	}

	@Bean
	public Executor taskExecutor() {
		int corePoolSize = this.config != null && this.config.getMinThreads() != null ? this.config.getMinThreads() : 1;
		int maxPoolSize = this.config != null && this.config.getMaxThreads() != null ? this.config.getMaxThreads() : 1;
		int queueCapacity = this.config != null && this.config.getExecutorQueueCapacity() != null
				? this.config.getExecutorQueueCapacity()
				: 100;
		LOGGER.info("OpenI40 useMultithreading=" + (config != null && config.isUseMultithreading()) + " corePoolSize="
				+ corePoolSize + " maxPoolSize=" + maxPoolSize + " queueCapacity=" + queueCapacity);
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("openi40-multithread-executor-");
		executor.initialize();
		return executor;
	}
}
