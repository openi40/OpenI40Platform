package com.openi40.mes.shared.assets.services;

import java.util.ArrayList;
import java.util.List;

import com.openi40.mes.shared.assets.model.AssetContextObject;
import com.openi40.mes.shared.assets.model.AssetContextObjectBidirectionalTree;
import com.openi40.mes.shared.assets.model.AssetContextObjectTree;
import com.openi40.mes.shared.assets.model.AssetContextType;
import com.openi40.mes.shared.assets.model.ContextPosition;

public interface IAssetContextTreeFactory {
	public List<AssetContextObjectTree> getContextRoots();

	public default AssetContextObjectBidirectionalTree getContextPosition(String objectType, String objectCode) {
		AssetContextType contextType = AssetContextType.valueOf(objectType);
		AssetContextObject searchedObject = new AssetContextObject(contextType, objectCode);
		List<AssetContextObjectBidirectionalTree> rebuildRoots = new ArrayList<AssetContextObjectBidirectionalTree>();
		List<AssetContextObjectTree> roots = getContextRoots();

		AssetContextObjectBidirectionalTree result = null;
		for (AssetContextObjectTree assetContextObjectTree : roots) {
			AssetContextObjectBidirectionalTree transformedTree = getBidirectionalTree(assetContextObjectTree);
			if (result == null) {
				result = this.findNode(searchedObject, transformedTree);
			}
		}

		return result;
	}

	public default AssetContextObjectBidirectionalTree findNode(AssetContextObject searchedObject,
			AssetContextObjectBidirectionalTree transformedTree) {
		AssetContextObjectBidirectionalTree found = null;
		if (transformedTree.isNodeOf(searchedObject)) {
			found = transformedTree;
			if (found == null) {
				for (AssetContextObjectTree child : transformedTree.getChilds()) {
					if (child instanceof AssetContextObjectBidirectionalTree) {
						AssetContextObjectBidirectionalTree _child = (AssetContextObjectBidirectionalTree) child;
						found = this.findNode(searchedObject, _child);
					}
				}
			}
		}
		return found;
	}

	public default AssetContextObjectBidirectionalTree getBidirectionalTree(
			AssetContextObjectTree assetContextObjectTree) {
		AssetContextObjectBidirectionalTree assetItem = new AssetContextObjectBidirectionalTree();
		assetItem.setItem(assetContextObjectTree.getItem());
		for (AssetContextObjectTree child : assetContextObjectTree.getChilds()) {
			AssetContextObjectBidirectionalTree transformedChild = this.getBidirectionalTree(child);
			transformedChild.setParent(assetItem);
		}
		return assetItem;
	}

	public default ContextPosition findPosition(AssetContextObject contextObject) {
		return null;
	}
}
