package com.openi40.mes.iiot.tests;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openi40.mes.io.mqtt.generic.input.GenericalMQTTInputMessage;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Component
@Qualifier(value = MessageReceiver.IOT_SYSTEM_RECEIVER)
public class MqttToSaveableMessageRelayReceiver implements OI40IOTMessageReceiver<AbstractOI40IOTMetaMessage> {

	public MqttToSaveableMessageRelayReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {

	}

	@Override
	public boolean isCanManage(AbstractOI40IOTMetaMessage msg) {

		return msg instanceof GenericalMQTTInputMessage;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[] { new ManagedMessageType(GenericalMQTTInputMessage.class) };
	}

	@Override
	public String getHandlerId() {

		return "openi40::mes::system::MqttToSaveableMessageRelayReceiver";
	}

	@Override
	public String getLayerId() {

		return "system";
	}
}
