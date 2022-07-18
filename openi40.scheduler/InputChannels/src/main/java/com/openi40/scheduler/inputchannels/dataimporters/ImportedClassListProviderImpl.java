package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.ScheduledWorkOrderInputDto;
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
import com.openi40.scheduler.input.model.material.configuration.PlantProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.ProductiveCompanyProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.WarehouseProductSettingInputDto;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskProductionMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskPurchaseMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskStockMaterialReservationInputDto;
import com.openi40.scheduler.input.model.time.ApsWindowInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.inputchannels.streaminputs.config.ApsEntitiesIntegrationListConfig;
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
@Service
public class ImportedClassListProviderImpl implements IImportedClassListProvider {
	private static final Class<? extends InputDto> defaultEntitiesToSync[] = new Class[] { ApsWindowInputDto.class,
			TimesheetMetaInfoInputDto.class, ProductiveCompanyInputDto.class, PlantInputDto.class,
			DepartmentInputDto.class, WorkCenterInputDto.class, MachineInputDto.class,
			ChangeOverMatrixItemInputDto.class, ResourceGroupInputDto.class, SecondaryResourceInputDto.class,
			WarehouseInputDto.class, ProductInputDto.class, StockSupplyInputDto.class, CycleModelInputDto.class,
			SalesOrderInputDto.class, PurchaseOrderInputDto.class, ProductiveCompanyProductSettingInputDto.class,
			PlantProductSettingInputDto.class, WarehouseProductSettingInputDto.class, WorkOrderInputDto.class,
			TaskInputDto.class, TaskRelationInputDto.class, TaskProductionMaterialReservationInputDto.class,
			TaskStockMaterialReservationInputDto.class, TaskPurchaseMaterialReservationInputDto.class,
			PeggingInputDto.class, ApsSchedulingSetInputDto.class };
	ApsEntitiesIntegrationListConfig config = null;

	public ImportedClassListProviderImpl(@Autowired(required = false) ApsEntitiesIntegrationListConfig config) {
		this.config = config;
	}

	@Override
	public List<Class<? extends InputDto>> getClassesList() {
		List<Class<? extends InputDto>> list = new ArrayList<>();
		for (Class<? extends InputDto> classe : defaultEntitiesToSync) {
			list.add(classe);
		}
		if (this.config != null) {
			for (String _className : this.config.getAdditionalEntities()) {
				if (_className != null) {
					try {
						Class _classe = Class.forName(_className);
						if (InputDto.class.isAssignableFrom(_classe)) {
							list.add(_classe);
						} else {
							throw new OpenI40Exception(
									"The additional class " + _className + " must extend InputDto class");
						}
					} catch (ClassNotFoundException e) {
						throw new OpenI40Exception("The additional class " + _className
								+ " is not reachable, maybe an error in configuration", e);
					}
				}
			}
		}
		return list;
	}

}
