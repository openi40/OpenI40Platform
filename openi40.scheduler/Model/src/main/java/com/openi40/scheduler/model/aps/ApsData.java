package com.openi40.scheduler.model.aps;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.model.AbstractSchedulingEnvironmentNode;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.cycle.OperationEdgeModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.ITasksVisitor;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
@Data
public class ApsData extends AbstractSchedulingEnvironmentNode implements IApsData, ITimesheetAllocableObject {
	private static final String PRODUCTS = "Products";
	private static final String PRODUCTIVE_COMPANIES = "ProductiveCompanies";
	private static final String SCHEDULING_SETS = "SchedulingSets";
	private static final String CALENDAR_MODELS = "CalendarModels";
	protected String timesheetMetaInfoCode = null;
	protected Timesheet timesheet = null;
	protected boolean infiniteCapacity = false;
	protected boolean realtime = false;
	protected boolean productionControlEnabled = false;
	protected Date actualDateTime = null;

	private HashMap<String, Object> schedulingUtilCache = new HashMap<>();
	private boolean active = false;
	@Setter(value = AccessLevel.NONE)
	private List<ApsSchedulingSet> schedulingSets = createCleanChild(this, SCHEDULING_SETS, ApsSchedulingSet.class);

	private ApsWindow schedulingWindow;
	private String dataSourceName = null;
	private String dataSetName = null;

	private String dataSetVariant = null;
	private boolean assertionActive = true;
	private boolean fullyPlanned = false;
	private boolean scheduled = false;
	private boolean initialized = false;
	@Setter(value = AccessLevel.NONE)
	private List<ProductiveCompany> productiveCompanies = createCleanChild(this, PRODUCTIVE_COMPANIES,
			ProductiveCompany.class);
	private List<TimesheetMetaInfo> timesheetMetaInfos = createCleanChild(this, CALENDAR_MODELS,
			TimesheetMetaInfo.class);
	@Setter(value = AccessLevel.NONE)
	private List<Product> products = createCleanChild(this, PRODUCTS, Product.class);

	public ApsData() {
		super(null);
		this.schedulingWindow = new ApsWindow(this);

	}

	protected void finalize() throws Throwable {

		schedulingSets.clear();
		schedulingSets = null;
	}

	@Override
	public void resetSchedulingData() {
		this.schedulingUtilCache.clear();

		for (ProductiveCompany productiveCompany : productiveCompanies) {
			productiveCompany.resetSchedulingData();
		}

	}

	public void debugLogging() {
		this.productiveCompanies.forEach((pc) -> {
			pc.getPlants().forEach((plant) -> {
				plant.getWarehouses().forEach((warehouse) -> {
					warehouse.debugLogging();
				});
			});
		});
	}

	@Override
	public ApsData getContext() {
		return this;
	}

	public <T> void addCache(String key, T value) {
		this.schedulingUtilCache.put(key, value);
	}

	public boolean isInCache(String key) {
		return this.schedulingUtilCache.containsKey(key);
	}

	public <T> T getCache(String key, Class<T> type) {
		return (T) this.schedulingUtilCache.get(key);
	}

	@Override
	public String toString() {
		return "ApsData{code=" + code + ",description=" + description + ",id=" + id + "}";
	}

	public void removeSchedulingSetById(String schedulingSetId) {
		ApsSchedulingSet selectedSet = null;
		for (ApsSchedulingSet actualSet : this.getSchedulingSets()) {
			if (actualSet.getId().equals(schedulingSetId)) {
				selectedSet = actualSet;
			}
		}
		if (selectedSet != null) {
			this.schedulingSets.remove(selectedSet);
			selectedSet.resetSchedulingData();
			List<WorkOrder> workorders = selectedSet.getWorkOrders();
			for (WorkOrder workOrder : workorders) {
				workOrder.setParentSchedulingAction(null);
			}
		}
	}
}