package com.openi40.scheduler.model.equipment;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ApsModelException;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;
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
@Data
public abstract class UseModel<EquipmentGroupType extends Group<IObjectWithCalendarType>, IObjectWithCalendarType extends ITimesheetAllocableObject> extends AbstractApsObject implements IMetaInfo {
	Class<EquipmentGroupType> equipmentGroupClass = null;
	private boolean fromBegin = false;
	private boolean fromEnd = false;
	private boolean fullTime = true;
	private EquipmentGroupType group = null;
	private GroupingPolicy groupingPolicy = GroupingPolicy.INSTANCE_IDENTIFIED;
	private int qty;
	private ResourceRequiredCalculationType resourceRequiredCalculationType=ResourceRequiredCalculationType.CONSTANT;
	private double resourceCalculatedLot=1;
	private Class<IObjectWithCalendarType> resourcesClass = null;
	private long time = 0;
	public UseModel(ApsData context, Class<IObjectWithCalendarType> resourcesClass, Class<EquipmentGroupType> equipmentGroupClass) {
		super(context);
		this.resourcesClass = resourcesClass;
		this.equipmentGroupClass = equipmentGroupClass;
		groupingPolicy = GroupingPolicy.INSTANCE_IDENTIFIED;
		resourceRequiredCalculationType=ResourceRequiredCalculationType.CONSTANT;
		try {
			this.group = equipmentGroupClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ApsModelException("Exception allocating ResourceGroup", e);
		}
	}


}