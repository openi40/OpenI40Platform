package com.openi40.mes.assetworkstation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AssetContextObjectTree implements Serializable {
	private AssetContextObject item = null;
	private List<? extends AssetContextObjectTree> childs = new ArrayList<AssetContextObjectTree>();

	public AssetContextObjectTree() {

	}

	public AssetContextObject getItem() {
		return item;
	}

	public void setItem(AssetContextObject item) {
		this.item = item; 
	}

	public List<? extends AssetContextObjectTree> getChilds() {
		return childs;
	}

	public void setChilds(List<? extends AssetContextObjectTree> childs) {
		this.childs = childs;
	}

}
