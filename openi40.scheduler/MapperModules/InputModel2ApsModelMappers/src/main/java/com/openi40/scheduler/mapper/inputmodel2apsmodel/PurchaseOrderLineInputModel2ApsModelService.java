package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderLineInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Demand;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
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
public class PurchaseOrderLineInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<PurchaseOrderLineInputDto, PurchaseOrderLine> {

	public PurchaseOrderLineInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(PurchaseOrderLineInputDto.class, PurchaseOrderLine.class, beanFactory, businessModelFactory);

	}

	@Autowired
	IProductDao productDao;

	@Override
	public void copyValue(PurchaseOrderLineInputDto originalObject, PurchaseOrderLine targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (targetObject.getSupply() == null || targetObject.getSupply().getSuppliedItem() == null) {
			targetObject.setSupply(businessModelFactory.create(PurchaseSupply.class, targetObject.getContext()));
			Product product;
			try {
				product = productDao.findByCode(originalObject.getProductCode(), targetObject.getContext());
				targetObject.getSupply().setSuppliedItem(product);
			} catch (DataModelDaoException e) {
				throw new MapperException("unable to use IItemDao", e);
			}
		}
		Date _date = originalObject.getPlannedDeliveryDate();
		if (_date == null)
			_date = originalObject.getAskedDeliveryDate();
		targetObject.getSupply().setAvailabilityDateTime(_date);
		targetObject.getSupply().setOrderCode(targetObject.getCode());
		targetObject.getSupply().setQtyTotal(targetObject.getTotalQty());

	}

}
