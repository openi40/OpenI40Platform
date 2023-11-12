package com.openi40.mes.metamessaging.metainfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

@Service
public class ConfigurableMessageTypesAggregatorImpl implements IConfigurableMessageTypesAggregator {
	List<IConfigurableMessageTypes> typedescs = null;

	public ConfigurableMessageTypesAggregatorImpl(
			@Autowired(required = false) List<IConfigurableMessageTypes> typedescs) {
		this.typedescs = typedescs != null ? typedescs : new ArrayList<IConfigurableMessageTypes>();
	}

	@Override
	public List<Class<? extends AbstractOI40IOTMetaMessage>> getConfigurableTypes() {

		List<Class<? extends AbstractOI40IOTMetaMessage>> types = new ArrayList<Class<? extends AbstractOI40IOTMetaMessage>>();
		for (IConfigurableMessageTypes iConfigurableMessageTypes : typedescs) {
			types.addAll(iConfigurableMessageTypes.getConfigurableTypes());
		}
		return types;
	}

}
