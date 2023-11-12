package com.openi40.mes.integration.ifaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.shared.model.OI40DBMesAsset;
import com.openi40.mes.shared.repositories.OI40DBMesAssetRepository;

@Service
public class OpenI40IntegratedEndpointsRetrieverImpl implements IOpenI40IntegratedEndpointsRetriever {
	private IntegrationHandlersRepository integrationHandler = null;
	private OI40DBMesAssetRepository mesAssetRepository;

	private static ConfiguredEndpointInfo transcodeConfiguredEndpoint(AssetAssociation a) {
		return null;
	}

	public OpenI40IntegratedEndpointsRetrieverImpl(@Autowired IntegrationHandlersRepository integrationHandler,
			@Autowired OI40DBMesAssetRepository mesAssetRepository) {
		this.integrationHandler = integrationHandler;
		this.mesAssetRepository = mesAssetRepository;
	}

	@Override
	public List<ConfiguredEndpointInfo> getConfiguredEndpoints(String integrationId, String channelId)
			throws IntegrationHandlerException {
		IntegrationHandler handler = integrationHandler.getIntegrationHandler(integrationId, channelId);
		List<IntegrationEndpointInfo> configured = handler.getEndPoints(channelId);
		return elaborateConfigured(configured, integrationId, channelId, handler.getIntegrationHandlerId());
	}

	static class AssetAssociation implements ConfiguredEndpointInfo {

		String channelId = null;
		String integrationId = null;
		String handlerId = null;
		String assetCode = null;
		IntegrationEndpointInfo endpoint = null;

		@Override
		public String getAssetCode() {

			return assetCode;
		}

		@Override
		public String getChannelId() {

			return channelId;
		}

		@Override
		public String getHandlerId() {

			return handlerId;
		}

		@Override
		public IntegrationEndpointInfo getEndPointInfo() {

			return endpoint;
		}

	}

	private List<ConfiguredEndpointInfo> elaborateConfigured(List<IntegrationEndpointInfo> configured,
			String integrationId, String channelId, String handlerId) {
		List<OI40DBMesAsset> assets = this.mesAssetRepository.findByIntegrationIdEquals(integrationId);
		final List<ConfiguredEndpointInfo> foundMatching = new ArrayList<ConfiguredEndpointInfo>();
		for (IntegrationEndpointInfo iei : configured) {

			boolean hasSetAttributes = false;
			assets.forEach(asset -> {
				boolean matches = asset.getRemoved() == null || !asset.getRemoved();
				matches = matches && asset.getIntegrationId() != null && integrationId != null
						&& asset.getIntegrationId().equals(integrationId);
				matches = matches && asset.getProtocolType() != null && iei.getProtocolType() != null
						&& asset.getProtocolType().equals(iei.getProtocolType());
				if (iei.isCanRead()) {
					matches = matches && asset.getIntegrationReadUrl() != null && iei.getReadUri() != null
							&& asset.getIntegrationReadUrl().equals(iei.getReadUri());
				}
				if (iei.isCanWrite()) {
					matches = matches && asset.getIntegrationWriteUrl() != null && iei.getWriteUri() != null
							&& asset.getIntegrationWriteUrl().equals(iei.getWriteUri());
				}
				if (matches) {
					AssetAssociation a = new AssetAssociation();
					a.assetCode = asset.getCode();
					a.integrationId = integrationId;
					a.channelId = channelId;
					a.handlerId = handlerId;
					a.endpoint = iei;
					foundMatching.add(a);
				}
			});

		}
		return foundMatching;
	}

}
