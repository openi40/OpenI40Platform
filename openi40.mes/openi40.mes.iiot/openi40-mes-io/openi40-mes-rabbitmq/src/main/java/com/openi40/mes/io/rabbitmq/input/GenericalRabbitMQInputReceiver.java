package com.openi40.mes.io.rabbitmq.input;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;

@Service
public class GenericalRabbitMQInputReceiver implements MessageListener {
	IMicroKernel microKernel = null;
	IAssetsInfoProvider assetsInfoProvider = null;

	public GenericalRabbitMQInputReceiver(IMicroKernel microKernel, IAssetsInfoProvider assetsInfoProvider) {
		this.microKernel = microKernel;
		this.assetsInfoProvider = assetsInfoProvider;
	}

	@Override
	public void onMessage(Message message) {
		Map<String, ConfiguredEndpointInfo> map = assetsInfoProvider.getReadAssetsMap();
		String topic = message.getMessageProperties().getConsumerQueue();
		ConfiguredEndpointInfo info = map.get(topic);
		String channelId = info != null ? info.getChannelId() : null;
		String integrationId = info != null ? info.getHandlerId() : null;
		String assetCode = info != null ? info.getAssetCode() : null;
		GenericalRabbitMQInputMessage inputMessage = new GenericalRabbitMQInputMessage();
		inputMessage.setChannelId(channelId);
		inputMessage.setIntegrationId(integrationId);
		inputMessage.setTopic(topic);
		byte buffer[] = message.getBody();
		if (buffer != null) {
			inputMessage.setPayload(buffer);
		}
		String messageId = message.getMessageProperties().getMessageId();
		inputMessage.setRabbitMessageId(messageId);
		inputMessage.setFrom("rabbitmq::" + channelId + "::" + integrationId + "::topic:" + topic);
		inputMessage.setTo(IMicroKernel.MICROKERNEL_ID);
		inputMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
		inputMessage.setAssetCode(assetCode);
		microKernel.onMessage(inputMessage, null);
	}

}
