package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.cycle.BatchTransferType;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IBomItemModelDao;
import com.openi40.scheduler.model.dao.IPeggingDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.tasks.TaskEdge;
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
@Service
public class TaskRelationInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<TaskRelationInputDto, TaskEdge> {
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IPeggingDao peggingDao;
	@Autowired
	IBomItemModelDao bomItemModelDao;

	public TaskRelationInputModel2ApsModelService(AutowireCapableBeanFactory autowireCapable,
			IBusinessModelFactory businessModelFactory) {
		super(TaskRelationInputDto.class, TaskEdge.class, autowireCapable, businessModelFactory);

	}

	@Override
	public void copyValue(TaskRelationInputDto originalObject, TaskEdge targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		try {

			if (targetObject.getConsumerTask() == null) {

				targetObject.setConsumerTask(
						taskDao.findByCode(originalObject.getConsumerTaskCode(), targetObject.getContext()));

			}
			if (targetObject.getProducerTask() == null) {
				targetObject.setProducerTask(
						taskDao.findByCode(originalObject.getSupplierTaskCode(), targetObject.getContext()));
			}
			if (targetObject.getConsumingBatchInfo() == null) {
				targetObject.setConsumingBatchInfo(new BatchingInfo());
			}
			targetObject.getConsumingBatchInfo().setBatchQty(originalObject.getConsumptionBatchQty());
			targetObject.getConsumingBatchInfo()
					.setBatchTransferType(originalObject.getConsumptionTransferType() != null
							? BatchTransferType.valueOf(originalObject.getConsumptionTransferType())
							: BatchTransferType.TRANSFER_ALL);
			if (originalObject.getBomItemCode() != null && originalObject.getBomItemCode().trim().length() > 0) {
				BomItemModel bom = bomItemModelDao.findByCode(originalObject.getBomItemCode(),
						targetObject.getContext());
				targetObject.setBomItemModel(bom);
			}
			if (originalObject.getPeggingEdge() != null && originalObject.getPeggingEdge()) {
				Pegging pegging = peggingDao.findByCode(originalObject.getPeggingCode(), targetObject.getContext());
				if (pegging != null) {
					targetObject.setRappresentedPegging(pegging);
					pegging.setEdge(targetObject);
				}
			}
			
		} catch (DataModelDaoException e) {

			throw new MapperException("Problem accessing tasks with ITaskDao ", e);
		}
	}

}
