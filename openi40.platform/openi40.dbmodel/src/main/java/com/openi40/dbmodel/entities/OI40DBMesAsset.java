package com.openi40.dbmodel.entities;

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
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })


public class OI40DBMesAsset extends OI40DBBaseEntity {
	private String mesAssetGroupCode = null;
	private String mesAssetTypeCode = null;
	private String mesAssetStatusCode = null;
	private String macCode=null;
	private String altCode=null;
	private String ipAddress=null;  	
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

}
