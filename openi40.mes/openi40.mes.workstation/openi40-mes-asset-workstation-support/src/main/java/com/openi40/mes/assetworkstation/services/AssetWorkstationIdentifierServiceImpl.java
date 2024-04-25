package com.openi40.mes.assetworkstation.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.shared.model.OI40DBMesAsset;
import com.openi40.mes.shared.model.OI40DBMesAssetGroup;
import com.openi40.mes.shared.repositories.OI40DBMesAssetGroupRepository;
import com.openi40.mes.shared.repositories.OI40DBMesAssetRepository;

@Service
public class AssetWorkstationIdentifierServiceImpl implements IAssetWorkstationIdentifierService {
	@Autowired
	OI40DBMesAssetRepository assetRepository;
	@Autowired
	OI40DBMesAssetGroupRepository assetGroupRepository;

	public AssetWorkstationIdentifierServiceImpl() {

	}

	@Override
	public List<AssetWorkstationIdentifier> getWorkstationIdentifiersByContext(String parentObjectType,
			String parentObjectCode) {
		List<AssetWorkstationIdentifier> outvals = new ArrayList<AssetWorkstationIdentifier>();
		List<OI40DBMesAssetGroup> groups = assetGroupRepository
				.findByParentObjectTypeEqualsAndParentObjectCodeEquals(parentObjectType, parentObjectCode);
		List<String> assetGroups = new ArrayList<String>();
		for (OI40DBMesAssetGroup group : groups) {
			List<OI40DBMesAsset> assets = this.assetRepository
					.findByMesAssetGroupCodeEqualsAndMesAssetTypeCodeEquals(group.getCode(), "WORKSTATION");
			for (OI40DBMesAsset asset : assets) {
				outvals.add(AssetWorkstationIdentifier.from(group, asset));
			}
		}
		return outvals;
	}

	@Override
	public AssetWorkstationIdentifier getWorkstationIdentifier(String ipAddress) {
		AssetWorkstationIdentifier outValue = null;
		List<OI40DBMesAsset> assets = assetRepository.findByIpAddressEqualsAndMesAssetTypeCodeEquals(ipAddress,
				"WORKSTATION");
		if (assets.size() > 1)
			throw new RuntimeException("" + assets.size() + " workstations configured for the IP:" + ipAddress);
		if (!assets.isEmpty()) {
			OI40DBMesAsset asset = assets.get(0);
			outValue = AssetWorkstationIdentifier.from(assetGroupRepository.getOne(asset.getMesAssetGroupCode()),
					asset);

		}
		return outValue;
	}

	@Override
	public AssetWorkstationIdentifier save(AssetWorkstationIdentifier identifier) {
		
		return null;
	}

	@Override
	public void delete(AssetWorkstationIdentifier identifier) {
		this.assetRepository.deleteById(identifier.getAssetCode());
	}

	@Override
	public boolean isValidWorkstation(AssetWorkstationIdentifier identifier) {
		Optional<OI40DBMesAsset> asset = this.assetRepository.findById(identifier.getAssetCode());
		Optional<OI40DBMesAssetGroup> group = this.assetGroupRepository.findById(identifier.getAssetGroupCode());
		
		return asset.isPresent() && group.isPresent() && asset.get().getMesAssetGroupCode().equals(identifier.getAssetGroupCode());
	}

}
