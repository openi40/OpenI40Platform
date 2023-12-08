package com.openi40.generical.dbintegration.configuration;

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

public class SqlEntityIntegrationConfig extends EntityIntegrationConfig {
	String extractSql = null;
	String incrementalSyncSql = null;
	String modificationTimestampField = null;

	public SqlEntityIntegrationConfig() {

	}

	public String getExtractSql() {
		return extractSql;
	}

	public void setExtractSql(String extractSql) {
		this.extractSql = extractSql;
	}

	public String getIncrementalSyncSql() {
		return incrementalSyncSql;
	}

	public void setIncrementalSyncSql(String incrementalSyncSql) {
		this.incrementalSyncSql = incrementalSyncSql;
	}

	public String getModificationTimestampField() {
		return modificationTimestampField;
	}

	public void setModificationTimestampField(String modificationTimestampField) {
		this.modificationTimestampField = modificationTimestampField;
	}

}
