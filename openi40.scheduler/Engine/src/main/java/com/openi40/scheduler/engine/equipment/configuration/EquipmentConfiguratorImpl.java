package com.openi40.scheduler.engine.equipment.configuration;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.tasks.Task;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IEquipmentConfigurator.class, entityClass = TaskEquipmentModelOptions.class)
public class EquipmentConfiguratorImpl extends BusinessLogic<TaskEquipmentModelOptions>
		implements IEquipmentConfigurator {
	/**
	 * Get alternative configurations for all Setup+Working equipments expressed as
	 * first parameter without doing calendar allocations
	 * 
	 * @param toConfigure
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	public List<TaskEquipmentInfo> calculateEquipmentConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task scheduledActivity, ApsData scheduleDataHolder) {
		IEquipmentConfigurationResourcesExpansor expansor = this.componentsFactory
				.create(IEquipmentConfigurationResourcesExpansor.class, scheduleDataHolder, scheduleDataHolder);
		List<TaskEquipmentInfo> outList = new ArrayList<TaskEquipmentInfo>();
		List<TaskEquipmentModelInfo> expanded = expansor.expandList(toConfigure.getEquipmentModels(), scheduledActivity,
				scheduledActivity.getParentSchedulingSet());
		for (TaskEquipmentModelInfo model : expanded) {
			TaskPreparationPlanned setup = ConfigurationDataComposer.configure(model.getPreparationModel(),
					scheduledActivity, scheduleDataHolder);
			TaskExecutionPlanned working = ConfigurationDataComposer.configure(model.getExecutionModel(),
					scheduledActivity, scheduleDataHolder);
			TaskEquipmentInfo tinfo = new TaskEquipmentInfo(scheduleDataHolder);
			tinfo.setSetupGroupCode(model.getSetupGroupCode());
			tinfo.setMetaInfo(model);
			tinfo.setPreparation(setup);
			tinfo.setExecution(working);
			tinfo.setTaskInfo(new TaskProcessInfo(scheduleDataHolder, model.getTaskMetaInfo()));
			tinfo.setSetupGroupCode(model.getSetupGroupCode());
			outList.add(tinfo);
		}

		return outList;
	}

	

}