package com.openi40.mes.metamessaging.model;

public class AbstractOI40IOTApplicationMessage extends AbstractOI40IOTMetaMessage {
	private String assetFrom=null;
	private String assetTo=null;
	public AbstractOI40IOTApplicationMessage() {
		
	}
	public String getAssetFrom() {
		return assetFrom;
	}
	public void setAssetFrom(String assetFrom) {
		this.assetFrom = assetFrom;
	}
	public String getAssetTo() {
		return assetTo;
	}
	public void setAssetTo(String assetTo) {
		this.assetTo = assetTo;
	}

}
