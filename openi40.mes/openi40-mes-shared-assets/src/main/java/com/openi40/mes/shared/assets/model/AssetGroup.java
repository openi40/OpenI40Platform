package com.openi40.mes.shared.assets.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AssetGroup {
	private String code = null, description = null, parentObjectCode = null;
	private AssetContextType parentObjectType = null;
	private List<AssetObject> assets = new ArrayList<AssetObject>();

	public AssetGroup() {

	}

}
