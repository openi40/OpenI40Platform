package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Product;
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
public class ProductDao extends AbstractApsDataModelDao<Product> implements IProductDao {

	public ProductDao() {
		super(Product.class, new DataPathCfg(Product.class).withPath(ApsData.class, "ApsData/Products"));

	}

	@Override
	public void insert(Product entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getProducts().add(entry);
		}
	}

	@Override
	public void delete(Product entry, ApsData context) throws DataModelDaoException {
		synchronized (context) {
			context.getProducts().remove(entry);
		}
		
	}

}