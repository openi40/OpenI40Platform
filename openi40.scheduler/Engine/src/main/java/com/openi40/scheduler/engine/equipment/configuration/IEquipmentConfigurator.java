package com.openi40.scheduler.engine.equipment.configuration;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
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
 *
 *         Component that reads equipment meta-informations and generates all
 *         possible equipment configurations that they reppresent
 */
@BusinessInterface(entityClass = TaskEquipmentModelOptions.class)
public interface IEquipmentConfigurator extends IBusinessLogic<TaskEquipmentModelOptions> {
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
	List<TaskEquipmentInfo> calculatePlanningConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder);

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
	 * @param usedResourcesInfo       TODO
	 * @return
	 */
	List<TaskEquipmentInfo> calculateProducingConfigurations(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder,
			TaskEquipmentInfoSample taskEquipmentInfoSample, List<UsedSecondaryResourcesInfo> setupUsedResourcesInfo,
			List<UsedSecondaryResourcesInfo> workUsedResourcesInfo);
}