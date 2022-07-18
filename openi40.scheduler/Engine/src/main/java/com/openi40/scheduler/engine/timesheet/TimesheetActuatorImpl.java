package com.openi40.scheduler.engine.timesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IOperationActuator;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
public class TimesheetActuatorImpl implements IOperationActuator<TimesheetReservation> {
	@Autowired
	ILazyContextualBusinessLogicFactoryLoader lazyComponentsFactory;

	public TimesheetActuatorImpl() {

	}

	@Override
	public void apply(TimesheetReservation operation) {
		ITimesheetLogic handler = lazyComponentsFactory.getComponentFactory().create(ITimesheetLogic.class,
				operation.getReservedObject(), operation.getContext());
		handler.addReservation(operation.getReservedObject(), operation);

	}

	@Override
	public void reverse(TimesheetReservation operation) {
		ITimesheetLogic handler = lazyComponentsFactory.getComponentFactory().create(ITimesheetLogic.class,
				operation.getReservedObject(), operation.getContext());
		handler.removeReservation(operation.getReservedObject(), operation);
	}

	@Override
	public Class<TimesheetReservation> getRootOperationType() {

		return TimesheetReservation.class;
	}

}
