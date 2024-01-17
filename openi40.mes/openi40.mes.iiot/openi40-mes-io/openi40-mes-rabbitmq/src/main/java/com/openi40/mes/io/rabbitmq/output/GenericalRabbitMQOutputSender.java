package com.openi40.mes.io.rabbitmq.output;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Component
@Qualifier(value = MessageReceiver.IOT_APPLICATION_RECEIVER)
public class GenericalRabbitMQOutputSender implements OI40IOTMessageReceiver<AbstractOI40IOTApplicationMessage> {

	private RabbitTemplate template = null;

	public GenericalRabbitMQOutputSender(RabbitTemplate template) {
		this.template = template;
	}

	@Override
	public void onMessage(AbstractOI40IOTApplicationMessage msg, MessagingEnvironment environment) {
		GenericalRabbitMQOutputMessage message = (GenericalRabbitMQOutputMessage) msg;
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setAppId(getHandlerId());
		messageProperties.setConsumerQueue(message.getTopic());
		messageProperties.setCorrelationId(message.getCorrelationId());
		messageProperties.setContentType(message.getContentType());
		Message rabbitMessage = new Message(message.getPayload(), messageProperties);
		template.send(rabbitMessage);
	}

	@Override
	public boolean isCanManage(AbstractOI40IOTApplicationMessage msg) {

		return msg != null && msg instanceof GenericalRabbitMQOutputMessage;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[] { new ManagedMessageType(GenericalRabbitMQOutputMessage.class) };
	}

	@Override
	public String getLayerId() {

		return "application";
	}

	@Override
	public String getHandlerId() {

		return "openi40::mes::application::generical-rabbitmq-sender";
	}
}
