package com.openi40.scheduler.apsdatacache;

import java.util.List;

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
public interface IApsDataSource {
	public String getDataSourceName();

	public List<DataSetEntry> getAvailableEntries();

	public ApsData loadDataSet(String dataSetId, String variant) throws ApsDataCacheException;

	public void saveDataSet(ApsData data) throws ApsDataCacheException;

	public void refreshDataSet(ApsData data) throws ApsDataCacheException;

	public boolean isReloadable(String dataSetId, String variant);

	public boolean isSaveable(String dataSetId, String variant);

	public boolean isDisableUserAccess();

	public void setDisableUserAccess(boolean _v);
}
