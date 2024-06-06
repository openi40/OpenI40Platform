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
@BusinessInterface(entityClass = Task.class)
public interface IResourcesAllocator extends IBusinessLogic<Task> {
		
		
		ResourcesCombination elaborateAllocation(List<EquipmentChoice> equipmentPlans,List<MaterialChoice> materialPlans,Task task, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange,ApsSchedulingSet action, ApsLogicDirection direction, IRuleSolutionListener constraintSolutionListener);

		ResourcesCombination elaborateUnderProductionAllocation(Machine usedMachine, List<EquipmentChoice> equpmentChoices,
				List<MaterialChoice> materialPlans, Task task,
				ApsSchedulingSet schedulingSet, ApsLogicDirection direction,
				IRuleSolutionListener constraintSolutionListener);
}
