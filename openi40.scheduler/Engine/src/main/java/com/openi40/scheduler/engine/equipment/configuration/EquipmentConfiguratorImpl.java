package com.openi40.scheduler.engine.equipment.configuration;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.asm.AsmManager.ModelInfo;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.tasks.Task;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
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
	public List<TaskEquipmentInfo> calculatePlanningConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task scheduledActivity, ApsData scheduleDataHolder) {
		IPlanningConfigurationResourcesExpansor expansor = this.componentsFactory
				.create(IPlanningConfigurationResourcesExpansor.class, scheduleDataHolder, scheduleDataHolder);
		List<TaskEquipmentInfo> outList = new ArrayList<TaskEquipmentInfo>();
		List<TaskEquipmentModelInfo> expanded = expansor.expandList(toConfigure.getEquipmentModels(), scheduledActivity,
				scheduledActivity.getParentSchedulingSet());
		for (TaskEquipmentModelInfo model : expanded) {
			TaskPreparationPlanned setup = ConfigurationDataComposer.configure(model.getPreparationModel(),
					apsLogicOptions, scheduledActivity, scheduleDataHolder);
			TaskExecutionPlanned working = ConfigurationDataComposer.configure(model.getExecutionModel(),
					apsLogicOptions, scheduledActivity, scheduleDataHolder);
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

	/***
	 * Get alternative configurations for a single equipment metaInfo and a
	 * preselected Machine The logic of this method is to try to allocate the III
	 * parameter indicated secondary resources for setup, the IV for work and if not
	 * contained inside that list try to allocate still the taskEquipmentInfoSample
	 * indicated secondary resources to try to have stable indicated secondary
	 * resources during production control.
	 * 
	 * @param modelInfo
	 * @param presetMachine
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @param taskEquipmentInfoSample
	 * @param setupUsedResourcesInfo
	 * @param workUsedResourcesInfo
	 * @return
	 */
	@Override
	public List<TaskEquipmentInfo> calculateProducingConfigurations(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder,
			TaskEquipmentInfoSample taskEquipmentInfoSample, List<UsedSecondaryResourcesInfo> setupUsedResourcesInfo,
			List<UsedSecondaryResourcesInfo> workUsedResourcesInfo) {
		IProducingConfigurationResourcesExpansor expansor = this.componentsFactory
				.create(IProducingConfigurationResourcesExpansor.class, scheduleDataHolder, scheduleDataHolder);

		return expansor.calculateConfigurations(modelInfo, presetMachine, apsLogicOptions, task, scheduleDataHolder,
				taskEquipmentInfoSample, setupUsedResourcesInfo, workUsedResourcesInfo);
	}

}