package com.openi40.dbmodel.easydbbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistentCounterManager extends SCounterManager {

	public PersistentCounterManager() {

	}

	class PSCache {
		PreparedStatement ps_read = null;
		PreparedStatement ps_insert = null;
		PreparedStatement ps_update = null;

		void clear() {
			try {
				ps_read.close();
			} catch (Throwable th) {
			}
			try {
				ps_insert.close();
			} catch (Throwable th) {
			}
			try {
				ps_update.close();
			} catch (Throwable th) {
			}
		}
	}

	PSCache providePSCache(Connection connection) throws SQLException {
		PSCache c = null;

		c = new PSCache();
		c.ps_read = connection.prepareStatement("SELECT CNTR FROM H_COUNTERS WHERE table_name=? and counter_name=?");
		c.ps_insert = connection
				.prepareStatement("INSERT INTO H_COUNTERS(table_name,counter_name,cntr) values (?,?,?)");
		c.ps_update = connection
				.prepareStatement("UPDATE H_COUNTERS SET CNTR=CNTR+1 WHERE  table_name=? and counter_name=?");

		return c;
	}

	@Override
	protected Long getActualValue(String table, String counterName, Connection connection) throws SQLException {
		Long value = 0l;
		table = table.trim().toUpperCase();
		counterName = counterName.trim().toUpperCase();
		PSCache c = providePSCache(connection);
		c.ps_read.clearParameters();
		c.ps_read.setString(1, table);
		c.ps_read.setString(2, counterName);
		ResultSet rs = c.ps_read.executeQuery();
		if (rs.next()) {
			value = rs.getLong(1);
		}
		rs.close();
		if (value <= 0l) {
			String _sql = "SELECT MAX(" + counterName + ") from " + table;
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(_sql);
			if (rs.next()) {
				value = rs.getLong(1);
			}
			rs.close();
			c.ps_insert.clearParameters();
			c.ps_insert.setString(1, table);
			c.ps_insert.setString(2, counterName);
			c.ps_insert.setLong(3, value);
			c.ps_insert.executeUpdate();
		}
		c.ps_update.clearParameters();
		c.ps_update.setString(1, table);
		c.ps_update.setString(2, counterName);
		c.ps_update.executeUpdate();
		return value;

	}
}
