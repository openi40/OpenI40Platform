package com.openi40.dbmodel.easydbbeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CounterManager da il servizio di management di contatore. Creation date:
 * (10/09/00 17.03.40)
 * 
 * @author: architectures@openi40.org
 */
public class SCounterManager implements java.io.Serializable {

	/**
	 * Ritorna il valore attuale di un contatore passatogli come Creation date:
	 * (26/01/2003 11.46.14)
	 * 
	 * @return java.lang.Number
	 * @param counterName java.lang.String
	 * @throws SQLException
	 */
	protected  Long getActualValue(String table, String counterName, Connection connection)
			throws SQLException {
		long outValue = 0l;
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String _sql = "select max(" + counterName + ") from " + table;
			rs = statement.executeQuery(_sql);
			if (rs.next()) {
				outValue = rs.getLong(1);
			}
		} finally {
			try {
				rs.close();
			} catch (Throwable th) {
			}
			try {
				statement.close();
			} catch (Throwable t) {
			}
		}
		return outValue;
	}

	/**
	 * Ritorna il valore del contatore incrementato
	 * 
	 * @return long
	 * @param counterName java.lang.String
	 * @throws SQLException
	 */
	public  short getNewInt16CounterValue(String table, String counterName, Connection connection)
			throws SQLException {

		return (short) (getActualValue(table, counterName, connection).intValue() + 1);
	}

	/**
	 * Ritorna il valore del contatore incrementato
	 * 
	 * @return long
	 * @param counterName java.lang.String
	 * @throws SQLException
	 */
	public  int getNewInt32CounterValue(String table, String counterName, Connection connection)
			throws SQLException {

		return (getActualValue(table, counterName, connection).intValue() + 1);
	}

	/**
	 * Ritorna il valore del contatore incrementato
	 * 
	 * @return long
	 * @param counterName java.lang.String
	 * @throws SQLException
	 */
	public  long getNewInt64CounterValue(String table, String counterName, Connection connection)
			throws SQLException {
		return (getActualValue(table, counterName, connection).longValue() + 1l);
	}

}
