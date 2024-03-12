package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.commons.multithreading.config.OpenI40MultithreadingConfig;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.AlternativeImplementation;
import com.openi40.scheduler.engine.rules.planner.IPlanner;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 *
 * Backward scheduling algorithm implementation
 */
@AlternativeImplementation(implemented = IApsLogic.class, entityClass = ApsSchedulingSet.class, key = ApsLogics.BACKWARD_APS, switchImplementationProperty = "algorithmType")
public class BackwardApsLogicImpl extends AbstractApsLogic implements IBackwardApsLogic {
	public BackwardApsLogicImpl(OpenI40MultithreadingConfig multithreadingConfig, AsyncDelegateService delegateService) {
		super(multithreadingConfig, delegateService);
		
	}

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public ApsLogicDirection getDirection() {
		return ApsLogicDirection.BACKWARD;
	}

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
		Date initDateTime = EntityObject.getContext().getSchedulingWindow().getStartDateTime();
		Date endDateTime = EntityObject.getContext().getSchedulingWindow().getEndDateTime();
		boolean parentIsOK = true;
		if (task.getParentTask() != null) {
			if (task.getParentTask().getConsumerTask().isSuccessfullyScheduled()) {
				// FIXME: manage alignment coherently with material transfer policies
				endDateTime = DateUtil.getInstance().Min(task.getParentTask().getConsumerTask().getStartDateTime(),
						endDateTime);

			} else {
				Map<String, Object> environment = new HashMap<String, Object>();
				environment.put("task", task);
				environment.put("relatedTask", task.getParentTask().getConsumerTask());
				ApsMessage apsMessage = new ApsMessage(this,task, ApsMessageConstrants.SUCCESSIVE_TASK_UNSCHEDULED,
						environment,task.getContext());
				task.getMessages().add(apsMessage);
				parentIsOK = false;
			}
		}
		if (parentIsOK) {
			IPlanner constraintsSolutionCoordinator = this.componentsFactory.create(IPlanner.class, EntityObject,
					EntityObject);

			TimeSegmentRequirement SetupTimeRange = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME, null,
					EndDateTimeAlignment.END_BEFORE_END_ASAP);
			TimeSegmentRequirement WorkTimeRange = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME, null,
					EndDateTimeAlignment.END_BEFORE_END_ASAP);
			SetupTimeRange.setStartDateTime(initDateTime);
			WorkTimeRange.setEndDateTime(endDateTime);
			task.setDecisionGraphItem(constraintsSolutionCoordinator.doPlanSupervision(task, EntityObject,
					(observer != null ? observer.getConstraintSolutionListener() : null), SetupTimeRange, WorkTimeRange,
					ApsLogicDirection.BACKWARD));
			task.setSuccessfullyScheduled(verifyAllConstraints(task, observer));

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
	public ApsSchedulingSet autoSetTasks(ApsSchedulingSet apsSet, List<WorkOrder> unscheduled, ApsData apsData) {
		// Check if dependent work orders are contained
		List<WorkOrder> dependantOrders = new ArrayList<>();
		// include depending unschedled depending tasks
		for (WorkOrder wo : apsSet.getWorkOrders()) {
			List<Pegging> peggings = wo.getPeggings();
			boolean hasNotAllDependantIn = false;
			for (Pegging peg : peggings) {
				hasNotAllDependantIn = hasNotAllDependantIn
						|| (peg.getSupplier() == wo && unscheduled.contains(peg.getConsumer()));
			}
			if (hasNotAllDependantIn && !dependantOrders.contains(wo))
				dependantOrders.add(wo);
		}
		generateTransitiveConsumingWorkOrders(dependantOrders, unscheduled, apsSet, apsData);
		return apsSet;
	}

	private void generateTransitiveConsumingWorkOrders(List<WorkOrder> dependantOrders, List<WorkOrder> unscheduled,
			ApsSchedulingSet apsSet, ApsData apsData) {
		for (WorkOrder workOrder : dependantOrders) {
			List<WorkOrder> clients = generateUnscheduledConsumerDependencies(workOrder, dependantOrders, unscheduled,
					apsSet, apsData);
			for (WorkOrder workOrder2add : clients) {
				if (!apsSet.getWorkOrders().contains(workOrder2add))
					apsSet.addWorkOrder(workOrder2add);
			}
		}

	}

	private List<WorkOrder> generateUnscheduledConsumerDependencies(WorkOrder workOrder,
			List<WorkOrder> dependantOrders, List<WorkOrder> unscheduled, ApsSchedulingSet apsSet, ApsData apsData) {
		List<WorkOrder> outList = new ArrayList<>();
		outList.add(workOrder);
		List<Pegging> peggings = workOrder.getPeggings();
		for (Pegging pegging : peggings) {
			if (pegging.getSupplier() == workOrder && unscheduled.contains(pegging.getConsumer())
					&& !apsSet.getWorkOrders().contains(pegging.getConsumer())
					&& !dependantOrders.contains(pegging.getConsumer())) {
				List<WorkOrder> dependencies = this.generateUnscheduledConsumerDependencies(pegging.getConsumer(),
						unscheduled, dependantOrders, apsSet, apsData);
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