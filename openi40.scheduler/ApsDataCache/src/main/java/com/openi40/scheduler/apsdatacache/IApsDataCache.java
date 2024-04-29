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
 *
 * + Holds ApsData in cache live with shared references with algorithms
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public interface IApsDataCache {
	/*********************************************************************
	 * Retrieves the list of actually cached entries
	 * 
	 * @return
	 */
	public List<DataSetEntry> getCachedEntryList();

	/*********************************************************************
	 * Retrieves the list of actually staged entries
	 * 
	 * @return
	 */
	public List<DataSetEntry> getStagedEntryList();

	/**********************************************************************
	 * Retrieves an already present ApsData entry
	 * 
	 * @param dataSetName
	 * @param dataSetVariant
	 * @return
	 */
	public ApsData getCachedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	/***************************************************************************
	 * Adds a cached entry
	 * 
	 * @param dataSetName
	 * @param dataSetVariant
	 * @param data
	 */
	public void addCachedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException;

	/****************************************************************************
	 * Removes a cached entry
	 * 
	 * @param dataSetName
	 * @param dataSetVariant
	 * @return
	 */
	public ApsData removeCachedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	/*****************************************************************************
	 * Updates a cached entry
	 * 
	 * @param dataSetName
	 * @param dataSetVariant
	 * @param data
	 */
	public void updateCachedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException;

	public void addStagedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException;

	public ApsData getStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	public void publishStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	public ApsData removeStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	public String getDataSourceName();

	public boolean isReloadable(String dataSetName, String dataSetVariant);

	public boolean isCanSave(String dataSetName, String dataSetVariant);

	public ApsData reload(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	public void save(String dataSetName, String dataSetVariant) throws ApsDataCacheException;

	public void setDataSource(IApsDataSource dataSource);

	public IApsDataSource getDataSource();

	public boolean isCacheDisabled();

	public void setCacheDisabled(boolean d);
}
