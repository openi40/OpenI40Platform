package com.openi40.mes.metamessaging.model;

import lombok.Data;

@Data
public class AbstractOI40IOTApplicationMessage extends AbstractOI40IOTMetaMessage {
	private String assetFrom=null;
	private String assetTo=null;
	public AbstractOI40IOTApplicationMessage() {
		
	}

}
