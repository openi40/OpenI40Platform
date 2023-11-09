package com.openi40.mes.assetworkstation.services;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.stereotype.Service;

import com.openi40.mes.assetworkstation.model.AssetContextObjectTree;
@Singleton
@Service
public class AssetContextTreeFactoryImpl implements IAssetContextTreeFactory {

	public AssetContextTreeFactoryImpl() {
		
	}

	@Override
	public List<AssetContextObjectTree> getContextRoots() {
		
		return null;
	}

}
