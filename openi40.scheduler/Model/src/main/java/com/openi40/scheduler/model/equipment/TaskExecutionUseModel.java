package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class TaskExecutionUseModel<EquipmentType extends ITimesheetAllocableObject, EquipmentGroupType extends Group<EquipmentType>> extends UseModel<EquipmentGroupType, EquipmentType> {
	public TaskExecutionUseModel(Class<EquipmentType> equipmentClass, Class<EquipmentGroupType> equipmentGroupClass) {
		super(null, equipmentClass, equipmentGroupClass);
	}

	public TaskExecutionUseModel(ApsData context, Class<EquipmentType> equipmentClass, Class<EquipmentGroupType> equipmentGroupClass) {
		super(context, equipmentClass, equipmentGroupClass);
	}

	private int MinQty;

	public final int getMinQty() {
		return MinQty;
	}

	public final void setMinQty(int value) {
		MinQty = value;
	}

	private int MaxQty;

	public final int getMaxQty() {
		return MaxQty;
	}

	public final void setMaxQty(int value) {
		MaxQty = value;
	}

	private List<Integer> DiscreteRange = new ArrayList<Integer>();

	public final List<Integer> getDiscreteRange() {
		return DiscreteRange;
	}

	public final void setDiscreteRange(List<Integer> value) {
		DiscreteRange = value;
	}

	private boolean AffectsCapacity;

	public final boolean getAffectsCapacity() {
		return AffectsCapacity;
	}

	public final void setAffectsCapacity(boolean value) {
		AffectsCapacity = value;
	}

}