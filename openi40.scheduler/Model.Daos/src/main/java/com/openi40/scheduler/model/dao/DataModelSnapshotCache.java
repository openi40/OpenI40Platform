package com.openi40.scheduler.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openi40.scheduler.common.aps.IApsObject;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public class DataModelSnapshotCache<ApsType extends IApsObject> {
	List<ApsType> list = null;
	Map<String, ApsType> byId = new HashMap<>();
	Map<String, ApsType> byCode = new HashMap<>();

	public DataModelSnapshotCache(List<ApsType> list) {
		this.list = new ArrayList<ApsType>(list);
		for (ApsType apsType : list) {
			byId.put(apsType.getId(), apsType);
			byCode.put(apsType.getCode(), apsType);
		}
	}

	public ApsType findById(String id) {
		return byId.get(id);
	}

	public ApsType findByCode(String code) {
		return byCode.get(code);
	}

	public List<ApsType> getList() {
		return list;
	}

	public void add(ApsType apsObj) {
		list.add(apsObj);
		byId.put(apsObj.getId(), apsObj);
		byCode.put(apsObj.getCode(), apsObj);
	}

}
