package com.openi40.mes.assetworkstation.model;

import lombok.Data;

@Data
public class AssetWorkstationRequestWrapper<T> {
	private T content = null;
	private AssetWorkstationIdentifier workstationIdentifier = null;

	public AssetWorkstationRequestWrapper() {

	}

	public AssetWorkstationRequestWrapper(T c, AssetWorkstationIdentifier i) {
		this.content = c;
		this.workstationIdentifier = i;
	}

}
