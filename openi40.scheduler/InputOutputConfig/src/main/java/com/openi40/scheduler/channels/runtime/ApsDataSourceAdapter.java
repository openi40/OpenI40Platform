/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.scheduler.channels.runtime;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.DataSetEntry;
import com.openi40.scheduler.apsdatacache.IApsDataSource;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterAgent;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.outputchannels.dataexporters.IDataExporterAgent;
import com.openi40.scheduler.outputchannels.dataexporters.IDataExporterFactoryRepository;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public class ApsDataSourceAdapter implements IApsDataSource {
	TransactionalWrapper transactionalWrapper=null;
	// Loading data components
	IDataImporterAgent dataImporterAgent = null;
	IInputDataStreamFactory streamFactory = null;
	IDataImporterFactoryRepository diFactoryRepository = null;

	// Saving data components
	IDataExporterAgent dataExporterAgent = null;
	IOutputDataConsumerFactory outputDataConsumerFactory = null;
	IDataExporterFactoryRepository deFactoryRepository = null;
	private boolean disableUserAccess = false;

	public ApsDataSourceAdapter(TransactionalWrapper transactionalWrapper,IDataImporterAgent dataImporterAgent, IInputDataStreamFactory streamFactory,
			IDataImporterFactoryRepository diFactoryRepository) {
		this.transactionalWrapper=transactionalWrapper;
		this.dataImporterAgent = dataImporterAgent;
		this.streamFactory = streamFactory;
		this.diFactoryRepository = diFactoryRepository;
	}

	public ApsDataSourceAdapter(TransactionalWrapper transactionalWrapper,IDataImporterAgent dataImporterAgent, IInputDataStreamFactory streamFactory,
			IDataImporterFactoryRepository diFactoryRepository, IDataExporterAgent dataExporterAgent,
			IOutputDataConsumerFactory outputDataConsumerFactory, IDataExporterFactoryRepository deFactoryRepository) {
		this.transactionalWrapper=transactionalWrapper;
		this.dataImporterAgent = dataImporterAgent;
		this.streamFactory = streamFactory;
		this.diFactoryRepository = diFactoryRepository;
		this.dataExporterAgent = dataExporterAgent;
		this.outputDataConsumerFactory = outputDataConsumerFactory;
		this.deFactoryRepository = deFactoryRepository;
	}

	@Override
	public String getDataSourceName() {

		return streamFactory.getDataSourceName();
	}

	@Override
	public List<DataSetEntry> getAvailableEntries() {
		List<DataSetEntry> entries = new ArrayList<DataSetEntry>();
		DataSetEntry entry = new DataSetEntry();
		entry.setDataSourceName(getDataSourceName());
		entry.setDataSetName(streamFactory.getDataSetName());
		entry.setDataSetVariant(streamFactory.getDataSetVariant());
		entry.setDescription(streamFactory.getDataSourceDescription());
		entry.setReloadable(isReloadable(entry.getDataSetName(), entry.getDataSetVariant()));
		entry.setSaveable(isSaveable(entry.getDataSetName(), entry.getDataSetVariant()));
		entries.add(entry);
		return entries;
	}

	@Override
	public ApsData loadDataSet(String dataSetId, String variant) throws ApsDataCacheException {
		/*ApsData data = new ApsData();
		data.setDataSourceName(getDataSourceName());
		data.setDataSetName(dataSetId);
		data.setDataSetVariant(variant);
		try {
			this.dataImporterAgent.doSync(data, streamFactory, diFactoryRepository);
		} catch (DataModelDaoException | MapperException | InputDataStreamException e) {
			throw new ApsDataCacheException("Error loading dataSource", e);
		}
		return data;*/
		return transactionalWrapper.loadDataSet(getDataSourceName(), dataSetId, variant, dataImporterAgent, streamFactory, diFactoryRepository);
	}

	@Override
	public void saveDataSet(ApsData data) throws ApsDataCacheException {
		if (dataExporterAgent == null)
			throw new IllegalStateException("dataExporterAgent not configured");
		if (outputDataConsumerFactory == null)
			throw new IllegalStateException("outputDataConsumerFactory not configured");
		if (deFactoryRepository == null)
			throw new IllegalStateException("deFactoryRepository not configured");
		if (data.getDataSourceName() != null && data.getDataSetName() != null && data.getDataSetVariant() != null
				&& outputDataConsumerFactory.getDataSourceName() != null
				&& outputDataConsumerFactory.getDataSetName() != null
				&& outputDataConsumerFactory.getDataSetVariant() != null
				&& data.getDataSourceName().equals(outputDataConsumerFactory.getDataSourceName())
				&& data.getDataSetName().equals(outputDataConsumerFactory.getDataSetName())
				&& data.getDataSetVariant().equals(outputDataConsumerFactory.getDataSetVariant())) {
			/*try {
				dataExporterAgent.doSync(data, outputDataConsumerFactory, deFactoryRepository);
			} catch (DataModelDaoException | MapperException | OutputDataStreamException e) {
				throw new ApsDataCacheException("error saving", e);
			}*/
			transactionalWrapper.saveDataSet(dataExporterAgent, outputDataConsumerFactory, deFactoryRepository, data);
		} else
			throw new ApsDataCacheException("No outputDataConsumerFactory matching this dataSource");
	}

	@Override
	public void refreshDataSet(ApsData data) throws ApsDataCacheException {

		try {
			this.dataImporterAgent.doSync(data, streamFactory, diFactoryRepository);
		} catch (DataModelDaoException | MapperException | InputDataStreamException e) {
			throw new ApsDataCacheException("Error loading dataSource", e);
		}

	}

	public boolean isDisableUserAccess() {
		return disableUserAccess;
	}

	public void setDisableUserAccess(boolean disableUserAccess) {
		this.disableUserAccess = disableUserAccess;
	}

	@Override
	public boolean isReloadable(String dataSetId, String variant) {

		return true;
	}

	@Override
	public boolean isSaveable(String dataSetId, String variant) {

		return true;
	}

	public IDataImporterAgent getDataImporterAgent() {
		return dataImporterAgent;
	}

	public void setDataImporterAgent(IDataImporterAgent dataImporterAgent) {
		this.dataImporterAgent = dataImporterAgent;
	}

	public IInputDataStreamFactory getStreamFactory() {
		return streamFactory;
	}

	public void setStreamFactory(IInputDataStreamFactory streamFactory) {
		this.streamFactory = streamFactory;
	}

	public IDataImporterFactoryRepository getDiFactoryRepository() {
		return diFactoryRepository;
	}

	public void setDiFactoryRepository(IDataImporterFactoryRepository diFactoryRepository) {
		this.diFactoryRepository = diFactoryRepository;
	}

	public IDataExporterAgent getDataExporterAgent() {
		return dataExporterAgent;
	}

	public void setDataExporterAgent(IDataExporterAgent dataExporterAgent) {
		this.dataExporterAgent = dataExporterAgent;
	}

	public IOutputDataConsumerFactory getOutputDataConsumerFactory() {
		return outputDataConsumerFactory;
	}

	public void setOutputDataConsumerFactory(IOutputDataConsumerFactory outputDataConsumerFactory) {
		this.outputDataConsumerFactory = outputDataConsumerFactory;
	}

	public IDataExporterFactoryRepository getDeFactoryRepository() {
		return deFactoryRepository;
	}

	public void setDeFactoryRepository(IDataExporterFactoryRepository deFactoryRepository) {
		this.deFactoryRepository = deFactoryRepository;
	}

}
