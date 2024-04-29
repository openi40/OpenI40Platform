package com.openi40.scheduler.engine.rules.equipment;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.rules.IRulePlanSolver;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.equipment.EquipmentEvaluatedChoice;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.tasks.Task;
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
@BusinessInterface(entityClass = EquipmentRule.class)
public interface IEquipmentPlanSolver extends IRulePlanSolver<EquipmentRule, EquipmentChoice, EquipmentEvaluatedChoice> {

	

	List<EquipmentEvaluatedChoice> generateUnderProductionChoices(Machine usedMachine, EquipmentChoice equipmentPlan,
			Task task, ApsSchedulingSet schedulingSet, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener);

}
