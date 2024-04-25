package com.openi40.mes.iiot.tests;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openi40.mes.io.mqtt.generic.input.GenericalMQTTInputMessage;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Component
@Qualifier(value = MessageReceiver.IOT_SYSTEM_RECEIVER)
public class MqttToSaveableMessageRelayReceiver implements OI40IOTMessageReceiver<AbstractOI40IOTMetaMessage> {
	
	public static class RelayMQTTContentMessage extends AbstractOI40IOTApplicationMessage {
		String relayedPayload = null;
		String channelId;
		String integrationId;
		public String getRelayedPayload() {
			return relayedPayload;
		}
		public void setRelayedPayload(String relayedPayload) {
			this.relayedPayload = relayedPayload;
		}
		public String getChannelId() {
			return channelId;
		}
		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}
		public String getIntegrationId() {
			return integrationId;
		}
		public void setIntegrationId(String integrationId) {
			this.integrationId = integrationId;
		}
	}

	public MqttToSaveableMessageRelayReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {
		if (msg instanceof GenericalMQTTInputMessage) {
			GenericalMQTTInputMessage im = (GenericalMQTTInputMessage) msg;
			String _msg = new String(im.getPayload());
			RelayMQTTContentMessage relay = new RelayMQTTContentMessage();
			relay.setRelayedPayload(_msg);
			relay.setAssetFrom(im.getAssetCode());
			relay.channelId = im.getChannelId();
			relay.integrationId = im.getIntegrationId();
			environment.getLoopbackSender().onMessage(relay, environment);
		}
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
