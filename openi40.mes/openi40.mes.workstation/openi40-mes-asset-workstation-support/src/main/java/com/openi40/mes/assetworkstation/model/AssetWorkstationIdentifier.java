package com.openi40.mes.assetworkstation.model;

import com.openi40.mes.shared.model.OI40DBMesAsset;
import com.openi40.mes.shared.model.OI40DBMesAssetGroup;


public class AssetWorkstationIdentifier {
	String ipAddress = null, assetCode = null, assetGroupCode = null, assetStatusCode = null, parentObjectCode = null,
			parentObjectType = null, assetGroupDescription = null, assetDescription = null;

	public AssetWorkstationIdentifier() {

	}

	public static AssetWorkstationIdentifier from(OI40DBMesAssetGroup group, OI40DBMesAsset asset) {
		AssetWorkstationIdentifier identifier = new AssetWorkstationIdentifier();
		identifier.setAssetCode(asset.getCode());
		identifier.setAssetGroupCode(asset.getMesAssetGroupCode());
		identifier.setAssetStatusCode(asset.getMesAssetStatusCode());
		identifier.setIpAddress(asset.getIpAddress());
		identifier.setAssetDescription(asset.getDescription());
		identifier.setParentObjectCode(group.getParentObjectCode());
		identifier.setParentObjectType(group.getParentObjectType());
		identifier.setAssetGroupDescription(group.getDescription());
		identifier.setAssetGroupCode(group.getCode());
		return identifier;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetGroupCode() {
		return assetGroupCode;
	}

	public void setAssetGroupCode(String assetGroupCode) {
		this.assetGroupCode = assetGroupCode;
	}

	public String getAssetStatusCode() {
		return assetStatusCode;
	}

	public void setAssetStatusCode(String assetStatusCode) {
		this.assetStatusCode = assetStatusCode;
	}

	public String getParentObjectCode() {
		return parentObjectCode;
	}

	public void setParentObjectCode(String parentObjectCode) {
		this.parentObjectCode = parentObjectCode;
	}

	public String getParentObjectType() {
		return parentObjectType;
	}

	public void setParentObjectType(String parentObjectType) {
		this.parentObjectType = parentObjectType;
	}

	public String getAssetGroupDescription() {
		return assetGroupDescription;
	}

	public void setAssetGroupDescription(String assetGroupDescription) {
		this.assetGroupDescription = assetGroupDescription;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

}
