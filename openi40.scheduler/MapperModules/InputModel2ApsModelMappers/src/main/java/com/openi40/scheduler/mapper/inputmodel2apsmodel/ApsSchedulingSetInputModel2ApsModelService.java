package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.ApsSchedulingSetInputDto;
import com.openi40.scheduler.input.model.ScheduledWorkOrderInputDto;
import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.Department;
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
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Service
public class ApsSchedulingSetInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<ApsSchedulingSetInputDto, ApsSchedulingSet> {
	ObjectMapper objectMapper = null;
	@Autowired
	IWorkOrderDao workOrderDao;

	@Autowired
	public ApsSchedulingSetInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory, ObjectMapper objectMapper) {
		super(ApsSchedulingSetInputDto.class, ApsSchedulingSet.class, beanFactory, businessModelFactory);
		this.objectMapper = objectMapper;

	}

	@Override
	public void copyValue(ApsSchedulingSetInputDto originalObject, ApsSchedulingSet targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (originalObject.getOptions() != null) {
			String options = originalObject.getOptions();
			try {
				byte[] optionBytes = options != null ? options.getBytes() : new byte[0];
				if (optionBytes != null && optionBytes.length > 0) {
					ApsLogicOptions apsLogicOptions = objectMapper.readValue(options.getBytes(), ApsLogicOptions.class);
					targetObject.setOptions(apsLogicOptions);
				}
			} catch (IOException e) {
				throw new MapperException("cannot read ApsLogicOptions from json:" + options, e);
			}

		}
		for (ScheduledWorkOrderInputDto wo : originalObject.getScheduledWorkOrders()) {
			try {
				WorkOrder workOrder = workOrderDao.findByCode(wo.getWorkOrderCode(), targetObject.getContext());
				targetObject.addWorkOrder(workOrder);
			} catch (DataModelDaoException e) {
				throw new MapperException("problem accessing workOrder " + wo.getWorkOrderCode(), e);
			}
		}
	}
}