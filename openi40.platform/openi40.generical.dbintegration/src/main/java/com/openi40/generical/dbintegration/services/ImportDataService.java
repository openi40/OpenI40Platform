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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.EntityIntegrationConfig;
import com.openi40.generical.dbintegration.configuration.IntegrationConfiguration;
import com.openi40.generical.dbintegration.configuration.JavaEntityIntegrationConfig;
import com.openi40.generical.dbintegration.configuration.SqlEntityIntegrationConfig;
import com.openi40.generical.dbintegration.handlers.IEntityDeletionHandler;
import com.openi40.generical.dbintegration.meta.configuration.EntityMetaData;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;
import com.openi40.generical.dbintegration.utils.MetaDataUtils;

@Service
public class ImportDataService {
	static Logger LOGGER = LoggerFactory.getLogger(ImportDataService.class);
	EntityImporterFactory importerFactory = null;
	IEntityDeletionHandler defaultDeletionHandler = null;

	@Autowired
	public ImportDataService(EntityImporterFactory importerFactory,
			@Qualifier(DefaultEntityDeletionHandler.DEFAULT_DELETION_HANDLER_SERVICE) IEntityDeletionHandler defaultDeletionHandler) {
		this.importerFactory = importerFactory;
		this.defaultDeletionHandler = defaultDeletionHandler;
	}

