package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.dao.IWorkCenterDao;
import com.openi40.scheduler.model.equipment.Machine;
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
public class MachineDataModelDao extends AbstractApsDataModelDao<Machine> implements IMachineDao {

	public MachineDataModelDao() {
		super(Machine.class, new DataPathCfg(Machine.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters/WorkCenter/Resources")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters/WorkCenter/Resources")
				.withPath(Plant.class, "Plant/Departments/Department/WorkCenters/WorkCenter/Resources"));

	}

	@Autowired
	IWorkCenterDao workCenterDao = null;

	@Override
	public void insert(Machine entry, ApsData context) throws DataModelDaoException {
		String workCenterCode = entry.getWorkCenterCode();

		WorkCenter wc = findParent(entry, workCenterCode, WorkCenter.class, workCenterDao, context);

		synchronized (context) {
			wc.getResources().add(entry);
		}

	}

	@Override
	public void delete(Machine entry, ApsData context) throws DataModelDaoException {
		String workCenterCode = entry.getWorkCenterCode();

		WorkCenter wc = findParent(entry, workCenterCode, WorkCenter.class, workCenterDao, context);

		synchronized (context) {
			wc.getResources().remove(entry);
		}

	}

}