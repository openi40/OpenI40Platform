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
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.SqlEntityIntegrationConfig;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;
import com.openi40.generical.dbintegration.utils.JDBCCopy;
import com.openi40.generical.dbintegration.utils.MetaDataUtils;

@Service
public class SqlEntityImporter implements IEntityImporter<SqlEntityIntegrationConfig> {

	static Logger LOGGER = LoggerFactory.getLogger(SqlEntityImporter.class);

	public void transferEntities(SqlEntityIntegrationConfig sqlConfig, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection, Connection otherDBConnection, boolean runIncremental, Timestamp ts) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		JDBCCopy jdbcCopy = null;
		boolean inTransaction = false;
		try {
			if (runIncremental && sqlConfig.isIncrementalSync() && (sqlConfig.getIncrementalSyncSql() == null
					|| sqlConfig.getIncrementalSyncSql().trim().length() == 0)) {
				String msg = "Entity=>" + sqlConfig.getEntityName() + " has not got incrementalSyncSql configuration";
				LOGGER.error(msg);
				throw new RuntimeException(msg);
			}
			String runSql = runIncremental ? sqlConfig.getIncrementalSyncSql() : sqlConfig.getExtractSql();
			LOGGER.info("Entity: " + sqlConfig.getEntityName() + " SQL=>" + sqlConfig.getExtractSql());
			LOGGER.info("Creating query resultset");
			ps = otherDBConnection.prepareStatement(runSql);
			ps.clearParameters();
			if (runIncremental) {
				ParameterMetaData pmd = ps.getParameterMetaData();
				int nParams = pmd.getParameterCount();
				for (int i = 0; i < nParams; i++) {
					ps.setTimestamp(i + 1, ts);
				}
			}
			String tableName = MetaDataUtils.getTableName(sqlConfig.getEntityName(), metaData);
			rs = ps.executeQuery();
			LOGGER.info("Query resultset created");
			openi40Connection.setAutoCommit(false);
			inTransaction = true;
			jdbcCopy = new JDBCCopy(rs, openi40Connection, tableName, JDBCCopy.INSERT_UPDATE);
			jdbcCopy.boolean_as_boolean = true;
			jdbcCopy.copy();
			jdbcCopy.releaseResources();
			jdbcCopy = null;
			inTransaction = false;
			openi40Connection.commit();
			openi40Connection.setAutoCommit(true);
		} catch (Throwable th) {
			String msg = "Error importing Entity: " + sqlConfig.getEntityName();
			LOGGER.error(msg, th);
			if (inTransaction) {
				try {
					openi40Connection.rollback();
					openi40Connection.setAutoCommit(true);
				} catch (Throwable th1) {
				}
			}
			throw new RuntimeException(msg, th);
		} finally {
			try {
				rs.close();
			} catch (Throwable t) {
			}
			try {
				ps.close();
			} catch (Throwable t) {
			}
			try {
				if (jdbcCopy != null)
					jdbcCopy.releaseResources();
			} catch (Throwable th) {
			}
		}

	}

	@Override
	public Class<SqlEntityIntegrationConfig> getManagedConfigurationType() {
		return SqlEntityIntegrationConfig.class;
	}

	@Override
	public boolean isSupportsConfiguration(SqlEntityIntegrationConfig config) {

		return true;
	}

}
