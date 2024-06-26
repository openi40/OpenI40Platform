package com.openi40.mes.asset.workstation.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@ComponentScan("com.openi40")

@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = "com.openi40.mes")
@EnableJpaRepositories(basePackages = "com.openi40")
public class Main implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(Main.class, args);
		LOG.info("APPLICATION FINISHED");
		
	}

	@Override
	public void run(String... args) {

	}
}
