package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.equipment.ResourceGroup;

@Service
public class TimeSheetsInitializer {
	static Logger LOGGER = LoggerFactory.getLogger(TimeSheetsInitializer.class);
	@Autowired
	protected IContextualBusinessLogicFactory componentsFactory;

	public void initializeCalendars(ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin initializeClaendars(..)");
		}
		List<ITimesheetAllocableObject> list = new ArrayList<>();
		list.add(context);
		list.addAll(context.getProductiveCompanies());
		for (ProductiveCompany pc : context.getProductiveCompanies()) {
			list.addAll(pc.getPlants());
			for (Plant plant : pc.getPlants()) {
				list.addAll(plant.getDepartments());
				for (Department dep : plant.getDepartments()) {
					list.addAll(dep.getWorkCenters());
					for (WorkCenter wc : dep.getWorkCenters()) {
						list.addAll(wc.getResources());
					}
					for (ResourceGroup grp : dep.getSecondaryResourceGroups()) {
						list.addAll(grp.getResources());
					}
				}
				list.addAll(plant.getWarehouses());
			}
		}
		for (ITimesheetAllocableObject r : list) {
			ITimesheetLogic timesheetLogic = this.componentsFactory.create(ITimesheetLogic.class, r, context);
			r.setTimesheet(timesheetLogic.createCleanCalendar(r));
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End initializeClaendars(..)");
		}
	}

}
