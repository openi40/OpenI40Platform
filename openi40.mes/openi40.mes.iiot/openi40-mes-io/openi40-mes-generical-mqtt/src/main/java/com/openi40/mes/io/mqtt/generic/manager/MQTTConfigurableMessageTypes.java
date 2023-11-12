package com.openi40.mes.io.mqtt.generic.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.mes.io.mqtt.generic.input.GenericalMQTTInputMessage;
import com.openi40.mes.io.mqtt.generic.output.GenericalMQTTOutputMessage;
import com.openi40.mes.metamessaging.metainfo.IConfigurableMessageTypes;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

@Service
public class MQTTConfigurableMessageTypes implements IConfigurableMessageTypes {
	private static final List<Class<? extends AbstractOI40IOTMetaMessage>> MESSAGETYPES = new ArrayList<Class<? extends AbstractOI40IOTMetaMessage>>();
	static {
		MESSAGETYPES.add(GenericalMQTTInputMessage.class);
		MESSAGETYPES.add(GenericalMQTTOutputMessage.class);
	}

	public MQTTConfigurableMessageTypes() {

	}

	@Override
	public List<Class<? extends AbstractOI40IOTMetaMessage>> getConfigurableTypes() {

		return MESSAGETYPES;
	}

}
