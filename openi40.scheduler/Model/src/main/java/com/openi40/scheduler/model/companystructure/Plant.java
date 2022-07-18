package com.openi40.scheduler.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.orders.ProductionOrder;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.time.Timesheet;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
public class Plant extends AbstractApsObject
		implements ICompanyStructureNode<ProductiveCompany>, ITimesheetAllocableObject {
	private static final String WORK_ORDERS = "WorkOrders";
	private static final String PURCHASE_ORDERS = "PurchaseOrders";
	private static final String PRODUCTION_ORDERS = "ProductionOrders";
	private static final String SALES_ORDERS = "SalesOrders";
	private static final String CYCLE_MODELS = "CycleModels";
	private static final String WAREHOUSES = "Warehouses";
	private static final String DEPARTMENTS = "Departments";
	private static final String PRODUCT_SETTINGS = "ProductSettings";
	private boolean infiniteCapacity = false;
	protected ProductiveCompany parent = null;
	@Setter(value = AccessLevel.NONE)
	protected List<Department> departments = createCleanChild(this, DEPARTMENTS, Department.class);
	@Setter(value = AccessLevel.NONE)
	protected List<Warehouse> warehouses = createCleanChild(this, WAREHOUSES, Warehouse.class);
	@Setter(value = AccessLevel.NONE)
	protected List<CycleModel> cycleModels = createCleanChild(this, CYCLE_MODELS, CycleModel.class);
	@Setter(value = AccessLevel.NONE)
	protected List<SalesOrder> salesOrders = createCleanChild(this, SALES_ORDERS, SalesOrder.class);
	@Setter(value = AccessLevel.NONE)
	protected List<ProductionOrder> productionOrders = createCleanChild(this, PRODUCTION_ORDERS, ProductionOrder.class);
	@Setter(value = AccessLevel.NONE)
	protected List<PurchaseOrder> purchaseOrders = createCleanChild(this, PURCHASE_ORDERS, PurchaseOrder.class);
	@Setter(value = AccessLevel.NONE)
	protected List<WorkOrder> workOrders = createCleanChild(this, WORK_ORDERS, WorkOrder.class);
	@Setter(value = AccessLevel.NONE)
	protected List<PlantProductSetting> productSettings = createCleanChild(this, PRODUCT_SETTINGS,
			PlantProductSetting.class);

	protected String productiveCompanyCode = null;
	protected String timesheetMetaInfoCode = null;
	protected Timesheet timesheet = null;

	public Plant(ApsData c) {
		super(c);
	}

	public Plant(ApsData c, ProductiveCompany parent) {
		super(c);
		this.parent = parent;
	}

	@Override
	public void resetSchedulingData() {
		List<IApsObject> objects = new ArrayList<>();
		objects.addAll(departments);
		objects.addAll(warehouses);
		objects.addAll(cycleModels);
		objects.addAll(salesOrders);
		objects.addAll(productionOrders);
		objects.addAll(purchaseOrders);
		objects.addAll(workOrders);
		for (IApsObject iApsObject : objects) {
			iApsObject.resetSchedulingData();
		}
	}

}