	private List<EntityIntegrationConfig> sortByInsertOrder(List<EntityIntegrationConfig> list,
			IntegrationMetaDataConfiguration metaData) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin sortByInsertOrder(" + list + "," + metaData + ")");
		}
		List<EntityIntegrationConfig> outList = new ArrayList<>();
		List<EntityMetaData> entitiesList = metaData.getImportedEntities();
		if (entitiesList != null) {
			for (Iterator iterator = entitiesList.iterator(); iterator.hasNext();) {
				EntityMetaData entityMetaData = (EntityMetaData) iterator.next();
				boolean found = false;
				for (EntityIntegrationConfig x : list) {
					found = x.getEntityName().trim().equalsIgnoreCase(entityMetaData.getEntityName().trim());
					if (found) {
						outList.add(x);
						LOGGER.info("Found configured entity to transfer:" + entityMetaData.getEntityName());
						break;
					}
				}
				if (!found) {
					LOGGER.warn("Entity importation: " + entityMetaData.getEntityName() + " not found");
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End sortByInsertOrder(...) returning=>" + outList);
		}
		return outList;
	}

	public static final String IMPORT_ALGORITHM = "IMPORT";
	public static final String TS_PROPERTY = "INTEGRATION_TS";

	private Timestamp getEntityLastTimestamp(EntityIntegrationConfig eConfig, Connection openi40Connection,
			IntegrationMetaDataConfiguration meta) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getEntityLastTimestamp(...) for entity=>" + eConfig.getEntityName());
		}
		String tableName = MetaDataUtils.getTableName(eConfig.getEntityName(), meta);
		String selectSQL = "SELECT TS_VALUE FROM OI40_INCREMENTAL_SYNC WHERE ALGORITHM_ID='" + IMPORT_ALGORITHM
				+ "' AND TABLE_NAME='" + tableName + "' AND TS_PROPERTY='" + TS_PROPERTY + "'";
		Timestamp ts = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = openi40Connection.createStatement();
			rs = statement.executeQuery(selectSQL);
			if (rs.next()) {
				ts = rs.getTimestamp(1);
			}
		} catch (Throwable th) {
			String msg = "Cannot read timestamp for table config=>" + eConfig.toString();
			LOGGER.error(msg, th);
			throw new RuntimeException(msg, th);
		} finally {
			try {
				rs.close();
			} catch (Throwable t) {
			}
			try {
				statement.close();
			} catch (Throwable t) {
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End getEntityLastTimestamp(...) for entity=>" + eConfig.getEntityName() + " timestamp=" + ts);
		}
		return ts;
	}

	private void updateEntityLastTimestamp(EntityIntegrationConfig eConfig, Connection openi40Connection,
			IntegrationMetaDataConfiguration meta) {
		Statement statement = null;
		String tableName = MetaDataUtils.getTableName(eConfig.getEntityName(), meta);
		String _sqlDelete = "DELETE FROM OI40_INCREMENTAL_SYNC WHERE ALGORITHM_ID='" + IMPORT_ALGORITHM
				+ "' AND TABLE_NAME='" + tableName + "' AND TS_PROPERTY='" + TS_PROPERTY + "'";
		String update = "INSERT INTO OI40_INCREMENTAL_SYNC(ALGORITHM_ID, TABLE_NAME, TS_PROPERTY, TS_VALUE) SELECT '"
				+ IMPORT_ALGORITHM + "','" + tableName + "','" + TS_PROPERTY + "',max(" + TS_PROPERTY + ") FROM "
				+ tableName;
		try {
			openi40Connection.setAutoCommit(false);
			statement = openi40Connection.createStatement();
			statement.executeUpdate(_sqlDelete);
			statement.executeUpdate(update);
			openi40Connection.commit();
		} catch (Throwable th) {
			String _MSG = "ERROR UPDATING INTEGRATION TIMESTAMP FOR " + eConfig.getEntityName() + "table=" + tableName;
			LOGGER.error(_MSG, th);
			try {
				openi40Connection.rollback();
			} catch (Throwable t) {
			}
			throw new RuntimeException(_MSG, th);
		} finally {
			try {
				statement.close();
			} catch (Throwable t) {
			}
		}
	}

	public void importData(Connection openi40Connection, Connection otherDBConnection,
			IntegrationConfiguration configuration, IntegrationMetaDataConfiguration metaData, boolean incremental) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin importData(..) with incremental=" + incremental);
		}
		List<EntityIntegrationConfig> entitiesConfig = new ArrayList<>();
		if (configuration.getJavaExtracted() != null) {
			entitiesConfig.addAll(configuration.getJavaExtracted());
		}
		if (configuration.getSqlExtracted() != null) {
			entitiesConfig.addAll(configuration.getSqlExtracted());
		}

		entitiesConfig = sortByInsertOrder(entitiesConfig, metaData);
		for (EntityIntegrationConfig eConfig : entitiesConfig) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Running sync algorithm for entity=>" + eConfig.getEntityName() + " config=" + eConfig);
			}
			Timestamp ts = null;
			boolean runTimestampUpdate = eConfig.isIncrementalSync();
			if (runTimestampUpdate) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Entity=>" + eConfig.getEntityName() + " has incrementalSync=true");
				}
				ts = getEntityLastTimestamp(eConfig, openi40Connection, metaData);
			}
			IEntityImporter<EntityIntegrationConfig> importer = importerFactory.getImplementationFor(eConfig);
			boolean runIncremental = eConfig.isIncrementalSync() && incremental && ts != null;
			importer.transferEntities(eConfig, metaData, openi40Connection, otherDBConnection, runIncremental, ts);
			if (runTimestampUpdate) {
				updateEntityLastTimestamp(eConfig, openi40Connection, metaData);
			}
			if (eConfig.isHandleDeletions()) {
				IEntityDeletionHandler deletionHandler = this.defaultDeletionHandler;
				if (eConfig.getCustomDeletionHandlerClass() != null
						&& eConfig.getCustomDeletionHandlerClass().trim().length() > 0) {
					try {
						deletionHandler = (IEntityDeletionHandler) Class
								.forName(eConfig.getCustomDeletionHandlerClass().trim()).newInstance();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						String msg = "Cannot instantiate configured deletion handler=>"
								+ eConfig.getCustomDeletionHandlerClass()
								+ " this must have a public default constructor without parameters and must implement "
								+ IEntityDeletionHandler.class.getName();
						LOGGER.error(msg, e);
						throw new RuntimeException(msg, e);
					}

				}
				deletionHandler.handleDeletions(eConfig, metaData, openi40Connection, otherDBConnection, incremental);

			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End importData(..) with incremental=" + incremental);
		}
	}

}
