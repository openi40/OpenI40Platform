package com.openi40.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDBSupportTest {
	protected Logger LOGGER=LoggerFactory.getLogger(getClass());
	protected @Autowired DataSource dataSource;
	public AbstractDBSupportTest() {
		
	}
	private void executeInputStream(InputStream is, Connection connextion, boolean avoidExitOnException,
			boolean logExceptions) throws IOException, SQLException {
		String sql = this.readAll(is);
		List<String> sqlCommands = this.splitCommands(sql);
		Statement statement = null;
		try {
			statement = connextion.createStatement();
			for (String sqlCommand : sqlCommands) {

				try {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Executing: " + sqlCommand);
					}
					statement.execute(sqlCommand);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Executed OK!");
					}
				} catch (Throwable th) {
					String msg = "Error executing:" + sqlCommand;
					if (logExceptions)
						LOGGER.error(msg, th);
					if (!avoidExitOnException) {
						throw new SQLException(msg, th);
					}
				}
			}
		} finally {
			try {
				statement.close();
			} catch (Throwable s) {
			}
		}
	}

	@Transactional(value = TxType.SUPPORTS)
	protected void runScript(String path, boolean avoidExitOnException, boolean logExceptions)
			throws IOException, SQLException {
		InputStream is = null; 
		if (new File(path).exists()) {
			is = new FileInputStream(path);
		} else {
			is = this.getClass().getClassLoader().getResourceAsStream(path);
		}
		if (is == null) {
			throw new FileNotFoundException(
					"Path: " + path + " does not correspond to external path or classpath resource path");
		}
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(true);
			executeInputStream(is, connection, avoidExitOnException, logExceptions);
		} finally {
			try {
				connection.close();
			} catch (Throwable t) {
			}
		}
	}

	

	private List<String> splitCommands(String sql) {
		StringTokenizer tokenizer = new StringTokenizer(sql, ";");
		List<String> al = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			if (s.trim().length() > 0) {
				al.add(s.trim());
			}
		}
		return al;
	}

	private String readAll(InputStream is) throws IOException {
		byte buffer[] = new byte[4096];
		StringBuffer sb = new StringBuffer();
		int nRead = 0;
		while ((nRead = is.read(buffer)) > 0) {
			sb.append(new String(buffer, 0, nRead));
		}
		return sb.toString();
	}
}
