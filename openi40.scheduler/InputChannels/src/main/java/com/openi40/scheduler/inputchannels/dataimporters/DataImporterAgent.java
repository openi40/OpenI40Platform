package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;
import com.openi40.scheduler.input.model.cycles.ChangeOverMatrixItemInputDto;
import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;
import com.openi40.scheduler.input.model.equipment.MachineInputDto;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;
import com.openi40.scheduler.input.model.material.ProductInputDto;
import com.openi40.scheduler.input.model.material.StockSupplyInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.time.ApsWindowInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.material.configuration.ProductiveCompanyProductSetting;
import com.openi40.scheduler.model.material.configuration.WarehouseProductSetting;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@Service
public class DataImporterAgent implements IDataImporterAgent {

	ILazyContextualBusinessLogicFactoryLoader lazyBusinessLogicFactory = null;
	IImportedClassListProvider classListProvider = null;

	public DataImporterAgent(@Autowired ILazyContextualBusinessLogicFactoryLoader lazyBusinessLogicFactory,
			@Autowired IImportedClassListProvider classListProvider) {
		this.lazyBusinessLogicFactory = lazyBusinessLogicFactory;
		this.classListProvider = classListProvider;
	}

	public void doSync(ApsData context, IInputDataStreamFactory streamFactory,
			IDataImporterFactoryRepository diFactoryRepository)
			throws DataModelDaoException, MapperException, InputDataStreamException {
		List<Class<? extends InputDto>> entitiesToSync = this.classListProvider.getClassesList();
		synchronized (context) {
			context.setRealtime(streamFactory.isRealtime());
			context.setProductionControlEnabled(streamFactory.isProductionControlEnabled());

			for (Class<? extends InputDto> entityClass : entitiesToSync) {
				Stream<? extends InputDto> stream = streamFactory.getStream(entityClass);
				IDataImporterFactory importerFactory = diFactoryRepository.getImporterFactory(entityClass);
				if (importerFactory == null)
					throw new InputDataStreamException(
							"Cannot retrieve IDataImporterFactory for class:" + entityClass.getName());
				IDataImporterConsumer importer = importerFactory.create(context);
				stream.forEach(importer);
				importer.endOfStream();
			}
			IApsDataManager apsDataManager = this.lazyBusinessLogicFactory.getComponentFactory()
					.create(IApsDataManager.class, context, context);
			if (!context.isInitialized()) {
				apsDataManager.initialize(context);
			}
		}
	}

}
