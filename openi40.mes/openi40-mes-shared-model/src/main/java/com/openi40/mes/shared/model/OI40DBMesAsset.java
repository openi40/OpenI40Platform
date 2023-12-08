package com.openi40.mes.shared.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	public String getMesAssetGroupCode() {
		return mesAssetGroupCode;
	}

	public void setMesAssetGroupCode(String mesAssetGroupCode) {
		this.mesAssetGroupCode = mesAssetGroupCode;
	}

	public String getMesAssetTypeCode() {
		return mesAssetTypeCode;
	}

	public void setMesAssetTypeCode(String mesAssetTypeCode) {
		this.mesAssetTypeCode = mesAssetTypeCode;
	}

	public String getMesAssetStatusCode() {
		return mesAssetStatusCode;
	}

	public void setMesAssetStatusCode(String mesAssetStatusCode) {
		this.mesAssetStatusCode = mesAssetStatusCode;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getAltCode() {
		return altCode;
	}

	public void setAltCode(String altCode) {
		this.altCode = altCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	public String getIntegrationReadUrl() {
		return integrationReadUrl;
	}

	public void setIntegrationReadUrl(String integrationReadUrl) {
		this.integrationReadUrl = integrationReadUrl;
	}

	public String getReadContentType() {
		return readContentType;
	}

	public void setReadContentType(String readContentType) {
		this.readContentType = readContentType;
	}

	public String getIntegrationWriteUrl() {
		return integrationWriteUrl;
	}

	public void setIntegrationWriteUrl(String integrationWriteUrl) {
		this.integrationWriteUrl = integrationWriteUrl;
	}

	public String getWriteContentType() {
		return writeContentType;
	}

	public void setWriteContentType(String writeContentType) {
		this.writeContentType = writeContentType;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(String jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

}
