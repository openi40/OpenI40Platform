package com.openi40.scheduler.model.equipment;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class TaskPreparationUseModel<EquipmentType extends ITimesheetAllocableObject, EquipmentGroupType extends Group<EquipmentType>> extends UseModel<EquipmentGroupType, EquipmentType> {
	public TaskPreparationUseModel(Class<EquipmentType> equipmentClass, Class<EquipmentGroupType> equipmentGroupClass) {
		super(null, equipmentClass, equipmentGroupClass);
	}

	public TaskPreparationUseModel(ApsData context, Class<EquipmentType> equipmentClass, Class<EquipmentGroupType> equipmentGroupClass) {
		super(context, equipmentClass, equipmentGroupClass);
	}
}