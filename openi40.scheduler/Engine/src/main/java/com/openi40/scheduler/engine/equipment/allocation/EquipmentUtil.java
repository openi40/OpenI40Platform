package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
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
public class EquipmentUtil {
	private EquipmentUtil() {
	}

	private static EquipmentUtil Instance = new EquipmentUtil();

	public static EquipmentUtil getInstance() {
		return Instance;
	}

	

	

	


	public TaskPreparationPlanned clone(TaskPreparationPlanned planned) {
		TaskPreparationPlanned plannedEquipmentIpotesis = new TaskPreparationPlanned(planned.getContext());

		plannedEquipmentIpotesis.getResource().setMetaInfo(planned.getResource().getMetaInfo());
		plannedEquipmentIpotesis.getResource().setUsedQty(planned.getResource().getUsedQty());
		plannedEquipmentIpotesis.setOwnerTask(planned.getOwnerTask());
		plannedEquipmentIpotesis.getResource().setChoosenEquipment(null);
		plannedEquipmentIpotesis.setNominalSetupTime(planned.getNominalSetupTime());
		plannedEquipmentIpotesis.setNominalChangeOverTime(planned.getNominalChangeOverTime());
		plannedEquipmentIpotesis.setNominalManteinanceBreakTime(planned.getNominalManteinanceBreakTime());
		plannedEquipmentIpotesis.setNominalManteinancePeriod(planned.getNominalManteinancePeriod());

		for (SetupSecondaryResourceInfos secondaryResourceUse : planned.getSecondaryResources()) {
			TaskPreparationPlanned.SetupSecondaryResourceInfos secondaryCopy = new TaskPreparationPlanned.SetupSecondaryResourceInfos(
					planned.getContext());
			secondaryCopy.setMetaInfo(secondaryResourceUse.getMetaInfo());
			secondaryCopy.setUsedQty(secondaryResourceUse.getUsedQty());
			secondaryCopy.setResourceRequiredCalculationType(secondaryResourceUse.getResourceRequiredCalculationType());
			secondaryCopy.setResourceCalculatedLot(secondaryResourceUse.getResourceCalculatedLot());
			plannedEquipmentIpotesis.getSecondaryResources().add(secondaryCopy);
		}

		return plannedEquipmentIpotesis;
	}

	public TaskExecutionPlanned clone(TaskExecutionPlanned planned) {
		TaskExecutionPlanned plannedEquipmentIpotesis = new TaskExecutionPlanned(planned.getContext());
		plannedEquipmentIpotesis.getResource().setMetaInfo(planned.getResource().getMetaInfo());
		plannedEquipmentIpotesis.getResource().setUsedQty(planned.getResource().getUsedQty());
		plannedEquipmentIpotesis.setOwnerTask(planned.getOwnerTask());
		plannedEquipmentIpotesis.getResource().setChoosenEquipment(null);
		plannedEquipmentIpotesis.setNominalWorkTime(planned.getNominalWorkTime());

		for (com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos secondaryResourceUse : planned
				.getSecondaryResources()) {
			TaskExecutionPlanned.WorkSecondaryResourceInfos secondaryCopy = new TaskExecutionPlanned.WorkSecondaryResourceInfos(
					plannedEquipmentIpotesis.getContext());
			secondaryCopy.setMetaInfo(secondaryResourceUse.getMetaInfo());
			secondaryCopy.setUsedQty(secondaryResourceUse.getUsedQty());
			secondaryCopy.setResourceRequiredCalculationType(secondaryResourceUse.getResourceRequiredCalculationType());
			secondaryCopy.setResourceCalculatedLot(secondaryResourceUse.getResourceCalculatedLot());
			plannedEquipmentIpotesis.getSecondaryResources().add(secondaryCopy);
		}

		return plannedEquipmentIpotesis;
	}

	public TaskEquipmentInfo clone(TaskEquipmentInfo info) {
		TaskEquipmentInfo i = new TaskEquipmentInfo(info.getContext());
		i.setSetupGroupCode(info.getSetupGroupCode());
		i.setPreparation(clone(info.getPreparation()));
		i.setExecution(clone(info.getExecution()));
		i.setCode(info.getCode());
		i.setDescription(info.getDescription());
		return i;
	}

	public <ReservableType extends ITimesheetAllocableObject> List<ReservableType> orderByLowerUsing(
			List<ReservableType> resources, IContextualBusinessLogicFactory componentsFactory, ApsData context) {
		List<ReservableType> outList = new ArrayList<>();
		Map<Long, List<ReservableType>> loadingList = new TreeMap<Long, List<ReservableType>>();
		for (ReservableType resource : resources) {
			ITimesheetLogic timesheetLogic = componentsFactory.create(ITimesheetLogic.class, resource, context);
			long actualTotalTime = timesheetLogic.getMinutesReserved(resource);
			if (!loadingList.containsKey(actualTotalTime)) {
				loadingList.put(actualTotalTime, new ArrayList<>());
			}
			loadingList.get(actualTotalTime).add(resource);
		}
		for (Entry<Long, List<ReservableType>> entry : loadingList.entrySet()) {
			List<ReservableType> rcs = entry.getValue();
			outList.addAll(rcs);
		}
		return outList;
	}
}