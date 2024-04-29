package com.openi40.scheduler.engine.tasksoffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
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
@DefaultImplementation(implemented = ITasksRelationOffset.class, entityClass = TasksRelationRule.class)
public class TasksRelationOffsetImpl extends BusinessLogic<TasksRelationRule> implements ITasksRelationOffset {
	static Logger LOGGER = LoggerFactory.getLogger(TasksRelationOffsetImpl.class);

	public double calculateStartWorkOffset(TasksRelationRule rule, TaskEquipmentInfo producerEquipment,
			TaskEquipmentInfo consumerEquipment, ApsSchedulingSet apsAction) {

		double minutes = 0l;
		if (rule.getEdge().getAlignmentType() == PeriodsAlignmentType.START_AFTER_END) {
			return 0.0;
		}
		Task producerTask = rule.getEdge().getProducerTask();
		Task consumerTask = rule.getEdge().getConsumerTask();
		BatchingInfo consumptionBatching = rule.getEdge().getConsumingBatchInfo();
		BatchingInfo productionBatching = producerTask.getMetaInfo().getProducingBatchInfo();
		double consumptionBatchQty = consumptionBatching != null ? consumptionBatching.getBatchQty() : 1.0;
		double productionBatchQty = productionBatching != null ? productionBatching.getBatchQty() : 1.0;
		double currentTaskProductionBatchQty = rule.getEdge().getConsumerTask().getProducingBatchInfo() != null
				? rule.getEdge().getConsumerTask().getProducingBatchInfo().getBatchQty()
				: 1.0;
		// When producing starts to produce productionBatchQty at least, and required
		// exactly same qty if this edge
		// is on the same work order (isPegging==false or getBomItemModel()==null)
		// otherwise transfers at least
		// the qty required to produce a batch qty multiplied by its bom useCoefficient
		double nrProducedOffset = Math.max(consumptionBatchQty, productionBatchQty);
		if (rule.getEdge().getBomItemModel() != null) {
			// Calculates qty of producer's task material to produce the consumer task batch
			// of production.
			// Consider the quantity rounded by the producer task
			double requiredBatchProductionQty = currentTaskProductionBatchQty
					* rule.getEdge().getBomItemModel().getQty();
			nrProducedOffset = Math.max(requiredBatchProductionQty, productionBatchQty);
			nrProducedOffset = Math.floor(requiredBatchProductionQty / productionBatchQty) * productionBatchQty;
		}
		if (producerEquipment!= null) {
			IWorkTimeLogic workTimeCalculator = componentsFactory.create(IWorkTimeLogic.class, producerEquipment,
					apsAction);
			minutes = workTimeCalculator.calculateWorkTime(producerTask, nrProducedOffset, producerEquipment);
			if (minutes <= 0) {
				LOGGER.warn("For task: " + consumerTask.getWorkOrderCode() + "/" + consumerTask.getSequenceCode()
						+ " producing task " + producerTask.getWorkOrderCode() + "/" + producerTask.getSequenceCode()
						+ " with 0 (ZERO) offset!! Machine: "
						+ (producerTask.getEquipment() != null && producerTask.getEquipment().getExecution() != null
								&& producerTask.getEquipment().getExecution().getResource() != null
								&& producerTask.getEquipment().getExecution().getResource()
										.getChoosenEquipment() != null
												? producerTask.getEquipment().getExecution().getResource()
														.getChoosenEquipment().getCode()
												: "NO MACHINE"));
			}
		}

		return minutes;
	}

	@Override
	public double calculateEndWorkOffset(TasksRelationRule rule, TaskEquipmentInfo producerEquipment, TaskEquipmentInfo consumerEquipment, ApsSchedulingSet apsAction) {
		if (rule.getEdge().getAlignmentType() == PeriodsAlignmentType.START_AFTER_END) {
			return 0.0;
		}
		// The offset is calculated on the current task machine
		Task producerTask = rule.getEdge().getProducerTask();
		Task consumerTask = rule.getEdge().getConsumerTask();
		BatchingInfo consumptionBatching = rule.getEdge().getConsumingBatchInfo();
		BatchingInfo productionBatching = producerTask.getMetaInfo().getProducingBatchInfo();
		double consumptionBatchQty = consumptionBatching != null ? consumptionBatching.getBatchQty() : 1.0;
		double productionBatchQty = productionBatching != null ? productionBatching.getBatchQty() : 1.0;
		double currentTaskProductionBatchQty = rule.getEdge().getConsumerTask().getProducingBatchInfo() != null
				? rule.getEdge().getConsumerTask().getProducingBatchInfo().getBatchQty()
				: 1.0;
		double offset = 0;
		// After the full arrive of all goods from producer task it remains to produce a
		// qty that is proportional to
		// the current task production batching value
		IWorkTimeLogic workTimeCalculator = componentsFactory.create(IWorkTimeLogic.class, consumerEquipment, apsAction);
		offset = workTimeCalculator.calculateWorkTime(consumerTask, productionBatchQty, consumerEquipment);

		return offset;
	}
}