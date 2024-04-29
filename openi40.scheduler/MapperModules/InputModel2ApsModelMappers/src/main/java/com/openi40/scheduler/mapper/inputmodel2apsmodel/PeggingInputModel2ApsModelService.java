package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class PeggingInputModel2ApsModelService extends AbstractInputModel2ApsModelService<PeggingInputDto, Pegging> {
	static Logger LOGGER = LoggerFactory.getLogger(PeggingInputModel2ApsModelService.class);
	@Autowired
	IWorkOrderDao workOrderDao;

	@Autowired
	public PeggingInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(PeggingInputDto.class, Pegging.class, beanFactory, businessModelFactory);

	}

	@Override
	public void copyValue(PeggingInputDto originalObject, Pegging targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		try {
			WorkOrder consumer = workOrderDao.findByCode(originalObject.getConsumerWorkOrderCode(),
					targetObject.getContext());
			WorkOrder producer = workOrderDao.findByCode(originalObject.getProducerWorkOrderCode(),
					targetObject.getContext());
			if (consumer != null) {
				consumer.getPeggings().add(targetObject);
			} else {
				LOGGER.warn("The consumer work order => " + originalObject.getConsumerWorkOrderCode()
						+ " has not been found");
			}
			if (producer != null) {
				producer.getPeggings().add(targetObject);
			} else {
				LOGGER.warn("The producer work order => " + originalObject.getProducerWorkOrderCode()
						+ " has not been found");
			}
			targetObject.setConsumer(consumer);
			targetObject.setSupplier(producer);
		} catch (DataModelDaoException e) {
			throw new MapperException(e);
		} finally {
		}
	}

}