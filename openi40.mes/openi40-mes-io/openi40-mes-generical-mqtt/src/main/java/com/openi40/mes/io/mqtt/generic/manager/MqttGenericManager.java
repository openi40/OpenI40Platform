package com.openi40.mes.io.mqtt.generic.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttPersistenceException;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;
import com.openi40.mes.integration.ifaces.IntegrationHandlerException;
import com.openi40.mes.integration.ifaces.IntegrationProtocolTypes;
import com.openi40.mes.integration.ifaces.OpenI40IntegratedEndpointsRetriever;
import com.openi40.mes.io.mqtt.generic.config.GenericMQTTChannelConfig;
import com.openi40.mes.io.mqtt.generic.config.IntegratedChannelsConfig;
import com.openi40.mes.io.mqtt.generic.config.MqttBrokerConfig;
import com.openi40.mes.io.mqtt.generic.input.GenericalMQTTInputReceiver;
import com.openi40.mes.io.mqtt.generic.output.GenericalMQTTOutputMessage;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;

@Singleton
@Service
public class MqttGenericManager implements ApplicationContextAware {
	static Logger LOGGER = LoggerFactory.getLogger(MqttGenericManager.class);
	ApplicationContext applicationContext = null;
	@Autowired(required = false)
	OpenI40IntegratedEndpointsRetriever configuredEndpointsRetriever;
	@Autowired(required = false)
	GenericMQTTChannelConfig mqttConfigs;
	Map<String, IMqttToken> actualSubscribedTokens = new HashMap<String, IMqttToken>();
	Map<String, MqttClient> activeClients = new HashMap<String, MqttClient>();

	public MqttGenericManager() {

	}

	public class WrappedReceiver implements MqttCallback {
		IntegratedChannelsConfig integratedChannelsConfig = null;
		GenericalMQTTInputReceiver receiver = null;

		public WrappedReceiver(IntegratedChannelsConfig i) throws IntegrationHandlerException {
			integratedChannelsConfig = i;
			receiver = applicationContext.getBean(GenericalMQTTInputReceiver.class);
			receiver.setChannelId(integratedChannelsConfig.getChannelId());
			receiver.setIntegrationId(integratedChannelsConfig.getIntegrationHandlerId());
			List<ConfiguredEndpointInfo> endpoints = MqttGenericManager.this.configuredEndpointsRetriever
					.getConfiguredEndpoints(integratedChannelsConfig.getIntegrationHandlerId(),
							integratedChannelsConfig.getChannelId());
			for (ConfiguredEndpointInfo endPoint : endpoints) {
				if (endPoint.getEndPointInfo().getProtocolType().equalsIgnoreCase(IntegrationProtocolTypes.MQTT)
						&& endPoint.getEndPointInfo().isCanRead()) {
					receiver.getTopicToAssetCodeMap().put(endPoint.getEndPointInfo().getUri(), endPoint.getAssetCode());
				}
			}
		}

		@Override
		public void disconnected(MqttDisconnectResponse disconnectResponse) {
			MqttGenericManager.this.manageDisconnection(integratedChannelsConfig, disconnectResponse);

		}

		@Override
		public void mqttErrorOccurred(MqttException exception) {
			MqttGenericManager.this.manageMqttError(integratedChannelsConfig, exception);

		}

		@Override
		public void messageArrived(String topic, MqttMessage message) throws Exception {
			receiver.messageArrived(topic, message);

		}

		@Override
		public void deliveryComplete(IMqttToken token) {
			MqttGenericManager.this.manageDeliveryComplete(integratedChannelsConfig, token);

		}

		@Override
		public void connectComplete(boolean reconnect, String serverURI) {
			// TODO Auto-generated method stub

		}

		@Override
		public void authPacketArrived(int reasonCode, MqttProperties properties) {
			// TODO Auto-generated method stub

		}

	}

	public void publish(GenericalMQTTOutputMessage message) throws MqttPersistenceException, MqttException {
		String clientKey=getClientKey(message);
		MqttClient client = this.activeClients.get(clientKey);
		client.publish(message.getTopic() , message.getPayload(),message.getMqttQos(),true);
	}

