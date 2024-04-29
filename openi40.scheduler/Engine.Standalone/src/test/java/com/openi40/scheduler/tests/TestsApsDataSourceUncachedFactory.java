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
package com.openi40.scheduler.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.inputchannels.dataimporters.IConfiguredApsDataSourcesRepository;
import com.openi40.scheduler.model.aps.ApsData;

@Service
public class TestsApsDataSourceUncachedFactory {
	@Autowired  IConfiguredApsDataSourcesRepository repository;
	
	public TestsApsDataSourceUncachedFactory() {
		
	}

	public ApsData loadData(String dataSourceName, String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		return repository.loadData(dataSourceName, dataSetName, dataSetVariant);
	}
}
