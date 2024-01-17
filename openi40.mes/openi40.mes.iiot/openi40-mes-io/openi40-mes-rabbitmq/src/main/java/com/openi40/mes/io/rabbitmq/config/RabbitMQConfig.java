package com.openi40.mes.io.rabbitmq.config;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;
import com.openi40.mes.io.rabbitmq.input.GenericalRabbitMQInputReceiver;
import com.openi40.mes.io.rabbitmq.input.IAssetsInfoProvider;

@Configuration
public class RabbitMQConfig {
	static Logger LOGGER=LoggerFactory.getLogger(RabbitMQConfig.class);
	IAssetsInfoProvider assetsInfoProvider = null;
	BeanFactory beanFactory = null;

	public RabbitMQConfig(@Autowired IAssetsInfoProvider assetsInfoProvider, @Autowired BeanFactory beanFactory) {
		this.assetsInfoProvider = assetsInfoProvider;
		this.beanFactory = beanFactory;
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer(final ConnectionFactory connectionFactory) {
		LOGGER.info("Start configuring RabbitMQ queues listerer");
		final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		String[] queueNames = {};
		Map<String, ConfiguredEndpointInfo> readAssetsMap = assetsInfoProvider.getReadAssetsMap();
		queueNames = new String[readAssetsMap.size()];
		Set<String> keys = readAssetsMap.keySet();
		LOGGER.info("Listening to queues:"+keys);
		queueNames = keys.toArray(new String[0]);
		container.setQueueNames(queueNames);
		container.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				GenericalRabbitMQInputReceiver receiver = beanFactory.getBean(GenericalRabbitMQInputReceiver.class);
				receiver.onMessage(message);
			}
		});
		LOGGER.info("End configuring RabbitMQ queues listerer");
		return container;
	}
}
