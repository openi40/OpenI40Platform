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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.JavaEntityIntegrationConfig;
import com.openi40.generical.dbintegration.handlers.IEntityIntegrationFactory;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;
import com.openi40.generical.dbintegration.utils.MetaDataUtils;
import com.openi40.generical.dbintegration.utils.TypesManager;

@Service
public class JavaEntityImporter implements IEntityImporter<JavaEntityIntegrationConfig> {

	static Logger LOGGER = LoggerFactory.getLogger(JavaEntityImporter.class);

	private void updateValues(Map<Object, Object> entries, String entityName, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection) throws SQLException {
		Map<String, Object> lowerCaseMap = new HashMap<>();

		for (Map.Entry entry : entries.entrySet()) {
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (key != null && val != null) {
				String k = key.toString().trim().toLowerCase();
				lowerCaseMap.put(k, val);
			}
		}
		if (!lowerCaseMap.containsKey("code"))
			throw new RuntimeException(
					"The code primary key field is mandatory in entity =>" + entityName + " actual map=" + entries);
		Object code = lowerCaseMap.remove("code");
		List<String> fields = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		for (Map.Entry<String, Object> entry : lowerCaseMap.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			fields.add(key);
			values.add(val);
		}
		String tableName = MetaDataUtils.getTableName(entityName, metaData);
		String insertSQL = "insert into " + tableName + "(";
		String updateSQL = "update " + tableName + " set ";
		for (int i = 0; i < fields.size(); i++) {
			insertSQL += fields.get(i) + ",";
			updateSQL += fields.get(i) + "=?" + ",";
		}
		insertSQL += "code) values (";
		for (int i = 0; i < fields.size(); i++) {
			insertSQL += "?,";
		}
		insertSQL += "?)";
		updateSQL += "code=? where code=?";
		PreparedStatement updatePS = null;
		PreparedStatement insertPS = null;
		try {
			LOGGER.info("Try executing: " + updateSQL + " with values => " + values);
			updatePS = openi40Connection.prepareStatement(updateSQL);

			TypesManager.fillPreparedStatementParameter(updatePS, true, new Vector(values));

			updatePS.setString(fields.size() + 1, code.toString());
			updatePS.setString(fields.size() + 2, code.toString());
			if (updatePS.executeUpdate() == 0) {
				LOGGER.info("Try executing: " + insertSQL + " with values => " + values);
				insertPS = openi40Connection.prepareStatement(insertSQL);
				TypesManager.fillPreparedStatementParameter(insertPS, true, new Vector(values));
				insertPS.setString(fields.size() + 1, code.toString());
				insertPS.executeUpdate();
			}
		} catch (Throwable th) {
			String msg = "Updating problem on entry entity=" + entityName + " values " + entries;
			LOGGER.error(msg, th);
			throw new RuntimeException(msg, th);
		} finally {
			try {
				updatePS.close();
			} catch (Throwable t) {
			}
			try {
				insertPS.close();
			} catch (Throwable t) {
			}
		}
	}

	@Override
	public void transferEntities(JavaEntityIntegrationConfig jEntityConfig, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection, Connection otherDBConnection, boolean runIncremental, Timestamp ts) {
		List mapList = new ArrayList<>();
		if (jEntityConfig.getEntityIntegrationFactory() != null
				&& jEntityConfig.getEntityIntegrationFactory().trim().length() > 0) {
			try {
				Class factoryType = Class.forName(jEntityConfig.getEntityIntegrationFactory().trim());
				IEntityIntegrationFactory factory = (IEntityIntegrationFactory) factoryType.newInstance();

				if (runIncremental) {
					mapList = factory.getEntities(jEntityConfig.getParams(), ts);
				} else {
					mapList = factory.getEntities(jEntityConfig.getParams());
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				String msg = "Error using factory => " + jEntityConfig.getEntityIntegrationFactory();
				LOGGER.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		} else {
			mapList = jEntityConfig.getMappedEntries();
		}
		if (mapList != null && !mapList.isEmpty()) {
			Map<Object, Object> map = null;
			boolean inTransaction = false;
			try {
				openi40Connection.setAutoCommit(false);
				inTransaction = true;
				for (Object object : mapList) {
					map = (Map) object;
					updateValues(map, jEntityConfig.getEntityName(), metaData, openi40Connection);
				}
				map = null;
				openi40Connection.commit();
				openi40Connection.setAutoCommit(true);
			} catch (Throwable th) {
				String msg = "Error inserting for entity=>" + jEntityConfig.getEntityName() + " values => "
						+ (map != null ? map : "NULL");
				LOGGER.error(msg, th);
				if (inTransaction) {
					try {
						openi40Connection.rollback();
						openi40Connection.setAutoCommit(true);
					} catch (Throwable th1) {
					}
				}
				throw new RuntimeException(msg, th);
			}
		}

	}

	@Override
	public Class<JavaEntityIntegrationConfig> getManagedConfigurationType() {

		return JavaEntityIntegrationConfig.class;
	}

	@Override
	public boolean isSupportsConfiguration(JavaEntityIntegrationConfig config) {

		return true;
	}

}