	public void checkConfiguration() {
		if (configuredEndpointsRetriever == null) {
			LOGGER.warn("No configuredEndpointsRetriever injected");
		}
		if (mqttConfigs == null) {
			LOGGER.warn("No mqttConfigs injected");
		}
		if (configuredEndpointsRetriever != null && mqttConfigs != null) {
			List<IntegratedChannelsConfig> integrations = mqttConfigs.getIntegrations();
			if (integrations != null) {
				for (IntegratedChannelsConfig integratedChannelsConfig : integrations) {
					try {
						String clientKey = getClientKey(integratedChannelsConfig);
						MqttBrokerConfig brokerConfig = integratedChannelsConfig.getBrokerConfig();
						int qos = 1;
						MqttClient client = this.activeClients.get(clientKey);
						if (client != null && !client.isConnected()) {
							try {
								client.disconnect();
							} catch (Throwable th) {

							} finally {
								client = null;
							}
						}
						if (client == null || !client.isConnected()) {
							client = new MqttClient(brokerConfig.getBrokerUrl(), IMicroKernel.MICROKERNEL_ID,
									new MemoryPersistence());
							MqttConnectionOptions options = new MqttConnectionOptions();
							options.setUserName(brokerConfig.getUsername());
							options.setPassword(brokerConfig.getPassword().getBytes());
							options.setConnectionTimeout(60);
							options.setKeepAliveInterval(60);

							WrappedReceiver receiver = new WrappedReceiver(integratedChannelsConfig);

							client.setCallback(receiver);
							client.connect(options);
						}
						this.activeClients.put(clientKey, client);
						List<ConfiguredEndpointInfo> endpoints = configuredEndpointsRetriever.getConfiguredEndpoints(
								integratedChannelsConfig.getIntegrationHandlerId(),
								integratedChannelsConfig.getChannelId());
						for (ConfiguredEndpointInfo endPoint : endpoints) {
							if (endPoint.getEndPointInfo().getProtocolType().equalsIgnoreCase(
									IntegrationProtocolTypes.MQTT) && endPoint.getEndPointInfo().isCanRead()) {
								try {
									String key = clientKey + "::" + integratedChannelsConfig.getChannelId() + "::"
											+ endPoint.getEndPointInfo().getUri();

									IMqttToken token = actualSubscribedTokens.get(key);

									if (token == null) {
										// TODO: CHECK INVALID TOKENS CONDITION TO RENEW
										token = client.subscribe(endPoint.getEndPointInfo().getUri(), qos);
									}
									actualSubscribedTokens.put(key, token);
								} catch (Throwable th) {
									LOGGER.error("Topic:" + endPoint.getEndPointInfo().getUri()
											+ " seems to be impossible to listen", th);
								}
							}
						}

					} catch (IntegrationHandlerException | MqttException e) {
						LOGGER.error(
								"Error trying to get endpoints:" + integratedChannelsConfig.getIntegrationHandlerId()
										+ " " + integratedChannelsConfig.getChannelId(),
								e);
					}
				}
			}
		}
	}

	public void manageDeliveryComplete(IntegratedChannelsConfig integratedChannelsConfig, IMqttToken token) {
		// TODO Auto-generated method stub

	}

	public void manageMqttError(IntegratedChannelsConfig integratedChannelsConfig, MqttException exception) {
		// TODO Auto-generated method stub

	}

	public void manageDisconnection(IntegratedChannelsConfig integratedChannelsConfig,
			MqttDisconnectResponse disconnectResponse) {
		// TODO Auto-generated method stub

	}

	private String getClientKey(GenericalMQTTOutputMessage msg) {
		return "openi40::" + msg.getIntegrationId() + "::" + msg.getChannelId();
	}

	private String getClientKey(IntegratedChannelsConfig integratedChannelsConfig) {
		return "openi40::" + integratedChannelsConfig.getIntegrationHandlerId() + "::"
				+ integratedChannelsConfig.getChannelId();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
