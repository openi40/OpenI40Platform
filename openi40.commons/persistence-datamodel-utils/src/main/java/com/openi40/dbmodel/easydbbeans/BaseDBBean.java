package com.openi40.dbmodel.easydbbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;

/**
 * DB Bean di Base Creation date: (24/04/2002 15.42.21)
 * 
 * @author Paolo Zavalloni : architectures@openi40.org (paolo.zavalloni@zconsultancies.com)
 */

public class BaseDBBean extends AutoDescribingObject implements Serializable {
	static Logger LOGGER = LoggerFactory.getLogger(BaseDBBean.class);
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String TRUE = "1";
	// protected boolean usedAsJSFPOJO = false;

	public static String FALSE = "0";
	@JsonIgnore
	public boolean PUT_ZERO_AUTOINCREMENTS = false; // SE TRUE

	// AUTOINCREMENTARE
	// E' SUBORDINATO
	// ALLA PRESENZA DI
	// UNO 0 O NULL in
	// CAMPO CONTATORE
	@JsonIgnore
	protected String table = null;
	@JsonIgnore
	protected String primaryKeyProperties[] = null;
	@JsonIgnore
	protected String autoIncrements[] = null;
	@JsonIgnore
	protected boolean New = true;
	@JsonIgnore
	private String tableAlias = "";
	@JsonIgnore
	protected String application = null;
	@JsonIgnore
	protected SCounterManager counterManager = new SCounterManager();

	protected BaseDBBean createNewInstance() throws InstantiationException, IllegalAccessException {

		return (BaseDBBean) this.getClass().newInstance();

	}

	@JsonIgnore
	public boolean MANAGE_APPLICATION_LEVEL_COUNTERS = true;
	@JsonIgnore
	public boolean MANAGE_CENTRAL_TABLE_COUNTERS = false;

	/**
	 * BaseDBBean constructor
	 */
	public BaseDBBean() {
		super();
	}

	public BaseDBBean(boolean manageCentralTableCounterrs) {
		MANAGE_CENTRAL_TABLE_COUNTERS = manageCentralTableCounterrs;
		if (manageCentralTableCounterrs) {
			this.counterManager = new PersistentCounterManager();
		}
	}

	/**
	 * Esegue la join naturale tra oggetti di classi passate come parametro Creation
	 * date: (21/01/2003 12.17.10)
	 * 
	 * @return java.util.Vector
	 * @param dbBeanClass java.lang.Class[]
	 * @param aliases     java.lang.String[]
	 * @param whereStrin  java.lang.String
	 * @param orderString java.lang.String
	 * @param connection  java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 * @throws PersistenceException
	 */
	public static java.util.Vector[] cartesianProduct(Class[] dbBeanClass, String[] aliases, String whereString,
			String orderString, java.sql.Connection connection) throws PersistenceException {
		BaseDBBean objects[] = new BaseDBBean[dbBeanClass.length];
		StringBuffer buffer = new StringBuffer("select ");
		for (int i = 0; i < dbBeanClass.length; i++) {
			try {
				objects[i] = (BaseDBBean) dbBeanClass[i].newInstance();
			} catch (Throwable th) {
				throw new IllegalStateException("Impossible to allocate " + dbBeanClass[i].getClass().getName());
			}
			java.beans.PropertyDescriptor pd[] = objects[i].thlGetPropertyList();
			for (int index = 0; index < pd.length; index++) {
				buffer.append(aliases[i] + "." + pd[index].getName() + " as " + aliases[i] + "_" + pd[index].getName());
				if (index < pd.length - 1 || i < dbBeanClass.length - 1)
					buffer.append(",");
			}
		}
		buffer.append(" from ");
		for (int i = 0; i < objects.length; i++) {
			buffer.append(objects[i].table);
			buffer.append(" ");
			buffer.append(aliases[i]);
			if (i < objects.length - 1)
				buffer.append(",");
		}
		if (whereString != null && whereString.trim().length() != 0) {
			buffer.append(" where ");
			buffer.append(whereString);
		}
		if (orderString != null && orderString.trim().length() != 0) {
			buffer.append(" order by ");
			buffer.append(orderString);
		}
		return join(dbBeanClass, aliases, buffer.toString(), connection);
	}

	/**
	 * Reads object state from passed BaseDBBean Creation date: (24/04/2002
	 * 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public void copyData(BaseDBBean dbBean) throws PersistenceException {
		java.beans.PropertyDescriptor pds[] = dbBean.thlGetPropertyList();
		for (int index = 0; index < pds.length; index++) {
			try {
				Object value = dbBean.thlGet(pds[index].getName());
				thlSet(pds[index].getName(), value);

			} catch (Throwable th1) {
				LOGGER.error("Error in copyData", th1);
				throw new PersistenceException("Error in copyData", th1);
			}
		}
	}

	/**
	 * Deletes the DB image of this bean from DB Creation date: (24/04/2002
	 * 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public void delete(java.sql.Connection connection) throws java.sql.SQLException {
		if (New)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " is in New state and U R tryng to deleting it");
		if (table == null)
			throw new IllegalStateException("The bean " + getClass().getName() + " has null table name");
		if (primaryKeyProperties == null)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " has null primary key properties array");
		java.sql.PreparedStatement ps = null;
		try {
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES DELLA
			// QUERY
			StringBuffer delete = new StringBuffer(" DELETE FROM " + table + " WHERE ");
			// VETTORE PER VALORI DELLE PROPERTIES
			java.util.Vector propertyValues = new java.util.Vector();
			// VETTORE PER NOME DEI CAMPI DEL DB
			java.util.Vector fieldList = new java.util.Vector();
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			for (; index < primaryKeyProperties.length; index++) {
				Object object = thlGet(primaryKeyProperties[index]);
				if (object == null) {
					throw new java.sql.SQLException("Primary key field " + primaryKeyProperties[index] + " is null");
				} else {
					delete.append(primaryKeyProperties[index] + " = ? ");
					propertyValues.addElement(object);
				}
				if (index < primaryKeyProperties.length - 1)
					delete.append(" AND ");
			}
			index = 0;
			ps = connection.prepareStatement(delete.toString());
			// RIEMPIO I PARAMETRI DEL PREPARED STATEMENT
			for (; index < primaryKeyProperties.length; index++) {
				Object object = propertyValues.elementAt(index);
				fillPreparedStatementParameter(ps, object, object.getClass(), index + 1);
			}
			// FACCIO UPDATE
			ps.executeUpdate();
			New = true;
		} catch (java.sql.SQLException exc) {
			LOGGER.error("Error in delete", exc);
			throw exc;
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Throwable th) {
				}
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (15/06/2003 16.57.01)
	 * 
	 * @param ps java.sql.PreparedStatement
	 * @exception PersistenceException  The exception description.
	 * @exception java.sql.SQLException The exception description.
	 */
	public void fillInsertPS(java.sql.PreparedStatement ps, java.sql.Connection connection)
			throws PersistenceException {
		java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
		Object value = null;
		try {
			ps.clearParameters();
			for (int index = 0; index < pds.length; index++) {
				try {
					String propertyName = pds[index].getName();
					boolean autoInc = false;
					for (int a = 0; autoIncrements != null && !autoInc && a < autoIncrements.length; a++) {
						autoInc = autoIncrements[a].equals(propertyName);
					}
					if (autoInc) {

						long id = counterManager.getNewInt64CounterValue(table, propertyName, connection);
						if (pds[index].getPropertyType().equals(Integer.class)) {
							value = new Integer((int) id);
						} else if (pds[index].getPropertyType().equals(Short.class)) {
							value = new Short((short) id);
						} else if (pds[index].getPropertyType().equals(Long.class)) {
							value = new Long(id);
						}

					} else {
						value = thlGet(propertyName);
					}
					TypesManager.fillPreparedStatementParameter(ps, true, value, pds[index].getPropertyType(),
							index + 1);
				} catch (java.sql.SQLException exc) {
					LOGGER.error("Error in fillInsertPS", exc);
					throw new PersistenceException("Error in fillInsertPS", exc);
				}
			}
			ps.executeUpdate();
		} catch (java.sql.SQLException exc) {
			LOGGER.error("Error in fillInsertPS", exc);
			throw new PersistenceException("Error in fillInsertPS", exc);
		}
	}

