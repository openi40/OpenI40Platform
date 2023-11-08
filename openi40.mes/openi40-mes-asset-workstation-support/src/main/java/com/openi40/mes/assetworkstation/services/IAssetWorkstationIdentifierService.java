package com.openi40.mes.assetworkstation.services;

import java.util.List;

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;

public interface IAssetWorkstationIdentifierService {
	public List<AssetWorkstationIdentifier> getWorkstationIdentifiersByContext(String parentObjectType,String parentObjectCode);
	public AssetWorkstationIdentifier getWorkstationIdentifier(String ipAddress);
	public AssetWorkstationIdentifier save(AssetWorkstationIdentifier identifier);
	public void delete(AssetWorkstationIdentifier identifier);
	public boolean isValidWorkstation(AssetWorkstationIdentifier identifier);
}
