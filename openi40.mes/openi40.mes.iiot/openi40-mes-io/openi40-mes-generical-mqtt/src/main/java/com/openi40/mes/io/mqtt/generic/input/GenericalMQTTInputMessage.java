package com.openi40.mes.io.mqtt.generic.input;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.VolatileMessageType;

import lombok.Data;

@Data
@VolatileMessageType
public class GenericalMQTTInputMessage extends AbstractOI40IOTMetaMessage {
	private byte payload[] = null;
	private String topic = null;
	private String channelId = null;
	private String integrationId = null;
	private String assetCode = null;
	private int mqttMsgId = 0;
	private int mqttQos = 0;

	public GenericalMQTTInputMessage() {

	}

}
