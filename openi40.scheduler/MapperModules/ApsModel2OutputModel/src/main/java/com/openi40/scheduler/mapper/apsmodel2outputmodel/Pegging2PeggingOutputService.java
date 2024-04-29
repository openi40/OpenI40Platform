package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
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
public class Pegging2PeggingOutputService extends AbstractApsModel2OutputModelService<Pegging, PeggingOutputDto> {

	public Pegging2PeggingOutputService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Pegging.class, PeggingOutputDto.class);

	}

	public void copyValue(Pegging originalObject, PeggingOutputDto targetObject,
			com.openi40.scheduler.mapper.IEntitiesFactory entityFactory, java.util.Map environment, boolean recursive)
			throws com.openi40.scheduler.mapper.MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setPeggingQty(originalObject.getTotalQty());
		targetObject.setConsumerWorkOrderCode(
				originalObject.getConsumer() != null ? originalObject.getConsumer().getCode() : null);
		targetObject.setProducerWorkOrderCode(
				originalObject.getSupplier() != null ? originalObject.getSupplier().getCode() : null);
		targetObject.setConsumerTaskCode(
				originalObject.getEdge() != null ? originalObject.getEdge().getConsumerTask().getCode() : null);
		targetObject.setProducerTaskCode(
				originalObject.getEdge() != null ? originalObject.getEdge().getProducerTask().getCode() : null);
	};

}
