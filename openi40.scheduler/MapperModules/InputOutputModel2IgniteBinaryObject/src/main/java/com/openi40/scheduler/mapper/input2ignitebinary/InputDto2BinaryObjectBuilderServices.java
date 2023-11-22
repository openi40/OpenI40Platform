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
public class InputDto2BinaryObjectBuilderServices {
	@Service
	public static class ApsWindowInput2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ApsWindowInputDto> {
		ApsWindowInput2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ApsWindowInputDto.class);
		}
	}

	@Service
	public static class TimesheetMetaInfoInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TimesheetMetaInfoInputDto> {
		TimesheetMetaInfoInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TimesheetMetaInfoInputDto.class);
		}
	}

	@Service
	public static class ProductiveCompanyInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ProductiveCompanyInputDto> {
		ProductiveCompanyInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ProductiveCompanyInputDto.class);
		}
	}

	@Service
	public static class PlantInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<PlantInputDto> {
		PlantInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PlantInputDto.class);
		}
	}

	@Service
	public static class DepartmentInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<DepartmentInputDto> {
		DepartmentInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, DepartmentInputDto.class);
		}
	}

	@Service
	public static class WorkCenterInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<WorkCenterInputDto> {
		WorkCenterInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WorkCenterInputDto.class);
		}
	}

	@Service
	public static class MachineInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<MachineInputDto> {
		MachineInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, MachineInputDto.class);
		}
	}

	@Service
	public static class ChangeOverMatrixItemInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ChangeOverMatrixItemInputDto> {
		ChangeOverMatrixItemInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ChangeOverMatrixItemInputDto.class);
		}
	}

	@Service
	public static class ResourceGroupInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ResourceGroupInputDto> {
		ResourceGroupInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ResourceGroupInputDto.class);
		}
	}

	@Service
	public static class SecondaryResourceInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<SecondaryResourceInputDto> {
		SecondaryResourceInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, SecondaryResourceInputDto.class);
		}
	}

	@Service
	public static class WarehouseInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<WarehouseInputDto> {
		WarehouseInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WarehouseInputDto.class);
		}
	}

	@Service
	public static class ProductInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ProductInputDto> {
		ProductInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ProductInputDto.class);
		}
	}

	@Service
	public static class StockSupplyInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<StockSupplyInputDto> {
		StockSupplyInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, StockSupplyInputDto.class);
		}
	}

	@Service
	public static class CycleModelInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<CycleModelInputDto> {
		CycleModelInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, CycleModelInputDto.class);
		}
	}

	@Service
	public static class SalesOrderInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<SalesOrderInputDto> {
		SalesOrderInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, SalesOrderInputDto.class);
		}
	}

	@Service
	public static class PurchaseOrderInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<PurchaseOrderInputDto> {
		PurchaseOrderInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PurchaseOrderInputDto.class);
		}
	}

	@Service
	public static class ProductiveCompanyProductSettingInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ProductiveCompanyProductSettingInputDto> {
		ProductiveCompanyProductSettingInputDto2BinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, ProductiveCompanyProductSettingInputDto.class);
		}
	}

	@Service
	public static class PlantProductSettingInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<PlantProductSettingInputDto> {
		PlantProductSettingInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PlantProductSettingInputDto.class);
		}
	}

	@Service
	public static class WarehouseProductSettingInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<WarehouseProductSettingInputDto> {
		WarehouseProductSettingInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WarehouseProductSettingInputDto.class);
		}
	}

	@Service
	public static class WorkOrderInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<WorkOrderInputDto> {
		WorkOrderInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, WorkOrderInputDto.class);
		}
	}

	@Service
	public static class TaskInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TaskInputDto> {
		TaskInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TaskInputDto.class);
		}
	}

	@Service
	public static class TaskRelationInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TaskRelationInputDto> {
		TaskRelationInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, TaskRelationInputDto.class);
		}
	}

	@Service
	public static class TaskProductionMaterialReservationInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TaskProductionMaterialReservationInputDto> {
		TaskProductionMaterialReservationInputDto2BinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskProductionMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class TaskStockMaterialReservationInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TaskStockMaterialReservationInputDto> {
		TaskStockMaterialReservationInputDto2BinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskStockMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class TaskPurchaseMaterialReservationInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<TaskPurchaseMaterialReservationInputDto> {
		TaskPurchaseMaterialReservationInputDto2BinaryObjectBuilder(@Autowired Ignite ignite)
				throws IntrospectionException {
			super(ignite, TaskPurchaseMaterialReservationInputDto.class);
		}
	}

	@Service
	public static class PeggingInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<PeggingInputDto> {
		PeggingInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, PeggingInputDto.class);
		}
	}

	@Service
	public static class ApsSchedulingSetInputDto2BinaryObjectBuilder
			extends AbstractInputDto2IgniteBinaryObjectBuilder<ApsSchedulingSetInputDto> {
		ApsSchedulingSetInputDto2BinaryObjectBuilder(@Autowired Ignite ignite) throws IntrospectionException {
			super(ignite, ApsSchedulingSetInputDto.class);
		}
	}

}
