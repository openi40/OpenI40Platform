package com.openi40.scheduler.engine.resallocator;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.engine.rules.RulePlanningEvent.RuleEventType;
import com.openi40.scheduler.engine.rules.equipment.IEquipmentPlanSolver;
import com.openi40.scheduler.engine.rules.material.IMaterialPlanSolver;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SchedulingPriorities;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.equipment.EquipmentEvaluatedChoice;
import com.openi40.scheduler.model.planning.material.MaterialChoice;
import com.openi40.scheduler.model.planning.material.MaterialEvaluatedChoice;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IResourcesAllocator.class, entityClass = Task.class)
public class ResourcesAllocatorImpl extends BusinessLogic<Task> implements IResourcesAllocator {
	static Logger LOGGER = LoggerFactory.getLogger(ResourcesAllocatorImpl.class);

	public static class UnavailableSolutions {
		TimeSegmentRequirement SetupTimeRangeSpec = null, WorkTimeRangeSpec = null;

		public UnavailableSolutions(TimeSegmentRequirement SetupTimeRangeSpec,
				TimeSegmentRequirement WorkTimeRangeSpec) {
			this.SetupTimeRangeSpec = SetupTimeRangeSpec;
			this.WorkTimeRangeSpec = WorkTimeRangeSpec;

		}

		public TimeSegmentRequirement getSetupTimeRangeSpec() {
			return SetupTimeRangeSpec;
		}

		public void setSetupTimeRangeSpec(TimeSegmentRequirement setupTimeRangeSpec) {
			SetupTimeRangeSpec = setupTimeRangeSpec;
		}

		public TimeSegmentRequirement getWorkTimeRangeSpec() {
			return WorkTimeRangeSpec;
		}

		public void setWorkTimeRangeSpec(TimeSegmentRequirement workTimeRangeSpec) {
			WorkTimeRangeSpec = workTimeRangeSpec;
		}
	}

