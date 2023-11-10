package com.openi40.mes.shared.assets.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContextPosition {
	private List<AssetContextObject> parentObjects=new ArrayList<AssetContextObject>();
	private AssetContextObjectTree contextItem=null;
}