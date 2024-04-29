package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.time.TimesheetReservation;
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

public class Use<EquipmentType extends ITimesheetAllocableObject, EquipmentModelType extends UseModel<EquipmentGroupType, EquipmentType>, EquipmentGroupType extends Group<EquipmentType>>
		extends AbstractApsObject implements IReferencingMetaInfo<EquipmentModelType>, ICloneable, Cloneable {
	private List<TimesheetReservation> timesheetReservations = new ArrayList<TimesheetReservation>();
	private EquipmentType choosenEquipment;

	private List<EquipmentType> choosenEquipmentList = new ArrayList<EquipmentType>();

	private EquipmentModelType metaInfo;

	private ResourceRequiredCalculationType resourceRequiredCalculationType = ResourceRequiredCalculationType.CONSTANT;

	private int usedQty;
	private double resourceCalculatedLot = 1;

	public Use(ApsData context) {
		super(context);
	}

	public final ICloneable cleanClone() throws CloneNotSupportedException {
		Use<EquipmentType, EquipmentModelType, EquipmentGroupType> cp = (Use<EquipmentType, EquipmentModelType, EquipmentGroupType>) clone();
		cp.setChoosenEquipment(null);
		cp.setChoosenEquipmentList(new ArrayList<>());
		cp.setTimesheetReservations(new ArrayList<TimesheetReservation>());
		return cp;
	}

	@Override
	public void resetSchedulingData() {

		for (TimesheetReservation reservation : getTimesheetReservations()) {
			reservation.resetSchedulingData();

		}
		if (this.getChoosenEquipment() != null) {
			this.getChoosenEquipment().resetSchedulingData();
		}
		getTimesheetReservations().clear();
		this.setChoosenEquipment(null);
		if (this.getChoosenEquipmentList() != null)
			for (EquipmentType eq : this.getChoosenEquipmentList()) {
				eq.resetSchedulingData();
			}
		this.getChoosenEquipmentList().clear();
		this.setChoosenEquipmentList(new ArrayList<>());
	}

	public List<TimesheetReservation> getTimesheetReservations() {
		return timesheetReservations;
	}

	public void setTimesheetReservations(List<TimesheetReservation> timesheetReservations) {
		this.timesheetReservations = timesheetReservations;
	}

	public EquipmentType getChoosenEquipment() {
		return choosenEquipment;
	}

	public void setChoosenEquipment(EquipmentType choosenEquipment) {
		this.choosenEquipment = choosenEquipment;
	}

	public List<EquipmentType> getChoosenEquipmentList() {
		return choosenEquipmentList;
	}

	public void setChoosenEquipmentList(List<EquipmentType> choosenEquipmentList) {
		this.choosenEquipmentList = choosenEquipmentList;
	}

	public EquipmentModelType getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(EquipmentModelType metaInfo) {
		this.metaInfo = metaInfo;
	}

	public ResourceRequiredCalculationType getResourceRequiredCalculationType() {
		return resourceRequiredCalculationType;
	}

	public void setResourceRequiredCalculationType(ResourceRequiredCalculationType resourceRequiredCalculationType) {
		this.resourceRequiredCalculationType = resourceRequiredCalculationType;
	}

	public int getUsedQty() {
		return usedQty;
	}

	public void setUsedQty(int usedQty) {
		this.usedQty = usedQty;
	}

	public double getResourceCalculatedLot() {
		return resourceCalculatedLot;
	}

	public void setResourceCalculatedLot(double resourceCalculatedLot) {
		this.resourceCalculatedLot = resourceCalculatedLot;
	}

}