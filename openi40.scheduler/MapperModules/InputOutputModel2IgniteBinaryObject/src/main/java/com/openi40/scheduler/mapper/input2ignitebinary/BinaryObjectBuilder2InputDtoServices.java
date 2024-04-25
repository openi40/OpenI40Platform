package com.openi40.scheduler.mapper.input2ignitebinary;

import java.beans.IntrospectionException;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

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
@Configuration
public class BinaryObjectBuilder2InputDtoServices {
	@Service
	public static class ApsWindowInputFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ApsWindowInputDto> {
		ApsWindowInputFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ApsWindowInputDto.class);
		}
	}

	@Service
	public static class TimesheetMetaInfoInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TimesheetMetaInfoInputDto> {
		TimesheetMetaInfoInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TimesheetMetaInfoInputDto.class);
		}
	}

	@Service
	public static class ProductiveCompanyInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ProductiveCompanyInputDto> {
		ProductiveCompanyInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ProductiveCompanyInputDto.class);
		}
	}

	@Service
	public static class PlantInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<PlantInputDto> {
		PlantInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PlantInputDto.class);
		}
	}

	@Service
	public static class DepartmentInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<DepartmentInputDto> {
		DepartmentInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, DepartmentInputDto.class);
		}
	}

	@Service
	public static class WorkCenterInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<WorkCenterInputDto> {
		WorkCenterInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WorkCenterInputDto.class);
		}
	}

	@Service
	public static class MachineInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<MachineInputDto> {
		MachineInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, MachineInputDto.class);
		}
	}

	@Service
	public static class ChangeOverMatrixItemInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ChangeOverMatrixItemInputDto> {
		ChangeOverMatrixItemInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ChangeOverMatrixItemInputDto.class);
		}
	}

	@Service
	public static class ResourceGroupInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ResourceGroupInputDto> {
		ResourceGroupInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ResourceGroupInputDto.class);
		}
	}

	@Service
	public static class SecondaryResourceInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<SecondaryResourceInputDto> {
		SecondaryResourceInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, SecondaryResourceInputDto.class);
		}
	}

	@Service
	public static class WarehouseInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<WarehouseInputDto> {
		WarehouseInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WarehouseInputDto.class);
		}
	}

	@Service
	public static class ProductInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ProductInputDto> {
		ProductInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ProductInputDto.class);
		}
	}

	@Service
	public static class StockSupplyInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<StockSupplyInputDto> {
		StockSupplyInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, StockSupplyInputDto.class);
		}
	}

	@Service
	public static class CycleModelInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<CycleModelInputDto> {
		CycleModelInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, CycleModelInputDto.class);
		}
	}

	@Service
	public static class SalesOrderInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<SalesOrderInputDto> {
		SalesOrderInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, SalesOrderInputDto.class);
		}
	}

	@Service
	public static class PurchaseOrderInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<PurchaseOrderInputDto> {
		PurchaseOrderInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PurchaseOrderInputDto.class);
		}
	}

	@Service
	public static class ProductiveCompanyProductSettingInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ProductiveCompanyProductSettingInputDto> {
		ProductiveCompanyProductSettingInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, ProductiveCompanyProductSettingInputDto.class);
		}
	}

	@Service
	public static class PlantProductSettingInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<PlantProductSettingInputDto> {
		PlantProductSettingInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PlantProductSettingInputDto.class);
		}
	}

	@Service
	public static class WarehouseProductSettingInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<WarehouseProductSettingInputDto> {
		WarehouseProductSettingInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WarehouseProductSettingInputDto.class);
		}
	}

	@Service
	public static class WorkOrderInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<WorkOrderInputDto> {
		WorkOrderInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WorkOrderInputDto.class);
		}
	}

	@Service
	public static class TaskInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TaskInputDto> {
		TaskInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TaskInputDto.class);
		}
	}

	@Service
	public static class TaskRelationInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TaskRelationInputDto> {
		TaskRelationInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TaskRelationInputDto.class);
		}
	}

	@Service
	public static class TaskProductionMaterialReservationInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TaskProductionMaterialReservationInputDto> {
		TaskProductionMaterialReservationInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskProductionMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class TaskStockMaterialReservationInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TaskStockMaterialReservationInputDto> {
		TaskStockMaterialReservationInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskStockMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class TaskPurchaseMaterialReservationInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<TaskPurchaseMaterialReservationInputDto> {
		TaskPurchaseMaterialReservationInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskPurchaseMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class PeggingInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<PeggingInputDto> {
		PeggingInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PeggingInputDto.class);
		}
	}

	@Service
	public static class ApsSchedulingSetInputDtoFromBinaryObjectBuilder
			extends AbstractBinaryObjectBuilder2InputDto<ApsSchedulingSetInputDto> {
		ApsSchedulingSetInputDtoFromBinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ApsSchedulingSetInputDto.class);
		}
	}

}
