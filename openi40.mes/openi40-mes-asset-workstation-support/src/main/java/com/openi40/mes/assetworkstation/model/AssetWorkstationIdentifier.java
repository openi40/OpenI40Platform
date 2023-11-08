package com.openi40.mes.assetworkstation.model;

import com.openi40.mes.datamodel.OI40DBMesAsset;
import com.openi40.mes.datamodel.OI40DBMesAssetGroup;

import lombok.Data;

@Data
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

}
