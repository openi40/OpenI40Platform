package com.openi40.mes.assetworkstation.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AssetContextObjectTree {
	private AssetContextObject item = null;
	private List<AssetContextObjectTree> childs = new ArrayList<AssetContextObjectTree>();

	public AssetContextObjectTree() {

	}

}
