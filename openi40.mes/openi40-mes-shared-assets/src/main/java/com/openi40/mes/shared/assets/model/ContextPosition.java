package com.openi40.mes.shared.assets.model;

import java.util.ArrayList;
import java.util.List;


public class ContextPosition {
	private List<AssetContextObject> parentObjects=new ArrayList<AssetContextObject>();
	private AssetContextObjectTree contextItem=null;
	public List<AssetContextObject> getParentObjects() {
		return parentObjects;
	}
	public void setParentObjects(List<AssetContextObject> parentObjects) {
		this.parentObjects = parentObjects;
	}
	public AssetContextObjectTree getContextItem() {
		return contextItem;
	}
	public void setContextItem(AssetContextObjectTree contextItem) {
		this.contextItem = contextItem;
	}
}