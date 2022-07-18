package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.model.companystructure.Department;
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
public class DepartmentInputModel2ApsModelServiceService
		extends AbstractInputModel2ApsModelService<DepartmentInputDto, Department> {
	@Autowired 
	public DepartmentInputModel2ApsModelServiceService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(DepartmentInputDto.class, Department.class, beanFactory,businessModelFactory);

	}

}