package com.openi40.mes.assetworkstation.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContextPosition {
	private List<AssetContextObject> parentObjects=new ArrayList<AssetContextObject>();
	private AssetContextObjectTree contextItem=null;
}