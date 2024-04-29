package com.openi40.scheduler.model.aps;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.common.aps.ICustomObject;
import com.openi40.scheduler.model.AbstractSchedulingEnvironmentNode;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class ApsData extends AbstractSchedulingEnvironmentNode implements IApsData, ITimesheetAllocableObject {
	protected ICustomObject customObject=null;
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
	
	private List<ApsSchedulingSet> schedulingSets = createCleanChild(this, SCHEDULING_SETS, ApsSchedulingSet.class);

	private ApsWindow schedulingWindow;
	private String dataSourceName = null;
	private String dataSetName = null;

	private String dataSetVariant = null;
	private boolean assertionActive = true;
	private boolean fullyPlanned = false;
	private boolean scheduled = false;
	private boolean initialized = false;
	private int apsMessagesCounter=0;
	
	private List<ProductiveCompany> productiveCompanies = createCleanChild(this, PRODUCTIVE_COMPANIES,
			ProductiveCompany.class);
	private List<TimesheetMetaInfo> timesheetMetaInfos = createCleanChild(this, CALENDAR_MODELS,
			TimesheetMetaInfo.class);
	
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
		apsMessagesCounter=0;
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

	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}

	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}

	public Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

	public boolean isInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	public boolean isRealtime() {
		return realtime;
	}

	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}

	public boolean isProductionControlEnabled() {
		return productionControlEnabled;
	}

	public void setProductionControlEnabled(boolean productionControlEnabled) {
		this.productionControlEnabled = productionControlEnabled;
	}

	public Date getActualDateTime() {
		return actualDateTime;
	}

	public void setActualDateTime(Date actualDateTime) {
		this.actualDateTime = actualDateTime;
	}

	public HashMap<String, Object> getSchedulingUtilCache() {
		return schedulingUtilCache;
	}

	public void setSchedulingUtilCache(HashMap<String, Object> schedulingUtilCache) {
		this.schedulingUtilCache = schedulingUtilCache;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ApsSchedulingSet> getSchedulingSets() {
		return schedulingSets;
	}

	

	public ApsWindow getSchedulingWindow() {
		return schedulingWindow;
	}

	public void setSchedulingWindow(ApsWindow schedulingWindow) {
		this.schedulingWindow = schedulingWindow;
	}

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

	public boolean isAssertionActive() {
		return assertionActive;
	}

	public void setAssertionActive(boolean assertionActive) {
		this.assertionActive = assertionActive;
	}

	public boolean isFullyPlanned() {
		return fullyPlanned;
	}

	public void setFullyPlanned(boolean fullyPlanned) {
		this.fullyPlanned = fullyPlanned;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public int getApsMessagesCounter() {
		return apsMessagesCounter;
	}

	public void setApsMessagesCounter(int apsMessagesCounter) {
		this.apsMessagesCounter = apsMessagesCounter;
	}

	public List<ProductiveCompany> getProductiveCompanies() {
		return productiveCompanies;
	}

	public List<TimesheetMetaInfo> getTimesheetMetaInfos() {
		return timesheetMetaInfos;
	}

	public List<Product> getProducts() {
		return products;
	}

	public ICustomObject getCustomObject() {
		return customObject;
	}

	public void setCustomObject(ICustomObject customObject) {
		this.customObject = customObject;
	}
}