package com.openi40.mes.integration.ifaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.integration.config.IntegrationConfig;
import com.openi40.mes.integration.config.ManualChannelConfig;

@Singleton
@Service
public class ManuallyConfiguredIntegrationHandlerImpl implements IntegrationHandler {
	public static final String HANDLER_ID = "OPENI40::MES::MANUALLY-CONFIGURED-DEVICES";
	IntegrationConfig config = null;

	public ManuallyConfiguredIntegrationHandlerImpl(@Autowired(required = false) IntegrationConfig c) {
		this.config = c;
	}

	@Override
	public String getIntegrationHandlerId() {
		return HANDLER_ID;
	}

	@Override
	public boolean hasChannel(String channelId) throws IntegrationHandlerException {
		return config.getChannels().stream()
				.filter((cc) -> cc.getChannelId() != null && channelId != null && cc.getChannelId().equals(channelId))
				.findAny().isPresent();
	}

	@Override
	public boolean isOnline(String channelId) throws IntegrationHandlerException {

		return this.hasChannel(channelId);
	}

	@Override
	public boolean tryReconnect(String channelId) throws IntegrationHandlerException {
		return true;
	}

	@Override
	public List<IntegrationEndpointInfo> getEndPoints(String channelId) throws IntegrationHandlerException {
		if (config == null)
			return new ArrayList<IntegrationEndpointInfo>();
		List<IntegrationEndpointInfo> endpoints = new ArrayList<IntegrationEndpointInfo>();
		Optional<ManualChannelConfig> found = config.getChannels().stream()
				.filter((cc) -> cc.getChannelId() != null && channelId != null && cc.getChannelId().equals(channelId))
				.findFirst();
		return found.isPresent() ? new ArrayList<IntegrationEndpointInfo>(found.get().getEndpoints())
				: new ArrayList<IntegrationEndpointInfo>();
	}

}
