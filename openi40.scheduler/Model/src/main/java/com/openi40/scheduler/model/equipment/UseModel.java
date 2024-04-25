package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ApsModelException;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;
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

public abstract class UseModel<EquipmentGroupType extends Group<IObjectWithCalendarType>, IObjectWithCalendarType extends ITimesheetAllocableObject> extends AbstractApsObject implements IMetaInfo,IApsResourcesDependencyTreeObject {
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
	public Class<EquipmentGroupType> getEquipmentGroupClass() {
		return equipmentGroupClass;
	}
	public void setEquipmentGroupClass(Class<EquipmentGroupType> equipmentGroupClass) {
		this.equipmentGroupClass = equipmentGroupClass;
	}
	public boolean isFromBegin() {
		return fromBegin;
	}
	public void setFromBegin(boolean fromBegin) {
		this.fromBegin = fromBegin;
	}
	public boolean isFromEnd() {
		return fromEnd;
	}
	public void setFromEnd(boolean fromEnd) {
		this.fromEnd = fromEnd;
	}
	public boolean isFullTime() {
		return fullTime;
	}
	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}
	public EquipmentGroupType getGroup() {
		return group;
	}
	public void setGroup(EquipmentGroupType group) {
		this.group = group;
	}
	public GroupingPolicy getGroupingPolicy() {
		return groupingPolicy;
	}
	public void setGroupingPolicy(GroupingPolicy groupingPolicy) {
		this.groupingPolicy = groupingPolicy;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public ResourceRequiredCalculationType getResourceRequiredCalculationType() {
		return resourceRequiredCalculationType;
	}
	public void setResourceRequiredCalculationType(ResourceRequiredCalculationType resourceRequiredCalculationType) {
		this.resourceRequiredCalculationType = resourceRequiredCalculationType;
	}
	public double getResourceCalculatedLot() {
		return resourceCalculatedLot;
	}
	public void setResourceCalculatedLot(double resourceCalculatedLot) {
		this.resourceCalculatedLot = resourceCalculatedLot;
	}
	public Class<IObjectWithCalendarType> getResourcesClass() {
		return resourcesClass;
	}
	public void setResourcesClass(Class<IObjectWithCalendarType> resourcesClass) {
		this.resourcesClass = resourcesClass;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		ResourceDepsItemMetaInfo info=new ResourceDepsItemMetaInfo(this);
		return info;
	}
	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		List<IApsResourcesDependencyTreeObject> nodes=new ArrayList<IApsResourcesDependencyTreeObject>();
		if (group!=null) {
			nodes.add(group);
		}
		return nodes;
	}


}