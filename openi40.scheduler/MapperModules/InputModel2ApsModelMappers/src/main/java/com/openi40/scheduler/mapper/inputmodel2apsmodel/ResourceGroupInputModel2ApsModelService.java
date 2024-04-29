package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.time.Timesheet;
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
public class ResourceGroupInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<ResourceGroupInputDto, ResourceGroup> {
	@Autowired
	ILazyContextualBusinessLogicFactoryLoader contextualBLFactory;

	@Autowired
	public ResourceGroupInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(ResourceGroupInputDto.class, ResourceGroup.class, beanFactory, businessModelFactory);

	}

	@Override
	public void copyValue(ResourceGroupInputDto originalObject, ResourceGroup targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IContextualBusinessLogicFactory factory = contextualBLFactory.getComponentFactory();

		if (originalObject.getResourcesNumber() > 0) {
			if (originalObject.getResourcesNumber() != targetObject.getResources().size()) {
				while (originalObject.getResourcesNumber() > targetObject.getResources().size()) {
					Resource rc = businessModelFactory.create(Resource.class, targetObject.getContext());
					rc.setCode(originalObject.getCode() + "-" + targetObject.getResources().size());
					rc.setResourcesGroupCode(originalObject.getCode());
					rc.setTimesheetMetaInfoCode(originalObject.getTimesheetMetaInfoCode());
					rc.setInfiniteCapacity(
							originalObject.getInfiniteCapacity() != null && originalObject.getInfiniteCapacity());
					targetObject.getResources().add(rc);
				}
				while (originalObject.getResourcesNumber() < targetObject.getResources().size()) {
					targetObject.getResources().remove(targetObject.getResources().size() - 1);
				}
				for (Resource rc : targetObject.getResources()) {
					rc.setTimesheetMetaInfoCode(originalObject.getTimesheetMetaInfoCode());
					ITimesheetLogic timesheetLogic = factory.create(ITimesheetLogic.class, rc,
							targetObject.getContext());
					Timesheet calendar = timesheetLogic.createCleanCalendar(rc);
					rc.setTimesheet(calendar);
				}
			}
		}
	}

}