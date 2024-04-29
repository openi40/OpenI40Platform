package com.openi40.scheduler.apsdatacache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

public class ApsDataCacheAggregatorImpl implements IApsDataCacheAggregator {

	List<IApsDataCache> apsDataCaches = new ArrayList<IApsDataCache>();

	public ApsDataCacheAggregatorImpl(@Autowired(required = false) List<IApsDataCache> apsDataCaches) {
		this.apsDataCaches=new ArrayList<IApsDataCache>(apsDataCaches!=null?apsDataCaches:new ArrayList<IApsDataCache>());
	}

	@Override
	public List<IApsDataCache> getDataCaches() {

		return apsDataCaches;
	}

	@Override
	public IApsDataCache getDataCache(String dataSourceName) {
		List<IApsDataCache> outValue = new ArrayList<IApsDataCache>();
		apsDataCaches.forEach((x) -> {
			if (x != null && x.getDataSourceName() != null && x.getDataSourceName().equals(dataSourceName)
					&& outValue.isEmpty()) {
				outValue.add(x);
			}
		});
		return !outValue.isEmpty() ? outValue.get(0) : null;
	}

	@Override
	public void addApsDataCache(IApsDataCache dataCache) {
		if (getDataCache(dataCache.getDataSourceName()) == null) {
			this.apsDataCaches.add(dataCache);
		} else
			throw new IllegalStateException(
					"Data cache with name: " + dataCache.getDataSourceName() + " already exists");

	}

}
