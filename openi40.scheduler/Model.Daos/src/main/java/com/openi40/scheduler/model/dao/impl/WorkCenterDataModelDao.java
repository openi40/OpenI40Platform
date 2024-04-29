package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IDepartmentDao;
import com.openi40.scheduler.model.dao.IWorkCenterDao;
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
@Service
public class WorkCenterDataModelDao extends AbstractApsDataModelDao<WorkCenter> implements IWorkCenterDao {

	public WorkCenterDataModelDao() {
		super(WorkCenter.class, new DataPathCfg(WorkCenter.class)
				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/Departments/Department/WorkCenters")
				.withPath(Plant.class, "Plant/Departments/Department/WorkCenters"));

	}

	@Autowired
	IDepartmentDao departmentDao;

	@Override
	public void insert(WorkCenter entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getDepartmentCode();
		Department department = findParent(entry, plantCode, Department.class, departmentDao, context);
		synchronized (context) {
			entry.setParent(department);
			department.getWorkCenters().add(entry);

		}

	}

	@Override
	public void delete(WorkCenter entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getDepartmentCode();
		Department department = findParent(entry, plantCode, Department.class, departmentDao, context);
		synchronized (context) {
			entry.setParent(null);
			department.getWorkCenters().remove(entry);
		}

	}

}