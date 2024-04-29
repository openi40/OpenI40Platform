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
package com.openi40.generical.dbintegration.services;

import java.sql.Connection;
import java.sql.Timestamp;

import com.openi40.generical.dbintegration.configuration.EntityIntegrationConfig;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;

public interface IEntityImporter<ConfigEntity extends EntityIntegrationConfig> {
	public void transferEntities(ConfigEntity config, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection, Connection otherDBConnection, boolean runIncremental, Timestamp ts);
	public Class<ConfigEntity> getManagedConfigurationType();
	public boolean isSupportsConfiguration(ConfigEntity config);
}
