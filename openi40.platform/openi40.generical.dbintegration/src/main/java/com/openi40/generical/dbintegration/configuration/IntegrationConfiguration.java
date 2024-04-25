package com.openi40.generical.dbintegration.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@ConfigurationProperties(prefix = "com.openi40.generical.dbintegration")

public class IntegrationConfiguration {	
	IDBConfig otherdb=new IDBConfig();
	List<SqlEntityIntegrationConfig> sqlExtracted = new ArrayList<>();
	List<JavaEntityIntegrationConfig> javaExtracted = new ArrayList<>();

	public IntegrationConfiguration() {

	}

	public IDBConfig getOtherdb() {
		return otherdb;
	}

	public void setOtherdb(IDBConfig otherdb) {
		this.otherdb = otherdb;
	}

	public List<SqlEntityIntegrationConfig> getSqlExtracted() {
		return sqlExtracted;
	}

	public void setSqlExtracted(List<SqlEntityIntegrationConfig> sqlExtracted) {
		this.sqlExtracted = sqlExtracted;
	}

	public List<JavaEntityIntegrationConfig> getJavaExtracted() {
		return javaExtracted;
	}

	public void setJavaExtracted(List<JavaEntityIntegrationConfig> javaExtracted) {
		this.javaExtracted = javaExtracted;
	}

}
