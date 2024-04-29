package com.openi40.scheduler.apsdatacache;

import com.openi40.scheduler.model.aps.ApsData;
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
public interface IApsDataCacheListener {
	public void onCacheAdded(IApsDataCache cache, String dataSetName, String dataSetVariant, ApsData data);

	public void onCacheRemoved(IApsDataCache cache, String dataSetName, String dataSetVariant, ApsData data);

	public void onCacheUpdated(IApsDataCache cache, String dataSetName, String dataSetVariant, ApsData data);
}
