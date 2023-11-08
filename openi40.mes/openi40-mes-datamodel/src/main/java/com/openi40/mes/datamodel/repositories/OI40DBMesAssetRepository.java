package com.openi40.mes.datamodel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openi40.mes.datamodel.OI40DBMesAsset;

@Repository
public interface OI40DBMesAssetRepository extends OI40MesBaseRepository<OI40DBMesAsset> {
	public List<OI40DBMesAsset> findByIpAddressEqualsAndMesAssetTypeCodeEquals(String ipAddress, String assetTypeCode);

	public List<OI40DBMesAsset> findByMesAssetGroupCodeEqualsAndMesAssetTypeCodeEquals(String code, String string);
}
