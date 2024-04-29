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

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.generical.dbintegration.configuration.EntityIntegrationConfig;
import com.openi40.generical.dbintegration.handlers.IEntityDeletionHandler;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;
import com.openi40.generical.dbintegration.utils.MetaDataUtils;

@Service
@Qualifier(DefaultEntityDeletionHandler.DEFAULT_DELETION_HANDLER_SERVICE)
public class DefaultEntityDeletionHandler implements IEntityDeletionHandler {
	static Logger LOGGER = LoggerFactory.getLogger(DefaultEntityDeletionHandler.class);
	public static final String DEFAULT_DELETION_HANDLER_SERVICE = "defaultDeletionHandlerService";
	ObjectMapper mapper = new ObjectMapper();

	public DefaultEntityDeletionHandler() {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	private static class Oi40DeletedEntries {
		public String code = null;
		public String tablename = null;
		public Timestamp integrationTs = null;
		public String contentType = null;;
		public String recordValue;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getTablename() {
			return tablename;
		}
		public void setTablename(String tablename) {
			this.tablename = tablename;
		}
		public Timestamp getIntegrationTs() {
			return integrationTs;
		}
		public void setIntegrationTs(Timestamp integrationTs) {
			this.integrationTs = integrationTs;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public String getRecordValue() {
			return recordValue;
		}
		public void setRecordValue(String recordValue) {
			this.recordValue = recordValue;
		}
	}

	@Override
	public int handleDeletions(EntityIntegrationConfig config, IntegrationMetaDataConfiguration metaData,
			Connection openi40Connection, Connection otherDBConnection, boolean runIncremental) {
		PreparedStatement ps = null;
		boolean inTransaction = false;
		ResultSet rs = null;
		String tableName = MetaDataUtils.getTableName(config.getEntityName(), metaData);
		List<Oi40DeletedEntries> toBeDeleted = new ArrayList<Oi40DeletedEntries>();
		Timestamp ts = getEntityLastTimestamp(tableName, openi40Connection);
		String _sql = "select code,tablename,integration_ts,content_type,record_value from OI40_DELETED_ENTRIES  WHERE  tablename=?";
		if (runIncremental && ts != null) {
			_sql += " AND integration_ts>?";
		}
		try {
			ps = openi40Connection.prepareStatement(_sql);
			ps.clearParameters();
			ps.setString(1, tableName);
			if (runIncremental && ts != null) {

				ps.setTimestamp(2, ts);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Oi40DeletedEntries entry = new Oi40DeletedEntries();
				entry.code = rs.getString(1);
				entry.tablename = rs.getString(2);
				entry.integrationTs = rs.getTimestamp(3);
				entry.contentType = rs.getString(4);
				entry.recordValue = rs.getString(5);
				toBeDeleted.add(entry);
			}
			rs.close();
			ps.close();
			rs = null;
			ps = null;
			String updateLogicalDeletion = "UPDATE " + tableName + " SET removed=?,modified_ts=? where code=?";
			openi40Connection.setAutoCommit(false);
			inTransaction = true;
			ps = openi40Connection.prepareStatement(updateLogicalDeletion);
			for (Oi40DeletedEntries oi40DeletedEntry : toBeDeleted) {
				List<String> codes = extractCodes(oi40DeletedEntry);
				if (codes != null) {
					for (String code : codes) {
						ps.clearParameters();
						ps.setBoolean(1, true);
						ps.setTimestamp(2, oi40DeletedEntry.integrationTs);
						ps.setString(3, code);
						ps.executeUpdate();
					}
				}

			}
			updateEntityLastTimestamp(tableName, openi40Connection);
			openi40Connection.commit();
		} catch (Throwable e) {
			if (inTransaction) {
				try {
					openi40Connection.rollback();
				} catch (Throwable t) {
				}
			}
			String msg = "Error in handling standard deletion for " + config.getEntityName();
			LOGGER.error(msg, e);
			throw new RuntimeException(msg, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Throwable t) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Throwable t) {
			}
		}
		return toBeDeleted.size();
	}

	private List<String> extractCodes(Oi40DeletedEntries entry) {
		List<String> codes = new ArrayList<String>();
		if (entry.recordValue != null && entry.recordValue.trim().length() > 0) {
			String contentType = entry.contentType != null && entry.contentType.trim().length() > 0
					? entry.contentType.trim().toLowerCase()
					: "text/plain";

			switch (entry.contentType) {
			case "text/plain": {
				codes.add(entry.recordValue.trim());
			}
				break;
			case "application/xml": {
				codes.addAll(decodeXMLEntries(entry));
			}
				break;
			case "application/json": {
				codes.addAll(decodeJSONEntries(entry));
			}
				break;
			default:
				throw new RuntimeException("ContentType=" + contentType + " unhandled for entry=>" + entry.toString());
			}
		} else {
			LOGGER.warn("The deletion entry=>" + entry.toString()
					+ " does not carry a record(s) code information, its record_value field is null or blank");
		}
		return codes;
	}

	
	public static class CodeItem {
		private String code = null;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	public static class CodeItemArray extends ArrayList<CodeItem> {
	};

	private List<String> decodeJSONEntries(Oi40DeletedEntries entry) {
		List<String> codes = new ArrayList<String>();
		CodeItem codeItem = null;
		try {
			codeItem = mapper.readValue(entry.recordValue.getBytes(), CodeItem.class);
			if (codeItem != null && codeItem.getCode() != null) {
				codes.add(codeItem.getCode().trim());
			}
		} catch (Throwable e) {
			LOGGER.warn("Cannot read single item with code='...' attribute from " + entry.getRecordValue()
					+ " deletion entry=>" + entry.toString(), e);
		}
		CodeItemArray array = null;
		try {
			array = mapper.readValue(entry.recordValue.getBytes(), CodeItemArray.class);
			if (array != null) {
				for (CodeItem item : array) {
					if (item != null && item.getCode() != null) {
						codes.add(item.getCode().trim());
					}
				}
			}
		} catch (Throwable e) {
			LOGGER.warn("Cannot read array of item(s) with code='...' attribute from " + entry.getRecordValue()
					+ " deletion entry=>" + entry.toString(), e);
		}
		if (codes.isEmpty()) {
			String msg = "Cannot read code(s) to delete from " + entry.getRecordValue() + " from deletion infos:"
					+ entry.toString();
			throw new RuntimeException(msg);
		}
		return codes;
	}

	private void fillList(Element element, List<String> codes) {
		if (element.hasAttribute("code")) {
			codes.add(element.getAttribute("code"));
		}
		org.w3c.dom.NodeList childs = element.getChildNodes();
		for (int childId = 0; childId < childs.getLength(); childId++) {
			org.w3c.dom.Node node = childs.item(childId);
			if (node instanceof Element) {
				Element item = (Element) node;
				fillList(item, codes);
			}
		}
	}

	private List<String> decodeXMLEntries(Oi40DeletedEntries entry) {
		String recordValue = entry.recordValue;
		List<String> codes = new ArrayList<String>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(new ByteArrayInputStream(recordValue.getBytes()));
			Element root = document.getDocumentElement();
			fillList(root, codes);
		} catch (Throwable e) {
			String msg = "Cannot read from " + recordValue + " list of codes from entry=>" + entry.toString();
			LOGGER.error(msg, e);
			throw new RuntimeException(msg, e);
		}
		return codes;
	}

	public static final String DELETION_ALGORITHM = "DELETION";
	public static final String TS_PROPERTY = "INTEGRATION_TS";

	private Timestamp getEntityLastTimestamp(String tableName, Connection openi40Connection) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getEntityLastTimestamp(...) for table=>" + tableName);
		}
		String selectSQL = "SELECT TS_VALUE FROM OI40_INCREMENTAL_SYNC WHERE ALGORITHM_ID='" + DELETION_ALGORITHM
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
			String msg = "Cannot read timestamp for table config=>" + tableName;
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
			LOGGER.debug("End getEntityLastTimestamp(...) for table=>" + tableName + " timestamp=" + ts);
		}
		return ts;
	}

	private void updateEntityLastTimestamp(String tableName, Connection openi40Connection) throws SQLException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin updateEntityLastTimestamp('"+tableName+"',..) ");
		}
		Statement statement = null;

		String _sqlDelete = "DELETE FROM OI40_INCREMENTAL_SYNC WHERE ALGORITHM_ID='" + DELETION_ALGORITHM
				+ "' AND TABLE_NAME='" + tableName + "' AND TS_PROPERTY='" + TS_PROPERTY + "'";
		
		String update = "INSERT INTO OI40_INCREMENTAL_SYNC(ALGORITHM_ID, TABLE_NAME, TS_PROPERTY, TS_VALUE) SELECT '"
				+ DELETION_ALGORITHM + "','" + tableName + "','" + TS_PROPERTY + "',max(" + TS_PROPERTY
				+ ") FROM OI40_DELETED_ENTRIES where tablename='" + tableName + "'";
		try {

			statement = openi40Connection.createStatement();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Executing: "+_sqlDelete);
			}
			statement.executeUpdate(_sqlDelete);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Executing: "+update);
			}
			statement.executeUpdate(update);

		} catch (SQLException th) {
			String _MSG = "ERROR UPDATING INTEGRATION TIMESTAMP FOR deletion on table=" + tableName;
			LOGGER.error(_MSG, th);

			throw new SQLException(_MSG, th);
		} finally {
			try {
				statement.close();
			} catch (Throwable t) {
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End updateEntityLastTimestamp('"+tableName+"',..) ");
		}
	}
}
