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
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class ApsSchedulingSetDto2ApsSchedulingSetService
		extends AbstractReflectorMapper<ApsSchedulingSetDto, ApsSchedulingSet>
		implements IMapper<ApsSchedulingSetDto, ApsSchedulingSet> {
	@Autowired
	IWorkOrderDao workOrderDao = null;

	public ApsSchedulingSetDto2ApsSchedulingSetService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ApsSchedulingSetDto.class, ApsSchedulingSet.class,
				ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(ApsSchedulingSetDto originalObject, ApsSchedulingSet targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (originalObject.getWorkOrders() != null) {
			ApsData context = targetObject.getContext();
			for (ClientDto wo : originalObject.getWorkOrders()) {
				try {
					WorkOrder wObject = workOrderDao.findByCode(wo.getCode(), context);
					targetObject.addWorkOrder(wObject);
				} catch (DataModelDaoException e) {
					String msg = "Error searching for workOrder with code:" + wo.getCode();
					throw new MapperException(msg, e);
				}
			}
		}
	}

}