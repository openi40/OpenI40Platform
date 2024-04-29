package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Demand;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
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
public class SalesOrderLineInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<SalesOrderLineInputDto, SalesOrderLine> {

	public SalesOrderLineInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(SalesOrderLineInputDto.class, SalesOrderLine.class, beanFactory, businessModelFactory);

	}

	@Autowired
	IProductDao productDao;

	@Override
	public void copyValue(SalesOrderLineInputDto originalObject, SalesOrderLine targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		try {
			targetObject.setExplodeWorkOrders(originalObject.isExplodeWorkOrders());
			targetObject.setExplodeWithCycleCode(originalObject.getExplodeWithCycleCode());
			Product product = null;
			product = productDao.findByCode(originalObject.getProductCode(), targetObject.getContext());
			if (targetObject.getDemand() == null || targetObject.getDemand().getProduct() == null) {
				targetObject.setDemand(new Demand(targetObject.getContext()));

				targetObject.getDemand().setProduct(product);

			}
			targetObject.setProduct(product);
			targetObject.getDemand().setRequiredDateTime(originalObject.getAskedDeliveryDate());
			targetObject.getDemand().setRequiredQty(originalObject.getResidualQty());
		} catch (DataModelDaoException e) {
			throw new MapperException("unable to use IItemDao", e);
		}
	}

}
