package com.openi40.mes.io.mqtt.generic.output;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

import lombok.Data;
@Data
public class GenericalMQTTOutputMessage extends AbstractOI40IOTApplicationMessage {
	private byte payload[] = null;
	private String topic = null;
	private String channelId = null;
	private String integrationId = null;
	private int mqttMsgId=0;
	private int mqttQos=0;
	public GenericalMQTTOutputMessage() {
		
	}

}
