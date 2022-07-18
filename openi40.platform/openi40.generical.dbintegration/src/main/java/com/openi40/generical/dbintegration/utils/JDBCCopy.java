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

package com.openi40.generical.dbintegration.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCCopy {
	static Logger LOGGER=LoggerFactory.getLogger(JDBCCopy.class);
	String targetSchema = null;

	private boolean doubleQuoteTables = false;
	private boolean skipStrangeCharInFieldName = true;

	public boolean boolean_as_boolean = false;

	public boolean boolean_target_is_char = false;

	public boolean manageNAN = false;

	java.sql.ResultSet source;

	int inserted = 0, updated = 0;

	java.sql.PreparedStatement preparedStatement = null;

	Vector addon_autoincrement_field = new Vector();

	Vector addon_fields = new Vector();

	Vector addon_values = new Vector();

	Vector fields = new Vector();

	Vector values = new Vector();

	java.sql.Connection dest;

	String tableName;

	boolean active = true;

	boolean canCommit = true;

	public static final int INSERT = 1;

	public static final int INSERT_UPDATE = 2;
	private TreeMap setNullToBlankFields = new TreeMap();
	int copy_mode = 0;

	ResultSetMetaData meta_data = null;

	Hashtable source_types = new Hashtable();

	int _source_types[] = null;

	public JDBCCopy(java.sql.Connection sourceConnection, java.sql.Connection dest, String tableName, String inputSchema, String outputSchema,
			String whereCondition, int copyMode) throws SQLException {
		setupCopy(sourceConnection, dest, tableName, inputSchema, outputSchema, whereCondition, copyMode);

	}

	private void setupCopy(java.sql.Connection sourceConnection, java.sql.Connection dest, String tableName, String inputSchema, String outputSchema,
			String whereCondition, int copyMode) throws SQLException {
		this.dest = dest;
		this.tableName = tableName;
		this.copy_mode = copyMode;
		// this.source = source;
		Vector d_fields = new Vector();
		Vector s_fields = new Vector();
		Vector fields = new Vector();
		DatabaseMetaData md = sourceConnection.getMetaData();
		String _schema = inputSchema;
		String outSchema = outputSchema;
		if (inputSchema != null) {
			tableName = inputSchema + "." + tableName;
		}
		String _table = null;
		if (tableName.indexOf(".") > -1) {
			_schema = tableName.substring(0, tableName.indexOf("."));
			_table = tableName.substring(tableName.indexOf(".") + 1);
		} else {
			_table = tableName;
		}
		ResultSet rs = md.getColumns(null, _schema, _table, "%");
		while (rs.next()) {
			s_fields.addElement(rs.getString("COLUMN_NAME").trim().toLowerCase());
		}
		rs.close();
		if (s_fields.size() == 0 && sourceConnection.getClass().toString().toLowerCase().indexOf("db2") != -1) {
			LOGGER.info("IBM DB2V6.1 JDBC BUG WORKRND FOR SOURCE !");
			Statement catalog_statement = sourceConnection.createStatement();
			rs = catalog_statement.executeQuery("select name from sysibm.syscolumns where tbname='" + _table.toUpperCase() + "' "
					+ (_schema != null ? " and tbcreator = '" + _schema.toUpperCase() + "'" : ""));
			while (rs.next()) {

				s_fields.addElement(rs.getString(1).toLowerCase().trim());
			}
			rs.close();
		}
		md = dest.getMetaData();
		rs = md.getColumns(null, outputSchema, _table, "%");
		while (rs.next()) {
			String COLUMN_NAME = rs.getString("COLUMN_NAME").trim().toLowerCase();
			d_fields.addElement(COLUMN_NAME);
		}
		rs.close();
		if (d_fields.size() == 0 && dest.getClass().toString().toLowerCase().indexOf("db2") != -1) {
			LOGGER.info("IBM DB2V6.1 JDBC BUG WORKRND FOR DESTINATION !");
			Statement catalog_statement = dest.createStatement();
			rs = catalog_statement.executeQuery("select name from sysibm.syscolumns where tbname='" + _table.toUpperCase() + "' "
					+ (_schema != null ? " and tbcreator = '" + _schema.toUpperCase() + "'" : ""));
			while (rs.next()) {
				d_fields.addElement(rs.getString(1).toLowerCase().trim());
			}
			rs.close();
		}
		for (Iterator iter = d_fields.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if (element.toLowerCase().equals("h_id_company")) {
				// this.targetContainsH_id_company=true;
			}
			if (s_fields.contains(element)) {
				fields.addElement(element);
			}
		}
		LOGGER.info("Contained fields in both source and destination " + tableName + " " + fields);
		String sql = "select ";
		for (int i = 0; i < fields.size(); i++) {
			sql += (quoteFieldNames ? "\"" : "") + fields.elementAt(i) + (quoteFieldNames ? "\"" : "");
			if (i < fields.size() - 1)
				sql += ",";
		}
		sql += " from " + tableName;
		if (whereCondition != null) {
			sql += " where " + whereCondition;
		}
		this.source = sourceConnection.createStatement().executeQuery(sql);
	}

	public JDBCCopy(java.sql.Connection sourceConnection, java.sql.Connection dest, String tableName, String inputSchema, String outputSchema,
			int copyMode) throws SQLException {
		super();
		setupCopy(sourceConnection, dest, tableName, inputSchema, outputSchema, null, copyMode);

	}

	public void addSetNullToBlankString(String fieldName) {
		this.setNullToBlankFields.put(fieldName.toLowerCase(), "");
	}

	/**
	 * JDBCCopy constructor comment.
	 */
	public JDBCCopy(java.sql.Connection sourceConnection, java.sql.Connection dest, String tableName, int copyMode) throws SQLException {
		super();
		this.dest = dest;
		this.tableName = tableName;
		this.copy_mode = copyMode;
		// this.source = source;
		Vector d_fields = new Vector();
		Vector s_fields = new Vector();
		Vector fields = new Vector();
		DatabaseMetaData md = sourceConnection.getMetaData();
		String _schema = null;
		String _table = null;
		if (tableName.indexOf(".") > -1) {
			_schema = tableName.substring(0, tableName.indexOf("."));
			_table = tableName.substring(tableName.indexOf(".") + 1);
		} else {
			_table = tableName;
		}
		ResultSet rs = md.getColumns(null, _schema, _table, "%");
		while (rs.next()) {
			s_fields.addElement(rs.getString("COLUMN_NAME").trim().toLowerCase());
		}
		rs.close();
		if (s_fields.size() == 0 && sourceConnection.getClass().toString().toLowerCase().indexOf("db2") != -1) {
			LOGGER.info("IBM DB2V6.1 JDBC BUG WORKRND FOR SOURCE !");
			Statement catalog_statement = sourceConnection.createStatement();
			rs = catalog_statement.executeQuery("select name from sysibm.syscolumns where tbname='" + _table.toUpperCase() + "' "
					+ (_schema != null ? " and tbcreator = '" + _schema.toUpperCase() + "'" : ""));
			while (rs.next()) {
				s_fields.addElement(rs.getString(1).toLowerCase().trim());
			}
			rs.close();
		}
		md = dest.getMetaData();
		rs = md.getColumns(null, _schema, _table, "%");
		while (rs.next()) {
			String COLUMN_NAME = rs.getString("COLUMN_NAME").trim().toLowerCase();
			d_fields.addElement(COLUMN_NAME);
		}
		rs.close();
		if (d_fields.size() == 0 && dest.getClass().toString().toLowerCase().indexOf("db2") != -1) {
			LOGGER.info("IBM DB2V6.1 JDBC BUG WORKRND FOR DESTINATION !");
			Statement catalog_statement = dest.createStatement();
			rs = catalog_statement.executeQuery("select name from sysibm.syscolumns where tbname='" + _table.toUpperCase() + "' "
					+ (_schema != null ? " and tbcreator = '" + _schema.toUpperCase() + "'" : ""));
			while (rs.next()) {
				d_fields.addElement(rs.getString(1).toLowerCase().trim());
			}
			rs.close();
		}
		for (Iterator iter = d_fields.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if (element.toLowerCase().equals("h_id_company")) {
				// this.targetContainsH_id_company=true;
			}
			if (s_fields.contains(element)) {
				fields.addElement(element);
			}
		}
		LOGGER.info("Contained fields in both source and destination " + tableName + " " + fields);
		String sql = "select ";
		for (int i = 0; i < fields.size(); i++) {
			sql += (quoteFieldNames ? "\"" : "") + fields.elementAt(i) + (quoteFieldNames ? "\"" : "");
			if (i < fields.size() - 1)
				sql += ",";
		}
		sql += " from " + tableName;
		this.source = sourceConnection.createStatement().executeQuery(sql);
	}

	boolean quoteFieldNames = false;
	boolean continueOnError = false;

	public JDBCCopy(java.sql.ResultSet source, java.sql.Connection dest, String tableName) {
		super();
		this.source = source;
		this.dest = dest;
		this.tableName = tableName;
		this.copy_mode = INSERT;
	}

	public JDBCCopy(java.sql.ResultSet source, java.sql.Connection dest, String tableName, int copyMode) {
		super();
		this.source = source;
		this.dest = dest;
		this.tableName = tableName;
		this.copy_mode = copyMode;
	}

	/**
	 * Insert the method's description here. Creation date: (29/04/2001
	 * 13.45.56)
	 * 
	 * @return java.sql.PreparedStatement
	 */
	private PreparedStatement buildPreparedStatement() throws SQLException {
		ResultSetMetaData md = (meta_data == null) ? meta_data = source.getMetaData() : meta_data;
		PreparedStatement ps = null;
		String stat = "INSERT INTO " + tableName + " (";
		for (int index = 1; index <= md.getColumnCount(); index++) {
			String column = ((quoteFieldNames ? "\"" : "")
					+ (this.skipStrangeCharInFieldName ? md.getColumnName(index).replace('#', 'R').replace(' ', 'S').replace('$', 'D') : md
							.getColumnName(index).trim()) + (quoteFieldNames ? "\"" : "")).toLowerCase();
			String column4query = column;
			if (dest.getClass().getName().toLowerCase().indexOf("db2") != -1 && specialFieldNameQuoted) {
				if (column.equalsIgnoreCase("timestamp")) {
					column4query = "\"timestamp\"";
				}
				if (column.equalsIgnoreCase("role")) {
					column4query = "\"role\"";
				}
			}
			stat += column4query;
			if (index < md.getColumnCount()) {
				stat += ",";
			}
		}
		for (int i = 0; i < this.addon_autoincrement_field.size(); i++) {
			stat += "," + this.addon_autoincrement_field.elementAt(i);
		}
		for (int i = 0; i < this.addon_fields.size(); i++) {
			stat += ",";
			stat += (quoteFieldNames ? "\"" : "") + this.addon_fields.elementAt(i) + (quoteFieldNames ? "\"" : "");
		}
		stat += ") values (";
		for (int index = 1; index <= md.getColumnCount(); index++) {
			stat += "?";
			if (index < md.getColumnCount()) {
				stat += ",";
			}
		}
		for (int i = 0; i < this.addon_autoincrement_field.size(); i++) {
			stat += ",?";
		}
		for (int i = 0; i < this.addon_values.size(); i++) {
			stat += ",";
			stat += (this.addon_values.elementAt(i) instanceof String) ? "'" + this.addon_values.elementAt(i).toString().trim() + "'"
					: this.addon_values.elementAt(i).toString().trim();
		}
		stat += ")";
		LOGGER.info("INSERT:" + stat);
		ps = dest.prepareStatement(stat);
		return ps;
	}

	boolean specialFieldNameQuoted = true;

	/**
	 * Insert the method's description here. Creation date: (29/04/2001
	 * 13.41.55)
	 * 
	 * @return int[]
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */
	public int[] buildTypesArray() throws java.sql.SQLException {
		ResultSetMetaData md = (meta_data == null) ? meta_data = source.getMetaData() : meta_data;
		;
		String query = "SELECT ";
		_source_types = new int[md.getColumnCount()];
		for (int index = 1; index <= md.getColumnCount(); index++) {

			String column = skipStrangeCharInFieldName ? md.getColumnName(index).replace('#', 'R').replace(' ', 'S').replace('$', 'D').toLowerCase()
					: md.getColumnName(index).toLowerCase();
			String column4query = (quoteFieldNames ? "\"" : "") + column + (quoteFieldNames ? "\"" : "");
			if (dest.getClass().getName().toLowerCase().indexOf("db2") != -1 && specialFieldNameQuoted) {
				if (column.equalsIgnoreCase("timestamp")) {
					column4query = "\"timestamp\"";
				}
				if (column.equalsIgnoreCase("role")) {
					column4query = "\"role\"";
				}
			}
			_source_types[index - 1] = md.getColumnType(index);
			query += column4query;
			fields.addElement(skipStrangeCharInFieldName ? md.getColumnName(index).replace('#', 'R').replace(' ', 'S').replace('$', 'D')
					.toLowerCase() : md.getColumnName(index).toLowerCase());
			if (index < md.getColumnCount())
				query += ",";
			source_types.put(md.getColumnName(index).toLowerCase(), new Integer(md.getColumnType(index)));
		}
		String _tableName = targetSchema != null && targetSchema.trim().length() > 0 && tableName.indexOf(".") == -1 ? targetSchema + "." + tableName
				: tableName;
		query += " FROM " + _tableName + " ";
		LOGGER.info(query);
		Statement st = dest.createStatement();
		ResultSet rs = st.executeQuery(query);
		md = rs.getMetaData();
		int typesV[] = new int[md.getColumnCount()];
		for (int index = 1; index <= md.getColumnCount(); index++) {
			typesV[index - 1] = md.getColumnType(index);
		}
		rs.close();
		st.close();
		
		return typesV;
	}

	byte buffer[] = new byte[512];

	/**
	 * Insert the method's description here. Creation date: (29/04/2001
	 * 13.52.21)
	 * 
	 * @param rs
	 *            java.sql.ResultSet
	 * @param ps
	 *            java.sql.PreparedStatement
	 * @param types
	 *            int
	 */
	private void doInsert(ResultSet rs, PreparedStatement insert_ps, int types[]) throws SQLException {
		insert_ps.clearParameters();

		try {
			Vector bises = new Vector();
			Vector blobs = new Vector();
			values.removeAllElements();
			for (int parIndex = 1; parIndex <= types.length; parIndex++) {
				String _columnName = (String) this.fields.elementAt(parIndex - 1);
				_columnName = _columnName.trim().toLowerCase();
				Vector cumulated_data = (Vector) this.readed_fields.get(_columnName);
				switch (types[parIndex - 1]) {
				case Types.BLOB:
				case Types.BINARY:
				case Types.VARBINARY:
				case Types.LONGVARBINARY: {
					/*
					 * Blob is = rs.getBlob(parIndex); if (is != null) {
					 * insert_ps.setBlob(parIndex, is); blobs.addElement(is); }
					 * else { insert_ps.setNull(parIndex, types[parIndex - 1]);
					 * }
					 */
					InputStream is = rs.getBinaryStream(parIndex);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int nc = 0;
					int tot_nc = 0;
					try {
						while (is != null && (nc = is.read(buffer)) >= 0) {
							bos.write(buffer, 0, nc);
							tot_nc += nc;
						}

						if (tot_nc > 0) {
							bos.flush();
							bos.close();
							byte _buff[] = bos.toByteArray();
							if (_buff != null && _buff.length < 1000) {
								insert_ps.setBytes(parIndex, _buff);
							} else {
								ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
								bises.addElement(bis);
								insert_ps.setBinaryStream(parIndex, bis, _buff.length);
								bis.close();
								bis = null;
							}

							bos = null;
						} else {
							insert_ps.setNull(parIndex, types[parIndex - 1]);
						}

						values.addElement("STREAM");
					} catch (IOException exc) {
						exc.printStackTrace();
						throw new IllegalStateException(exc.getMessage());
					}
					try {

						bos.close();
					} catch (Throwable th12) {
					}
					try {

						is.close();
					} catch (Throwable th12) {
					}

				}
					break;
				case Types.BIT:
				case Types.BOOLEAN: {
					if (boolean_as_boolean) {
						boolean value = rs.getBoolean(parIndex);
						insert_ps.setBoolean(parIndex, value);
						values.addElement(new Boolean(value));
						if (cumulated_data != null) {
							cumulated_data.addElement(new Boolean(value));
						}
					} else {
						Class type = TypesManager.getJavaMappedType(types[parIndex - 1]);
						Object value = TypesManager.getObject(rs, parIndex, type);
						if (value instanceof String) {
							value = value.toString().trim();
						}
						if (value instanceof Clob) {
							value = value.toString().trim();
						}
						values.addElement((value));
						if (cumulated_data != null) {
							cumulated_data.addElement(value);
						}
						if (value == null)
							TypesManager.fillPreparedStatementParameter(insert_ps, true, value, type, parIndex);
						else
							TypesManager.fillPreparedStatementParameter(insert_ps, true, value, value.getClass(), parIndex);

					}
				}
					break;
				case Types.VARCHAR:
				case Types.CHAR: {
					if (boolean_as_boolean) {
						String value = rs.getString(parIndex);
						if (value == null) {
							insert_ps.setNull(parIndex, types[parIndex - 1]);
						} else {
							insert_ps.setString(parIndex, value.trim());

						}
						values.addElement((value));
						if (cumulated_data != null) {
							cumulated_data.addElement(value);
						}
						break;
					} else if (this.boolean_target_is_char) {
						if (this._source_types != null) {
							if (this._source_types[parIndex - 1] == Types.BIT || this._source_types[parIndex - 1] == Types.BOOLEAN) {
								boolean _boolean = rs.getBoolean(parIndex);
								String _s = _boolean ? TypesManager.TRUE : TypesManager.FALSE;
								insert_ps.setString(parIndex, _s);
								if (cumulated_data != null) {
									cumulated_data.addElement(_s);
								}
								break;
							}
						}
					}

				}
					;
				default: {
					Class type = TypesManager.getJavaMappedType(types[parIndex - 1]);
					Object value = TypesManager.getObject(rs, parIndex, type);
					// SE IL CAMPO E' IN setNullToBlankFields   stringa e, leggo
					// il null allora lo rimpiazzo con la stringa vuota.
					if (type.equals(String.class) && this.setNullToBlankFields.containsKey(_columnName) && value == null) {
						value = "";
					}
					if (value instanceof String) {
						value = value.toString().trim();
					}
					if (value instanceof Clob) {
						value = value.toString().trim();
					}
					if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
						if (manageNAN) {
							Number number = (Number) value;
							if (Double.isNaN(number.doubleValue())) {
								if (value instanceof Double) {
									value = new Double((double) 0);
								} else if (value instanceof Float) {
									value = new Float(0);
								} else if (value instanceof BigDecimal) {
									value = new BigDecimal((double) 0);
								}
							}
						}
					}
					values.addElement((value));
					if (cumulated_data != null) {
						cumulated_data.addElement(value);
					}
					if (value == null)
						TypesManager.fillPreparedStatementParameter(insert_ps, true, value, type, parIndex);
					else
						TypesManager.fillPreparedStatementParameter(insert_ps, true, value, value.getClass(), parIndex);
				}
				}
			}
			for (int i = 0; i < this.addon_autoincrement_field.size(); i++) {
				String _fieldName = this.addon_autoincrement_field.elementAt(i).toString();
				long _value = ((Long) this._startupCounters.get(_fieldName)).longValue();
				insert_ps.setLong(types.length + i + 1, _value);
				_value++;
			}
			inserted += insert_ps.executeUpdate();
			for (Iterator iter = blobs.iterator(); iter.hasNext();) {
				Blob element = (Blob) iter.next();

			}
			for (Iterator iter = bises.iterator(); iter.hasNext();) {
				InputStream element = (InputStream) iter.next();
				try {
					element.close();
				} catch (Throwable th12) {
				}
			}
			bises.removeAllElements();
			blobs.removeAllElements();
		} catch (SQLException exc) {
			for (int i = 0; i < Math.min(fields.size(), values.size()); i++) {
				LOGGER.info(fields.elementAt(i) + "-->" + values.elementAt(i));
			}
			throw exc;
		} finally {
		}
	}

	private void doReplaceUpdate(ResultSet rs, PreparedStatement insert_ps, PreparedStatement update_ps, PreparedStatement verify_ps,
			boolean useVerification, int types[], int index_page[]) throws SQLException {
		Object vals[] = new Object[types.length];
		Class j_types[] = new Class[types.length];
		Hashtable _dataRec = new Hashtable();
		int howmany = 0;
		try {
			for (int parIndex = 1; parIndex <= types.length; parIndex++) {
				String _columnName = (String) this.fields.elementAt(parIndex - 1);
				_columnName = _columnName.trim().toLowerCase();
				Vector cumulated_data = (Vector) this.readed_fields.get(_columnName);
				Class type = null;
				Object value = null;

				switch (types[parIndex - 1]) {
				case Types.BIT:
				case Types.BOOLEAN: {
					if (boolean_as_boolean) {
						boolean _boolean = rs.getBoolean(parIndex);
						type = Boolean.class;
						value = new Boolean(_boolean);
					} else {
						type = TypesManager.getJavaMappedType(types[parIndex - 1]);
						value = TypesManager.getObject(rs, parIndex, type);
						// rs.getObject(parIndex);
						if (value instanceof String) {
							value = value.toString().trim();
						}
						if (value instanceof Clob) {
							value = value.toString().trim();
						}
					}
				}
					break;
				case Types.CHAR:
				case Types.VARCHAR: {
					if (this._source_types[parIndex - 1] == Types.BIT || this._source_types[parIndex - 1] == Types.BOOLEAN) {
						boolean _boolean = rs.getBoolean(parIndex);
						String _s = _boolean ? TypesManager.TRUE : TypesManager.FALSE;
						value = _s;
						type = String.class;
						break;
					}
				}
				// case Types.BINARY: {
				// byte buffer[] = rs.getBytes(parIndex);
				// value = buffer;
				// type = buffer.getClass();
				// }
				// break;
				// case Types.BLOB: {
				// Blob blob = rs.getBlob(parIndex);
				// value=blob;
				// type = Blob.class;
				// }
				// break;
				default: {
					type = TypesManager.getJavaMappedType(types[parIndex - 1]);
					value = TypesManager.getObject(rs, parIndex, type);
					// SE IL CAMPO E' IN setNullToBlankFields   stringa e, leggo
					// il null allora lo rimpiazzo con la stringa vuota.
					if (type.equals(String.class) && this.setNullToBlankFields.containsKey(_columnName) && value == null) {
						value = "";
					}

					// rs.getObject(parIndex);
					if (value instanceof String) {
						value = value.toString().trim();
					}
					if (value instanceof Clob) {
						value = value.toString().trim();
					}
					if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
						if (manageNAN) {
							Number number = (Number) value;
							if (Double.isNaN(number.doubleValue())) {
								if (value instanceof Double) {
									value = new Double(0);
								} else if (value instanceof Float) {
									value = new Float(0);
								} else if (value instanceof BigDecimal) {
									value = new BigDecimal((double) 0);
								}
							}
						}
					}
				}
				}
				if (value != null) {
					_dataRec.put(_columnName, value);
				}
				vals[parIndex - 1] = value;
				j_types[parIndex - 1] = type;
				if (cumulated_data != null) {
					cumulated_data.addElement(value);
				}
			}
			Object value = null;
			if (useVerification) {
				verify_ps.clearParameters();
				for (int parIndex = 1; parIndex <= index_page.length; parIndex++) {
					value = vals[parIndex - 1];
					if (value == null) {
						switch (types[parIndex - 1]) {
						case Types.BIT:
						case Types.BOOLEAN: {
							if (boolean_as_boolean) {
								verify_ps.setNull(parIndex, types[parIndex - 1]);
							} else {
							}
						}
							break;

						default:
							TypesManager.fillPreparedStatementParameter(verify_ps, true, value, j_types[parIndex - 1], index_page[parIndex - 1]);
						}

					} else {

						if (value instanceof Boolean && boolean_as_boolean) {
							verify_ps.setBoolean(index_page[parIndex - 1], ((Boolean) value).booleanValue());
						} else
							TypesManager.fillPreparedStatementParameter(verify_ps, true, value, value.getClass(), index_page[parIndex - 1]);
					}
				}
				ResultSet thisrs = verify_ps.executeQuery();
				if (thisrs.next()) {
					howmany = thisrs.getInt(1);
				}
				thisrs.close();
			} else {
				update_ps.clearParameters();
				for (int parIndex = 1; parIndex <= index_page.length; parIndex++) {
					value = vals[parIndex - 1];
					if (value == null) {
						switch (types[parIndex - 1]) {
						case Types.BIT:
						case Types.BOOLEAN: {
							if (boolean_as_boolean) {
								update_ps.setNull(parIndex, types[parIndex - 1]);
							} else {
							}
						}
							break;
						// case Types.BLOB: {
						//
						// update_ps.setNull(parIndex, types[parIndex - 1]);
						// }
						// break;

						default:
							TypesManager.fillPreparedStatementParameter(update_ps, true, value, j_types[parIndex - 1], index_page[parIndex - 1]);
						}

					} else {
						// if (value instanceof Blob) {
						// update_ps.setBlob(parIndex, (Blob) value);
						// } else
						if (value instanceof Boolean && boolean_as_boolean) {
							update_ps.setBoolean(index_page[parIndex - 1], ((Boolean) value).booleanValue());
						} else
							TypesManager.fillPreparedStatementParameter(update_ps, true, value, value.getClass(), index_page[parIndex - 1]);
					}
				}
				howmany = update_ps.executeUpdate();
			}
			updated += howmany;
			if (howmany == 0) {
				insert_ps.clearParameters();
				for (int parIndex = 1; parIndex <= index_page.length; parIndex++) {
					value = vals[parIndex - 1];
					if (value == null) {
						switch (types[parIndex - 1]) {
						case Types.BIT:
						case Types.BOOLEAN: {
							if (boolean_as_boolean) {
								insert_ps.setNull(parIndex, Types.BOOLEAN);
							} else {
								TypesManager.fillPreparedStatementParameter(insert_ps, true, value, j_types[parIndex - 1], parIndex);
							}
						}
							break;
						default:
							TypesManager.fillPreparedStatementParameter(insert_ps, true, value, j_types[parIndex - 1], parIndex);
						}

					} else {
						if (value instanceof Boolean && boolean_as_boolean) {
							insert_ps.setBoolean(parIndex, ((Boolean) value).booleanValue());
						} else
							TypesManager.fillPreparedStatementParameter(insert_ps, true, value, value.getClass(), parIndex);
					}
					// if (value == null)
					// TypesManager.fillPreparedStatementParameter(insert_ps,
					// true, value, j_types[parIndex - 1], parIndex);
					// else
					// TypesManager.fillPreparedStatementParameter(insert_ps,
					// true, value, value.getClass(), parIndex);
				}
				inserted += insert_ps.executeUpdate();
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			LOGGER.info("Data error on : " + _dataRec);
			throw exc;
		} finally {
		}
	}

	/**
	 * Insert the method's description here. Creation date: (29/04/2001
	 * 14.23.10)
	 * 
	 * @return java.lang.Object
	 */
	/**
	 * Insert the method's description here. Creation date: (01/05/2001
	 * 10.28.54)
	 * 
	 * @return java.lang.Throwable
	 */
	/**
	 * Insert the method's description here. Creation date: (29/04/2001
	 * 14.24.25)
	 * 
	 * @return boolean
	 */
	public boolean isActive() {
		return active;
	}

	public void releaseResources() {
		try {
			preparedStatement.close();
		} catch (Throwable th) {
		}
		try {
			source.close();
		} catch (Throwable th) {
		}
	}

	public void addIfPresent(String field, Object value) throws SQLException {
		Statement statement = null;
		ResultSet rs = null;
		Hashtable ht = new Hashtable();
		try {
			statement = this.dest.createStatement();
			rs = statement.executeQuery("select * from " + tableName);
			ResultSetMetaData md = rs.getMetaData();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				ht.put(md.getColumnName(i).toLowerCase(), "");
			}
		} finally {
			try {
				rs.close();
			} catch (Throwable th1) {
			}
			try {
				statement.close();
			} catch (Throwable th1) {
			}
		}
		if (ht.containsKey(field.toLowerCase())) {
			LOGGER.info("Setting " + tableName + "." + field + "--> " + value);
			addon_fields.addElement(field);
			addon_values.addElement(value);
		}
	}

	boolean dumpStructure = false;

	public int copy() throws SQLException {
		int index = 0;
		try {
			int[] types = buildTypesArray();
			preparedStatement = buildPreparedStatement();
			switch (this.copy_mode) {
			case INSERT: {
				while (source.next()) {
					try {
						doInsert(source, preparedStatement, types);
						index++;
					} catch (Throwable th) {
						th.printStackTrace();
						if (!continueOnError) {
							throw new SQLException("nexted exception", th);
						}
					}
				}
			}
				;
				break;
			case INSERT_UPDATE: {
				PreparedStatement update_statement = null;
				PreparedStatement verifyExistenceStatement = null;
				try {
					DatabaseMetaData meta_data = dest.getMetaData();
					StringTokenizer tokenizer = new StringTokenizer(tableName, ".");
					String table = null;
					String schema = null;
					Vector strings = new Vector();
					while (tokenizer.hasMoreTokens()) {
						strings.addElement(tokenizer.nextToken());
					}
					if (strings.size() >= 2) {
						schema = strings.elementAt(0).toString();
						table = strings.elementAt(1).toString();
					}
					if (strings.size() == 1) {
						table = strings.elementAt(0).toString();
					}
					if (strings.size() == 0) {
						throw new IllegalStateException("The table " + tableName + " has no schema.table definition");
					}
					if (targetSchema != null && schema == null)
						schema = targetSchema;
					ResultSet rs_pk = meta_data.getPrimaryKeys(null, schema, table);
					Vector pkey_fields = new Vector();
					while (rs_pk.next()) {
						pkey_fields.addElement(rs_pk.getString("COLUMN_NAME").toLowerCase().trim());
					}
					rs_pk.close();
					if (pkey_fields.size() == 0) {
						rs_pk = meta_data.getPrimaryKeys(null, null, tableName);
						while (rs_pk.next()) {
							pkey_fields.addElement(rs_pk.getString("COLUMN_NAME").toLowerCase().trim());
						}
						rs_pk.close();
					}
					if (pkey_fields.size() == 0) {
						rs_pk = meta_data.getPrimaryKeys("%", schema, table);
						while (rs_pk.next()) {
							pkey_fields.addElement(rs_pk.getString("COLUMN_NAME").toLowerCase().trim());
						}
						rs_pk.close();
					}
					if (pkey_fields.size() == 0 && dest.getClass().toString().toLowerCase().indexOf("db2") != -1) {
						LOGGER.info("IBM DB2V6.1 JDBC BUG WORKRND !");
						Statement catalog_statement = dest.createStatement();
						if (schema == null)
							schema = targetSchema;
						rs_pk = catalog_statement.executeQuery("select name from sysibm.syscolumns where tbname='" + table.toUpperCase()
								+ "' and tbcreator='" + schema.toUpperCase() + "' and keyseq is not null");
						while (rs_pk.next()) {
							pkey_fields.addElement(rs_pk.getString(1).toLowerCase().trim());
						}
						rs_pk.close();
					}
					if (pkey_fields.size() == 0)
						throw new IllegalStateException("The JDBC Driver  returns the " + tableName + " not having a primary key");
					LOGGER.info("The table " + tableName + " has primary key -->" + pkey_fields);
					Vector non_pkey = new Vector();
					Vector pkey = new Vector();
					int index_table[] = new int[types.length];
					for (int i = 0; i < fields.size(); i++) {
						Object field = fields.elementAt(i);
						if (pkey_fields.contains(field)) {
							pkey.addElement(field);
						} else {
							non_pkey.addElement(field);
						}
					}
					for (int i = 0; i < fields.size(); i++) {
						Object field = fields.elementAt(i);
						if (non_pkey.contains(field)) {
							index_table[i] = non_pkey.indexOf(field) + 1;
						}
						if (pkey.contains(field)) {
							index_table[i] = non_pkey.size() + pkey.indexOf(field) + 1;
						}
					}

					String update_sql = "update " + tableName + " set ";
					for (int i = 0; i < non_pkey.size(); i++) {
						update_sql += non_pkey.elementAt(i) + " = ?";
						if (i < non_pkey.size() - 1)
							update_sql += ",";
					}
					update_sql += " where ";
					String verify_sql = "select count(*) from " + tableName + " where ";
					for (int i = 0; i < pkey.size(); i++) {
						update_sql += pkey.elementAt(i) + " = ?";
						verify_sql += pkey.elementAt(i) + " = ?";
						if (i < pkey.size() - 1) {
							update_sql += " and ";
							verify_sql += " and ";
						}
					}
					boolean useVerify = non_pkey.size() == 0;
					System.out.println("UPDATE:" + update_sql);
					System.out.println("VERIFY CONTEGGIO:" + verify_sql + " is used = " + useVerify);
					update_statement = dest.prepareStatement(update_sql);
					verifyExistenceStatement = dest.prepareStatement(verify_sql);
					while (source.next()) {
						try {
							doReplaceUpdate(source, preparedStatement, update_statement, verifyExistenceStatement, useVerify, types, index_table);
							index++;
						} catch (Throwable th) {
							th.printStackTrace();
							if (!continueOnError) {
								throw new SQLException("nexted exception", th);
							}
						}
					}
				} finally {
					try {
						update_statement.close();
					} catch (Throwable th1) {
					}
					try {
						verifyExistenceStatement.close();
					} catch (Throwable th1) {
					}
				}
			}
				;
				break;
			default:
				break;
			}
		} finally {
			try {
				preparedStatement.close();
			} catch (Throwable th1) {
			}
			LOGGER.info("Table " + tableName + " inserted " + inserted + " recs updated " + updated
					+ " recs");
		}
		return index;
	}

	public int getInserted() {
		return inserted;
	}

	public ResultSetMetaData getMeta_data() {
		return meta_data;
	}

	public void setMeta_data(ResultSetMetaData meta_data) {
		this.meta_data = meta_data;
	}

	public boolean isBoolean_as_boolean() {
		return boolean_as_boolean;
	}

	public boolean isBoolean_target_is_char() {
		return boolean_target_is_char;
	}

	public void setBoolean_as_boolean(boolean boolean_as_boolean) {
		this.boolean_as_boolean = boolean_as_boolean;
	}

	public void setBoolean_target_is_char(boolean boolean_target_is_char) {
		this.boolean_target_is_char = boolean_target_is_char;
	}

	public String getTargetSchema() {
		return targetSchema;
	}

	public void setTargetSchema(String targetSchema) {
		this.targetSchema = targetSchema;
	}

	public boolean isQuoteFieldNames() {
		return quoteFieldNames;
	}

	public void setQuoteFieldNames(boolean quoteFieldNames) {
		this.quoteFieldNames = quoteFieldNames;
	}

	Hashtable readed_fields = new Hashtable();

	public void addReaded_field(String fieldName) {
		readed_fields.put(fieldName.trim().toLowerCase(), new Vector());
	}

	Hashtable _startupCounters = new Hashtable();

	public void addAutoIncrementField(String fieldName, long startupValue) {
		this.addon_autoincrement_field.addElement(fieldName);
		this._startupCounters.put(fieldName, new Long(startupValue));
	}

	public Vector getReaded_field(String fieldName) {
		return (Vector) readed_fields.get(fieldName.trim().toLowerCase());
	}

	public boolean isDoubleQuoteTables() {
		return doubleQuoteTables;
	}

	public void setDoubleQuoteTables(boolean doubleQuoteTables) {
		this.doubleQuoteTables = doubleQuoteTables;
	}

	public boolean isSpecialFieldNameQuoted() {
		return specialFieldNameQuoted;
	}

	public void setSpecialFieldNameQuoted(boolean specialFieldNameQuoted) {
		this.specialFieldNameQuoted = specialFieldNameQuoted;
	}

	public boolean isSkipStrangeCharInFieldName() {
		return skipStrangeCharInFieldName;
	}

	public void setSkipStrangeCharInFieldName(boolean skipStrangeCharInFieldName) {
		this.skipStrangeCharInFieldName = skipStrangeCharInFieldName;
	}

	public boolean isDumpStructure() {
		return dumpStructure;
	}

	public void setDumpStructure(boolean dumpStructure) {
		this.dumpStructure = dumpStructure;
	}

	public boolean isContinueOnError() {
		return continueOnError;
	}

	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	// public short getOverriddenH_id_company() {
	// return overriddenH_id_company;
	// }
	//
	// public void setOverriddenH_id_company(short overriddenH_id_company) {
	// this.overriddenH_id_company = overriddenH_id_company;
	// }
}