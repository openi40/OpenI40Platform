package com.openi40.generical.dbintegration.handlers;

import java.sql.Connection;

import com.openi40.generical.dbintegration.configuration.EntityIntegrationConfig;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;
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
public interface IEntityDeletionHandler {
	public int handleDeletions(EntityIntegrationConfig config, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection, Connection otherDBConnection, boolean runIncremental);
}
