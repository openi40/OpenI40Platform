package com.openi40.scheduler.input.model;

import java.util.ArrayList;
import java.util.List;

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
import com.openi40.scheduler.input.model.material.configuration.ProductiveCompanyProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.PlantProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.WarehouseProductSettingInputDto;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.input.model.time.ApsWindowInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.input.model.tasks.*;

import lombok.Data;
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
@Data
public class ApsInputData extends InputDto {
	String dataSourceName = null;
	String dataSetName = null;
	String dataSetVariant = null;
	String timesheetMetaInfoCode = null;
	ApsWindowInputDto schedulingWindow = null;
	List<ApsSchedulingSetInputDto> schedulingSets = new ArrayList<>();
	List<TaskProductionMaterialReservationInputDto> taskProductionReservations = new ArrayList<>();
	List<TaskStockMaterialReservationInputDto> taskStockReservations = new ArrayList<>();
	List<TaskPurchaseMaterialReservationInputDto> taskPurchaseReservations = new ArrayList<>();
	List<ProductiveCompanyInputDto> companies = new ArrayList();
	List<PlantInputDto> plants = new ArrayList();
	List<DepartmentInputDto> departments = new ArrayList();
	List<WorkCenterInputDto> workcenters = new ArrayList();
	List<ResourceGroupInputDto> secondaryResourcesGroup = new ArrayList();
	List<MachineInputDto> machines = new ArrayList();
	List<SecondaryResourceInputDto> secondaryResources = new ArrayList();
	List<CycleModelInputDto> cycleModels = new ArrayList<CycleModelInputDto>();
	List<ProductInputDto> products = new ArrayList<ProductInputDto>();
	List<TimesheetMetaInfoInputDto> timesheetMetaInfos = new ArrayList<TimesheetMetaInfoInputDto>();
	List<WorkOrderInputDto> workOrders = new ArrayList();
	List<SalesOrderInputDto> salesOrders = new ArrayList();
	List<PurchaseOrderInputDto> purchaseOrders = new ArrayList();
	List<PeggingInputDto> peggings = new ArrayList();
	List<TaskInputDto> tasks = new ArrayList();
	List<TaskRelationInputDto> taskRelations = new ArrayList();
	List<WarehouseInputDto> warehouses = new ArrayList<WarehouseInputDto>();
	List<StockSupplyInputDto> stockInventories = new ArrayList<StockSupplyInputDto>();
	List<ChangeOverMatrixItemInputDto> changeOverMatrix = new ArrayList<>();
	List<WarehouseProductSettingInputDto> warehouseProductSettings = new ArrayList<>();
	List<PlantProductSettingInputDto> plantProductSettings = new ArrayList<>();
	List<ProductiveCompanyProductSettingInputDto> companyProductSettings = new ArrayList<>();
}
