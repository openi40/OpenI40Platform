package com.openi40.mes.shared.assets.model;

public class AssetContextObjectBidirectionalTree extends AssetContextObjectTree {
	private AssetContextObjectBidirectionalTree parent = null;

	public AssetContextObjectBidirectionalTree() {

	}

	public boolean isNodeOf(AssetContextObject searchedObject) {
		AssetContextObject item = getItem();
		return item != null && searchedObject != null && item.getObjectCode() != null && item.getObjectType() != null
				&& searchedObject.getObjectCode() != null && searchedObject.getObjectType() != null
				&& (item.getObjectType().equals(searchedObject.getObjectType()))
				&& (item.getObjectCode().equals(searchedObject.getObjectCode()));
	}

	public AssetContextObjectBidirectionalTree getParent() {
		return parent;
	}

	public void setParent(AssetContextObjectBidirectionalTree parent) {
		this.parent = parent;
	}

}
