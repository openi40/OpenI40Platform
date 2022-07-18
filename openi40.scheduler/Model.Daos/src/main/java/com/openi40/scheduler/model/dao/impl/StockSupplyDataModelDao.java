package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.StockSupply;
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
@Service
public class StockSupplyDataModelDao extends AbstractSupplyDao<StockSupply> implements IStockSupplyDao {

	public StockSupplyDataModelDao() {
		super(StockSupply.class, new DataPathCfg(StockSupply.class)
				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Warehouses/Warehouse/StockSupplies")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/Warehouses/Warehouse/StockSupplies")
				.withPath(Plant.class, "Plant/Warehouses/Warehouse/StockSupplies")
				.withPath(Warehouse.class, "Warehouse/StockSupplies"));

	}
	@Autowired IWarehouseDao warehouseDao;
	
	@Override
	public void insert(StockSupply entry, ApsData context) throws DataModelDaoException {
		Warehouse warehouse;
		String warehouseCode=entry.getWarehouseCode();
		warehouse=this.findParent(entry, warehouseCode, Warehouse.class, warehouseDao, context);
		synchronized(context) {
			warehouse.getStockSupplies().add(entry);
		}
	}

	@Override
	public void delete(StockSupply entry, ApsData context) throws DataModelDaoException {
		Warehouse warehouse;
		String warehouseCode=entry.getWarehouseCode();
		warehouse=this.findParent(entry, warehouseCode, Warehouse.class, warehouseDao, context);
		synchronized(context) {
			warehouse.getStockSupplies().remove(entry);
		}
		
	}

}