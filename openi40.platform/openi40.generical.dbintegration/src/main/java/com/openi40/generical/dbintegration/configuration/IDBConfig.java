package com.openi40.generical.dbintegration.configuration;

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

public class IDBConfig {
	String jdbcDriver = null,jdbcConnectionString=null;
	String jdbcUid = null, jdbcPwd = null;

	public IDBConfig() {

	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcConnectionString() {
		return jdbcConnectionString;
	}

	public void setJdbcConnectionString(String jdbcConnectionString) {
		this.jdbcConnectionString = jdbcConnectionString;
	}

	public String getJdbcUid() {
		return jdbcUid;
	}

	public void setJdbcUid(String jdbcUid) {
		this.jdbcUid = jdbcUid;
	}

	public String getJdbcPwd() {
		return jdbcPwd;
	}

	public void setJdbcPwd(String jdbcPwd) {
		this.jdbcPwd = jdbcPwd;
	}

}
