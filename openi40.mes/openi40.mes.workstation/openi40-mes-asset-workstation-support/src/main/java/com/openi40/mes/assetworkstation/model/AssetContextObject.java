package com.openi40.mes.assetworkstation.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AssetContextObject implements Serializable {
	private AssetContextType objectType = null;
	private String objectCode = null;
	private String objectDescription = null;

	public AssetContextObject() {

	}

	public AssetContextObject(AssetContextType type, String code) {
		this.objectType = type;
		this.objectCode = code;

	}

	public AssetContextObject(AssetContextType type, String code, String description) {
		this.objectType = type;
		this.objectCode = code;
		this.objectDescription = description;
	}
}