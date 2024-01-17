package com.openi40.mes.io.rabbitmq.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;
import com.openi40.mes.integration.ifaces.IOpenI40IntegratedEndpointsRetriever;
import com.openi40.mes.integration.ifaces.IntegrationHandlerException;
import com.openi40.mes.integration.ifaces.IntegrationProtocolTypes;
import com.openi40.mes.io.rabbitmq.config.GenericRabbitMQChannelConfig;
import com.openi40.mes.io.rabbitmq.config.IntegratedChannelsConfig;

@Singleton
@Service
public class AssetsInfoProviderImpl implements IAssetsInfoProvider {
	static Logger LOGGER = LoggerFactory.getLogger(AssetsInfoProviderImpl.class);
	GenericRabbitMQChannelConfig channelsConfig = null;
	IOpenI40IntegratedEndpointsRetriever configuredEndpointsRetriever = null;
	Map<String, ConfiguredEndpointInfo> readmap = new HashMap<String, ConfiguredEndpointInfo>();
	Map<String, ConfiguredEndpointInfo> writemap = new HashMap<String, ConfiguredEndpointInfo>();

	public AssetsInfoProviderImpl(@Autowired(required = false) GenericRabbitMQChannelConfig channelsConfig,
			@Autowired(required = false) IOpenI40IntegratedEndpointsRetriever configuredEndpointsRetriever) {
		this.channelsConfig = channelsConfig;
		this.configuredEndpointsRetriever = configuredEndpointsRetriever;
	}

	private synchronized void refreshMaps() {
		Map<String, ConfiguredEndpointInfo> rmap = new HashMap<String, ConfiguredEndpointInfo>();
		Map<String, ConfiguredEndpointInfo> wmap = new HashMap<String, ConfiguredEndpointInfo>();
		if (channelsConfig != null && channelsConfig.getChannels() != null) {
			for (IntegratedChannelsConfig channel : channelsConfig.getChannels()) {
				if (configuredEndpointsRetriever != null) {
					try {
						List<ConfiguredEndpointInfo> configured = configuredEndpointsRetriever
								.getConfiguredEndpoints(channel.getIntegrationHandlerId(), channel.getChannelId());
						if (configured != null) {
							for (ConfiguredEndpointInfo configuredEndpointInfo : configured) {
								// IntegrationProtocolTypes.
								if (configuredEndpointInfo.getEndPointInfo().getProtocolType() != null
										&& (configuredEndpointInfo.getEndPointInfo().getProtocolType()
												.equalsIgnoreCase(IntegrationProtocolTypes.RABBITMQ)
												|| configuredEndpointInfo.getEndPointInfo().getProtocolType()
														.equalsIgnoreCase(IntegrationProtocolTypes.AMQP))) {
									if (configuredEndpointInfo.getEndPointInfo().getReadUri() != null)
										rmap.put(configuredEndpointInfo.getEndPointInfo().getReadUri(),
												configuredEndpointInfo);
									if (configuredEndpointInfo.getEndPointInfo().getWriteUri() != null)
										wmap.put(configuredEndpointInfo.getEndPointInfo().getWriteUri(),
												configuredEndpointInfo);
								}
							}
						}
					} catch (IntegrationHandlerException e) {
						LOGGER.error("Error retrieving endpoints", e);
					}
				}
			}
		}
		this.writemap = wmap;
		this.readmap = rmap;
	}

	@Override
	public Map<String, ConfiguredEndpointInfo> getReadAssetsMap() {
		if (readmap == null || readmap.isEmpty()) {
			this.refreshMaps();
		}
		return readmap;
	}

	@Override
	public Map<String, ConfiguredEndpointInfo> getWriteAssetsMap() {
		if (writemap == null || writemap.isEmpty())
			this.refreshMaps();
		return writemap;
	}
}
