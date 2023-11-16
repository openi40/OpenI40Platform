package com.openi40.mes.integration.ifaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.shared.model.OI40DBMesAsset;
import com.openi40.mes.shared.repositories.OI40DBMesAssetRepository;

import lombok.Data;

@Service
public class OpenI40IntegratedEndpointsRetrieverImpl implements IOpenI40IntegratedEndpointsRetriever {
	private IntegrationHandlersRepository integrationHandler = null;
	private OI40DBMesAssetRepository mesAssetRepository;
	static Logger LOGGER = LoggerFactory.getLogger(OpenI40IntegratedEndpointsRetrieverImpl.class);

	public OpenI40IntegratedEndpointsRetrieverImpl(@Autowired IntegrationHandlersRepository integrationHandler,
			@Autowired OI40DBMesAssetRepository mesAssetRepository) {
		this.integrationHandler = integrationHandler;
		this.mesAssetRepository = mesAssetRepository;
	}

	@Override
	public List<ConfiguredEndpointInfo> getConfiguredEndpoints(String integrationId, String channelId)
			throws IntegrationHandlerException {
		List<ConfiguredEndpointInfo> outlist = new ArrayList<ConfiguredEndpointInfo>();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getConfiguredEndpoints('" + integrationId + "','" + channelId + "')");
		}
		IntegrationHandler handler = integrationHandler.getIntegrationHandler(integrationId, channelId);
		if (handler != null) {
			List<IntegrationEndpointInfo> configured = handler.getEndPoints(channelId);
			outlist = elaborateConfigured(configured, integrationId, channelId, handler.getIntegrationHandlerId());
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End getConfiguredEndpoints('" + integrationId + "','" + channelId + "') returned "
					+ outlist.size() + " configured endpoints");
		}
		return outlist;
	}
	@Data
	static class AssetAssociation implements ConfiguredEndpointInfo {

		String channelId = null;
		String integrationId = null;
		String handlerId = null;
		String assetCode = null;
		IntegrationEndpointInfo endPointInfo = null;	

		

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
					a.endPointInfo = iei;
					foundMatching.add(a);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Found configured device:"+a.toString());
					}
				}
			});

		}
		return foundMatching;
	}

}
