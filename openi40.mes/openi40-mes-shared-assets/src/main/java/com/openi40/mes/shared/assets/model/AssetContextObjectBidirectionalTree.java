package com.openi40.mes.shared.assets.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
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

}
