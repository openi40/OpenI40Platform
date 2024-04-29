package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.aps.ApsSchedulingSetDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
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
public class ApsSchedulingSet2ApsSchedulingSetDtoService extends AbstractReflectorMapper<ApsSchedulingSet, ApsSchedulingSetDto>
		implements IMapper<ApsSchedulingSet, ApsSchedulingSetDto> {

	public ApsSchedulingSet2ApsSchedulingSetDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ApsSchedulingSet.class, ApsSchedulingSetDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	public void copyValue(ApsSchedulingSet originalObject, ApsSchedulingSetDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (targetObject.getWorkOrders().isEmpty()) {
			IMapper<WorkOrder, ClientDto> mapper = getMapperFactory().createMapper(WorkOrder.class,
					ClientDto.class);
			for (WorkOrder workOrder : originalObject.getWorkOrders()) {
				targetObject.getWorkOrders()
						.add(mapper.mapInstance(workOrder, ClientDto.class, entityFactory, environment, false));
			}
		}
	}
}