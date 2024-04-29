/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
package com.openi40.generical.dbintegration.meta.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.generical.dbmetadata")

public class IntegrationMetaDataConfiguration {
	List<EntityMetaData> importedEntities=new ArrayList<>();
	String schemaName=null;
	
	public IntegrationMetaDataConfiguration() {
		
	}

	public List<EntityMetaData> getImportedEntities() {
		return importedEntities;
	}

	public void setImportedEntities(List<EntityMetaData> importedEntities) {
		this.importedEntities = importedEntities;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}
