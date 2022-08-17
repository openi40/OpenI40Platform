package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsReservableObject;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;
import com.openi40.scheduler.model.time.TimesheetReservation;

import lombok.Data;

@Data
public class TaskEquipmentInfoSample {

	@Data
	public class ResourceUse<RType extends AbstractApsReservableObject> {
		RType resource = null;
		Group<RType> group = null;
		TimeSegmentsGroup timeGroup = null;
	}

	@Data
	public class ResourceUseGroup<RType extends AbstractApsReservableObject> {
		Group<RType> group = null;
		List<ResourceUse<RType>> resources = new ArrayList<TaskEquipmentInfoSample.ResourceUse<RType>>();
	}

	@Data
	public class EquipmentSet {
		ResourceUse<Machine> choosedMachine = new ResourceUse<Machine>();
		List<ResourceUseGroup<Resource>> secondaryResources = new ArrayList<TaskEquipmentInfoSample.ResourceUseGroup<Resource>>();
	}

	private TaskEquipmentModelInfo metaInfo = null;
	private EquipmentSet preparation = new EquipmentSet();
	private EquipmentSet execution = new EquipmentSet();

	public TaskEquipmentInfoSample(TaskEquipmentInfo info, Task ownerTask) {
		this.copyFrom(info, ownerTask);
	}

	private void copyReservations(List<TimesheetReservation> reservations, ResourceUse resourceUse,
			TimeSegmentType segmentType, Task ownerTask) {
		resourceUse.timeGroup = new TimeSegmentsGroup(segmentType, ownerTask);
		for (TimesheetReservation timesheetReservation : reservations) {
			resourceUse.timeGroup.Add(new TimeSegment(timesheetReservation));
		}
	}

	private ResourceUseGroup<Resource> cloneSecondary(Use<Resource, ?, ResourceGroup> use, TimeSegmentType segmentType,
			Task ownerTask) {
		ResourceUseGroup<Resource> useGroup = new ResourceUseGroup<Resource>();
		useGroup.group = use.getMetaInfo().getGroup();
		List<Resource> choosed = use.getChoosenEquipmentList();
		for (Resource resource : choosed) {
			ResourceUse<Resource> resourceUse = new ResourceUse<Resource>();
			resourceUse.resource = resource;
			resourceUse.group = useGroup.group;
			useGroup.resources.add(resourceUse);
			List<TimesheetReservation> reservations = new ArrayList<TimesheetReservation>();
			for (TimesheetReservation reservation : use.getTimesheetReservations()) {
				if (reservation.getReservedObject() == resource) {
					reservations.add(reservation);
				}
			}
			copyReservations(reservations, resourceUse, segmentType, ownerTask);
		}
		return useGroup;

	}

	private void copyFrom(TaskEquipmentInfo info, Task ownerTask) {
		metaInfo = info.getMetaInfo();
		if (info.getPreparation() != null) {
			if (info.getPreparation().getResource() != null) {
				Machine choosedEquipment = info.getPreparation().getResource().getChoosenEquipment();
				this.preparation.choosedMachine.resource = choosedEquipment;
				this.preparation.choosedMachine.group = info.getPreparation().getResource().getMetaInfo().getGroup();
				copyReservations(info.getPreparation().getResource().getTimesheetReservations(),
						this.preparation.choosedMachine, TimeSegmentType.SETUP_TIME, ownerTask);
				for (SetupSecondaryResourceInfos secondary : info.getPreparation().getSecondaryResources()) {
					this.preparation.secondaryResources
							.add(cloneSecondary(secondary, TimeSegmentType.SETUP_TIME, ownerTask));
				}

			}
			if (info.getExecution().getResource() != null) {
				Machine choosedEquipment = info.getExecution().getResource().getChoosenEquipment();
				this.execution.choosedMachine.resource = choosedEquipment;
				this.execution.choosedMachine.group = info.getExecution().getResource().getMetaInfo().getGroup();
				copyReservations(info.getExecution().getResource().getTimesheetReservations(),
						this.execution.choosedMachine, TimeSegmentType.WORK_TIME, ownerTask);
				for (WorkSecondaryResourceInfos secondary : info.getExecution().getSecondaryResources()) {
					this.execution.secondaryResources
							.add(cloneSecondary(secondary, TimeSegmentType.WORK_TIME, ownerTask));
				}
			}
		}
	}

}
