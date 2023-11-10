package com.openi40.mes.shared.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_asset")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
		@AttributeOverride(name = "integrationId", column = @Column(name = "integration_id")),
		@AttributeOverride(name = "integrationReadUrl", column = @Column(name = "integration_read_url")),
		@AttributeOverride(name = "readContentType", column = @Column(name = "read_content_type")),
		@AttributeOverride(name = "integrationWriteUrl", column = @Column(name = "integration_write_url")),
		@AttributeOverride(name = "writeContentType", column = @Column(name = "write_content_type")),
		@AttributeOverride(name = "protocolType", column = @Column(name = "protocol_type")),
		@AttributeOverride(name = "jsonConfig", column = @Column(name = "json_config")) })
@Data
/*
 * ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_ID VARCHAR(30); ALTER TABLE
 * MES_ASSET ADD COLUMN INTEGRATION_READ_URL VARCHAR(255); ALTER TABLE MES_ASSET
 * ADD COLUMN READ_CONTENT_TYPE VARCHAR(40); ALTER TABLE MES_ASSET ADD COLUMN
 * INTEGRATION_WRITE_URL VARCHAR(255); ALTER TABLE MES_ASSET ADD COLUMN
 * WRITE_CONTENT_TYPE VARCHAR(40); ALTER TABLE MES_ASSET ADD COLUMN
 * PROTOCOL_TYPE VARCHAR(20); ALTER TABLE MES_ASSET ADD COLUMN JSON_CONFIG TEXT;
 */
public class OI40DBMesAsset extends OI40DBMesBaseEntity {
	private String mesAssetGroupCode = null;
	private String mesAssetTypeCode = null;
	private String mesAssetStatusCode = null;
	private String macCode = null;
	private String altCode = null;
	private String ipAddress = null;
	private String integrationId = null, integrationReadUrl = null, readContentType = null, integrationWriteUrl = null,
			writeContentType = null, protocolType = null, jsonConfig = null;

	public OI40DBMesAsset() {

	}

}
