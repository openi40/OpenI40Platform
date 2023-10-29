package com.openi40.mes.metamessaging.model;

import lombok.Getter;

@Getter
public class ManagedMessageType {
	
	private Class<? extends AbstractOI40MetaMessage> type=null;
	private boolean manageExtensions=false;
	public ManagedMessageType(Class<? extends AbstractOI40MetaMessage> msgType) {
		this.type=msgType;
	}

}
