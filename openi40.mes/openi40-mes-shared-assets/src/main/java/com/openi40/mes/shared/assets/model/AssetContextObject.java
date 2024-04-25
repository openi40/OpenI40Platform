package com.openi40.mes.shared.assets.model;

import java.io.Serializable;


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

	public AssetContextType getObjectType() {
		return objectType;
	}

	public void setObjectType(AssetContextType objectType) {
		this.objectType = objectType;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectDescription() {
		return objectDescription;
	}

	public void setObjectDescription(String objectDescription) {
		this.objectDescription = objectDescription;
	}
}
