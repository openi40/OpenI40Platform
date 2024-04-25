package com.openi40.mes.metamessaging.metainfo;

import java.util.List;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public interface IConfigurableMessageTypes {
	public List<Class<? extends AbstractOI40IOTMetaMessage>> getConfigurableTypes();	
}
