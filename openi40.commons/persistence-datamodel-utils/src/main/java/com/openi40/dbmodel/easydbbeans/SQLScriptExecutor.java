/*
 * Created on 26-set-2006
 * By architectures@openi40.org
 * Il presente codice sorgente   di esclusiva propriet  di architectures@openi40.org,
 * nessun diritto di distribuzione, vendita o modifica   riconosciuto a terzi.
 */
package com.openi40.dbmodel.easydbbeans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLScriptExecutor {
	Logger LOGGER = LoggerFactory.getLogger(SQLScriptExecutor.class);

	public class SQLScriptExecutorException extends Exception {
		int row = 0;

		Throwable nested = null;

		public SQLScriptExecutorException(String message, int row, Throwable nested) {
			super(message + "\n row : " + row);
			this.row = row;
			this.nested = nested;
		}

		public int getRow() {
			return row;
		}

		public Throwable getNested() {
			return nested;
		}
	}

	String script;

	boolean logConsole = true;

	public SQLScriptExecutor(String _script, boolean logConsole) {
		this.script = _script;
		this.logConsole = logConsole;

	}

	HashMap<String, Object> values = null;

	public void mapVariabileExchanges(HashMap<String, Object> values) {
		this.values = values;
	}

	public void execute(Connection connection) throws IOException, SQLScriptExecutorException {
		execute(connection, true);
	}

	public void execute(Connection connection, boolean exitOnException) throws IOException, SQLScriptExecutorException {
		FileInputStream fis = new FileInputStream(this.script);
		BufferedReader bis = new BufferedReader(new InputStreamReader(fis));
		StringBuffer _sb = new StringBuffer();
		if (logConsole) {
			LOGGER.info("calling SQLScriptExecutor.execute(....); AT " + new Date());
		}
		boolean ok = true;
		String line = null;
		int idx = 0;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			while (ok && (line = bis.readLine()) != null) {

				if (line.trim().startsWith("--")) {
					idx++;
					continue;
				}
				long time = System.currentTimeMillis();
				long executed = 0;
				idx++;
				_sb.append(" ");
				if (line.indexOf(';') == -1) {
					_sb.append(line);
				} else {
					_sb.append(line.substring(0, line.indexOf(';')));
					String _sql = _sb.toString();
					if (_sql.trim().length() > 0) {

						boolean error = false;
						// CAMBIO I VALORI ALLE VARIABILI PRESENTI NELL'SQL
						if (values != null) {
							Iterator<Entry<String, Object>> iterator = values.entrySet().iterator();
							while (iterator.hasNext()) {
								Entry<String, Object> item = iterator.next();
								_sql = _sql.replace(item.getKey(), item.getValue().toString());
							}
						}
						if (logConsole) {
							LOGGER.info(_sql);
						}
						try {
							statement.execute(_sql);
						} catch (Throwable th12) {
							error = true;
							if (logConsole) {
								LOGGER.error("Error executing SQL =>" + _sql, th12);
							}

							if (exitOnException) {
								throw new SQLScriptExecutorException(
										"Errore nell'esecuzione dello script " + this.script + "", idx, th12);
							}
						} finally {
							executed = System.currentTimeMillis();
						}
						if (logConsole && !error) {
							LOGGER.info("DONE! in " + ((executed - time) / 1000) + " seconds");
						}
						_sb = new StringBuffer(line.substring(line.indexOf(';') + 1));
					}
				}
			}

		} catch (Throwable th12) {
			LOGGER.error("Exception executing script", th12);
			if (exitOnException) {
				throw new SQLScriptExecutorException("Errore nell'esecuzione dello script " + this.script + "", idx,
						th12);
			}
		} finally {
			try {
				statement.close();
			} catch (Throwable th12) {
			}
			if (logConsole) {
				LOGGER.info("ending SQLScriptExecutor.execute(....); AT " + new Date());
			}
		}
	}

	public boolean isLogConsole() {
		return logConsole;
	}

	public String getScript() {
		return script;
	}

	
}
