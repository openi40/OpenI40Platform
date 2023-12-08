package com.openi40.mes.shared.assets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openi40.mes.shared.assets.model.AssetContextObject;
import com.openi40.mes.shared.assets.model.AssetContextObjectTree;
import com.openi40.mes.shared.assets.model.AssetGroup;
import com.openi40.mes.shared.assets.model.ContextPosition;

public class AssetsController {

	public AssetsController() {
		
	}

	@Autowired
	com.openi40.mes.shared.assets.services.IAssetContextTreeFactory assetContextTreeFactory;

	@GetMapping(path = "getContextRoots")
	public List<AssetContextObjectTree> getContextRoots() {
		return assetContextTreeFactory.getContextRoots();
	}

	@PostMapping(path = "getContextPositionByContextObject")
	public ContextPosition getContextPositionByContextObject(@RequestBody AssetContextObject contextObject) {
		return this.assetContextTreeFactory.findPosition(contextObject);
	}

	@PostMapping(path = "getContextPositionByAssetGroup")
	public ContextPosition getContextPositionByAssetGroup(@RequestBody AssetGroup assetGroup) {
		AssetContextObject contextObject = new AssetContextObject(assetGroup.getParentObjectType(),
				assetGroup.getParentObjectCode());
		return this.assetContextTreeFactory.findPosition(contextObject);
	}

	
	public static class WorkstationContext {
		ContextPosition position = null;

		public ContextPosition getPosition() {
			return position;
		}

		public void setPosition(ContextPosition position) {
			this.position = position;
		}

	}

}
