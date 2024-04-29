package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ICycleModelDao;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.ItemProducedMetaInfo;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.WorkOrder;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Service
public class WorkOrderInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<WorkOrderInputDto, WorkOrder> {
	static Logger LOGGER = LoggerFactory.getLogger(WorkOrderInputModel2ApsModelService.class);

	public WorkOrderInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(WorkOrderInputDto.class, WorkOrder.class, beanFactory, businessModelFactory);

	}

	@Autowired
	ICycleModelDao cycleModelDao;
	@Autowired
	IProductDao productDao;

	@Override
	public void copyValue(WorkOrderInputDto originalObject, WorkOrder targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {

		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (recursive) {
			try {

				CycleModel cycleModel = cycleModelDao.findByCode(originalObject.getCycleCode(),
						targetObject.getContext());
				targetObject.setCycleModel(cycleModel);
				if (targetObject.getProducedPart() == null) {
					targetObject.setProducedPart(new ItemProducedMetaInfo(targetObject.getContext()));
				}
				if (targetObject.getProducedPart().getSuppliedItem() == null) {
					targetObject.getProducedPart().setSuppliedItem(
							productDao.findByCode(originalObject.getProductCode(), targetObject.getContext()));
				}
				if (targetObject.getProducedPart().getQty() == 0.0) {
					targetObject.getProducedPart().setQty(1.0);
				}
			} catch (DataModelDaoException e) {
				String msg = "Error accessing data model";
				LOGGER.error(msg, e);
				throw new MapperException(msg, e);
			}
		}

	}

}
