package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.ISalesOrderLineDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class WorkOrderDataModelDao extends AbstractApsDataModelDao<WorkOrder> implements IWorkOrderDao {

	public WorkOrderDataModelDao() {
		super(WorkOrder.class, new DataPathCfg(WorkOrder.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/WorkOrders")
				.withPath(Plant.class, "Plant/WorkOrders"));

	}

	@Autowired
	IPlantDao plantDao;
	@Autowired
	ISalesOrderLineDao salesOrderLineDao;

	@Override
	public void insert(WorkOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		String salesOrderLineCode = entry.getSalesOrderLineCode();
		SalesOrderLine orderLine = null;
		if (salesOrderLineCode != null) {
			orderLine = salesOrderLineDao.findByCode(salesOrderLineCode, context);

		}
		synchronized (context) {
			plant.getWorkOrders().add(entry);
			entry.setParentOrderLine(orderLine);			
		}

	}

	@Override
	public void delete(WorkOrder entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getWorkOrders().remove(entry);
			entry.setParentOrderLine(null);
		}

	}

	@Override
	public List<WorkOrder> findBySalesOrderLineCode(String code, ApsData context) throws DataModelDaoException {

		return this.findBySelector((wo) -> {
			return wo.getSalesOrderLineCode() != null && code != null && code.equals(wo.getSalesOrderLineCode());
		}, context);
	}

}