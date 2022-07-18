package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
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
public class ProductiveCompanyDataModelDao extends AbstractApsDataModelDao<ProductiveCompany>
		implements IProductiveCompanyDao {

	public ProductiveCompanyDataModelDao() {
		super(ProductiveCompany.class,
				new DataPathCfg(ProductiveCompany.class).withPath(ApsData.class, "ApsData/ProductiveCompanies"));

	}

	@Override
	public void insert(ProductiveCompany entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getProductiveCompanies().add(entry);
		}
	}

	@Override
	public void delete(ProductiveCompany entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getProductiveCompanies().remove(entry);
		}
		
	}

}