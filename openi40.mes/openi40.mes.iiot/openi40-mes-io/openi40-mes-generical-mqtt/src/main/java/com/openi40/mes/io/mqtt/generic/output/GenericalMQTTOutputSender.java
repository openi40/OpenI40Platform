package com.openi40.mes.io.mqtt.generic.output;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openi40.mes.io.mqtt.generic.manager.MqttGenericManager;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Service
@Qualifier(value = MessageReceiver.IOT_APPLICATION_RECEIVER)
public class GenericalMQTTOutputSender implements MessageReceiver<AbstractOI40IOTApplicationMessage> {
	static Logger LOGGER = LoggerFactory.getLogger(GenericalMQTTOutputSender.class);
	@Autowired
	MqttGenericManager mqttGenericManager;

	public GenericalMQTTOutputSender() {

	}

	@Override
	public void onMessage(AbstractOI40IOTApplicationMessage msg, MessagingEnvironment environment) {
		if (isCanManage(msg)) {
			GenericalMQTTOutputMessage message = (GenericalMQTTOutputMessage) msg;
			try {
				mqttGenericManager.publish(message);
			} catch (Throwable e) {
				LOGGER.error("Error sending", e);
			}
		}
	}

	@Override
	public boolean isCanManage(AbstractOI40IOTApplicationMessage msg) {

		return msg != null && msg.getClass().equals(GenericalMQTTOutputMessage.class);
	}

	static ManagedMessageType[] handled = new ManagedMessageType[] {
			new ManagedMessageType(GenericalMQTTOutputMessage.class) };

	@Override
	public String getLayerId() {

		return "application";
	}

	@Override
	public String getHandlerId() {

		return "openi40::mes::application::generical-mqtt-sender";
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return handled;
	}

}
