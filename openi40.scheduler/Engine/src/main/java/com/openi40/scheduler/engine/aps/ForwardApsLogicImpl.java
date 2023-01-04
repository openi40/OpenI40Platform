package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.AlternativeImplementation;
import com.openi40.scheduler.engine.rules.planner.IPlanner;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsSchedulingSet.ApsSchedulingSetType;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 *         Forward scheduling algorithm
 */
@AlternativeImplementation(implemented = IApsLogic.class, entityClass = ApsSchedulingSet.class, key = ApsLogics.FORWARD_APS, switchImplementationProperty = "algorithmType")
public class ForwardApsLogicImpl extends AbstractApsLogic implements IForwardApsLogic {
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	protected PlanGraphItem schedule(Task task, ApsSchedulingSet EntityObject, ApsLogicNotifiedObjects observer) {
		if (observer != null && observer.getObserver() != null) {
			try {
				observer.getObserver().startProcessingElement(task);
			} catch (Throwable th) {
				LOGGER.error("Error in observer", th);
			}
		}
		regenerateTaskConstraints(task);

		boolean allChildsOk = true;
		int nChilds = 0;
		// Check on every task wich this one depends on the end date time (this will be
		// enhanced with advanced alignment management for transfer batch/split ecc...)
		for (TaskEdge childTask : task.getChildTasks()) {
			if (!childTask.getProducerTask().isSuccessfullyScheduled()) {
				Map<String, Object> environment = new HashMap<String, Object>();
				environment.put("task", task);
				environment.put("relatedTask", childTask.getProducerTask());
				ApsMessage apsMessage = new ApsMessage(this, ApsMessageConstrants.PREDECESSOR_TASK_UNSCHEDULED,
						environment);
				task.getMessages().add(apsMessage);
				allChildsOk = false;
			}
		}

		if (allChildsOk) {
			TimeSegmentRequirement SetupTimeRange = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME,
					StartDateTimeAlignment.START_AFTER_START_ASAP, null);
			TimeSegmentRequirement WorkTimeRange = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME,
					StartDateTimeAlignment.START_AFTER_START_ASAP, null);
			IPlanner constraintsSolutionCoordinator = this.componentsFactory.create(IPlanner.class, EntityObject,
					EntityObject);
			task.setDecisionGraphItem(constraintsSolutionCoordinator.doPlanSupervision(task, EntityObject,
					(observer != null ? observer.getConstraintSolutionListener() : null), SetupTimeRange, WorkTimeRange,
					ApsLogicDirection.FORWARD));

