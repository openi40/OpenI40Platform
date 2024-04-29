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
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.IDBConfig;
import com.openi40.generical.dbintegration.configuration.IntegrationConfiguration;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;

@Service
public class DBIntegrationService {
	DataSource openi40DataSource = null;
	IntegrationConfiguration integrationConfiguration = null;
	IntegrationMetaDataConfiguration metaData=null;
	ImportDataService importService = null;
	ExportDataService exportService = null;
	static Logger LOGGER = LoggerFactory.getLogger(DBIntegrationService.class);

	@Autowired
	public DBIntegrationService(DataSource openi40DataSource, IntegrationConfiguration integrationConfiguration,IntegrationMetaDataConfiguration metaData,
			ImportDataService importService, ExportDataService exportService) {
		this.openi40DataSource = openi40DataSource;
		this.integrationConfiguration = integrationConfiguration;
		this.importService = importService;
		this.exportService = exportService;
		this.metaData=metaData;
	}

	public void importData(boolean incremental) {
		Connection openi40Connection = null;
		Connection otherDBConnection = null;
		try {
			IDBConfig otherDBCfg = integrationConfiguration.getOtherdb();
			openi40Connection = this.openi40DataSource.getConnection();
			otherDBConnection = DriverManager.getConnection(otherDBCfg.getJdbcConnectionString(),
					otherDBCfg.getJdbcUid(), otherDBCfg.getJdbcPwd());
			importService.importData(openi40Connection, otherDBConnection, integrationConfiguration,metaData,incremental);
		} catch (Throwable throwable) {
			LOGGER.error("Error in importData()", throwable);
			throw new RuntimeException("Error in importData()", throwable);
		} finally {
			try {
				openi40Connection.close();
			} catch (Throwable t) {
			}
			try {
				otherDBConnection.close();
			} catch (Throwable t) {
			}
		}
	}

	public void exportData() {

	}

}
