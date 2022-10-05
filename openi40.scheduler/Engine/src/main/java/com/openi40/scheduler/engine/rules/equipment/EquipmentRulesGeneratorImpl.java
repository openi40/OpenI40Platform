package com.openi40.scheduler.engine.rules.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.changeover.IChangeOverLogic;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.equipment.configuration.IEquipmentConfigurator;
import com.openi40.scheduler.engine.setuptime.ISetupTimeLogic;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentChangeOverInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(entityClass = Task.class, implemented = IEquipmentRulesGenerator.class)
public class EquipmentRulesGeneratorImpl extends BusinessLogic<Task> implements IEquipmentRulesGenerator {
	static Logger LOGGER = LoggerFactory.getLogger(EquipmentRulesGeneratorImpl.class);

	@Override
	public List<EquipmentRule> rebuildRules(List<EquipmentRule> actualRules, Task task, ApsLogicOptions options) {
		Map<String,Object> environment=new HashMap<String,Object>();
		environment.put("task", task);
		List<EquipmentRule> rules = new ArrayList<EquipmentRule>();
		EquipmentRule constraint = new EquipmentRule(task.getContext());
		constraint.setTargetTask(task);
		constraint.setEquipmentModelOptions(task.getMetaInfo().getEquipmentModelOptions());
		constraint.setTaskEquipmentInfos(getCompatibleEquipments(task.getParentSchedulingSet(),
				constraint.getEquipmentModelOptions(), task, task.getContext(), options));
		constraint.setOrigin(Rule.ConstraintOrigin.SCHEDULING);
		ApsMessage unavailableEquipmentMessage = new ApsMessage(this, ApsMessageConstrants.UNAVAILABLE_EQUIPMENT,environment);
		constraint.setUnmetConstraintMessage(unavailableEquipmentMessage);
		rules.add(constraint);
		return rules;
	}

	static final String EQUIPMENT_CFG_CACHE_PREAMBLE = "equipment_cfg_cache";

	/**
	 * Creates the list of all possible equipment configuration expressed by the
	 * equipment model options present in the task model. Also calculates the
	 * setup/work nominal times and setup expired times for the equipment on the
	 * passed task.
	 * 
	 * @param taskEquipmentModelOptions
	 * @param task
	 * @param context
	 * @param apsLogicOptions
	 * @return
	 */
	private List<TaskEquipmentInfo> getCompatibleEquipments(ApsSchedulingSet EntityObject,
			TaskEquipmentModelOptions taskEquipmentModelOptions, Task task, ApsData context,
			ApsLogicOptions apsLogicOptions) {
		String configKey = EQUIPMENT_CFG_CACHE_PREAMBLE + "$" + taskEquipmentModelOptions.getId();
		if (!context.isInCache(configKey)) {
			IEquipmentConfigurator configurator = this.componentsFactory.create(IEquipmentConfigurator.class,
					taskEquipmentModelOptions, EntityObject);

			List<TaskEquipmentInfo> pList = configurator.calculatePlanningConfigurations(taskEquipmentModelOptions,
					apsLogicOptions, task, context);
			context.addCache(configKey, pList);
		}
		List<TaskEquipmentInfo> plannedList = new ArrayList<TaskEquipmentInfo>();
		List<TaskEquipmentInfo> cachedList = context.getCache(configKey, List.class);
		for (TaskEquipmentInfo taskEquipmentInfo : cachedList) {
			try {
				plannedList.add((TaskEquipmentInfo) taskEquipmentInfo.cleanClone());
			} catch (CloneNotSupportedException e) {
				LOGGER.error("Cannot duplicate equipment", e);
				throw new OpenI40Exception("Cannot duplicate equipment", e);
			}
		}
		for (TaskEquipmentInfo equipment : plannedList) {
			setNominalTimes(equipment, task);
		}
		return plannedList;
	}

	/**
	 * Sets nominal setup time for the equipment and the passed task
	 * 
	 * @param equipment
	 * @param task
	 */

	/**
	 * Sets nominal work time for the equipment and the passed task
	 * 
	 * @param equipment
	 * @param task
	 */
	private void setNominalTimes(TaskEquipmentInfo info, Task task) {
		ISetupTimeLogic setupTimeLogic = this.componentsFactory.create(ISetupTimeLogic.class, info,
				task.getParentSchedulingSet());
		double NominalSetupTime = setupTimeLogic.calculateSetupTime(info, task);
		info.getPreparation().setNominalSetupTime(NominalSetupTime);
		
		IWorkTimeLogic equipmentCapacityEvaluator = this.componentsFactory.create(IWorkTimeLogic.class, info,
				task.getParentSchedulingSet());
		info.getExecution().setNominalWorkTime(equipmentCapacityEvaluator.calculateWorkTime(task, info));
	}

	/**
	 * Sets nominal task changeover on the setup equipment according to last task
	 * present on te same primary resource on the task before.
	 * 
	 * @param before
	 * @param after
	 * @param aferTask
	 */
	private void setNominalChangeoverTimes(TaskEquipmentInfo before, TaskEquipmentInfo after, Task aferTask) {
		TaskEquipmentChangeOverInfo info = new TaskEquipmentChangeOverInfo(before.getContext());
		info.setBeforeEquipment(before);
		info.setAfterEquipment(after);
		IChangeOverLogic evaluator = this.componentsFactory.create(IChangeOverLogic.class, info,
				aferTask.getParentSchedulingSet());
		after.getPreparation().setNominalChangeOverTime(evaluator.calculateChangeOverTime(aferTask, info));
	}

}
