package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.dao.IWarehouseProductSettingDao;
import com.openi40.scheduler.model.material.configuration.WarehouseProductSetting;
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
public class WarehouseProductSettingDao extends AbstractProductSettingDao<WarehouseProductSetting>
		implements IWarehouseProductSettingDao {

	public WarehouseProductSettingDao() {
		super(WarehouseProductSetting.class, new DataPathCfg(WarehouseProductSetting.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Warehouses/Warehouse/ProductSettings")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/Warehouses/Warehouse/ProductSettings")
				.withPath(Plant.class, "Plant/Warehouses/Warehouse/ProductSettings")
				.withPath(Warehouse.class, "Warehouse/ProductSettings"));
	}

	@Autowired
	IWarehouseDao warehouseDao;

	@Override
	public void insert(WarehouseProductSetting entry, ApsData context) throws DataModelDaoException {
		String wCode = entry.getWarehouseCode();
		Warehouse wh = findParent(entry, wCode, Warehouse.class, warehouseDao, context);
		synchronized (context) {
			wh.getProductSettings().add(entry);
		}
	}

	@Override
	public void delete(WarehouseProductSetting entry, ApsData context) throws DataModelDaoException {
		String wCode = entry.getWarehouseCode();
		Warehouse wh = findParent(entry, wCode, Warehouse.class, warehouseDao, context);
		synchronized (context) {
			wh.getProductSettings().remove(entry);
		}
	}

}
