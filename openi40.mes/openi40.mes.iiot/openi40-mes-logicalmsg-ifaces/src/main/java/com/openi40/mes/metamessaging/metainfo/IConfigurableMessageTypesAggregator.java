package com.openi40.mes.metamessaging.metainfo;

import java.util.List;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public interface IConfigurableMessageTypesAggregator {
	public List<Class<? extends AbstractOI40IOTMetaMessage>> getConfigurableTypes();
}
