package com.openi40.scheduler.apsdatacache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ApsDataCacheImpl implements IApsDataCache {
	static Logger LOGGER = LoggerFactory.getLogger(ApsDataCacheImpl.class);
	TreeMap<String, TreeMap<String, ApsData>> cache = new TreeMap<String, TreeMap<String, ApsData>>();
	TreeMap<String, TreeMap<String, ApsData>> stageCache = new TreeMap<String, TreeMap<String, ApsData>>();

	boolean cacheDisabled = false;
	IApsDataSource dataSource = null;
	List<IApsDataCacheListener> cacheListeners = null;
	String dataSourceName = null;

	public ApsDataCacheImpl(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public ApsDataCacheImpl(IApsDataSource dataSource, List<IApsDataCacheListener> cacheListeners) {
		this.dataSource = dataSource;
		if (dataSource != null) {
			dataSourceName = dataSource.getDataSourceName();
		}
		this.cacheListeners = cacheListeners;
	}

	@Override
	public List<DataSetEntry> getCachedEntryList() {
		synchronized (this) {
			List<DataSetEntry> entries = new ArrayList<DataSetEntry>();
			if (!cacheDisabled) {
				for (Entry<String, TreeMap<String, ApsData>> entry : cache.entrySet()) {
					for (Entry<String, ApsData> variant : entry.getValue().entrySet()) {
						DataSetEntry _entry = new DataSetEntry();
						_entry.setDataSourceName(variant.getValue().getDataSourceName());
						_entry.setDataSetName(variant.getValue().getDataSetName());
						_entry.setDataSetVariant(variant.getValue().getDataSetVariant());
						entries.add(_entry);
					}
				}
			}
			if (dataSource != null) {
				List<DataSetEntry> _entries = dataSource.getAvailableEntries();
				for (DataSetEntry dataSetEntry : _entries) {
					List<DataSetEntry> matching = new ArrayList<DataSetEntry>();
					entries.forEach((x) -> {
						if (dataSetEntry.getDataSetName() != null && x.getDataSetName() != null
								&& dataSetEntry.getDataSetName().equals(x.getDataSetName())
								&& dataSetEntry.getDataSetVariant() != null && x.getDataSetVariant() != null
								&& dataSetEntry.getDataSetVariant().equals(x.getDataSetVariant())
								&& dataSetEntry.getDataSourceName() != null && x.getDataSourceName() != null
								&& dataSetEntry.getDataSourceName().equals(x.getDataSourceName())) {
							x.setReloadable(dataSource.isReloadable(dataSetEntry.getDataSetName(),
									dataSetEntry.getDataSetVariant()));
							x.setSaveable(dataSource.isSaveable(dataSetEntry.getDataSetName(),
									dataSetEntry.getDataSetVariant()));
							if (x.getDescription() == null || x.getDescription().trim().length() == 0) {
								x.setDescription(dataSetEntry.getDescription());
							} else if (dataSetEntry.getDescription() == null
									|| dataSetEntry.getDescription().trim().length() == 0) {
								dataSetEntry.setDescription(x.getDescription());
							}
							if (!matching.contains(dataSetEntry)) {
								matching.add(dataSetEntry);
							}
						}
					});
					if (matching.isEmpty()) {
						entries.add(dataSetEntry);
					}
				}
			}
			return entries;
		}

	}

	@Override
	public ApsData getCachedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		synchronized (this) {
			if (!cache.containsKey(dataSetName) || !cache.get(dataSetName).containsKey(dataSetVariant)
					|| cacheDisabled) {
				if (dataSource != null) {
					ApsData ds = dataSource.loadDataSet(dataSetName, dataSetVariant);
					addCachedData(dataSetName, dataSetVariant, ds);
				}
				if (!cache.containsKey(dataSetName) || !cache.get(dataSetName).containsKey(dataSetVariant))
					throw new ApsDataCacheException("Entry " + dataSetName + " variant " + dataSetVariant + " unknown");
			}
			return cache.get(dataSetName).get(dataSetVariant);
		}
	}

	@Override
	public void addCachedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			if (cache.containsKey(dataSetName) && cache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException(
						"Entry " + dataSetName + " variant " + dataSetVariant + " already exists!");
			if (!cache.containsKey(dataSetName))
				cache.put(dataSetName, new TreeMap<String, ApsData>());
			cache.get(dataSetName).put(dataSetVariant, data);
			if (cacheListeners != null) {
				for (IApsDataCacheListener listener : cacheListeners) {
					listener.onCacheAdded(this, dataSetName, dataSetVariant, data);
				}
			}
		}
	}

	@Override
	public ApsData removeCachedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			if (!cache.containsKey(dataSetName) || !cache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException("Entry " + dataSetName + " variant " + dataSetVariant + " unknown");
			ApsData data = cache.get(dataSetName).remove(dataSetVariant);
			if (cacheListeners != null) {
				for (IApsDataCacheListener listener : cacheListeners) {
					listener.onCacheRemoved(this, dataSetName, dataSetVariant, data);
				}
			}
			return data;
		}
	}

	@Override
	public void updateCachedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			if (!cache.containsKey(dataSetName) || !cache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException("Entry " + dataSetName + " variant " + dataSetVariant + " unknown");
			cache.get(dataSetName).put(dataSetVariant, data);
			if (cacheListeners != null) {
				for (IApsDataCacheListener listener : cacheListeners) {
					listener.onCacheUpdated(this, dataSetName, dataSetVariant, data);
				}
			}
		}
	}

	@Override
	public void addStagedData(String dataSetName, String dataSetVariant, ApsData data) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			data.setDataSetName(dataSetName);
			data.setDataSetVariant(dataSetVariant);
			if (stageCache.containsKey(dataSetName) && stageCache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException(
						"Entry " + dataSetName + " variant " + dataSetVariant + " already exists in staging cache!");
			if (!stageCache.containsKey(dataSetName))
				stageCache.put(dataSetName, new TreeMap<String, ApsData>());
			stageCache.get(dataSetName).put(dataSetVariant, data);
		}
	}

	@Override
	public ApsData getStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			if (!stageCache.containsKey(dataSetName) || !stageCache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException(
						"Entry " + dataSetName + " variant " + dataSetVariant + " does not exist in staging area!");
			return stageCache.get(dataSetName).get(dataSetVariant);
		}
	}

	@Override
	public void publishStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			ApsData stagedData = getStagedData(dataSetName, dataSetVariant);
			addCachedData(dataSetName, dataSetVariant, stagedData);
			removeStagedData(dataSetName, dataSetVariant);
		}
	}

	@Override
	public String getDataSourceName() {

		return dataSourceName;
	}

	@Override
	public boolean isReloadable(String dataSetName, String dataSetVariant) {
		return dataSource != null && dataSource.isReloadable(dataSetName, dataSetVariant);
	}

	@Override
	public ApsData reload(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		synchronized (this) {
			if (!isReloadable(dataSetName, dataSetVariant))
				throw new ApsDataCacheException("Called reload(" + dataSetName + "," + dataSetVariant
						+ ") with no coherent entry in dataSource");
			try {
				return this.dataSource.loadDataSet(dataSetName, dataSetVariant);
			} catch (Throwable th) {
				String msg = "Cannot reload datasource /" + getDataSourceName() + "/" + dataSetName + "/"
						+ dataSetVariant + "/";
				throw new ApsDataCacheException(msg, th);
			}
		}
	}

	@Override
	public ApsData removeStagedData(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		if (cacheDisabled)
			throw new ApsDataCacheException("dataSourceName" + getDataSourceName() + " dataSetName=" + dataSetName
					+ " dataSetVariant=" + dataSetVariant
					+ " ApsDataCache has disabled cache so you cannot add/remove cached data directly ");
		synchronized (this) {
			if (!stageCache.containsKey(dataSetName) || !stageCache.get(dataSetName).containsKey(dataSetVariant))
				throw new ApsDataCacheException(
						"Entry " + dataSetName + " variant " + dataSetVariant + " does not exist in staging area!");
			return stageCache.get(dataSetName).remove(dataSetVariant);
		}

	}

	@Override
	public List<DataSetEntry> getStagedEntryList() {
		synchronized (this) {
			List<DataSetEntry> entries = new ArrayList<DataSetEntry>();

			for (Entry<String, TreeMap<String, ApsData>> entry : stageCache.entrySet()) {
				for (Entry<String, ApsData> variant : entry.getValue().entrySet()) {
					DataSetEntry _entry = new DataSetEntry();
					_entry.setDataSetName(entry.getKey());
					_entry.setDataSetVariant(variant.getKey());
					_entry.setReloadable(isReloadable(_entry.getDataSetName(), _entry.getDataSetVariant()));
					_entry.setSaveable(isCanSave(_entry.getDataSetName(), _entry.getDataSetVariant()));
					entries.add(_entry);
				}
			}

			return entries;
		}
	}

	public IApsDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(IApsDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public boolean isCacheDisabled() {
		return cacheDisabled;
	}

	public void setCacheDisabled(boolean cacheDisabled) {
		this.cacheDisabled = cacheDisabled;
	}

	@Override
	public boolean isCanSave(String dataSetName, String dataSetVariant) {

		return (dataSource != null && dataSource.isSaveable(dataSetName, dataSetVariant));
	}

	@Override
	public void save(String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		if (dataSource!=null) {
			ApsData data = this.getCachedData(dataSetName, dataSetVariant);
			dataSource.saveDataSet(data);
		}
		
	}

}
