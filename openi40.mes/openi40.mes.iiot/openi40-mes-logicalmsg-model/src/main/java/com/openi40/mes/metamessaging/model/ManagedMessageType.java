package com.openi40.mes.metamessaging.model;

public class ManagedMessageType {
	
	private Class<? extends AbstractOI40MetaMessage> type=null;
	private boolean manageExtensions=false;
	public ManagedMessageType(Class<? extends AbstractOI40MetaMessage> msgType) {
		this.type=msgType;
	}
	public Class<? extends AbstractOI40MetaMessage> getType() {
		return type;
	}
	public boolean isManageExtensions() {
		return manageExtensions;
	}

}
