package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
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
public class WarehouseDataModelDao extends AbstractApsDataModelDao<Warehouse> implements IWarehouseDao {

	public WarehouseDataModelDao() {
		super(Warehouse.class, new DataPathCfg(Warehouse.class).withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Warehouses").withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/Warehouses").withPath(Plant.class, "Plant/Warehouses"));

	}
	@Autowired
	IPlantDao plantDao;
	@Override
	public void insert(Warehouse entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);

		synchronized (context) {
			plant.getWarehouses().add(entry);
			entry.setParent(plant);
		}
		
	}

	@Override
	public void delete(Warehouse entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();

		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);

		synchronized (context) {
			plant.getWarehouses().remove(entry);
			entry.setParent(null);
		}
		
	}
}