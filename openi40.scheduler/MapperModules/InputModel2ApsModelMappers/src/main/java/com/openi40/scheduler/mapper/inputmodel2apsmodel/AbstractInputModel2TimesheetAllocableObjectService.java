package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.AbstractApsReservableObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.time.Timesheet;
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
public class AbstractInputModel2TimesheetAllocableObjectService<InputDtoType extends InputDto, ReservableType extends ITimesheetAllocableObject>
		extends AbstractInputModel2ApsModelService<InputDtoType, ReservableType> {
	@Autowired
	ILazyContextualBusinessLogicFactoryLoader contextualBLFactory;

	public AbstractInputModel2TimesheetAllocableObjectService(Class<InputDtoType> inputType, Class<ReservableType> apsType,
			AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(inputType, apsType, beanFactory, businessModelFactory);

	}

	@Override
	public ReservableType mapInstance(InputDtoType object, Class<ReservableType> targetType,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		ReservableType instance = super.mapInstance(object, targetType, entityFactory, environment, recursive);

		return instance;
	}

	@Override
	public void copyValue(InputDtoType originalObject, ReservableType targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IContextualBusinessLogicFactory factory = contextualBLFactory.getComponentFactory();
		ITimesheetLogic timesheetLogic = factory.create(ITimesheetLogic.class, targetObject, targetObject.getContext());
		Timesheet calendar = timesheetLogic.createCleanCalendar(targetObject);
		targetObject.setTimesheet(calendar);
	}

}
