package com.openi40.mes.assetworkstation.model;

public class AssetWorkstationRequestWrapper<T> {
	private T content = null;
	private AssetWorkstationIdentifier workstationIdentifier = null;

	public AssetWorkstationRequestWrapper() {

	}

	public AssetWorkstationRequestWrapper(T c, AssetWorkstationIdentifier i) {
		this.content = c;
		this.workstationIdentifier = i;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public AssetWorkstationIdentifier getWorkstationIdentifier() {
		return workstationIdentifier;
	}

	public void setWorkstationIdentifier(AssetWorkstationIdentifier workstationIdentifier) {
		this.workstationIdentifier = workstationIdentifier;
	}

}
