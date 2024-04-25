package com.openi40.dbmodel.easydbbeans;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Vector;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;

/**
 * Class rappresenting a transaction hides JDBC complexity,optimizes the use of
 * PreparedStatements and fires events on begin,successfull commit and rollback
 * Creation date: (22/09/2001 17.28.30)
 * 
 * @author: architectures@openi40.org
 */
public class PersistenceTransaction  {
	
	private boolean closeConnection = true, readOnly = false;
	protected boolean useCachedStatements = false;

	private boolean rollbackObjects = true;

	private Principal userPrincipal = null;

	private TreeMap company_id_map = new TreeMap();

	int transactionIdentifier = (int) (Math.random() * 100000.0);

	private java.util.Vector listeners = new java.util.Vector();
	private HashMap listenersMap = new HashMap();
	HashMap<String, Boolean> usedEntities = new HashMap<String, Boolean>();
	private Vector sql_objects = new Vector();
	private boolean usePersonalFiltering = true;
	private java.sql.Connection connection = null;
	private java.sql.Connection statsConnection = null;
	private java.util.Hashtable table = new java.util.Hashtable();

	java.util.Vector transactionOperations = new java.util.Vector();

	private String language = null;

	private int transactCounter = 0;

	long host_id = -1;

	private Statement usedStatement = null;

	private Hashtable psStatement = new Hashtable();

	public PreparedStatement prepareStatement(String _sql) throws SQLException {
		PreparedStatement ps = (PreparedStatement) psStatement.get(_sql.trim().toLowerCase());
		if (ps == null) {
			ps = this.getConnection().prepareStatement(_sql);
			this.addSQLObject(ps);
			this.psStatement.put(_sql.trim().toLowerCase(), ps);
		}
		return ps;
	}

	public Statement createStatement() throws SQLException {
		if (usedStatement == null) {
			usedStatement = getConnection().createStatement();
			this.addSQLObject(usedStatement);
		}
		return usedStatement;
	}

	String transaction_id = null;
	long timestamp = System.currentTimeMillis();

	/**
	 * PersistenceTransaction constructor restricted to package visibility
	 */
	protected PersistenceTransaction(java.sql.Connection connection, boolean readOnly) {
		super();
		this.readOnly = readOnly;

		setConnection(connection);

	}

	protected void initialize() throws SQLException {

	}

	/**
	 * Begins a transaction Creation date: (22/09/2001 17.44.54)
	 * 
	 * @exception java.sql.SQLException The exception description.
	 */
	public void begin() throws java.sql.SQLException {
		if (connection == null)
			throw new java.sql.SQLException("Stato della transazione incoerente, si sta cercando di riutilizzarla ");

		connection.setAutoCommit(false);

	}

	/**
	 * Releases all the resources Creation date: (22/09/2001 17.57.50)
	 */
	public void close() {
		try {
			for (Iterator iter = sql_objects.iterator(); iter.hasNext();) {
				Object _object = (Object) iter.next();
				if (_object instanceof ResultSet) {
					ResultSet rs = (ResultSet) _object;
					try {
						rs.close();
					} catch (Throwable th1) {
					}
				}
			}
			for (Iterator iter = sql_objects.iterator(); iter.hasNext();) {
				Object _object = (Object) iter.next();
				if (_object instanceof Statement) {
					Statement rs = (Statement) _object;
					try {
						rs.close();
					} catch (Throwable th1) {
					}
				}
			}
			sql_objects.removeAllElements();
			java.util.Enumeration enum1 = table.elements();
			while (enum1.hasMoreElements()) {
				try {
					java.sql.Statement statement = (java.sql.Statement) enum1.nextElement();
					statement.close();
				} catch (Throwable th) {
				}
			}
			table = new java.util.Hashtable();
			
			listeners.removeAllElements();
			listenersMap.clear();
			if (connection != null && closeConnection) {
				
				connection.setAutoCommit(true);
				connection.setReadOnly(false);
				connection.close();
				connection = null;
			}
			
		} catch (Throwable th) {
		}
		connection = null;
		
		// com.zconsultancies.threelayers.util.LogUtil.out.println("Transaction
		// # "+application.transactionCounter);
	}

	public boolean isTransactionStarted() {
		return transactCounter > 0;
	}

	

	

	/**
	 * Try to commit all the work Creation date: (22/09/2001 17.43.07)
	 * 
	 * @exception java.sql.SQLException The exception description.
	 */
	public void commit() throws java.sql.SQLException {
		
		
			connection.commit();
			connection.setAutoCommit(true);
		
	}

