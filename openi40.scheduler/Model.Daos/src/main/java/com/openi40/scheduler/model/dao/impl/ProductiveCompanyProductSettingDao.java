package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyProductSettingDao;
import com.openi40.scheduler.model.material.configuration.ProductiveCompanyProductSetting;
import com.openi40.scheduler.model.material.configuration.PlantProductSetting;
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
public class ProductiveCompanyProductSettingDao extends AbstractProductSettingDao<ProductiveCompanyProductSetting> implements IProductiveCompanyProductSettingDao{

	public ProductiveCompanyProductSettingDao() {
		super(ProductiveCompanyProductSetting.class, new DataPathCfg(ProductiveCompanyProductSetting.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/ProductSettings"));

	}
	@Autowired
	IProductiveCompanyDao productiveCompanyDao;
	@Override
	public void insert(ProductiveCompanyProductSetting entry, ApsData context) throws DataModelDaoException {
		String pcCode = entry.getProductiveCompanyCode();
		ProductiveCompany pc = findParent(entry, pcCode, ProductiveCompany.class, productiveCompanyDao, context);
		synchronized (context) {
			pc.getProductSettings().add(entry);
			
		}
	}

	@Override
	public void delete(ProductiveCompanyProductSetting entry, ApsData context) throws DataModelDaoException {
		String pcCode = entry.getProductiveCompanyCode();
		ProductiveCompany pc = findParent(entry, pcCode, ProductiveCompany.class, productiveCompanyDao, context);
		synchronized (context) {
			pc.getProductSettings().remove(entry);			
		}
	}

}
