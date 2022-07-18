package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.tasksgeneration.ITaskGenerator;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.input.model.tasks.AbstractTaskMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskProductionMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskPurchaseMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskResourceReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskStockMaterialReservationInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.dao.IOperationModelDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderDao;
import com.openi40.scheduler.model.dao.IPurchaseOrderLineDao;
import com.openi40.scheduler.model.dao.ISecondaryResourceDao;
import com.openi40.scheduler.model.dao.ISecondaryResourceGroupDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkResourceInfos;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.equipment.TaskProcessMetaInfo;
import com.openi40.scheduler.model.equipment.Use;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
public class TaskInputModel2ApsModelService extends AbstractInputModel2ApsModelService<TaskInputDto, Task> {
	static Logger LOGGER = LoggerFactory.getLogger(TaskInputModel2ApsModelService.class);

	public TaskInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(TaskInputDto.class, Task.class, beanFactory, businessModelFactory);

	}

	@Autowired
	IOperationModelDao operationModelDao;
	@Autowired
	IMachineDao machineDao;
	@Autowired
	IWorkOrderDao workOrderDao;
	@Autowired
	ILazyContextualBusinessLogicFactoryLoader contextualBLFactory;
	@Autowired
	ISecondaryResourceDao secondaryResourceDao;
	@Autowired
	ISecondaryResourceGroupDao secondaryResourceGroupDao;
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IPurchaseOrderLineDao purchaseOrderLineDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IWarehouseDao warehouseDao;

	@Override
	public void copyValue(TaskInputDto originalObject, Task targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		try {
			IContextualBusinessLogicFactory factory = contextualBLFactory.getComponentFactory();

			OperationModel operationModel = operationModelDao.findByCode(originalObject.getOperationCode(),
					targetObject.getContext());
			ITaskGenerator taskGenerator = factory.create(ITaskGenerator.class, operationModel,
					targetObject.getContext());
			WorkOrder workOrder = workOrderDao.findByCode(originalObject.getWorkOrderCode(), targetObject.getContext());
			// RIPORTARE LA QUANTITA QUI
			taskGenerator.initializeTask(targetObject, operationModel, workOrder, originalObject.getQtyTotal(), false,
					targetObject.getContext());

			targetObject.setAskedDeliveryDateTime(originalObject.getAskedDeliveryDateTime());
			targetObject.setCode(originalObject.getCode());
			String workCenter = originalObject.getWorkCenterCode();
			String machineCode = originalObject.getScheduledMachineCode();
			if (targetObject.getEquipment() == null) {
				targetObject.setEquipment(new TaskEquipmentInfo(targetObject.getContext()));
			}

			for (TaskEquipmentModelInfo actualMeta : operationModel.getEquipmentModelOptions().getEquipmentModels()) {
				if (actualMeta.getCode() != null && originalObject.getEquipmentSpecCode() != null
						&& actualMeta.getCode().equals(originalObject.getEquipmentSpecCode())) {

					TaskProcessInfo tpInfo = new TaskProcessInfo(targetObject.getContext(),
							actualMeta.getTaskMetaInfo());
					targetObject.getEquipment().setMetaInfo(actualMeta);
				}
			}

			if (targetObject.getEquipment().getPreparation() == null) {
				targetObject.getEquipment().setPreparation(new TaskPreparationPlanned(targetObject.getContext()));
			}
			SetupResourceInfos resource = new SetupResourceInfos(targetObject.getContext());
			targetObject.getEquipment().getPreparation().setResource(resource);
			Machine machine = null;
			if (machineCode != null && machineCode.trim().length() > 0) {
				machine = machineDao.findByCode(machineCode, targetObject.getContext());
			}
			resource.setChoosenEquipment(machine);
			if (targetObject.getEquipment().getExecution() == null) {
				targetObject.getEquipment().setExecution(new TaskExecutionPlanned(targetObject.getContext()));
			}
			WorkResourceInfos wResource = new WorkResourceInfos(targetObject.getContext());
			wResource.setChoosenEquipment(machine);
			targetObject.getEquipment().getExecution().setResource(wResource);

			if (machine != null) {
				ITimesheetLogic timesheetLogic = factory.create(ITimesheetLogic.class, machine, machine.getContext());
				if (originalObject.getStartPreparation() != null && originalObject.getEndPreparation() != null) {
					TimeSegmentRequirement tsr = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
					tsr.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
					tsr.setStartDateTime(originalObject.getStartPreparation());
					tsr.setEndDateTime(originalObject.getEndPreparation());
					TimesheetReservation setupReservation = timesheetLogic.addReservation(machine, tsr, targetObject);
					if (setupReservation == null) {
						LOGGER.error("Cannot reserve " + machine.getCode() + " preparation with period=>["
								+ originalObject.getStartPreparation() + "," + originalObject.getEndPreparation()
								+ "]");
					} else {
						resource.getTimesheetReservations().add(setupReservation);
						targetObject.getSetupPhaseExecution().Add(setupReservation);
					}
				}
				if (originalObject.getStartExecution() != null && originalObject.getEndExecution() != null) {
					TimeSegmentRequirement tsr = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
					tsr.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
					tsr.setStartDateTime(originalObject.getStartExecution());
					tsr.setEndDateTime(originalObject.getEndExecution());
					TimesheetReservation workReservation = timesheetLogic.addReservation(machine, tsr, targetObject);
					if (workReservation == null) {
						LOGGER.error("Cannot reserve " + machine.getCode() + " execution with period=>["
								+ originalObject.getStartExecution() + "," + originalObject.getEndExecution() + "]");
					} else {

						targetObject.getWorkPhaseExecution().Add(workReservation);
						wResource.getTimesheetReservations().add(workReservation);
					}
				}
			}
			List<TaskResourceReservationInputDto> resources = originalObject.getResourcesReservations();
			for (TaskResourceReservationInputDto rc : resources) {
				String resourceCode = rc.getResourceCode();
				String resourceGroupCode = rc.getSecondaryResourceGroupCode();
				Resource res = secondaryResourceDao.findByCode(resourceCode, targetObject.getContext());
				ResourceGroup group = secondaryResourceGroupDao.findByCode(resourceGroupCode,
						targetObject.getContext());
				ITimesheetLogic timesheetLogic = factory.create(ITimesheetLogic.class, res, targetObject.getContext());
				Use<Resource, ?, ResourceGroup> matchingUse = null;
				String slotType = rc.getSlotType() != null ? rc.getSlotType() : "SETUP";
				if (rc.getSlotType() != null && rc.getSlotType().equals("SETUP")) {
					if (targetObject.getEquipment().getPreparation().getSecondaryResources() == null) {
						targetObject.getEquipment().getPreparation().setSecondaryResources(new ArrayList<>());
					}
					for (SetupSecondaryResourceInfos _rc : targetObject.getEquipment().getPreparation()
							.getSecondaryResources()) {
						if (!_rc.getChoosenEquipmentList().isEmpty()) {
							Resource choosen = _rc.getChoosenEquipmentList().get(0);
							if (choosen.getResourcesGroupCode() != null
									&& choosen.getResourcesGroupCode().equals(resourceGroupCode)) {
								matchingUse = _rc;
							}
						}
					}
					if (matchingUse == null) {
						SetupSecondaryResourceInfos m = new SetupSecondaryResourceInfos(targetObject.getContext());
						matchingUse = m;
						targetObject.getEquipment().getPreparation().getSecondaryResources().add(m);
					}

				} else if (rc.getSlotType() != null && rc.getSlotType().equals("WORK")) {
					if (targetObject.getEquipment().getExecution().getSecondaryResources() == null) {
						targetObject.getEquipment().getExecution().setSecondaryResources(new ArrayList<>());
					}
					for (WorkSecondaryResourceInfos _rc : targetObject.getEquipment().getExecution()
							.getSecondaryResources()) {
						if (!_rc.getChoosenEquipmentList().isEmpty()) {
							Resource choosen = _rc.getChoosenEquipmentList().get(0);
							if (choosen.getResourcesGroupCode() != null
									&& choosen.getResourcesGroupCode().equals(resourceGroupCode)) {
								matchingUse = _rc;
							}
						}
					}
					if (matchingUse == null) {
						WorkSecondaryResourceInfos m = new WorkSecondaryResourceInfos(targetObject.getContext());
						matchingUse = m;
						targetObject.getEquipment().getExecution().getSecondaryResources().add(m);
					}
				}
				matchingUse.getChoosenEquipmentList().add(res);
				TimeSegmentRequirement tsr = new TimeSegmentRequirement(
						slotType.equals("SETUP") ? TimeSegmentType.SETUP_TIME : TimeSegmentType.WORK_TIME);
				tsr.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				tsr.setStartDateTime(rc.getStart());
				tsr.setEndDateTime(rc.getEnd());
				TimesheetReservation reservation = timesheetLogic.addReservation(res, tsr, targetObject);
				if (reservation == null) {
					LOGGER.error("Cannot reserve " +resourceCode + "  with period=>["
							+ rc.getStart() + "," + rc.getEnd()+ "]");
				} else {
					matchingUse.getTimesheetReservations().add(reservation);
				}
			}

		} catch (DataModelDaoException e) {
			throw new MapperException("Error searching operationModel", e);
		}
	}

	@Override
	public Task mapInstance(TaskInputDto object, Class<Task> targetType, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		IContextualBusinessLogicFactory factory = contextualBLFactory.getComponentFactory();
		Task task = super.mapInstance(object, targetType, entityFactory, environment, recursive);

		return task;
	}

}
