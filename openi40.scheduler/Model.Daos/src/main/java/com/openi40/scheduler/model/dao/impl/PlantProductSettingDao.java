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
import com.openi40.scheduler.model.dao.IPlantProductSettingDao;
import com.openi40.scheduler.model.dao.IWarehouseProductSettingDao;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
import com.openi40.scheduler.model.material.configuration.WarehouseProductSetting;
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
public class PlantProductSettingDao extends AbstractProductSettingDao<PlantProductSetting>
		implements IPlantProductSettingDao {

	public PlantProductSettingDao() {
		super(PlantProductSetting.class, new DataPathCfg(PlantProductSetting.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/ProductSettings")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/ProductSettings")
				.withPath(Plant.class, "Plant/ProductSettings"));
	}

	@Autowired
	IPlantDao plantDao;

	@Override
	public void insert(PlantProductSetting entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getProductSettings().add(entry);
		}
	}

	@Override
	public void delete(PlantProductSetting entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getProductSettings().remove(entry);
		}
	}

}
