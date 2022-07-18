package com.openi40.scheduler.model.companystructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.material.configuration.WarehouseProductSetting;
import com.openi40.scheduler.model.material.timeline.WarehouseProductMaterialTimeLine;
import com.openi40.scheduler.model.time.Timesheet;

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

public class Warehouse extends AbstractPlantRelatedApsObject
		implements ICompanyStructureNode<Plant>, ITimesheetAllocableObject {
	Plant parent = null;
	private boolean infiniteCapacity = false;
	public final static String PRODUCT_SETTINGS = "ProductSettings";

	public Warehouse(ApsData context, Plant parentPlant) {
		super(context);
		this.parent = parentPlant;

	}

	public Warehouse(ApsData context) {
		super(context);

	}

	protected List<WarehouseProductSetting> productSettings = createCleanChild(this, PRODUCT_SETTINGS,
			WarehouseProductSetting.class);
	protected String timesheetMetaInfoCode = null;
	protected Timesheet timesheet = null;
	protected List<StockSupply> stockSupplies = new ArrayList<StockSupply>();
	protected Map<String, WarehouseProductMaterialTimeLine> productsMaterialTimeLines = new HashMap<>();

	public void debugLogging() {
		productsMaterialTimeLines.forEach((key,timeline)->{
			timeline.debugLogging();
		});
	}

	@Override
	public void resetSchedulingData() {
		for (StockSupply stockSupply : stockSupplies) {
			stockSupply.resetSchedulingData();
		}
		productsMaterialTimeLines.clear();
	}

}