	@Override
	public ResourcesCombination elaborateAllocation(List<EquipmentChoice> equipmentPlans,
			List<MaterialChoice> materialPlans, Task task, TimeSegmentRequirement SetupTimeRange,
			TimeSegmentRequirement WorkTimeRange, ApsSchedulingSet action, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin ResourcesAllocatorImpl.elaborateAllocation(...) on task " + task.getCode());
		}
		ResourcesCombination activeCombination = null;
		ApsLogicOptions schedulingOptions = action.getOptions();
		List<ResourcesCombination> equipmentAndMaterialValidCombinations = new ArrayList<ResourcesCombination>();
		for (EquipmentChoice equipmentPlan : equipmentPlans) {

			IEquipmentPlanSolver handler = componentsFactory.create(IEquipmentPlanSolver.class,
					equipmentPlan.getConstraint(), task.getContext());
			List<EquipmentEvaluatedChoice> equipmentSatisfactionOptions = handler.generateChoices(equipmentPlan,
					SetupTimeRange, WorkTimeRange, direction);
			if (equipmentSatisfactionOptions.isEmpty() && constraintSolutionListener != null) {
				UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeRange, WorkTimeRange);
				RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
						this, task, equipmentPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE, unavailInTimes);
				constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);
			}
			for (PlanChoice equipmentSatisfactionOption : equipmentSatisfactionOptions) {
				// Try to restrict workTime by the allocation of equipments and look if
				// all material can be satisfactory supplied
				TimeSegmentRequirement SetupTimeTmp = new TimeSegmentRequirement(SetupTimeRange);
				TimeSegmentRequirement WorkTimeTmp = new TimeSegmentRequirement(WorkTimeRange);
				WorkTimeTmp.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				restrictTimeSegment(SetupTimeTmp, equipmentSatisfactionOption.getSetup());
				restrictTimeSegment(WorkTimeTmp, equipmentSatisfactionOption.getWork());
				boolean eachMaterialPlanCorrectlyAligned = true;
				List<MaterialEvaluatedChoice> materialSatisfactionOptions = new ArrayList<MaterialEvaluatedChoice>();

				for (MaterialChoice materialPlan : materialPlans) {
					IMaterialPlanSolver materialHandler = componentsFactory.create(IMaterialPlanSolver.class,
							materialPlan.getConstraint(), task.getContext());
					List<MaterialEvaluatedChoice> mSatisfactionOptions = materialHandler.generateChoices(materialPlan,
							SetupTimeTmp, WorkTimeTmp, direction);
					eachMaterialPlanCorrectlyAligned = eachMaterialPlanCorrectlyAligned
							&& !mSatisfactionOptions.isEmpty();
					// PLanOptions already are in order coherently with scheduling options so just
					// take the first one
					if (!mSatisfactionOptions.isEmpty()) {
						materialSatisfactionOptions.add((MaterialEvaluatedChoice) mSatisfactionOptions.get(0));
					} else {
						if (constraintSolutionListener != null) {
							UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeTmp, WorkTimeTmp);
							RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
									this, task, materialPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE,
									unavailInTimes);
							constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);
						}
					}
				}
				if (eachMaterialPlanCorrectlyAligned) {
					equipmentAndMaterialValidCombinations.add(new ResourcesCombination(
							(EquipmentEvaluatedChoice) equipmentSatisfactionOption, materialSatisfactionOptions));
				}
			}

		}
		if (!equipmentAndMaterialValidCombinations.isEmpty()) {

			activeCombination = chooseResources(equipmentAndMaterialValidCombinations, schedulingOptions, direction,
					action.getContext());
		}
		if (activeCombination == null) {
			// TODO: Try to allocate looking in the perspective of material allocation
			TimeSegmentRequirement SetupTimeTmp = null;
			TimeSegmentRequirement WorkTimeTmp = new TimeSegmentRequirement(WorkTimeRange);

			// WorkTimeTmp.setEndDateTime(null);
			WorkTimeTmp.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			// Try to satisfy first all material needs with alignment coherent with the
			// alignment found in the datesAlignment parameter
			// check all material solutions and check when all material comes
			Date startDateTime = task.getContext().getSchedulingWindow().getStartDateTime();
			boolean allMaterialNeedsHasOptions = true;
			for (MaterialChoice materialPlan : materialPlans) {
				IMaterialPlanSolver materialHandler = componentsFactory.create(IMaterialPlanSolver.class,
						materialPlan.getConstraint(), task.getContext());
				List<MaterialEvaluatedChoice> planOptions = materialHandler.generateChoices(materialPlan, SetupTimeTmp,
						WorkTimeTmp, direction);
				if (!planOptions.isEmpty()) {
					planOptions = sortByStartDate(planOptions);
					startDateTime = DateUtil.getInstance().Max(startDateTime,
							planOptions.get(0).getWork().getStartDateTime());
				} else {
					if (constraintSolutionListener != null) {
						UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeTmp, WorkTimeTmp);
						RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
								this, task, materialPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE,
								unavailInTimes);
						constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);
					}
					allMaterialNeedsHasOptions = false;
				}
			}

			if (allMaterialNeedsHasOptions) {
				List<MaterialEvaluatedChoice> materialOptions = new ArrayList<MaterialEvaluatedChoice>();
				if (!materialPlans.isEmpty()) {
					WorkTimeTmp.setStartDateTime(startDateTime);
					WorkTimeTmp.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
					for (MaterialChoice materialPlan : materialPlans) {
						IMaterialPlanSolver materialHandler = componentsFactory.create(IMaterialPlanSolver.class,
								materialPlan.getConstraint(), task.getContext());
						List<MaterialEvaluatedChoice> planOptions = materialHandler.generateChoices(materialPlan,
								SetupTimeTmp, WorkTimeTmp, direction);
						if (!planOptions.isEmpty()) {
							planOptions = sortByStartDate(planOptions);
							materialOptions.add((MaterialEvaluatedChoice) planOptions.get(0));
						} else {
							allMaterialNeedsHasOptions = false;
							if (constraintSolutionListener != null) {
								UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeTmp,
										WorkTimeTmp);
								RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
										this, task, materialPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE,
										unavailInTimes);
								constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);

							}
						}
					}
				}

				if (allMaterialNeedsHasOptions) {
					// FOR EQUIPMENT NOW WE TRY TO ALLOCATE NOT ON PRECISELY DATE BUT ON FROM
					// STARTDATE
					WorkTimeTmp.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
					for (EquipmentChoice equipmentPlan : equipmentPlans) {
						IEquipmentPlanSolver equipmentHandler = componentsFactory.create(IEquipmentPlanSolver.class,
								equipmentPlan.getConstraint(), task.getContext());
						List<EquipmentEvaluatedChoice> equipmentSatisfactionOptions = equipmentHandler
								.generateChoices(equipmentPlan, SetupTimeTmp, WorkTimeTmp, direction);
						for (PlanChoice equipmentSatisfactionOption : equipmentSatisfactionOptions) {
							equipmentAndMaterialValidCombinations.add(new ResourcesCombination(
									(EquipmentEvaluatedChoice) equipmentSatisfactionOption, materialOptions));
						}
						if (equipmentSatisfactionOptions.isEmpty() && constraintSolutionListener != null) {
							UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeRange,
									WorkTimeRange);
							RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
									this, task, equipmentPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE,
									unavailInTimes);
							constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);
						}
					}
				}

				if (!equipmentAndMaterialValidCombinations.isEmpty()) {
					activeCombination = chooseResources(equipmentAndMaterialValidCombinations, schedulingOptions,
							direction, action.getContext());
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End ResourcesAllocatorImpl.elaborateAllocation(...) on task " + task.getCode());
		}
		return activeCombination;
	}

	@Override
	public void reserveResources(ResourcesCombination combination) {
		combination.getEquipmentAllocationOption().choose();
		for (IOperation operation : combination.getEquipmentAllocationOption().getOperations()) {
			operation.apply(componentsFactory);
		}
		for (MaterialEvaluatedChoice materialAllocationOption : combination.getMaterialAllocationOptions()) {
			materialAllocationOption.choose();
			for (IOperation operation : materialAllocationOption.getOperations()) {
				operation.apply(componentsFactory);
			}

		}
	}

	protected final ResourcesCombination chooseResources(List<ResourcesCombination> equipmentAndMaterialList,
			ApsLogicOptions algorithmOptions, ApsLogicDirection direction, ApsData context) {
		List<ResourcesCombination> actualCombinations = equipmentAndMaterialList;
		for (SchedulingPriorities schedulingPriority : algorithmOptions.getSchedulingPrioritiesOptions()) {
			TreeMap<Double, List<ResourcesCombination>> sortedStructure = buildByOption(schedulingPriority, direction,
					actualCombinations, context);
			// Take the first in order set of values
			for (Map.Entry<Double, List<ResourcesCombination>> actualValue : sortedStructure.entrySet()) {
				actualCombinations = actualValue.getValue();
				break;
			}
		}
		return !actualCombinations.isEmpty() ? actualCombinations.get(0) : null;
	}

	private TreeMap<Double, List<ResourcesCombination>> buildByOption(
			ApsLogicOptions.SchedulingPriorities schedulingPriority, ApsLogicDirection direction,
			List<ResourcesCombination> equipmentAndMaterialList, ApsData context) {

		TreeMap<Double, List<ResourcesCombination>> outDictionary = new TreeMap<Double, List<ResourcesCombination>>();
		for (ResourcesCombination equipment : equipmentAndMaterialList) {
			double key = 0L;
			switch (schedulingPriority) {
			case MINIMIZE_SETUP: {
				key = equipment.getEquipmentAllocationOption().getSetup().getDurationMinutes();

			}
				break;
			case MINIMIZE_WORK_ELAPSED_TIME: {
				key = equipment.getEquipmentAllocationOption().getWork().getDurationMinutes();
			}
				break;
			case MINIMIZE_WORK_END_DATETIME: {
				switch (direction) {
				case FORWARD: {

					key = context.getSchedulingWindow().getStartDateTime().toInstant().until(
							equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant(),
							ChronoUnit.MINUTES);
				}
					break;
				case BACKWARD: {
					key = equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant()
							.until(context.getSchedulingWindow().getEndDateTime().toInstant(), ChronoUnit.MINUTES)
					/*
					 * context.getSchedulingWindow().getStartDateTime().toInstant().until(
					 * equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant
					 * (), ChronoUnit.MINUTES)
					 */;
				}
					break;
				}

			}
				break;
			}
			if (!outDictionary.containsKey(key)) {
				outDictionary.put(key, new ArrayList<ResourcesCombination>());
			}
			outDictionary.get(key).add(equipment);
		}
		return outDictionary;
	}

	protected final <PlanOptionType extends PlanChoice> List<PlanOptionType> sortByStartDate(
			List<PlanOptionType> planOpts) {
		List<PlanOptionType> outPlans = new ArrayList<PlanOptionType>();
		TreeMap<Date, List<PlanOptionType>> sortedDictionary = new TreeMap<Date, List<PlanOptionType>>();
		for (PlanOptionType planOption : planOpts) {
			if (planOption.getWork().getStartDateTime() != null) {
				if (!sortedDictionary.containsKey(planOption.getWork().getStartDateTime())) {
					sortedDictionary.put(planOption.getWork().getStartDateTime(), new ArrayList<PlanOptionType>());
				}
				sortedDictionary.get(planOption.getWork().getStartDateTime()).add(planOption);
			} else {
				outPlans.add(planOption);
			}
		}

		for (Map.Entry<Date, List<PlanOptionType>> entry : sortedDictionary.entrySet()) {
			CollectionUtil.getInstance().AddCollection(outPlans, entry.getValue());
		}
		return outPlans;
	}

	protected void restrictTimeSegment(TimeSegment outRange, TimeSegment newConditionRange) {
		if (outRange.isLowerLimited() && newConditionRange.isLowerLimited()) {
			outRange.setStartDateTime(
					DateUtil.getInstance().Max(newConditionRange.getStartDateTime(), outRange.getStartDateTime()));
		} else if (newConditionRange.isLowerLimited()) {
			outRange.setStartDateTime(newConditionRange.getStartDateTime());
		}

		if (outRange.isUpperLimited() && newConditionRange.isUpperLimited()) {
			outRange.setEndDateTime(
					DateUtil.getInstance().Min(newConditionRange.getEndDateTime(), outRange.getEndDateTime()));
		} else if (newConditionRange.isUpperLimited()) {
			outRange.setEndDateTime(newConditionRange.getEndDateTime());
		}
	}

	@Override
	public ResourcesCombination elaborateUnderProductionAllocations(Machine usedMachine,
			List<EquipmentChoice> equipmentChoices, List<MaterialChoice> materialPlans, Task task,
			ApsSchedulingSet schedulingSet, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener) {
		List<ResourcesCombination> choiceCombinations = new ArrayList<>();
		ApsLogicOptions schedulingOptions = schedulingSet.getOptions();
		for (EquipmentChoice equipmentPlan : equipmentChoices) {
			IEquipmentPlanSolver handler = componentsFactory.create(IEquipmentPlanSolver.class,
					equipmentPlan.getConstraint(), task.getContext());
			List<EquipmentEvaluatedChoice> equipmentSatisfactionOptions = handler.generateUnderProductionChoices(
					usedMachine, equipmentPlan, task, schedulingSet, direction, constraintSolutionListener);
			for (PlanChoice equipmentSatisfactionOption : equipmentSatisfactionOptions) {
				// Try to restrict workTime by the allocation of equipments and look if
				// all material can be satisfactory supplied
				TimeSegmentRequirement SetupTimeTmp = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
				TimeSegmentRequirement WorkTimeTmp = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
				WorkTimeTmp.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				restrictTimeSegment(SetupTimeTmp, equipmentSatisfactionOption.getSetup());
				restrictTimeSegment(WorkTimeTmp, equipmentSatisfactionOption.getWork());
				boolean eachMaterialPlanCorrectlyAligned = true;
				List<MaterialEvaluatedChoice> materialSatisfactionOptions = new ArrayList<MaterialEvaluatedChoice>();

				for (MaterialChoice materialPlan : materialPlans) {
					IMaterialPlanSolver materialHandler = componentsFactory.create(IMaterialPlanSolver.class,
							materialPlan.getConstraint(), task.getContext());
					List<MaterialEvaluatedChoice> mSatisfactionOptions = materialHandler.generateChoices(materialPlan,
							SetupTimeTmp, WorkTimeTmp, direction);
					eachMaterialPlanCorrectlyAligned = eachMaterialPlanCorrectlyAligned
							&& !mSatisfactionOptions.isEmpty();
					// PLanOptions already are in order coherently with scheduling options so just
					// take the first one
					if (!mSatisfactionOptions.isEmpty()) {
						materialSatisfactionOptions.add((MaterialEvaluatedChoice) mSatisfactionOptions.get(0));
					} else {
						if (constraintSolutionListener != null) {
							UnavailableSolutions unavailInTimes = new UnavailableSolutions(SetupTimeTmp, WorkTimeTmp);
							RulePlanningEvent<UnavailableSolutions> unavailEquipment = new RulePlanningEvent<UnavailableSolutions>(
									this, task, materialPlan.getConstraint(), RuleEventType.CONSTRAINT_UNSOLVABLE,
									unavailInTimes);
							constraintSolutionListener.onConstraintSolutionEvent(unavailEquipment);
						}
					}
				}
				if (eachMaterialPlanCorrectlyAligned) {
					choiceCombinations.add(new ResourcesCombination(
							(EquipmentEvaluatedChoice) equipmentSatisfactionOption, materialSatisfactionOptions));
				}
			}
		}

		if (!choiceCombinations.isEmpty()) {
			ResourcesCombination activeCombination = chooseResources(choiceCombinations, schedulingOptions, direction,
					schedulingSet.getContext());
			choiceCombinations.forEach(entry -> {
				if (entry != activeCombination) {
					discardResourcesOptions(entry);
				}
			});
			return activeCombination;
		} else
			return null;
	}

	@Override
	public void discardResourcesOptions(ResourcesCombination combination) {

		for (IOperation operation : combination.getEquipmentAllocationOption().getOperations()) {
			operation.discardOperation();
		}
		for (MaterialEvaluatedChoice materialAllocationOption : combination.getMaterialAllocationOptions()) {
			materialAllocationOption.choose();
			for (IOperation operation : materialAllocationOption.getOperations()) {
				operation.discardOperation();
			}

		}

	}
}
