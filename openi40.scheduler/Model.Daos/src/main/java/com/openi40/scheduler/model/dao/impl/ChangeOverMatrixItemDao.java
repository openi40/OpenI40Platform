package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.cycle.ChangeOverMatrixItem;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsWindowDao;
import com.openi40.scheduler.model.dao.ITimesheetMetaInfoDao;
import com.openi40.scheduler.model.dao.IChangeOverMatrixItemDao;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.IWorkCenterDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
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
@Service
public class ChangeOverMatrixItemDao extends AbstractApsDataModelDao<ChangeOverMatrixItem>
		implements IChangeOverMatrixItemDao {

	public ChangeOverMatrixItemDao() {
		super(ChangeOverMatrixItem.class, new DataPathCfg(ChangeOverMatrixItem.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters/WorkCenter/ChangeOverMatrixItems")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters/WorkCenter/ChangeOverMatrixItems")
				.withPath(Plant.class, "Plant/Departments/Department/WorkCenters/WorkCenter/ChangeOverMatrixItems")
				.withPath(Department.class, "Department/WorkCenters/WorkCenter/ChangeOverMatrixItems")
				.withPath(WorkCenter.class, "WorkCenter/ChangeOverMatrixItems"));

	}

	@Autowired
	IWorkCenterDao workCenterDao;

	@Override
	public void insert(ChangeOverMatrixItem entry, ApsData context) throws DataModelDaoException {
		WorkCenter parent = findParent(entry, entry.getWorkCenterCode(), WorkCenter.class, workCenterDao, context);
		synchronized (context) {
			parent.getChangeOverMatrixItems().add(entry);
		}
	}

	@Override
	public void delete(ChangeOverMatrixItem entry, ApsData context) throws DataModelDaoException {
		WorkCenter parent = findParent(entry, entry.getWorkCenterCode(), WorkCenter.class, workCenterDao, context);
		synchronized (context) {
			parent.getChangeOverMatrixItems().remove(entry);
		}

	}

	@Override
	public ChangeOverMatrixItem findBySetupCodes(String setupCodeFrom, String setupCodeTo, String machineCode,
			WorkCenter workCenter) throws DataModelDaoException {
		ChangeOverMatrixItem returned = null;
		ISelector<ChangeOverMatrixItem> selector = new ISelector<ChangeOverMatrixItem>() {
			@Override
			public boolean isSelectable(ChangeOverMatrixItem t) {

				return t.getSetupGroupCodeTo() != null && setupCodeTo != null
						&& t.getSetupGroupCodeTo().equals(setupCodeTo) && t.getSetupGroupCodeFrom() != null
						&& setupCodeFrom != null && t.getSetupGroupCodeFrom().equals(setupCodeFrom)
						&& t.getMachineCode() != null && t.getMachineCode().equals(machineCode);
			}
		};
		List<ChangeOverMatrixItem> matchingWithMachine = super.findBySelector(selector, workCenter);
		if (matchingWithMachine != null || matchingWithMachine.isEmpty()) {
			selector = new ISelector<ChangeOverMatrixItem>() {

				@Override
				public boolean isSelectable(ChangeOverMatrixItem t) {

					return t.getSetupGroupCodeTo() != null && setupCodeTo != null
							&& t.getSetupGroupCodeTo().equals(setupCodeTo) && t.getSetupGroupCodeFrom() != null
							&& setupCodeFrom != null && t.getSetupGroupCodeFrom().equals(setupCodeFrom)
							&& (t.getMachineCode() == null || t.getMachineCode().trim().length() == 0);
				}
			};
			List<ChangeOverMatrixItem> matchingSetupGroups = super.findBySelector(selector, workCenter);
			if (matchingSetupGroups != null && !matchingSetupGroups.isEmpty()) {
				returned = matchingSetupGroups.get(0);
			}
		} else {
			returned = matchingWithMachine.get(0);
		}
		return returned;
	}

}