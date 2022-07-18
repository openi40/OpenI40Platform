package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
import com.openi40.scheduler.input.model.material.StockSupplyInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Product;
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
public class StockInventoryInputModel2ApsModelServiceService
		extends AbstractInputModel2ApsModelService<StockSupplyInputDto, StockSupply> {
	@Autowired 
	public StockInventoryInputModel2ApsModelServiceService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(StockSupplyInputDto.class, StockSupply.class, beanFactory,businessModelFactory);

	}

	@Autowired
	IProductDao productDao;

	@Override
	public void copyValue(StockSupplyInputDto originalObject, StockSupply targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setQtyTotal(originalObject.getPhysicalStockQuantity());
		if (targetObject.getSuppliedItem() == null) {
			Product product;
			try {
				product = productDao.findByCode(originalObject.getProductCode(), targetObject.getContext());
				if (product == null) {
					throw new MapperException("cannot retrieve item with code=" + originalObject.getProductCode());
				}
				targetObject.setSuppliedItem(product);
			} catch (DataModelDaoException e) {
				throw new MapperException("exception retrieving item with code=" + originalObject.getProductCode(), e);
			}

		}
	}

}