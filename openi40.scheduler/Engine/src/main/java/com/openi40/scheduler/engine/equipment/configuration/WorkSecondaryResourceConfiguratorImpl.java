package com.openi40.scheduler.engine.equipment.configuration;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = IWorkSecondaryResourceConfigurator.class, entityClass = TaskExecutionModel.SecondaryModelInfo.class)

public class WorkSecondaryResourceConfiguratorImpl extends AbstractWorkResourceConfigurator<Resource, ResourceGroup, TaskExecutionModel.SecondaryModelInfo> implements IWorkSecondaryResourceConfigurator {
	public WorkSecondaryResourceConfiguratorImpl() {
		super(TaskExecutionModel.SecondaryModelInfo.class);
	}

	@Override
	public List<TaskExecutionModel.SecondaryModelInfo> explodeConfigurations(TaskExecutionModel.SecondaryModelInfo config, Task task, ApsSchedulingSet action) {
		List<TaskExecutionModel.SecondaryModelInfo> secondary = super.explodeConfigurations(config, task, action);
		for (TaskExecutionModel.SecondaryModelInfo entry : secondary) {
			entry.setGroupingPolicy(config.getGroupingPolicy());
		}
		return secondary;
	}
}