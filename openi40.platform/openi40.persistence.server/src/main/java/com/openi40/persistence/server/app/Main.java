/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.persistence.server.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.openi40.scheduler.engine.contextualplugarch.ContextualArchitectureChecker;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication

@ComponentScan("com.openi40")
@ComponentScan("com.openi40.scheduler.engine.contextualplugarch.generated")
@EntityScan(basePackages = "com.openi40")
@EnableJpaRepositories(basePackages = "com.openi40")
public class Main implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		//ContextualPluginArchitectureInitializer.initialize(System.getProperties(), "com.productionscheduler");
		SpringApplication.run(Main.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {

	}
}
