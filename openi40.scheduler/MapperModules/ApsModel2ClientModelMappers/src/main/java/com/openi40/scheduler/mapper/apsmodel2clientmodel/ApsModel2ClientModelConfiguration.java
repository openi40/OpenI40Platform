package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.function.Consumer;

import org.springframework.context.annotation.Configuration;

import com.openi40.scheduler.client.model.aps.ApsSchedulingSetDto;
import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.client.model.aps.ApsLogicOptionsDto;
import com.openi40.scheduler.client.model.companystructure.DepartmentDto;
import com.openi40.scheduler.client.model.companystructure.PlantDto;
import com.openi40.scheduler.client.model.companystructure.ProductiveCompanyDto;
import com.openi40.scheduler.client.model.companystructure.ResourceGroupDto;
import com.openi40.scheduler.client.model.companystructure.WarehouseDto;
import com.openi40.scheduler.client.model.companystructure.WorkCenterDto;
import com.openi40.scheduler.client.model.equipment.MachineDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.orders.WorkOrderDto;
import com.openi40.scheduler.client.model.time.UsageTimeSegmentDto;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.TypeMap;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
public class ApsModel2ClientModelConfiguration {
	static final TypeMap typeMap = new TypeMap();
	static {
		try {
			typeMap.add(ApsData.class, ApsDataDto.class);
			typeMap.add(ProductiveCompany.class, ProductiveCompanyDto.class);
			typeMap.add(ApsData.class, ApsDataDto.class, "ProductiveCompanies", ProductiveCompany.class,
					ProductiveCompanyDto.class);

			typeMap.add(Plant.class, PlantDto.class);
			typeMap.add(ProductiveCompany.class, ProductiveCompanyDto.class, "Plants", Plant.class, PlantDto.class);
			typeMap.add(Department.class, DepartmentDto.class);
			typeMap.add(Plant.class, PlantDto.class, "Departments", Department.class, DepartmentDto.class);
			typeMap.add(Warehouse.class, WarehouseDto.class);
			typeMap.add(Plant.class, PlantDto.class, "Warehouses", Warehouse.class, WarehouseDto.class);
			typeMap.add(WorkCenter.class, WorkCenterDto.class);
			typeMap.add(Department.class, DepartmentDto.class, "WorkCenters", WorkCenter.class, WorkCenterDto.class);
			typeMap.add(Department.class, DepartmentDto.class, "SecondaryResourceGroups", ResourceGroup.class,
					ResourceGroupDto.class);
			typeMap.add(Machine.class, MachineDto.class);
			typeMap.add(WorkCenter.class, WorkCenterDto.class, "Resources", Machine.class, MachineDto.class);
			typeMap.add(WorkOrder.class, WorkOrderDto.class);
			typeMap.add(Plant.class, PlantDto.class, "WorkOrders", WorkOrder.class, WorkOrderDto.class);
			typeMap.add(Product.class, ProductDto.class);
			typeMap.add(ApsData.class, ApsDataDto.class, "Products", Product.class, ProductDto.class);
			typeMap.add(ApsSchedulingSet.class, ApsSchedulingSetDto.class);
			typeMap.add(ApsLogicOptions.class, ApsLogicOptionsDto.class);
			typeMap.add(ApsLogicOptions.SortOption.class, ApsLogicOptionsDto.SortOptionDto.class);
			typeMap.add(ApsLogicOptions.SchedulingPriorities.class, String.class);
			typeMap.add(ApsLogicOptions.OnUnallocableChildTaskAction.class, String.class);
			typeMap.add(ApsLogicOptions.SortDirection.class, String.class);
		} catch (MapperException exception) {
			exception.printStackTrace();
		}
	}
}