	/**
	 * Puts a parameter object on index "index" of a prepared statement ps Creation
	 * date: (23/09/2001 14.25.14)
	 * 
	 * @param ps     java.sql.PreparedStatement
	 * @param fields com.zconsultancies.threelayers.persistence.PersistenceField[]
	 * @exception java.sql.SQLException The exception description.
	 */
	protected static void fillPreparedStatementParameter(java.sql.PreparedStatement ps, Object object, Class objClass,
			int index) throws java.sql.SQLException {
		if (objClass.equals(String.class)) {
			if (object == null)
				object = "";
			ps.setString(index, object.toString());
		} else if (objClass.equals(Integer.class)) {
			if (object == null)
				object = new Integer(0);
			ps.setInt(index, ((Number) object).intValue());
		} else if (objClass.equals(Long.class)) {
			if (object == null)
				object = new Integer(0);
			ps.setLong(index, ((Number) object).longValue());
		} else if (objClass.equals(Short.class)) {
			if (object == null)
				object = new Integer(0);
			ps.setShort(index, ((Number) object).shortValue());
		} else if (objClass.equals(Double.class)) {
			if (object == null)
				object = new Integer(0);
			ps.setDouble(index, ((Number) object).doubleValue());
		} else if (objClass.equals(Float.class)) {
			if (object == null)
				object = new Integer(0);
			ps.setFloat(index, ((Number) object).floatValue());
		} else if (objClass.equals(BigDecimal.class)) {
			if (object == null) {
				ps.setNull(index, Types.NUMERIC);
			} else {
				ps.setBigDecimal(index, ((BigDecimal) object));
			}
		} else if (objClass.equals(java.sql.Time.class)) {
			ps.setTime(index, (java.sql.Time) object);
		} else if (objClass.equals(java.sql.Timestamp.class)) {
			ps.setTimestamp(index, (java.sql.Timestamp) object);
		} else if (objClass.equals(java.sql.Date.class)) {
			ps.setDate(index, (java.sql.Date) object);
		} else if (objClass.equals(Boolean.class)) {
			if (object == null || object.toString().equals("false")) {
				ps.setString(index, FALSE);
			} else {
				ps.setString(index, TRUE);
			}
		} else
			throw new IllegalStateException("Tipo " + objClass.getName() + " non gestito");
		// TypesManager.fillPreparedStatementParameter(ps,
		// false, object, objClass, index);
	}

