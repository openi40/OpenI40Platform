package com.openi40.mes.io.mqtt.generic.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;
import com.openi40.mes.integration.ifaces.IntegrationHandlerException;
import com.openi40.mes.integration.ifaces.IntegrationProtocolTypes;
import com.openi40.mes.integration.ifaces.IOpenI40IntegratedEndpointsRetriever;
import com.openi40.mes.io.mqtt.generic.config.GenericMQTTChannelConfig;
import com.openi40.mes.io.mqtt.generic.config.IntegratedChannelsConfig;
import com.openi40.mes.io.mqtt.generic.config.MqttBrokerConfig;
import com.openi40.mes.io.mqtt.generic.config.MqttOptions;
import com.openi40.mes.io.mqtt.generic.input.GenericalMQTTInputReceiver;
import com.openi40.mes.io.mqtt.generic.output.GenericalMQTTOutputMessage;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;

@Singleton
@Service
public class MqttGenericManager {
	static Logger LOGGER = LoggerFactory.getLogger(MqttGenericManager.class);

	IOpenI40IntegratedEndpointsRetriever configuredEndpointsRetriever;
	BeanFactory beanFactory = null;
	GenericMQTTChannelConfig mqttConfigs;
	Map<String, IMqttToken> actualSubscribedTokens = new HashMap<String, IMqttToken>();
	Map<String, MqttClient> activeClients = new HashMap<String, MqttClient>();

	public MqttGenericManager(@Autowired(required = false) GenericMQTTChannelConfig mqttConfigs,
			@Autowired(required = false) IOpenI40IntegratedEndpointsRetriever configuredEndpointsRetriever,
			@Autowired BeanFactory beanFactory) {
		this.mqttConfigs = mqttConfigs;
		this.configuredEndpointsRetriever = configuredEndpointsRetriever;
		this.beanFactory = beanFactory;

	}

	public class WrappedReceiver implements MqttCallback {
		IntegratedChannelsConfig integratedChannelsConfig = null;
		GenericalMQTTInputReceiver receiver = null;

		public WrappedReceiver(IntegratedChannelsConfig i, List<ConfiguredEndpointInfo> endpoints)
				throws IntegrationHandlerException {
			integratedChannelsConfig = i;
			receiver = beanFactory.getBean(GenericalMQTTInputReceiver.class);
			receiver.setChannelId(integratedChannelsConfig.getChannelId());
			receiver.setIntegrationId(integratedChannelsConfig.getIntegrationHandlerId());

			if (endpoints != null) {
				for (ConfiguredEndpointInfo endPoint : endpoints) {

					if (endPoint.getEndPointInfo().getProtocolType().equalsIgnoreCase(IntegrationProtocolTypes.MQTT)
							&& endPoint.getEndPointInfo().isCanRead()) {
						receiver.getTopicToAssetCodeMap().put(endPoint.getEndPointInfo().getReadUri(),
								endPoint.getAssetCode());
					}
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
		String clientKey = getClientKey(message);
		MqttClient client = this.activeClients.get(clientKey);
		client.publish(message.getTopic(), message.getPayload(), message.getMqttQos(), true);
	}

	private boolean allChannelsStarted = false;
	private int nMqttClientsStarted = 0;

	public void configureConnections() {
		int mqttClientsStarted = 0;
		boolean _allChannelsStarted = true;
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
						List<ConfiguredEndpointInfo> endpoints = configuredEndpointsRetriever.getConfiguredEndpoints(
								integratedChannelsConfig.getIntegrationHandlerId(),
								integratedChannelsConfig.getChannelId());
						if (client == null || !client.isConnected()) {
							LOGGER.info("Try to connect to:" + brokerConfig.getBrokerUrl());
							client = new MqttClient(brokerConfig.getBrokerUrl(),
									"mqtt-kernel-" + UUID.randomUUID().toString());
							MqttConnectionOptions options = null;
							MqttOptions mqttoptions = brokerConfig.getOptions();
							if (mqttoptions != null) {
								options = new MqttConnectionOptions();
								// options.setHttpsHostnameVerificationEnabled(false);
								options.setAutomaticReconnect(true);
								if (mqttoptions.getUsername() != null)
									options.setUserName(mqttoptions.getUsername());
								if (mqttoptions.getPassword() != null)
									options.setPassword(mqttoptions.getPassword().getBytes());
								options.setConnectionTimeout(mqttoptions.getConnectionTimout());
								options.setKeepAliveInterval(mqttoptions.getKeepAliveInterval());
							}
							LOGGER.info("Setting receivers for: " + brokerConfig.getBrokerUrl());
							WrappedReceiver receiver = new WrappedReceiver(integratedChannelsConfig, endpoints);
							client.setCallback(receiver);

							LOGGER.info("Connectiong to: " + brokerConfig.getBrokerUrl() + ".....");
							if (options != null) {
								client.connect(options);
							} else {
								client.connect();
							}
							LOGGER.info("CONNECTED to: " + brokerConfig.getBrokerUrl() + "!");
							mqttClientsStarted++;
							_allChannelsStarted = _allChannelsStarted && true;

						}
						this.activeClients.put(clientKey, client);

						for (ConfiguredEndpointInfo endPoint : endpoints) {
							if (endPoint.getEndPointInfo().getProtocolType().equalsIgnoreCase(
									IntegrationProtocolTypes.MQTT) && endPoint.getEndPointInfo().isCanRead()) {
								try {
									String key = clientKey + "::" + integratedChannelsConfig.getChannelId() + "::"
											+ endPoint.getEndPointInfo().getReadUri();

									IMqttToken token = actualSubscribedTokens.get(key);

									if (token == null) {
										// TODO: CHECK INVALID TOKENS CONDITION TO RENEW
										if (LOGGER.isDebugEnabled()) {
											LOGGER.debug("MQTT=>subscribing topic:"
													+ endPoint.getEndPointInfo().getReadUri() + " for asset:"
													+ endPoint.getAssetCode() + " integration:"
													+ endPoint.getHandlerId() + " channel:" + endPoint.getChannelId());
										}
										token = client.subscribe(endPoint.getEndPointInfo().getReadUri(), qos);
									}
									actualSubscribedTokens.put(key, token);
								} catch (Throwable th) {
									LOGGER.error("Topic:" + endPoint.getEndPointInfo().getReadUri()
											+ " seems to be impossible to listen", th);
								}
							}
						}

					} catch (Throwable e) {
						_allChannelsStarted = false;
						LOGGER.error(
								"Error trying to get endpoints:" + integratedChannelsConfig.getIntegrationHandlerId()
										+ " " + integratedChannelsConfig.getChannelId(),
								e);
					}
				}
			}
		}
		this.allChannelsStarted = _allChannelsStarted;
		this.nMqttClientsStarted = mqttClientsStarted;
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

	@EventListener(value = org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void initialize(org.springframework.boot.context.event.ApplicationReadyEvent ready) {
		if (mqttConfigs != null && !mqttConfigs.isAvoidInitializeOnStartup()) {
			LOGGER.info("Begin mqtt connections initialization");
			this.configureConnections();
			LOGGER.info("End mqtt connections initialization");
		}
	}

	public boolean isAllChannelsStarted() {
		return allChannelsStarted;
	}

	public int getNMqttClientsStarted() {
		return nMqttClientsStarted;
	}

}
