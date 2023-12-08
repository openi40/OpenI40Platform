package com.openi40.mes.io.logical.apsbroker.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.metainfo.IConfigurableMessageTypes;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

@Service
public class ApsConfigurableMessageTypesImpl implements IConfigurableMessageTypes {
	private static final List<Class<? extends AbstractOI40IOTMetaMessage>> MESSAGETYPES = new ArrayList<Class<? extends AbstractOI40IOTMetaMessage>>();
	static {
		MESSAGETYPES.add(Openi40ApsOutgoingMessage.class);
	}

	public ApsConfigurableMessageTypesImpl() {

	}

	@Override
	public List<Class<? extends AbstractOI40IOTMetaMessage>> getConfigurableTypes() {

		return MESSAGETYPES;
	}

}