	/**
	 * Insert the method's description here. Creation date: (02/10/2001 21.54.29)
	 */
	protected void finalize() {
		try {
			close();
		} catch (Throwable th) {
		}
		
		connection = null;
		listeners.removeAllElements();
		listeners = null;
		listenersMap.clear();
		listenersMap = null;
		table.clear();
		table = null;
		this.transactionOperations.clear();
		this.transactionOperations = null;
		try {
			super.finalize();
		} catch (Throwable th) {
		}
	}

	/**
	 * Returns the used JDBC connection Creation date: (22/09/2001 17.29.45)
	 * 
	 * @return java.sql.Connection
	 */
	public java.sql.Connection getConnection() {
		return connection;
	}

	/**
	 * returns the prepared statement associated to the "key" name Creation date:
	 * (22/09/2001 18.10.43)
	 * 
	 * @return java.sql.PreparedStatement
	 * @param key java.lang.String
	 */
	public synchronized java.sql.PreparedStatement getPreparedStatement(String key) {
		if (!useCachedStatements)
			return null;
		java.sql.PreparedStatement ps = (java.sql.PreparedStatement) table.get(key);
		return ps;
	}

	/**
	 * Prepares a statement associated to the "key" name or returns it if it already
	 * exists Creation date: (22/09/2001 18.05.02)
	 * 
	 * @return java.sql.PreparedStatement
	 * @param query java.lang.String
	 * @param key   java.lang.String
	 * @exception java.sql.SQLException The exception description.
	 */
	public synchronized java.sql.PreparedStatement getPreparedStatement(String query, String key)
			throws java.sql.SQLException {
		java.sql.PreparedStatement ps = (java.sql.PreparedStatement) table.get(key);
		if (ps == null || !useCachedStatements) {
			ps = connection.prepareStatement(query);
			table.put(key, ps);
			addSQLObject(ps);
		}

		return ps;
	}

	
	/**
	 * Does a rollback Creation date: (22/09/2001 17.43.07)
	 * 
	 * @exception java.sql.SQLException The exception description.
	 */
	public void rollback() throws java.sql.SQLException, PersistenceException {
		try {
			
				connection.rollback();
			
		} catch (Throwable th) {
		}
		
		try {
			connection.setAutoCommit(true);
		} catch (Throwable th1) {
		}
		if (transactCounter <= 0) {
			throw new IllegalStateException(
					"Commit in process and no corresponding begin performed ! transactionCounter=" + transactCounter);
		}
		transactCounter--;
	}

	/**
	 * Sets the used JDBC Connection Creation date: (22/09/2001 17.29.45)
	 * 
	 * @param newConnection java.sql.Connection
	 */
	private void setConnection(java.sql.Connection newConnection) {
		connection = newConnection;
	}

	/**
	 * @return
	 */
	public boolean isCloseConnection() {
		return closeConnection;
	}

	/**
	 * @param b
	 */
	public void setCloseConnection(boolean b) {
		closeConnection = b;
	}

	public void addSQLObject(ResultSet rs) {
		sql_objects.addElement(rs);
	}

	public void addSQLObject(Statement st) {
		// TODO:RIMETTERE COME ERA
		sql_objects.addElement(st);
	}

	
	/**
	 * @return
	 */
	public Principal getUserPrincipal() {
		return userPrincipal;
	}

	/**
	 * @param userPrincipal
	 */
	void setUserPrincipal(Principal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}

	/**
	 * @return
	 */
	public java.util.Vector getTransactionOperations() {
		return transactionOperations;
	}

	/**
	 * @param transactionOperations
	 */
	public void setTransactionOperations(java.util.Vector transactionOperations) {
		this.transactionOperations = transactionOperations;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public int getTransactionIdentifier() {
		return transactionIdentifier;
	}

	public boolean isRollbackObjects() {
		return rollbackObjects;
	}

	public void setRollbackObjects(boolean rollbackObjects) {
		this.rollbackObjects = rollbackObjects;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getHost_id() {
		return host_id;
	}

	public synchronized String getTransaction_id() {
		if (transaction_id == null) {

			transaction_id=UUID.randomUUID().toString();
		}
		return transaction_id;
	}

	private void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}


	public boolean isUsePersonalFiltering() {
		return usePersonalFiltering;
	}

	public void setUsePersonalFiltering(boolean usePersonalFiltering) {
		this.usePersonalFiltering = usePersonalFiltering;
	}

	public class PKeyCacheCouple {
		public Class classe = null;

		public String key = null;
	}

	


	public java.sql.Connection getStatsConnection() {
		return statsConnection;
	}

}