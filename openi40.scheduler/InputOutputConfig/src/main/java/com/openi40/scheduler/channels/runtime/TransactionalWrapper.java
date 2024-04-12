package com.openi40.scheduler.channels.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.customsupport.ICustomObjectSupportHandler;
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

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 *         class TransactionalWrapper is service used to wrap eventual db
 *         operations with transaction support
 */
@Service
public class TransactionalWrapper {
	@Autowired
	IContextualBusinessLogicFactory businessFactory;
	static Logger LOGGER = LoggerFactory.getLogger(TransactionalWrapper.class);

	@Transactional(readOnly = true)
	public ApsData loadDataSet(String dataSourceName, String dataSetId, String variant,
			IDataImporterAgent dataImporterAgent, IInputDataStreamFactory streamFactory,
			IDataImporterFactoryRepository diFactoryRepository) {
		ApsData data = new ApsData();
		data.setDataSourceName(dataSourceName);
		data.setDataSetName(dataSetId);
		data.setDataSetVariant(variant);
		ICustomObjectSupportHandler customObjectsHandler = businessFactory.create(ICustomObjectSupportHandler.class,
				data, data);
		if (customObjectsHandler.isEntityWithCustomObjectSupport(ApsData.class)) {
			customObjectsHandler.initializeEntry(ApsData.class, data);
		}
		try {
			dataImporterAgent.doSync(data, streamFactory, diFactoryRepository);
		} catch (DataModelDaoException | MapperException | InputDataStreamException e) {
			throw new OpenI40Exception("Error loading dataSource", e);
		}
		return data;
	}

	@Transactional
	public void saveDataSet(IDataExporterAgent dataExporterAgent, IOutputDataConsumerFactory outputDataConsumerFactory,
			IDataExporterFactoryRepository deFactoryRepository, ApsData data) {
		try {
			dataExporterAgent.doSync(data, outputDataConsumerFactory, deFactoryRepository);
		} catch (DataModelDaoException | MapperException | OutputDataStreamException e) {
			throw new OpenI40Exception("error saving", e);
		}
	}
}
