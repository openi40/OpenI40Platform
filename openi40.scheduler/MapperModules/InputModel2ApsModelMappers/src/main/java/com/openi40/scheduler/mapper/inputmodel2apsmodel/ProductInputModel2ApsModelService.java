package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.material.ProductInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.companystructure.Plant;
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
public class ProductInputModel2ApsModelService extends AbstractInputModel2ApsModelService<ProductInputDto, Product> {
	@Autowired 
	public ProductInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(ProductInputDto.class, Product.class, beanFactory,businessModelFactory);

	}
	@Override
	public void copyValue(ProductInputDto originalObject, Product targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setAverageMinPurchaseQty(originalObject.getAverageMinPurchaseQty());
		targetObject.setCanBeProducedByScheduler(originalObject.getCanBeProducedByScheduler());
		targetObject.setCanBePurchasedByScheduler(originalObject.getCanBePurchasedByScheduler());
		targetObject.setLeadTimeDays(originalObject.getLeadTimeDays());
	}
}