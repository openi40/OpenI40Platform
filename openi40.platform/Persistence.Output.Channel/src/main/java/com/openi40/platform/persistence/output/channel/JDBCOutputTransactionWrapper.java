package com.openi40.platform.persistence.output.channel;

import java.sql.Connection;
import java.sql.SQLException;

import com.openi40.scheduler.outputchannels.streamoutputs.IOutputTransactionWrapper;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputTransactionException;

public class JDBCOutputTransactionWrapper implements IOutputTransactionWrapper {
	Connection connection = null;

	public JDBCOutputTransactionWrapper(Connection connection) {
		this.connection = connection;

	}

	@Override
	public void begin() throws OutputTransactionException {
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new OutputTransactionException("Exception in begin()", e);
		}

	}

	@Override
	public void commit() throws OutputTransactionException {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			throw new OutputTransactionException("Exception in commit()", e);
		}

	}

	@Override
	public void rollback() throws OutputTransactionException {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			throw new OutputTransactionException("Exception in rollback()", e);
		}

	}

	@Override
	public void close() throws OutputTransactionException {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new OutputTransactionException("Exception in close()", e);
		}

	}

	public Connection getConnection() {
		return connection;
	}

}