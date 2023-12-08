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
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public String getDataSetName() {
		return dataSetName;
	}
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	public String getDataSetVariant() {
		return dataSetVariant;
	}
	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}
	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}
	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}
	public ApsWindowInputDto getSchedulingWindow() {
		return schedulingWindow;
	}
	public void setSchedulingWindow(ApsWindowInputDto schedulingWindow) {
		this.schedulingWindow = schedulingWindow;
	}
	public List<ApsSchedulingSetInputDto> getSchedulingSets() {
		return schedulingSets;
	}
	public void setSchedulingSets(List<ApsSchedulingSetInputDto> schedulingSets) {
		this.schedulingSets = schedulingSets;
	}
	public List<TaskProductionMaterialReservationInputDto> getTaskProductionReservations() {
		return taskProductionReservations;
	}
	public void setTaskProductionReservations(List<TaskProductionMaterialReservationInputDto> taskProductionReservations) {
		this.taskProductionReservations = taskProductionReservations;
	}
	public List<TaskStockMaterialReservationInputDto> getTaskStockReservations() {
		return taskStockReservations;
	}
	public void setTaskStockReservations(List<TaskStockMaterialReservationInputDto> taskStockReservations) {
		this.taskStockReservations = taskStockReservations;
	}
	public List<TaskPurchaseMaterialReservationInputDto> getTaskPurchaseReservations() {
		return taskPurchaseReservations;
	}
	public void setTaskPurchaseReservations(List<TaskPurchaseMaterialReservationInputDto> taskPurchaseReservations) {
		this.taskPurchaseReservations = taskPurchaseReservations;
	}
	public List<ProductiveCompanyInputDto> getCompanies() {
		return companies;
	}
	public void setCompanies(List<ProductiveCompanyInputDto> companies) {
		this.companies = companies;
	}
	public List<PlantInputDto> getPlants() {
		return plants;
	}
	public void setPlants(List<PlantInputDto> plants) {
		this.plants = plants;
	}
	public List<DepartmentInputDto> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentInputDto> departments) {
		this.departments = departments;
	}
	public List<WorkCenterInputDto> getWorkcenters() {
		return workcenters;
	}
	public void setWorkcenters(List<WorkCenterInputDto> workcenters) {
		this.workcenters = workcenters;
	}
	public List<ResourceGroupInputDto> getSecondaryResourcesGroup() {
		return secondaryResourcesGroup;
	}
	public void setSecondaryResourcesGroup(List<ResourceGroupInputDto> secondaryResourcesGroup) {
		this.secondaryResourcesGroup = secondaryResourcesGroup;
	}
	public List<MachineInputDto> getMachines() {
		return machines;
	}
	public void setMachines(List<MachineInputDto> machines) {
		this.machines = machines;
	}
	public List<SecondaryResourceInputDto> getSecondaryResources() {
		return secondaryResources;
	}
	public void setSecondaryResources(List<SecondaryResourceInputDto> secondaryResources) {
		this.secondaryResources = secondaryResources;
	}
	public List<CycleModelInputDto> getCycleModels() {
		return cycleModels;
	}
	public void setCycleModels(List<CycleModelInputDto> cycleModels) {
		this.cycleModels = cycleModels;
	}
	public List<ProductInputDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductInputDto> products) {
		this.products = products;
	}
	public List<TimesheetMetaInfoInputDto> getTimesheetMetaInfos() {
		return timesheetMetaInfos;
	}
	public void setTimesheetMetaInfos(List<TimesheetMetaInfoInputDto> timesheetMetaInfos) {
		this.timesheetMetaInfos = timesheetMetaInfos;
	}
	public List<WorkOrderInputDto> getWorkOrders() {
		return workOrders;
	}
	public void setWorkOrders(List<WorkOrderInputDto> workOrders) {
		this.workOrders = workOrders;
	}
	public List<SalesOrderInputDto> getSalesOrders() {
		return salesOrders;
	}
	public void setSalesOrders(List<SalesOrderInputDto> salesOrders) {
		this.salesOrders = salesOrders;
	}
	public List<PurchaseOrderInputDto> getPurchaseOrders() {
		return purchaseOrders;
	}
	public void setPurchaseOrders(List<PurchaseOrderInputDto> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}
	public List<PeggingInputDto> getPeggings() {
		return peggings;
	}
	public void setPeggings(List<PeggingInputDto> peggings) {
		this.peggings = peggings;
	}
	public List<TaskInputDto> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskInputDto> tasks) {
		this.tasks = tasks;
	}
	public List<TaskRelationInputDto> getTaskRelations() {
		return taskRelations;
	}
	public void setTaskRelations(List<TaskRelationInputDto> taskRelations) {
		this.taskRelations = taskRelations;
	}
	public List<WarehouseInputDto> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<WarehouseInputDto> warehouses) {
		this.warehouses = warehouses;
	}
	public List<StockSupplyInputDto> getStockInventories() {
		return stockInventories;
	}
	public void setStockInventories(List<StockSupplyInputDto> stockInventories) {
		this.stockInventories = stockInventories;
	}
	public List<ChangeOverMatrixItemInputDto> getChangeOverMatrix() {
		return changeOverMatrix;
	}
	public void setChangeOverMatrix(List<ChangeOverMatrixItemInputDto> changeOverMatrix) {
		this.changeOverMatrix = changeOverMatrix;
	}
	public List<WarehouseProductSettingInputDto> getWarehouseProductSettings() {
		return warehouseProductSettings;
	}
	public void setWarehouseProductSettings(List<WarehouseProductSettingInputDto> warehouseProductSettings) {
		this.warehouseProductSettings = warehouseProductSettings;
	}
	public List<PlantProductSettingInputDto> getPlantProductSettings() {
		return plantProductSettings;
	}
	public void setPlantProductSettings(List<PlantProductSettingInputDto> plantProductSettings) {
		this.plantProductSettings = plantProductSettings;
	}
	public List<ProductiveCompanyProductSettingInputDto> getCompanyProductSettings() {
		return companyProductSettings;
	}
	public void setCompanyProductSettings(List<ProductiveCompanyProductSettingInputDto> companyProductSettings) {
		this.companyProductSettings = companyProductSettings;
	}
}
