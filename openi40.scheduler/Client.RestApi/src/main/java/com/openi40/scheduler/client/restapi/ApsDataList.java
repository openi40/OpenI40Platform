package com.openi40.scheduler.client.restapi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.DataSetEntry;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;

import io.swagger.annotations.Api;
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
@RestController
@RequestMapping("schedulerClient/ApsDataList")
@Api
public class ApsDataList {
	protected static Logger LOGGER = LoggerFactory.getLogger(ApsDataList.class);
	@Autowired
	IApsDataCacheAggregator apsDataCacheAggregator;

	public ApsDataList() {

	}

	@GetMapping("list")
	public ResponseEntity<List<DataSetEntry>> get() {
		List<DataSetEntry> entries = new ArrayList<DataSetEntry>();
		List<IApsDataCache> dataCaches = apsDataCacheAggregator.getDataCaches();
		for (IApsDataCache iApsDataCache : dataCaches) {
			entries.addAll(iApsDataCache.getCachedEntryList());
		}
		return ResponseEntity.ok().body(entries);
	}
}
