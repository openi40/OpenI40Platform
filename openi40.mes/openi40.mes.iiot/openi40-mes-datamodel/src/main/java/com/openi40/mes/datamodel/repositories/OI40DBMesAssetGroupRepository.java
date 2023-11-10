package com.openi40.mes.datamodel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openi40.mes.datamodel.OI40DBMesAsset;
import com.openi40.mes.datamodel.OI40DBMesAssetGroup;

@Repository
public interface OI40DBMesAssetGroupRepository extends OI40MesBaseRepository<OI40DBMesAssetGroup> {

	public List<OI40DBMesAssetGroup> findByParentObjectTypeEqualsAndParentObjectCodeEquals(String parentObjectType,
			String parentObjectCode);
	
}
