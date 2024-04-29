package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
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
public class PlantDataModelDao extends AbstractApsDataModelDao<Plant> implements IPlantDao {

	public PlantDataModelDao() {
		super(Plant.class,
				new DataPathCfg(Plant.class)
						.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants")
						.withPath(ProductiveCompany.class, "ProductiveCompany/Plants"));

	}

	@Autowired
	IProductiveCompanyDao productiveCompanyDao;

	@Override
	public void insert(Plant entry, ApsData context) throws DataModelDaoException {
		String pcCode = entry.getProductiveCompanyCode();
		ProductiveCompany pc = findParent(entry, pcCode, ProductiveCompany.class, productiveCompanyDao, context);
		synchronized (context) {
			pc.getPlants().add(entry);
			entry.setParent(pc);
		}
	}

	@Override
	public void delete(Plant entry, ApsData context) throws DataModelDaoException {
		String pcCode = entry.getProductiveCompanyCode();
		ProductiveCompany pc = findParent(entry, pcCode, ProductiveCompany.class, productiveCompanyDao, context);
		synchronized (context) {
			pc.getPlants().remove(entry);
			entry.setParent(null);
		}
		
	}
}