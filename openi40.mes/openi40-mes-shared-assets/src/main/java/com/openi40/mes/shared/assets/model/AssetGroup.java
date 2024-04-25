package com.openi40.mes.shared.assets.model;

import java.util.ArrayList;
import java.util.List;


public class AssetGroup {
	private String code = null, description = null, parentObjectCode = null;
	private AssetContextType parentObjectType = null;
	private List<AssetObject> assets = new ArrayList<AssetObject>();

	public AssetGroup() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentObjectCode() {
		return parentObjectCode;
	}

	public void setParentObjectCode(String parentObjectCode) {
		this.parentObjectCode = parentObjectCode;
	}

	public AssetContextType getParentObjectType() {
		return parentObjectType;
	}

	public void setParentObjectType(AssetContextType parentObjectType) {
		this.parentObjectType = parentObjectType;
	}

	public List<AssetObject> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetObject> assets) {
		this.assets = assets;
	}

}