			task.setSuccessfullyScheduled(verifyAllConstraints(task, observer));
		} else {
			task.setSuccessfullyScheduled(false);

		}
		if (observer != null && observer.getObserver() != null) {
			try {
				observer.getObserver().processedElement(task);
			} catch (Throwable th) {
				LOGGER.error("Error in observer", th);
			}
		}
		return task.getDecisionGraphItem();
	}

	@Override
	public ApsLogicDirection getDirection() {
		return ApsLogicDirection.FORWARD;
	}

	@Override
	public ApsSchedulingSet autoSetTasks(ApsSchedulingSet apsSet, List<WorkOrder> unscheduled, ApsData apsData) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin autoSetTasks( ) on type=>"
					+ (apsSet.getSchedulingSetType() != null ? apsSet.getSchedulingSetType().name() : "null"));
		}
		if (apsSet.getSchedulingSetType().equals(ApsSchedulingSetType.PLANNING)) {
			// This option normally is in interactive settings option
			List<WorkOrder> ordersWithUnsatisfiedDependency = new ArrayList<>();
			// include depending unschedled depending tasks
			for (WorkOrder wo : apsSet.getWorkOrders()) {
				List<Pegging> peggings = wo.getPeggings();
				boolean hasUnsatisfiedDependency = false;
				for (Pegging peg : peggings) {
					hasUnsatisfiedDependency = hasUnsatisfiedDependency
							|| (peg.getConsumer() == wo && unscheduled.contains(peg.getSupplier()));
				}
				if (hasUnsatisfiedDependency && !ordersWithUnsatisfiedDependency.contains(wo))
					ordersWithUnsatisfiedDependency.add(wo);
			}

			addOrdersWithTransitiveDependencies(ordersWithUnsatisfiedDependency, unscheduled, apsSet, apsData);
		} else if (apsSet.getSchedulingSetType().equals(ApsSchedulingSetType.PRODUCTION_CONTROL)) {
			moveTransitiveDependencies(apsSet, unscheduled, apsData);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End autoSetTasks( ) on type=>"
					+ (apsSet.getSchedulingSetType() != null ? apsSet.getSchedulingSetType().name() : "null"));
		}
		return apsSet;
	}

	private List<WorkOrder> avoidDuplicates(List<WorkOrder> list) {
		List<WorkOrder> outlist = new ArrayList<WorkOrder>();
		for (WorkOrder workOrder : list) {
			if (!outlist.contains(workOrder))
				outlist.add(workOrder);
		}

		return outlist;
	}

	private void moveTransitiveDependencies(ApsSchedulingSet apsSet, List<WorkOrder> unscheduled, ApsData apsData) {
		List<WorkOrder> transitive2Add = new ArrayList<WorkOrder>();
		for (WorkOrder workOrder : unscheduled) {
			transitive2Add.addAll(generateTransitiveDependencies(workOrder, apsSet, apsData));
		}
		transitive2Add = avoidDuplicates(transitive2Add);
		for (WorkOrder workOrder : transitive2Add) {
			if (workOrder.getParentSchedulingAction() != apsSet) {
				if (workOrder.getParentSchedulingAction() != null) {
					workOrder.getParentSchedulingAction().removeWorkOrder(workOrder);
				}
				apsSet.addWorkOrder(workOrder);
			}
		}
	}

	private void addOrdersWithTransitiveDependencies(List<WorkOrder> ordersWithUnsatisfiedDependency,
			List<WorkOrder> unscheduled, ApsSchedulingSet apsSet, ApsData apsData) {
		for (WorkOrder workOrder : ordersWithUnsatisfiedDependency) {
			List<WorkOrder> transitiveDependencies = generateUnscheduledTransitiveDependencies(workOrder, unscheduled,
					ordersWithUnsatisfiedDependency, apsSet, apsData);
			for (WorkOrder workOrder2add : transitiveDependencies) {
				if (!apsSet.getWorkOrders().contains(workOrder2add)) {
					apsSet.addWorkOrder(workOrder2add);
				}
			}
		}

	}

	private List<WorkOrder> generateTransitiveDependencies(WorkOrder workOrder, ApsSchedulingSet apsSet,
			ApsData apsData) {
		List<WorkOrder> outList = new ArrayList<>();
		outList.add(workOrder);
		List<Pegging> peggings = workOrder.getPeggings();
		for (Pegging pegging : peggings) {
			if (pegging.getConsumer() == workOrder) {
				List<WorkOrder> dependencies = this.generateTransitiveDependencies(pegging.getSupplier(), apsSet,
						apsData);
				for (WorkOrder dep : dependencies) {
					if (!outList.contains(dep)) {
						outList.add(dep);
					}
				}
			}
		}
		return outList;
	}

	private List<WorkOrder> generateUnscheduledTransitiveDependencies(WorkOrder workOrder, List<WorkOrder> unscheduled,
			List<WorkOrder> ordersWithUnsatisfiedDependency, ApsSchedulingSet apsSet, ApsData apsData) {
		List<WorkOrder> outList = new ArrayList<>();
		outList.add(workOrder);
		List<Pegging> peggings = workOrder.getPeggings();
		for (Pegging pegging : peggings) {
			if (pegging.getConsumer() == workOrder && unscheduled.contains(pegging.getSupplier())
					&& !apsSet.getWorkOrders().contains(pegging.getSupplier())
					&& !ordersWithUnsatisfiedDependency.contains(pegging.getSupplier())) {
				List<WorkOrder> dependencies = this.generateUnscheduledTransitiveDependencies(pegging.getSupplier(),
						unscheduled, ordersWithUnsatisfiedDependency, apsSet, apsData);
				for (WorkOrder dep : dependencies) {
					if (!outList.contains(dep)) {
						outList.add(dep);
					}
				}
			}
		}
		return outList;
	}
}