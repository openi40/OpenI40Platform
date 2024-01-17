package com.openi40.mes.io.rabbitmq.input;

import java.util.Map;

import com.openi40.mes.integration.ifaces.ConfiguredEndpointInfo;

public interface IAssetsInfoProvider {
	public Map<String, ConfiguredEndpointInfo> getReadAssetsMap();
	public Map<String, ConfiguredEndpointInfo> getWriteAssetsMap();
}
