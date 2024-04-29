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
package com.openi40.persistence.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.persistence.server.basecontrollers.BaseInputController;
import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
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
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;

public class InputControllers {

	@RestController
	@RequestMapping(value = "/input/ApsWindow")
	static public class ApsWindowInputController extends BaseInputController<ApsWindowInputDto> {

		public ApsWindowInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ApsWindowInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/ChangeOverMatrixItem")

	static public class ChangeOverMatrixInputController extends BaseInputController<ChangeOverMatrixItemInputDto> {

		public ChangeOverMatrixInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ChangeOverMatrixItemInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/Department")
	static public class DepartmentInputController extends BaseInputController<DepartmentInputDto> {

		public DepartmentInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(DepartmentInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/Machine")

	static public class MachineInputController extends BaseInputController<MachineInputDto> {

		public MachineInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(MachineInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/Plant")
	static public class PlantInputController extends BaseInputController<PlantInputDto> {

		public PlantInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(PlantInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/ProductiveCompany")

	static public class ProductiveCompanyInputController extends BaseInputController<ProductiveCompanyInputDto> {

		public ProductiveCompanyInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ProductiveCompanyInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/ResourceGroup")

	static public class ResourceGroupInputController extends BaseInputController<ResourceGroupInputDto> {

		public ResourceGroupInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ResourceGroupInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/SecondaryResource")

	static public class SecondaryResourceInputController extends BaseInputController<SecondaryResourceInputDto> {

		public SecondaryResourceInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(SecondaryResourceInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/TimesheetMetaInfo")

	static public class TimesheetMetaInfoInputController extends BaseInputController<TimesheetMetaInfoInputDto> {

		public TimesheetMetaInfoInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TimesheetMetaInfoInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/Warehouse")

	static public class WarehouseInputController extends BaseInputController<WarehouseInputDto> {

		public WarehouseInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(WarehouseInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/WorkCenter")

	static public class WorkCenterInputController extends BaseInputController<WorkCenterInputDto> {

		public WorkCenterInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(WorkCenterInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/Product")

	static public class ProductInputController extends BaseInputController<ProductInputDto> {

		public ProductInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ProductInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	@RestController
	@RequestMapping(value = "/input/StockSupply")

	static public class StockSupplyInputController extends BaseInputController<StockSupplyInputDto> {

		public StockSupplyInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(StockSupplyInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// CycleModelInputDto
	@RestController
	@RequestMapping(value = "/input/CycleModel")

	static public class CycleModelInputController extends BaseInputController<CycleModelInputDto> {

		public CycleModelInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(CycleModelInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// SalesOrderInputDto.class,
	@RestController
	@RequestMapping(value = "/input/SalesOrder")

	static public class SalesOrderInputController extends BaseInputController<SalesOrderInputDto> {

		public SalesOrderInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(SalesOrderInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// PurchaseOrderInputDto.class,
	@RestController
	@RequestMapping(value = "/input/PurchaseOrder")

	static public class PurchaseOrderInputController extends BaseInputController<PurchaseOrderInputDto> {

		public PurchaseOrderInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(PurchaseOrderInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// ProductiveCompanyProductSettingInputDto
	@RestController
	@RequestMapping(value = "/input/ProductiveCompanyProductSetting")

	static public class ProductiveCompanyProductSettingInputController
			extends BaseInputController<ProductiveCompanyProductSettingInputDto> {

		public ProductiveCompanyProductSettingInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ProductiveCompanyProductSettingInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// PlantProductSettingInputDto
	@RestController
	@RequestMapping(value = "/input/PlantProductSetting")

	static public class PlantProductSettingInputController extends BaseInputController<PlantProductSettingInputDto> {

		public PlantProductSettingInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(PlantProductSettingInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// WarehouseProductSettingInputDto
	@RestController
	@RequestMapping(value = "/input/WarehouseProductSetting")

	static public class WarehouseProductSettingInputController
			extends BaseInputController<WarehouseProductSettingInputDto> {

		public WarehouseProductSettingInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(WarehouseProductSettingInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// WorkOrderInputDto
	@RestController
	@RequestMapping(value = "/input/WorkOrder")

	static public class WorkOrderInputController extends BaseInputController<WorkOrderInputDto> {

		public WorkOrderInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(WorkOrderInputDto.class, persistenceInputDataStreamFactories);

		}

	}

	// TaskInputDto.class,
	@RestController
	@RequestMapping(value = "/input/Task")

	static public class TaskInputController extends BaseInputController<TaskInputDto> {

		public TaskInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TaskInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	@RestController
	@RequestMapping(value = "/input/TaskRelation")

	static public class TaskRelationInputController extends BaseInputController<TaskRelationInputDto> {

		public TaskRelationInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TaskRelationInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	@RestController
	@RequestMapping(value = "/input/TaskProductionMaterialReservation")

	static public class TaskProductionMaterialReservationInputController extends BaseInputController<TaskProductionMaterialReservationInputDto> {

		public TaskProductionMaterialReservationInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TaskProductionMaterialReservationInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	
	@RestController
	@RequestMapping(value = "/input/TaskStockMaterialReservation")

	static public class TaskStockMaterialReservationInputController extends BaseInputController<TaskStockMaterialReservationInputDto> {

		public TaskStockMaterialReservationInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TaskStockMaterialReservationInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	@RestController
	@RequestMapping(value = "/input/TaskPurchaseMaterialReservation")

	static public class TaskPurchaseMaterialReservationInputController extends BaseInputController<TaskPurchaseMaterialReservationInputDto> {

		public TaskPurchaseMaterialReservationInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(TaskPurchaseMaterialReservationInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	@RestController
	@RequestMapping(value = "/input/Pegging")

	static public class PeggingInputController extends BaseInputController<PeggingInputDto> {

		public PeggingInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(PeggingInputDto.class, persistenceInputDataStreamFactories);

		}

	}
	@RestController
	@RequestMapping(value = "/input/ApsSchedulingSet")

	static public class ApsSchedulingSetInputController extends BaseInputController<ApsSchedulingSetInputDto> {

		public ApsSchedulingSetInputController(
				@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
			super(ApsSchedulingSetInputDto.class, persistenceInputDataStreamFactories);

		}

	}
}
