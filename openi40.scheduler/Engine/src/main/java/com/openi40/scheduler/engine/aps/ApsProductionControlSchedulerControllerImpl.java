package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsSchedulingSet.ApsSchedulingSetType;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

@DefaultImplementation(implemented = IApsProductionControlSchedulerController.class, entityClass = ApsData.class)
public class ApsProductionControlSchedulerControllerImpl extends BusinessLogic<ApsData>
		implements IApsProductionControlSchedulerController {
	static Logger LOGGER = LoggerFactory.getLogger(ApsProductionControlSchedulerControllerImpl.class);

	public ApsProductionControlSchedulerControllerImpl() {

	}

	@Autowired
	ITaskDao taskDao;
	@Autowired
	IWorkOrderDao workOrderDao;
	private final TaskStatus PROCESSING_TASKS[] = new TaskStatus[] { TaskStatus.EXECUTING_SETUP, TaskStatus.SETUP_DONE,
			TaskStatus.EXECUTING_WORK, TaskStatus.PAUSED };

	@Override
	public void prepareSchedulingData(ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin prepareSchedulingData(..)");
		}
		try {
			List<Task> tasksUnderProcess = new ArrayList<Task>();
			List<Task> tasksExecuted = new ArrayList<Task>();
			List<Task> tasksAborted = new ArrayList<Task>();
			List<Task> tasksNotYetProcessed = new ArrayList<Task>();
			taskDao.consumeAll(new Consumer<Task>() {

				@Override
				public void accept(Task task) {
					boolean selected = false;
					TaskStatus ACTUAL_STATUS = task.getStatus();
					if (ACTUAL_STATUS != null) {
						if (ACTUAL_STATUS.equals(TaskStatus.NOT_YET_EXECUTED)) {
							tasksNotYetProcessed.add(task);
							return;
						}
						if (ACTUAL_STATUS.equals(TaskStatus.ABORTED)) {
							tasksAborted.add(task);
							return;
						}
						if (ACTUAL_STATUS.equals(TaskStatus.EXECUTED)) {
							tasksExecuted.add(task);
							return;
						}
						for (int i = 0; !selected && i < PROCESSING_TASKS.length; i++) {
							selected = selected || (ACTUAL_STATUS.equals(PROCESSING_TASKS[i]));
						}
					}
					if (selected)
						tasksUnderProcess.add(task);
				}
			}, context);
			List<ApsSchedulingSet> processControlSchedulingSets = new ArrayList<ApsSchedulingSet>();
			context.getSchedulingSets().forEach((set) -> {
				if (set.getSchedulingSetType() != null
						&& set.getSchedulingSetType().equals(ApsSchedulingSetType.PRODUCTION_CONTROL)) {
					processControlSchedulingSets.add(set);
				}
			});
			if (processControlSchedulingSets.size() > 1) {
				throw new OpenI40Exception("Data have " + processControlSchedulingSets.size()
						+ " scheduling sets in PRODUCTION_CONTROL state that's not permitted");
			}
			ApsSchedulingSet processControlSet = null;
			if (!processControlSchedulingSets.isEmpty()) {
				processControlSet = processControlSchedulingSets.get(0);
			} else {
				processControlSet = new ApsSchedulingSet(context);
				processControlSet.setSchedulingSetType(ApsSchedulingSetType.PRODUCTION_CONTROL);
				processControlSet.setAlgorithmType(ApsLogics.FORWARD_APS);
				processControlSet.setCode("PROCESS_CONTROL001");
				processControlSet.setDescription("Process control set");
				processControlSet.setOptions(null);
				context.getSchedulingSets().add(0, processControlSet);
			}
			Map<String, WorkOrder> workOrdersToAdd = new HashMap<String, WorkOrder>();
			for (Task task : tasksUnderProcess) {
				if (task.getParentSchedulingSet() != processControlSet || (task.getWorkOrder() != null
						&& task.getWorkOrder().getParentSchedulingAction() != processControlSet)) {
					if (task.getWorkOrder().getParentSchedulingAction() != null) {
						task.getWorkOrder().getParentSchedulingAction().removeWorkOrder(task.getWorkOrder());
					}
					workOrdersToAdd.put(task.getWorkOrder().getCode(), task.getWorkOrder());
				}
			}
			IApsLogic logic = componentsFactory.create(IApsLogic.class, processControlSet, context);
			if (processControlSet.getOptions() == null) {
				processControlSet.setOptions(logic.createDefaultOptions(processControlSet));
			}
			if (!workOrdersToAdd.isEmpty()) {
				logic.autoSetTasks(processControlSet, new ArrayList<WorkOrder>(workOrdersToAdd.values()), context);
			}

		} catch (DataModelDaoException e) {
			String MSG = "Error accessing data model in ApsProductionControlSchedulerControllerImpl.prepareSchedulingData(..)";
			LOGGER.error(MSG, e);
			throw new OpenI40Exception(MSG, e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End prepareSchedulingData(..)");
		}
	}

}
