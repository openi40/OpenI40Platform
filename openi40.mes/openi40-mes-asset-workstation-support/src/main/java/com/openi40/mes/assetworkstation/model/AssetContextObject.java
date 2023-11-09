package com.openi40.mes.assetworkstation.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AssetContextObject implements Serializable {
	private AssetContextType objectType = null;
	private String objectCode = null;

	public AssetContextObject() {

	}

	public AssetContextObject(AssetContextType type, String code) {
		this.objectType = type;
		this.objectCode = code;
	}
}
