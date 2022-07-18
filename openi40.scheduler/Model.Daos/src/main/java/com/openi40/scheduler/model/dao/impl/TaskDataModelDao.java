package com.openi40.scheduler.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;

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
@Service
public class TaskDataModelDao extends AbstractApsDataModelDao<Task> implements ITaskDao {
	@Autowired
	IWorkOrderDao workOrderDao;

	public TaskDataModelDao() {
		super(Task.class, new DataPathCfg(Task.class)

				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ChildTasks")

				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ChildTasks")

				.withPath(Plant.class, "Plant/WorkOrders/WorkOrder/ChildTasks"));

	}

	@Override
	public void insert(Task entry, ApsData context) throws DataModelDaoException {
		WorkOrder wo = workOrderDao.findByCode(entry.getWorkOrderCode(), context);
		if (entry.isWorkOrderRootTask()) {

			wo.setRootTask(entry);
		}
		wo.getChildTasks().add(entry);
		entry.setWorkOrder(wo);
	}

	@Override
	public void delete(Task entry, ApsData context) throws DataModelDaoException {

	}

}