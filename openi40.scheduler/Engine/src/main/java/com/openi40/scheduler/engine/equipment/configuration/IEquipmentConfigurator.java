package com.openi40.scheduler.engine.equipment.configuration;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
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
	List<TaskEquipmentInfo> calculateEquipmentConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder);

	
}