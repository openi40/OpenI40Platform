package com.openi40.scheduler.engine.resallocator;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.material.MaterialChoice;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;

@BusinessInterface(entityClass = Task.class)
public interface IResourcesAllocationsPlanner extends IBusinessLogic<Task> {
	void reserveResources(ResourcesCombination  combination);
	void discardResourcesOptions(ResourcesCombination combination);
	List<ResourcesCombination> elaborateAllocationPlans(List<EquipmentChoice> equipmentPlans,List<MaterialChoice> materialPlans,Task task, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange,ApsSchedulingSet action, ApsLogicDirection direction, IRuleSolutionListener constraintSolutionListener);
	List<ResourcesCombination> elaborateUnderProductionPlans(Machine usedMachine, List<EquipmentChoice> equpmentChoices,
			List<MaterialChoice> materialPlans, Task task,
			ApsSchedulingSet schedulingSet, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener);
}
