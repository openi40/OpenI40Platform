package com.openi40.scheduler.inputchannels.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.common.datamodel.IDataInputValidator;
import com.openi40.scheduler.common.datamodel.ValidationMessage;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterAgent;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterFactoryRepository;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.inputchannels.streaminputs.handlers.ApsInputDataStreamFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
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
@Profile(AbstractDataImporterController.RESTINPUTPROFILE)
@RestController
@RequestMapping(path = "/engine/dataSetManagement")
public class DataSetManagementController {
	@Autowired
	protected IApsDataCacheAggregator apsDataCacheAggregator = null;
	@Autowired
	protected IDataImporterAgent importerAgent;
	@Autowired
	protected IDataImporterFactoryRepository dataImporterFactoryRepository;
	@Autowired
	protected IDataInputValidator inputValidator;

	
	public static class DataSetOperationResult {
		boolean success = false;
		List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public List<ValidationMessage> getValidationMessages() {
			return validationMessages;
		}
		public void setValidationMessages(List<ValidationMessage> validationMessages) {
			this.validationMessages = validationMessages;
		}
	}

	@PostMapping("createStageDataSet/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public DataSetOperationResult createStageDataSet(String dataSourceName, String dataSetName, String dataSetVariant,
			@RequestBody ApsInputData apsInput)
			throws DataModelDaoException, MapperException, ApsDataCacheException, InputDataStreamException {
		List<ValidationMessage> validationMessages = inputValidator.validate(apsInput, ApsInputData.class,
				InputDto.class, (InputDto x) -> {
					return x.getCode();
				});
		DataSetOperationResult out = new DataSetOperationResult();
		if (validationMessages.isEmpty()) {
			ApsData data = new ApsData();
			data.setDataSourceName(dataSourceName);
			data.setDataSetName(dataSetName);
			data.setDataSetVariant(dataSetVariant);
			importerAgent.doSync(data, new ApsInputDataStreamFactory(apsInput), dataImporterFactoryRepository);
			IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
			apsDataCache.addStagedData(dataSetName, dataSetVariant, data);

			out.success = true;
			out.validationMessages.addAll(validationMessages);
		}
		return out;
	}

	@GetMapping("publish/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void publish(String dataSourceName, String dataSetName, String dataSetVariant) throws ApsDataCacheException {
		IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
		apsDataCache.publishStagedData(dataSetName, dataSetVariant);
	}

	@GetMapping("dropStageDataSet/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void dropStageDataSet(String dataSourceName, String dataSetName, String dataSetVariant)
			throws ApsDataCacheException {
		IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
		apsDataCache.removeStagedData(dataSetName, dataSetVariant);
	}

	@GetMapping("dropDataSet/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void dropDataSet(String dataSourceName, String dataSetName, String dataSetVariant)
			throws ApsDataCacheException {
		IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
		apsDataCache.removeCachedData(dataSetName, dataSetVariant);
	}

}