	/**
	 * Runs the passed query and returns a Vector of objects Creation date:
	 * (03/05/2002 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findBy(java.sql.Connection connection, String sql) throws java.sql.SQLException {
		java.sql.Statement st = null;
		java.sql.ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			java.util.Vector data = readObjects(rs);
			for (int i = 0; i < data.size(); i++) {
				BaseDBBean object = (BaseDBBean) data.elementAt(i);
				object.onDBRead(connection);
			}
			return data;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Throwable th) {
				}
			}
		}
	}

	public static <T extends BaseDBBean> Stream<T> streamBy(java.sql.Connection connection, String sql, Class<T> type)
			throws java.sql.SQLException, InstantiationException, IllegalAccessException {
		try {
			final java.sql.Statement st = connection.createStatement();
			final java.sql.ResultSet rs = st.executeQuery(sql);
			return streamBy(rs, type, (Void) -> {
				if (rs != null) {
					try {
						rs.close();
					} catch (Throwable th) {
					}
				}
				if (st != null) {
					try {
						st.close();
					} catch (Throwable th) {
					}
				}
			});
		} finally {

		}
	}

	public static interface CloseablePersister<T extends BaseDBBean> extends Consumer<T> {
		public void close();
	};

	public static <T extends BaseDBBean> CloseablePersister<T> getStreamPersister(Connection connection, Class<T> type,
			boolean updateOrInsert) {
		if (updateOrInsert) {
			return new CloseablePersister<T>() {

				@Override
				public void accept(T t) {
					try {
						if (updateOrInsert) {
							if (!t.update(connection)) {
								t.insert(connection, true);
							}
						} else {
							t.insert(connection, true);
						}

					} catch (PersistenceException e) {
						LOGGER.error("Error persisting stream", e);
						throw new RuntimeException("Error persisting stream", e);
					}

				}

				@Override
				public void close() {

				}

			};
		} else {
			return new CloseablePersister<T>() {
				PreparedStatement psInsert = null;

				@Override
				public void accept(T t) {
					try {
						if (psInsert == null) {
							psInsert = t.prepareInsertStatement(connection);
						}

						t.fillInsertPS(psInsert, connection);

					} catch (PersistenceException | SQLException e) {
						LOGGER.error("Error persisting stream", e);
						throw new RuntimeException("Error persisting stream", e);
					}

				}

				@Override
				public void close() {
					try {
						psInsert.close();
					} catch (Throwable th) {
					}
				}

			};
		}
	}

	public static <T extends BaseDBBean> Stream<T> streamBy(java.sql.Connection connection,
			java.util.HashMap propertiesTable, String prefix, String postfix, java.util.Vector orderFields,
			Class<T> type)
			throws java.sql.SQLException, PersistenceException, InstantiationException, IllegalAccessException {

		try {
			T sampleObject = type.newInstance();
			StringBuffer buffer = new StringBuffer("select * from " + sampleObject.table + " T ");
			java.util.Vector params = new java.util.Vector();
			java.util.Vector fields = new java.util.Vector();
			WhereCondition whereCondition = new WhereCondition();
			if (!propertiesTable.isEmpty()) {

				java.beans.PropertyDescriptor pds[] = sampleObject.thlGetPropertyList();
				for (int index = 0; index < pds.length; index++) {
					Object value = propertiesTable.get(prefix + pds[index].getName() + postfix);
					if (value != null) {
						if (!value.getClass().equals(pds[index].getPropertyType())) {
							throw new IllegalArgumentException("La property usata per la ricerca "
									+ pds[index].getName() + " e' di tipo " + value.getClass().getName()
									+ " mentre dovrebbe essere di tipo " + pds[index].getPropertyType().getName());
						}
						params.addElement(value);
						fields.addElement(pds[index].getName());
					}
				}
				for (int index = 0; index < fields.size(); index++) {
					whereCondition.addCondition(fields.elementAt(index) + " = ?");
				}
			}

			if (!whereCondition.isEmpty()) {
				buffer.append(" WHERE ");
				buffer.append(whereCondition.getWhereCondition());
			}
			if (orderFields.size() > 0) {
				buffer.append(" order by ");
				for (int index = 0; index < orderFields.size(); index++) {
					buffer.append(orderFields.elementAt(index));
					if (index < orderFields.size() - 1)
						buffer.append(",");
				}
			}
			if (params.isEmpty()) {
				final Statement st = connection.createStatement();
				final ResultSet rs = st.executeQuery(buffer.toString());
				return streamBy(rs, type, (Void) -> {
					if (rs != null) {
						try {
							rs.close();
						} catch (Throwable th) {
						}
					}
					if (st != null) {
						try {
							st.close();
						} catch (Throwable th) {
						}
					}

				});
			} else {
				final PreparedStatement ps = connection.prepareStatement(buffer.toString());
				// com.zconsultancies.threelayers.util.LogUtil.out.println(buffer.toString());
				ps.clearParameters();
				for (int index = 0; index < params.size(); index++) {
					fillPreparedStatementParameter(ps, params.elementAt(index), params.elementAt(index).getClass(),
							index + 1);
				}
				final ResultSet rs = ps.executeQuery();
				return streamBy(rs, type, (Void) -> {
					if (rs != null) {
						try {
							rs.close();
						} catch (Throwable th) {
						}
					}
					if (ps != null) {
						try {
							ps.close();
						} catch (Throwable th) {
						}
					}

				});
			}

		} finally {
		}
	}

	/**
	 * Runs the passed query and returns a Vector of objects Creation date:
	 * (03/05/2002 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findBy(java.sql.Connection connection, String sql, Vector params)
			throws java.sql.SQLException {
		PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.clearParameters();
			TypesManager.fillPreparedStatementParameter(ps, true, params);

			rs = ps.executeQuery();
			java.util.Vector data = readObjects(rs);
			for (int i = 0; i < data.size(); i++) {
				BaseDBBean object = (BaseDBBean) data.elementAt(i);
				object.onDBRead(connection);
			}
			return data;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Throwable th) {
				}
			}
		}
	}

	/**
	 * Finds a vector of objects from DB using values from propertiesTable as filter
	 * Creation date: (03/05/2002 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findBy(java.sql.Connection connection, java.util.Hashtable propertiesTable)
			throws java.sql.SQLException {
		return this.findBy(connection, propertiesTable, "", "", new java.util.Vector());
	}

	/**
	 * Finds a vector of objects from DB using values from propertiesTable as filter
	 * Creation date: (03/05/2002 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findBy(java.sql.Connection connection, java.util.Hashtable propertiesTable, String prefix,
			String postfix, java.util.Vector orderFields) throws java.sql.SQLException {
		java.sql.Statement st = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		try {
			StringBuffer buffer = new StringBuffer("select * from " + this.table + " T ");
			java.util.Vector params = new java.util.Vector();
			java.util.Vector fields = new java.util.Vector();
			if (!propertiesTable.isEmpty()) {
				buffer.append(" WHERE ");
				java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
				for (int index = 0; index < pds.length; index++) {
					Object value = propertiesTable.get(prefix + pds[index].getName() + postfix);
					if (value != null) {
						if (!value.getClass().equals(pds[index].getPropertyType())) {
							throw new IllegalArgumentException("La property usata per la ricerca "
									+ pds[index].getName() + " e' di tipo " + value.getClass().getName()
									+ " mentre dovrebbe essere di tipo " + pds[index].getPropertyType().getName());
						}
						params.addElement(value);
						fields.addElement(pds[index].getName());
					}
				}
				for (int index = 0; index < fields.size(); index++) {
					buffer.append(" ");
					buffer.append(fields.elementAt(index));
					buffer.append(" = ? ");
					if (index < params.size() - 1)
						buffer.append(" and ");
				}
			}
			if (orderFields.size() > 0) {
				buffer.append(" order by ");
				for (int index = 0; index < orderFields.size(); index++) {
					buffer.append(orderFields.elementAt(index));
					if (index < orderFields.size() - 1)
						buffer.append(",");
				}
			}
			if (params.isEmpty()) {
				st = connection.createStatement();
				rs = st.executeQuery(buffer.toString());
			} else {
				ps = connection.prepareStatement(buffer.toString());
				// com.zconsultancies.threelayers.util.LogUtil.out.println(buffer.toString());
				ps.clearParameters();
				for (int index = 0; index < params.size(); index++) {
					fillPreparedStatementParameter(ps, params.elementAt(index), params.elementAt(index).getClass(),
							index + 1);
				}
				rs = ps.executeQuery();
			}
			java.util.Vector data = readObjects(rs);
			for (int i = 0; i < data.size(); i++) {
				BaseDBBean object = (BaseDBBean) data.elementAt(i);
				object.onDBRead(connection);
			}
			return data;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Throwable th) {
				}
			}
		}
	}

	/**
	 * Finds a vector of objects from DB using values from propertiesTable as filter
	 * Creation date: (09/09/2014 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 * @throws PersistenceException
	 */
	public java.util.Vector findBy(PersistenceTransaction transaction, java.util.HashMap propertiesTable, String prefix,
			String postfix, java.util.Vector orderFields) throws java.sql.SQLException, PersistenceException {
		java.sql.Connection connection = transaction.getConnection();
		java.sql.Statement st = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		try {
			StringBuffer buffer = new StringBuffer("select * from " + this.table + " T ");
			java.util.Vector params = new java.util.Vector();
			java.util.Vector fields = new java.util.Vector();
			WhereCondition whereCondition = new WhereCondition();
			if (!propertiesTable.isEmpty()) {

				java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
				for (int index = 0; index < pds.length; index++) {
					Object value = propertiesTable.get(prefix + pds[index].getName() + postfix);
					if (value != null) {
						if (!value.getClass().equals(pds[index].getPropertyType())) {
							throw new IllegalArgumentException("La property usata per la ricerca "
									+ pds[index].getName() + " e' di tipo " + value.getClass().getName()
									+ " mentre dovrebbe essere di tipo " + pds[index].getPropertyType().getName());
						}
						params.addElement(value);
						fields.addElement(pds[index].getName());
					}
				}
				for (int index = 0; index < fields.size(); index++) {
					whereCondition.addCondition(fields.elementAt(index) + " = ?");
				}
			}

			if (!whereCondition.isEmpty()) {
				buffer.append(" WHERE ");
				buffer.append(whereCondition.getWhereCondition());
			}
			if (orderFields.size() > 0) {
				buffer.append(" order by ");
				for (int index = 0; index < orderFields.size(); index++) {
					buffer.append(orderFields.elementAt(index));
					if (index < orderFields.size() - 1)
						buffer.append(",");
				}
			}
			if (params.isEmpty()) {
				st = connection.createStatement();
				rs = st.executeQuery(buffer.toString());
			} else {
				ps = connection.prepareStatement(buffer.toString());
				// com.zconsultancies.threelayers.util.LogUtil.out.println(buffer.toString());
				ps.clearParameters();
				for (int index = 0; index < params.size(); index++) {
					fillPreparedStatementParameter(ps, params.elementAt(index), params.elementAt(index).getClass(),
							index + 1);
				}
				rs = ps.executeQuery();
			}
			java.util.Vector data = readObjects(rs);
			for (int i = 0; i < data.size(); i++) {
				BaseDBBean object = (BaseDBBean) data.elementAt(i);
				object.onDBRead(connection);
			}
			return data;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Throwable th) {
				}
			}
		}
	}

	/**
	 * Finds a vector of objects from DB using values from propertiesTable as filter
	 * Creation date: (03/05/2002 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findBy(java.sql.Connection connection, java.util.Hashtable propertiesTable,
			java.util.Vector orderFields) throws java.sql.SQLException {
		return this.findBy(connection, propertiesTable, "", "", orderFields);
	}

	/**
	 * Finds state of this object by primary key Creation date: (03/05/2002
	 * 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public BaseDBBean findByPrimaryKey(java.sql.Connection connection, java.util.Hashtable propertiesTable)
			throws java.sql.SQLException {
		return findByPrimaryKey(connection, propertiesTable, "", "");
	}

	/**
	 * Finds state of this object by primary key Creation date: (03/05/2002
	 * 11.57.07)
	 * 
	 * @return java.util.Vector
	 * @param connection      java.sql.Connection
	 * @param propertiesTable java.util.Hashtable
	 * @param orderFields     java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public BaseDBBean findByPrimaryKey(java.sql.Connection connection, java.util.Hashtable propertiesTable,
			String prefix, String postfix) throws java.sql.SQLException {
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		java.util.Vector pkeyVect = new java.util.Vector();
		for (int index = 0; index < primaryKeyProperties.length; index++) {
			pkeyVect.addElement(primaryKeyProperties[index]);
		}
		try {
			StringBuffer buffer = new StringBuffer("select * from " + this.table + " T ");
			java.util.Vector params = new java.util.Vector();
			java.util.Vector fields = new java.util.Vector();
			if (!propertiesTable.isEmpty()) {
				buffer.append(" WHERE ");
				java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
				for (int index = 0; index < pds.length; index++) {
					Object value = propertiesTable.get(prefix + pds[index].getName() + postfix);
					if (value != null) {
						if (!value.getClass().equals(pds[index].getPropertyType())) {
							throw new IllegalArgumentException("La property usata per la ricerca "
									+ pds[index].getName() + " e' di tipo " + value.getClass().getName()
									+ " mentre dovrebbe essere di tipo " + pds[index].getPropertyType().getName());
						}
						if (pkeyVect.contains(pds[index].getName())) {
							params.addElement(value);
							fields.addElement(pds[index].getName());
						}
					}
				}
				if (fields.size() != pkeyVect.size()) {
					throw new IllegalArgumentException(
							"Nel metodo findByPrimaryKey(..) manca un parametro facente parte la chiave primaria di ricerca");
				}
				for (int index = 0; index < fields.size(); index++) {
					buffer.append(" ");
					buffer.append(fields.elementAt(index));
					buffer.append(" = ? ");
					if (index < params.size() - 1) {
						buffer.append(" and ");
					}
				}
			} else
				throw new IllegalArgumentException(
						"Nel metodo findByPrimaryKey(..)  la chiave primaria di ricerca manca");
			ps = connection.prepareStatement(buffer.toString());
			ps.clearParameters();
			for (int index = 0; index < params.size(); index++) {
				fillPreparedStatementParameter(ps, params.elementAt(index), params.elementAt(index).getClass(),
						index + 1);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				readFromResultSet(rs);
				onDBRead(connection);
			} else
				return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Throwable th) {
				}
			}
		}
		return this;
	}

	/**
	 * Retrieve by where conditions Creation date: (21/06/2002 23.08.04)
	 * 
	 * @return java.util.Vector
	 * @param connectio   java.sql.Connection
	 * @param where       java.lang.String
	 * @param orderVector java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findByWhere(java.sql.Connection connection, String where, java.util.Vector orderVector)
			throws java.sql.SQLException {
		return findByWhere(connection, where, null, orderVector);
	}

	/**
	 * Retrieve by where conditions Creation date: (21/06/2002 23.08.04)
	 * 
	 * @return java.util.Vector
	 * @param connectio   java.sql.Connection
	 * @param where       java.lang.String
	 * @param params      java.util.Vector (parametri preparedstatement)
	 * @param orderVector java.util.Vector
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector findByWhere(java.sql.Connection connection, String where, Vector params,
			java.util.Vector orderVector) throws java.sql.SQLException {

		java.sql.Statement statement = null;
		java.sql.ResultSet rs = null;
		java.util.Vector data = new java.util.Vector();
		PreparedStatement ps = null;
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM " + this.table + " T  WHERE " + where;
			if (orderVector.size() > 0) {
				query += " ORDER BY ";
				for (int index = 0; index < orderVector.size(); index++) {
					query += orderVector.elementAt(index).toString();
					if (index < orderVector.size() - 1) {
						query += ",";
					}
				}
			}

			ps = connection.prepareStatement(query);
			ps.clearParameters();
			if (params != null) {
				TypesManager.fillPreparedStatementParameter(ps, true, params);
			}
			rs = ps.executeQuery();
			data = readObjects(rs);
			for (int i = 0; i < data.size(); i++) {
				BaseDBBean object = (BaseDBBean) data.elementAt(i);
				object.onDBRead(connection);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Throwable th) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Throwable th) {
				}
			}
		}
		return data;
	}

	public static <T extends BaseDBBean> Stream<T> streamByWhere(java.sql.Connection connection, String where,
			Vector params, java.util.Vector orderVector, Class<T> type)
			throws java.sql.SQLException, InstantiationException, IllegalAccessException {

		try {
			T sampleObject = type.newInstance();
			final java.sql.Statement statement = connection.createStatement();
			String query = "SELECT * FROM " + sampleObject.table + " T  WHERE " + where;
			if (orderVector.size() > 0) {
				query += " ORDER BY ";
				for (int index = 0; index < orderVector.size(); index++) {
					query += orderVector.elementAt(index).toString();
					if (index < orderVector.size() - 1) {
						query += ",";
					}
				}
			}

			final PreparedStatement ps = connection.prepareStatement(query);
			ps.clearParameters();
			if (params != null) {
				TypesManager.fillPreparedStatementParameter(ps, true, params);
			}
			final java.sql.ResultSet rs = ps.executeQuery();
			return streamBy(rs, type, (Void) -> {
				if (rs != null) {
					try {
						rs.close();
					} catch (Throwable th) {
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (Throwable th) {
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (Throwable th) {
					}
				}
			});
		} finally {

		}

	}

	/**
	 * Deletes the DB image of this bean from DB Creation date: (24/04/2002
	 * 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public void forceDelete(java.sql.Connection connection) throws java.sql.SQLException {
		if (table == null)
			throw new IllegalStateException("The bean " + getClass().getName() + " has null table name");
		if (primaryKeyProperties == null)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " has null primary key properties array");
		java.sql.PreparedStatement ps = null;
		try {
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES DELLA
			// QUERY
			StringBuffer delete = new StringBuffer(" DELETE FROM " + table + " WHERE ");
			// VETTORE PER VALORI DELLE PROPERTIES
			java.util.Vector propertyValues = new java.util.Vector();
			// VETTORE PER NOME DEI CAMPI DEL DB
			java.util.Vector fieldList = new java.util.Vector();
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			for (; index < primaryKeyProperties.length; index++) {
				Object object = thlGet(primaryKeyProperties[index]);
				if (object == null) {
					throw new java.sql.SQLException("Primary key field " + primaryKeyProperties[index] + " is null");
				} else {
					delete.append(primaryKeyProperties[index] + " = ? ");
					propertyValues.addElement(object);
				}
				if (index < primaryKeyProperties.length - 1)
					delete.append(" AND ");
			}
			index = 0;
			ps = connection.prepareStatement(delete.toString());
			// RIEMPIO I PARAMETRI DEL PREPARED STATEMENT
			for (; index < primaryKeyProperties.length; index++) {
				Object object = propertyValues.elementAt(index);
				fillPreparedStatementParameter(ps, object, object.getClass(), index + 1);
			}
			// FACCIO UPDATE
			ps.executeUpdate();
			New = true;
		} catch (java.sql.SQLException exc) {
			throw exc;
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Throwable th) {
				}
			}
		}
	}

	/**
	 * Forzatura di insert Creation date: (22/01/2003 15.10.32)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 * @throws PersistenceException
	 */
	public void forceInsert(java.sql.Connection connection) throws java.sql.SQLException, PersistenceException {
		insert(connection, true);
	}

	/**
	 * Forzatura di update Creation date: (22/01/2003 15.11.27)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 * @throws PersistenceException
	 */
	public void forceUpdate(java.sql.Connection connection) throws java.sql.SQLException, PersistenceException {
		update(connection, true);
	}

	/**
	 * Insert this Bean image in DB Creation date: (24/04/2002 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 * @throws PersistenceException
	 */
	public void insert(java.sql.Connection connection) throws PersistenceException {
		insert(connection, true);
	}

	/**
	 * Insert this Bean image in DB Creation date: (24/04/2002 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public void insert(java.sql.Connection connection, boolean forceInsert) throws PersistenceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin insert(...)");
			try {
				LOGGER.debug("Inserting==>" + objectMapper.writeValueAsString(this));
			} catch (Throwable th) {
			}
		}
		if (!forceInsert && !New)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " is not in New state and U R tryng to inserting it");
		if (table == null)
			throw new IllegalStateException("The bean " + getClass().getName() + " has null table name");
		if (primaryKeyProperties == null)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " has null primary key properties array");
		java.util.Vector autoIncrements = new java.util.Vector();
		java.util.Vector autoIncrementProperties = new java.util.Vector();
		for (int i = 0; this.autoIncrements != null && i < this.autoIncrements.length; i++) {
			autoIncrements.addElement(this.autoIncrements[i]);
		}
		java.sql.Statement statement = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		try {
			if (MANAGE_APPLICATION_LEVEL_COUNTERS) {
				// GESTISCO CONTATORI A LIVELLO DI APPLICAZIONE

				java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
				for (int index = 0; index < pds.length; index++) {
					if (autoIncrements.contains(pds[index].getName())) {
						// if (!usedAsJSFPOJO) {

						Object value = thlGet(pds[index].getName());
						if (value == null || (((value instanceof Number) && ((Number) value).intValue() == 0)
								&& PUT_ZERO_AUTOINCREMENTS)) {
							String id = ""
									+ counterManager.getNewInt64CounterValue(table, pds[index].getName(), connection);
							try {
								thlSet(pds[index].getName(),
										TypesManager.convertFromString(pds[index].getPropertyType(), id));
							} catch (java.text.ParseException exc) {
								throw new IllegalStateException(
										"" + id + " non parserizzabile come " + pds[index].getPropertyType().getName());
							}
						}

					}
				}

			}
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES DELLA
			// QUERY
			StringBuffer insert = new StringBuffer(" INSERT INTO " + table + "(");
			StringBuffer values = new StringBuffer(" VALUES (");
			// VETTORE PER VALORI DELLE PROPERTIES
			java.util.Vector propertyValues = new java.util.Vector();
			// VETTORE PER NOME DEI CAMPI DEL DB
			java.util.Vector fieldList = new java.util.Vector();
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			for (; index < primaryKeyProperties.length; index++) {
				if (thlGet(primaryKeyProperties[index]) == null
						&& !autoIncrements.contains(primaryKeyProperties[index])) {
					throw new java.sql.SQLException("Primary key field " + primaryKeyProperties[index] + " is null");
				}
			}
			index = 0;
			// PRENDO TUTTE LE PROPERTY NON NULLE E NON AUTO INCREMENTANTI
			// SE INVECE NON DEVO GESTIRE I CONTATORI A LIVELLO DI APPLICAZIONE,
			// ALLORA COSI GESTISCO TUTTI I CAMPI
			// (!MANAGE_APPLICATION_LEVEL_COUNTERS modificato il 5-5-2006)_
			for (; index < pd.length; index++) {
				if (!autoIncrements.contains(pd[index].getName()) || MANAGE_APPLICATION_LEVEL_COUNTERS) {
					Object value = thlGet(pd[index].getName());
					if (value != null) {
						propertyValues.addElement(value);
						fieldList.addElement(pd[index].getName());
					}
				} else {
					// VETTORE DI PROPERTY AUTOINCREMENTANTI
					autoIncrementProperties.addElement(pd[index]);
					if (PUT_ZERO_AUTOINCREMENTS) {
						try {
							propertyValues.addElement(TypesManager.convertFromString(pd[index].getPropertyType(), "0"));
						} catch (java.text.ParseException th) {
							th.printStackTrace();
						}
						fieldList.addElement(pd[index].getName());
					}
				}
			}
			index = 0;
			int size = fieldList.size();
			for (; index < size; index++) {
				insert.append(fieldList.elementAt(index));
				values.append("?");
				if (index < size - 1) {
					insert.append(",");
					values.append(",");
				}
			}
			insert.append(") ");
			values.append(") ");
			insert.append(values.toString());
			// PREPARO LO STATEMENT
			// System.err.println("inserting: " + insert.toString());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Preparing statement=>" + insert.toString());
			}
			ps = connection.prepareStatement(insert.toString());
			// CLEAR (NON LO SHAMPOO)
			ps.clearParameters();
			index = 0;
			// RIEMPIO I PARAMETRI DEL PREPARED STATEMENT
			for (; index < size; index++) {
				Object object = propertyValues.elementAt(index);
				TypesManager.fillPreparedStatementParameter(ps, true, object, object.getClass(), index + 1);
			}
			// FACCIO UPDATE
			ps.executeUpdate();
			// GESTISCO IL RETRIVING DI VALORE DI CONTATORE GESTITO DA TRIGGER
			// IN CASO IN CUI I CONTATORI NON SIANO GESTITI A LIVELLO DI
			// APPLICAZIONE
			if (autoIncrementProperties.size() > 0 && (!MANAGE_APPLICATION_LEVEL_COUNTERS)) {
				statement = connection.createStatement();
				String autoincquery = "SELECT ";
				for (int i = 0; i < autoIncrementProperties.size(); i++) {
					java.beans.PropertyDescriptor descriptor = (java.beans.PropertyDescriptor) autoIncrementProperties
							.elementAt(i);
					autoincquery += " max(" + descriptor.getName() + ") AS " + descriptor.getName();
					if (i < autoIncrementProperties.size() - 1) {
						autoincquery += ",";
					}
				}
				autoincquery += " FROM " + this.table;
				rs = statement.executeQuery(autoincquery);
				if (rs.next()) {
					for (int i = 0; i < autoIncrementProperties.size(); i++) {
						java.beans.PropertyDescriptor descriptor = (java.beans.PropertyDescriptor) autoIncrementProperties
								.elementAt(i);
						if (descriptor.getPropertyType().equals(Integer.class)) {
							thlSet(descriptor.getName(), new Integer(rs.getInt(i + 1)));
						} else if (descriptor.getPropertyType().equals(Short.class)) {
							thlSet(descriptor.getName(), new Short(rs.getShort(i + 1)));
						} else if (descriptor.getPropertyType().equals(Long.class)) {
							thlSet(descriptor.getName(), new Long(rs.getLong(i + 1)));
						}
					}
				}
				// System.err.println("Retrieve autoincrements: " +
				// autoincquery);
				rs.close();
			}
			New = false;
		} catch (java.sql.SQLException exc) {
			LOGGER.error("Error in insert", exc);
			throw new PersistenceException("Error in insert", exc);

		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Throwable th) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Throwable th) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Throwable th) {
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End insert(...)");
		}
	}

	/**
	 * Insert the method's description here. Creation date: (09/01/2003 12.09.19)
	 * 
	 * @return java.util.Vector[]
	 * @param baseDBBeanClasses java.lang.Class[]
	 * @param tableAliases      java.lang.String[]
	 * @param queryString       java.lang.String
	 * @param connection        java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public static java.util.Vector[] join(Class[] baseDBBeanClasses, String[] tableAliases, String queryString,
			java.sql.Connection connection) throws PersistenceException {
		java.util.Vector[] ret = null;
		ret = new java.util.Vector[baseDBBeanClasses.length];

		for (int index = 0; index < baseDBBeanClasses.length; index++) {
			if (!BaseDBBean.class.isAssignableFrom(baseDBBeanClasses[index]))
				throw new IllegalArgumentException("La classe " + baseDBBeanClasses[index].getName()
						+ " passata come parametro non deriva da BaseDBBean");
			ret[index] = new java.util.Vector();
		}
		java.sql.Statement st = null;
		java.sql.ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(queryString);
			BaseDBBean object = null;
			while (rs.next()) {
				for (int index = 0; index < baseDBBeanClasses.length; index++) {
					object = (BaseDBBean) baseDBBeanClasses[index].newInstance();
					object.tableAlias = tableAliases[index] + "_";
					object.setData(rs);
					ret[index].addElement(object);
				}
			}

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			LOGGER.error("join", e);
			throw new PersistenceException("Exception in join", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Throwable th) {
				}
			}
		}
		return ret;
	}

	/**
	 * Esegue la join naturale tra oggetti di classi passate come parametro Creation
	 * date: (21/01/2003 12.17.10)
	 * 
	 * @return java.util.Vector
	 * @param dbBeanClass java.lang.Class[]
	 * @param aliases     java.lang.String[]
	 * @param whereStrin  java.lang.String
	 * @param orderString java.lang.String
	 * @param connection  java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public static java.util.Vector[] naturalJoin(Class[] dbBeanClass, String[] aliases, String whereString,
			String orderString, java.sql.Connection connection) throws PersistenceException {
		BaseDBBean objects[] = new BaseDBBean[dbBeanClass.length];
		StringBuffer buffer = new StringBuffer("select ");
		for (int i = 0; i < dbBeanClass.length; i++) {
			try {
				objects[i] = (BaseDBBean) dbBeanClass[i].newInstance();
			} catch (Throwable th) {
				throw new IllegalStateException("Impossible to allocate " + dbBeanClass[i].getClass().getName());
			}
			java.beans.PropertyDescriptor pd[] = objects[i].thlGetPropertyList();
			for (int index = 0; index < pd.length; index++) {
				buffer.append(aliases[i] + "." + pd[index].getName() + " as " + aliases[i] + "_" + pd[index].getName());
				if (index < pd.length - 1 || i < dbBeanClass.length - 1)
					buffer.append(",");
			}
		}
		buffer.append(" from ");
		for (int i = 0; i < objects.length; i++) {
			buffer.append(objects[i].table);
			buffer.append(" ");
			buffer.append(aliases[i]);
			if (i < objects.length - 1)
				buffer.append(",");
		}
		buffer.append(" where ");
		java.util.Vector conditions = new java.util.Vector();
		for (int i = 0; i < objects.length; i++) {
			if (i < objects.length - 1) {
				java.util.Hashtable k = new java.util.Hashtable();
				java.beans.PropertyDescriptor pds[] = objects[i].thlGetPropertyList();
				for (int u = 0; u < pds.length; u++) {
					k.put(pds[u].getName(), "");
				}
				pds = objects[i + 1].thlGetPropertyList();
				for (int u = 0; u < pds.length; u++) {
					if (k.containsKey(pds[u].getName())) {
						String pkpr[] = objects[i].thlGetPrimaryKeyProperties();
						String pkpr1[] = objects[i + 1].thlGetPrimaryKeyProperties();
						boolean isPK = false;
						for (int w = 0; !isPK && w < pkpr.length; w++) {
							isPK = pds[u].getName().equals(pkpr[w]);
						}
						for (int w = 0; !isPK && w < pkpr1.length; w++) {
							isPK = pds[u].getName().equals(pkpr1[w]);
						}
						if (isPK) {
							conditions.addElement(aliases[i] + "." + pds[u].getName() + "=" + aliases[i + 1] + "."
									+ pds[u].getName());
						}
					}
				}
			}
		}
		for (int i = 0; i < conditions.size(); i++) {
			buffer.append(conditions.elementAt(i));
			if (i < conditions.size() - 1)
				buffer.append(" and ");
		}
		if (whereString != null && whereString.trim().length() != 0) {
			buffer.append(" and ");
			buffer.append("(");
			buffer.append(whereString);
			buffer.append(")");
		}
		if (orderString != null && orderString.trim().length() != 0) {
			buffer.append(" order by ");
			buffer.append(orderString);
		}
		return join(dbBeanClass, aliases, buffer.toString(), connection);
	}

	/**
	 * onDBRead Creation date: (01/08/2002 22.47.53)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	protected void onDBRead(java.sql.Connection connection) throws java.sql.SQLException {
	}

	/**
	 * Insert the method's description here. Creation date: (15/06/2003 16.49.21)
	 * 
	 * @return java.sql.PreparedStatement
	 * @param connection java.sql.Connection
	 * @exception PersistenceException  The exception description.
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.sql.PreparedStatement prepareInsertStatement(java.sql.Connection connection)
			throws PersistenceException, java.sql.SQLException {
		java.sql.Statement statement = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		java.util.Vector autoIncrements = new java.util.Vector();
		try {
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES DELLA
			// QUERY
			StringBuffer insert = new StringBuffer(" INSERT INTO " + table + "(");
			StringBuffer values = new StringBuffer(" VALUES (");
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			for (; index < pd.length; index++) {
				String name = pd[index].getName();
				insert.append(name);
				values.append("?");
				if (index < pd.length - 1) {
					insert.append(",");
					values.append(",");
				}
			}
			index = 0;
			insert.append(") ");
			values.append(") ");
			insert.append(values.toString());
			ps = connection.prepareStatement(insert.toString());
		} catch (java.sql.SQLException exc) {
			exc.printStackTrace();
			throw exc;
		}
		return ps;
	}

	/**
	 * Reads object state from resultset Creation date: (24/04/2002 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public synchronized void readFromResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
		readFromResultSet(rs, "");
	}

	/**
	 * Reads object state from resultset With fields names equal to property names
	 * plus a postfix Creation date: (24/04/2002 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public void readFromResultSet(java.sql.ResultSet rs, String postfix) throws java.sql.SQLException {
		java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
		java.beans.PropertyDescriptor descriptor = null;
		String tableField = null;
		String classField = null;
		for (int index = 0; pds != null && index < pds.length; index++) {
			tableField = tableAlias + pds[index].getName() + postfix;
			classField = pds[index].getName();
			descriptor = pds[index];
			Class type = descriptor.getPropertyType();
			Object value = TypesManager.getObject(rs, tableField, type);
			thlSet(classField, value);

		}
		New = false;
	}

	/**
	 * Reads from ResultSet and returns a Vector of object of this class Creation
	 * date: (03/05/2002 11.51.04)
	 * 
	 * @return java.util.Vector
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector readObjects(java.sql.ResultSet rs) throws java.sql.SQLException {
		return readObjects(rs, "");
	}

	public static <T extends BaseDBBean> Stream<T> streamBy(java.sql.ResultSet rs, Class<T> dbType,
			java.util.function.Consumer<Void> closingCallback)
			throws java.sql.SQLException, InstantiationException, IllegalAccessException {
		Iterator<T> elementsIterator = new Iterator<T>() {
			@Override
			public boolean hasNext() {

				try {
					return rs.next();
				} catch (SQLException e) {
					throw new RuntimeException("Exception moving cursor", e);
				}
			}

			@Override
			public T next() {
				T item;
				try {
					item = dbType.newInstance();
					item.readFromResultSet(rs);
					return item;
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					throw new RuntimeException("", e);
				}
				
			}
		};
		Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(elementsIterator, 0);
		
		
		return StreamSupport.stream(spliterator, false).onClose(()->{
			closingCallback.accept(null);
		});

	}

	/**
	 * Reads from ResultSet and returns a Vector of object of this class Creation
	 * date: (03/05/2002 11.51.04)
	 * 
	 * @return java.util.Vector
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public java.util.Vector readObjects(java.sql.ResultSet rs, String postfix) throws java.sql.SQLException {
		java.util.Vector data = new java.util.Vector();
		try {
			while (rs.next()) {
				BaseDBBean object = createNewInstance();
				object.readFromResultSet(rs, postfix);
				data.addElement(object);
			}
		} catch (InstantiationException exc) {
			exc.printStackTrace();
			throw new IllegalStateException("Instanziazione della classe " + getClass().getName() + " impossibile");
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
			throw new IllegalStateException("Instanziazione della classe " + getClass().getName()
					+ " impossibile accesso al costruttore negato");
		}
		return data;
	}

	/**
	 * Resets every property to a null value Creation date: (03/05/2002 15.14.55)
	 */
	public synchronized void reset() {
		this.reset(true);
	}

	/**
	 * Resets every property to a null value if the first parameter is true or to a
	 * default value otherwise Creation date: (03/05/2002 15.14.55)
	 */
	public void reset(boolean putNull) {
		java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
		for (int index = 0; index < pds.length; index++) {
			if (putNull) {
				thlSet(pds[index].getName(), null);
			} else {
				if (pds[index].getPropertyType().equals(String.class)) {
					thlSet(pds[index].getName(), "");
				} else if (Number.class.isAssignableFrom(pds[index].getPropertyType())) {
					try {
						thlSet(pds[index].getName(), TypesManager.convertFromString(pds[index].getPropertyType(), "0"));
					} catch (java.text.ParseException exc) {
						throw new IllegalStateException(
								"Errore impossibile mettere '0' in type " + pds[index].getPropertyType().getName());
					}
				} else {
					if (pds[index].getPropertyType().equals(java.sql.Date.class)) {
						thlSet(pds[index].getName(), new java.sql.Date(System.currentTimeMillis()));
					} else if (pds[index].getPropertyType().equals(java.sql.Time.class)) {
						thlSet(pds[index].getName(), new java.sql.Time(System.currentTimeMillis()));
					} else if (pds[index].getPropertyType().equals(java.sql.Timestamp.class)) {
						thlSet(pds[index].getName(), new java.sql.Timestamp(System.currentTimeMillis()));
					} else if (pds[index].getPropertyType().equals(Boolean.class)) {
						thlSet(pds[index].getName(), new Boolean(false));
					}
				}
			}
		}

	}

	/**
	 * Reads object state from passed BaseDBBean Creation date: (24/04/2002
	 * 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public void setData(BaseDBBean dbBean) throws java.sql.SQLException {
		if (!this.getClass().equals(dbBean.getClass()))
			throw new IllegalArgumentException(
					"Impossibile associare allo stato di un bean della classe " + getClass().getName()
							+ " i valori contenuti in un bean della classe " + dbBean.getClass().getName());
		java.beans.PropertyDescriptor pds[] = thlGetPropertyList();
		Object params[] = new Object[0];
		Object copyPar[] = new Object[1];
		for (int index = 0; index < pds.length; index++) {
			try {
				Object value = pds[index].getReadMethod().invoke(dbBean, params);
				copyPar[0] = value;
				pds[index].getWriteMethod().invoke(this, copyPar);
			} catch (java.lang.reflect.InvocationTargetException exc) {
			} catch (IllegalAccessException exc) {
			}
		}
	}

	/**
	 * Reads object state from resultset Creation date: (24/04/2002 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public void setData(java.sql.ResultSet rs) throws java.sql.SQLException {
		readFromResultSet(rs);
	}

	/**
	 * Reads object state from resultset Creation date: (24/04/2002 15.57.09)
	 * 
	 * @param rs java.sql.ResultSet
	 * @exception java.sql.SQLException The exception description.
	 */
	public void setData(java.sql.ResultSet rs, String postfix) throws java.sql.SQLException {
		readFromResultSet(rs, postfix);
	}

	/**
	 * Insert the method's description here. Creation date: (07/02/2003 18.10.25)
	 * 
	 * @return java.lang.String
	 */
	public String thlGetDefaultTableAlias() {
		return "T";
	}

	/**
	 * Returns value of the actual maximum for a property <br>
	 * Creation date: (26/01/2003 12.01.35) <br>
	 * 
	 * @return Object <br>
	 * @param propertyName java.lang.String property name to inspect <br>
	 * @param connection   java.sql.Connection actual transaction connection (used
	 *                     only if the counter is not initialized) <br>
	 * @exception java.sql.SQLException    <br>
	 * @exception IllegalArgumentException thrown if the inspected property is not
	 *                                     declared as a counter <br>
	 */
	public Object thlGetMaxValue(String propertyName, java.sql.Connection connection) throws java.sql.SQLException {
		Object value = null;
		boolean found = false;
		java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
		java.beans.PropertyDescriptor property = null;
		for (int index = 0; !found && index < pd.length; index++) {
			if (pd[index].getName().equalsIgnoreCase(propertyName)) {
				found = true;
				property = pd[index];
			}
		}
		if (!found) {
			throw new IllegalArgumentException("The property " + propertyName
					+ " is not set as a counter property in class " + getClass().getName() + " ");
		}
		java.sql.Statement statement = null;
		java.sql.ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select max(" + propertyName + ") from " + this.table);
			if (rs.next()) {
				Object read = rs.getObject(1);
				if (read != null) {
					value = TypesManager.convertFromString(property.getPropertyType(), read.toString());
				}
			}
		} catch (java.text.ParseException exc) {
			throw new IllegalStateException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Throwable th) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Throwable th) {
				}
			}
		}
		return value;
	}

	/**
	 * Ritorna una mappa che contiene i campi primary key Creation date: (31/12/2002
	 * 18.12.34)
	 * 
	 * @return java.util.Hashtable
	 */
	public java.util.Hashtable thlGetPrimaryKeyMap() {
		java.util.Hashtable table = new java.util.Hashtable();
		for (int index = 0; index < this.primaryKeyProperties.length; index++) {
			table.put(this.primaryKeyProperties[index], thlGet(this.primaryKeyProperties[index]));
		}
		return table;
	}

	/**
	 * Insert the method's description here. Creation date: (02/08/2002 12.53.30)
	 * 
	 * @return java.lang.String[]
	 */
	public String[] thlGetPrimaryKeyProperties() {
		if (primaryKeyProperties == null)
			return new String[0];
		return primaryKeyProperties;
	}

	/**
	 * Ritorna table in cui mappato questo bean. Creation date: (27/01/2003
	 * 15.40.34)
	 * 
	 * @return java.lang.String
	 */
	public String thlGetTable() {
		return this.table;
	}

	/**
	 * Insert the method's description here. Creation date: (09/01/2003 12.57.28)
	 * 
	 * @return java.lang.String
	 */
	public String thlGetXMLElementTag() {
		return this.table.toLowerCase();
	}

	/**
	 * Ritorna true se la copia sul DB dei dati contenuti differente dall'attuale
	 * stato del bean. Creation date: (29/01/2003 16.32.22)
	 * 
	 * @return boolean
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public boolean thlIsBeanChanged(java.sql.Connection connection) throws java.sql.SQLException {
		java.util.Hashtable table = thlGetPrimaryKeyMap();
		try {
			BaseDBBean dbCopy = (BaseDBBean) getClass().newInstance();
			if (dbCopy.findByPrimaryKey(connection, table) != null) {
				return !dbCopy.toString().equals(this.toString());
			}
		} catch (InstantiationException exc) {
		} catch (IllegalAccessException exc) {
		}
		return true;
	}

	/**
	 * Returns true if the object is not read or already inserted in the database
	 * Creation date: (24/04/2002 15.47.05)
	 * 
	 * @return boolean
	 */
	public boolean thlIsNew() {
		return New;
	}

	/**
	 * Ritorna true se la copia sul DB esiste dall'attuale stato del bean. Creation
	 * date: (29/01/2003 16.32.22)
	 * 
	 * @return boolean
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public boolean thlIsPersistent(java.sql.Connection connection) throws java.sql.SQLException {
		java.util.Hashtable table = new java.util.Hashtable();
		for (int index = 0; index < primaryKeyProperties.length; index++) {
			Object value = thlGet(primaryKeyProperties[index]);
			if (value != null) {
				table.put(primaryKeyProperties[index], value);
			}
		}
		try {
			BaseDBBean dbCopy = (BaseDBBean) getClass().newInstance();
			return (dbCopy.findByPrimaryKey(connection, table) != null);
		} catch (InstantiationException exc) {
		} catch (IllegalAccessException exc) {
		}
		return true;
	}

	/**
	 * Updates this object db image Creation date: (24/04/2002 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public boolean update(java.sql.Connection connection) throws PersistenceException {
		boolean updated = false;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin update(...)");
		}
		if (table == null)
			throw new IllegalStateException("The bean " + getClass().getName() + " has null table name");
		if (primaryKeyProperties == null)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " has null primary key properties array");
		java.sql.PreparedStatement ps = null;
		try {
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES
			// DELLA QUERY
			StringBuffer update = new StringBuffer(" UPDATE " + table + " SET ");
			StringBuffer values = new StringBuffer(" ");
			// VETTORE PER VALORI DELLE PROPERTIES
			java.util.Vector propertyValues = new java.util.Vector();
			// VETTORE PER NOME DEI CAMPI DEL DB
			java.util.Vector fieldList = new java.util.Vector();
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			index = 0;
			// PRENDO TUTTE LE PROPERTY NON NULLE
			for (; index < pd.length; index++) {
				update.append(pd[index].getName());
				update.append(" = ? ");
				if (index < pd.length - 1)
					update.append(",");
			}
			update.append(" WHERE ");
			for (index = 0; index < primaryKeyProperties.length; index++) {
				update.append(primaryKeyProperties[index]);
				update.append(" = ? ");
				if (index < primaryKeyProperties.length - 1)
					update.append(" AND ");
			}
			// PREPARO LO STATEMENT
			ps = connection.prepareStatement(update.toString());
			// CLEAR (NON LO SHAMPOO)
			ps.clearParameters();
			index = 0;
			// RIEMPIO I PARAMETRI DEL PREPARED STATEMENT
			for (; index < pd.length; index++) {
				Object object = thlGet(pd[index].getName());
				fillPreparedStatementParameter(ps, object, pd[index].getPropertyType(), index + 1);
			}
			index = 0;
			for (; index < primaryKeyProperties.length; index++) {
				Object object = thlGet(primaryKeyProperties[index]);
				if (object == null) {
					throw new java.sql.SQLException("Primary key field " + primaryKeyProperties[index] + " is null");
				} else {
					fillPreparedStatementParameter(ps, object,
							thlDescribeInterface().getPropertyDescriptor(primaryKeyProperties[index]).getPropertyType(),
							pd.length + index + 1);
				}
			}
			// FACCIO UPDATE
			int n = ps.executeUpdate();
			updated = n > 0;
			New = false;
		} catch (java.sql.SQLException exc) {
			LOGGER.error("Error in update", exc);
			throw new PersistenceException("Error in update", exc);
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Throwable th) {
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End update(...)");
		}
		return updated;
	}

	/**
	 * Updates this object db image Creation date: (24/04/2002 15.47.43)
	 * 
	 * @param connection java.sql.Connection
	 * @exception java.sql.SQLException The exception description.
	 */
	public boolean update(java.sql.Connection connection, boolean forceUpdate) throws PersistenceException {
		boolean updated = false;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin update(...)");
		}
		if (table == null)
			throw new IllegalStateException("The bean " + getClass().getName() + " has null table name");
		if (primaryKeyProperties == null)
			throw new IllegalStateException(
					"The bean " + getClass().getName() + " has null primary key properties array");
		java.sql.PreparedStatement ps = null;
		try {
			// INIZIALIZZO DUE STRINGBUFFERS PER LA PARTE INSERT E VALUES
			// DELLA QUERY
			StringBuffer update = new StringBuffer(" UPDATE " + table + " SET ");
			StringBuffer values = new StringBuffer(" ");
			// VETTORE PER VALORI DELLE PROPERTIES
			java.util.Vector propertyValues = new java.util.Vector();
			// VETTORE PER NOME DEI CAMPI DEL DB
			java.util.Vector fieldList = new java.util.Vector();
			java.beans.PropertyDescriptor pd[] = thlGetPropertyList();
			int index = 0;
			index = 0;
			// PRENDO TUTTE LE PROPERTY NON NULLE
			for (; index < pd.length; index++) {
				update.append(pd[index].getName());
				update.append(" = ? ");
				if (index < pd.length - 1)
					update.append(",");
			}
			update.append(" WHERE ");
			for (index = 0; index < primaryKeyProperties.length; index++) {
				update.append(primaryKeyProperties[index]);
				update.append(" = ? ");
				if (index < primaryKeyProperties.length - 1)
					update.append(" AND ");
			}
			// PREPARO LO STATEMENT
			ps = connection.prepareStatement(update.toString());
			// CLEAR (NON LO SHAMPOO)
			ps.clearParameters();
			index = 0;
			// RIEMPIO I PARAMETRI DEL PREPARED STATEMENT
			for (; index < pd.length; index++) {
				Object object = thlGet(pd[index].getName());
				TypesManager.fillPreparedStatementParameter(ps, true, object, pd[index].getPropertyType(), index + 1);
			}
			index = 0;
			for (; index < primaryKeyProperties.length; index++) {
				Object object = thlGet(primaryKeyProperties[index]);
				if (object == null) {
					throw new java.sql.SQLException("Primary key field " + primaryKeyProperties[index] + " is null");
				} else {
					TypesManager.fillPreparedStatementParameter(ps, true, object,
							thlDescribeInterface().getPropertyDescriptor(primaryKeyProperties[index]).getPropertyType(),
							pd.length + index + 1);
				}
			}
			// FACCIO UPDATE
			int n = ps.executeUpdate();
			updated = n > 0;
			New = false;
		} catch (java.sql.SQLException exc) {
			LOGGER.error("Error in update", exc);
			throw new PersistenceException("Error in update", exc);
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Throwable th) {
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End update(...)");
		}
		return updated;
	}

	/**
	 * @return the usedAsJSFPOJO
	 */
	// protected final boolean isUsedAsJSFPOJO() {
	// return usedAsJSFPOJO;
	// }

	/**
	 * @param usedAsJSFPOJO the usedAsJSFPOJO to set
	 */
	// protected final void setUsedAsJSFPOJO(boolean usedAsJSFPOJO) {
	// this.usedAsJSFPOJO = usedAsJSFPOJO;
	// }
	@Override
	public String thlGetEntityKey() {
		String pk[] = this.primaryKeyProperties;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(getClass().getName());
		if (pk == null)
			throw new IllegalStateException("non e' specificato il valore di primaryKeyProperties");
		sBuffer.append("[");
		for (int i = 0; i < pk.length; i++) {
			Object value = thlGet(pk[i]);
			if (value == null)
				value = "null";
			sBuffer.append(pk[i] + "=" + value.toString().trim());
			if (i < pk.length - 1)
				sBuffer.append(",");
		}
		sBuffer.append("]");
		return sBuffer.toString();
	}
}
