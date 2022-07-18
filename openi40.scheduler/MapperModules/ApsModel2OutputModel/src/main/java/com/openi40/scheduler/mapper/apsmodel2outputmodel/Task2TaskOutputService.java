package com.openi40.scheduler.mapper.apsmodel2outputmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimesheetReservation;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.LinkType;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.SecondaryReservation;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.TaskMaterialTransfer;
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
public class Task2TaskOutputService extends AbstractApsModel2OutputModelService<Task, TaskOutputDto> {

	public Task2TaskOutputService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Task.class, TaskOutputDto.class);

	}

	@Override
	public void copyValue(Task originalObject, TaskOutputDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setStartPreparation(originalObject.getSetupPhaseExecution() != null
				? originalObject.getSetupPhaseExecution().getStartDateTime()
				: null);
		targetObject.setEndPreparation(originalObject.getSetupPhaseExecution() != null
				? originalObject.getSetupPhaseExecution().getEndDateTime()
				: null);
		targetObject.setStartExecution(originalObject.getWorkPhaseExecution() != null
				? originalObject.getWorkPhaseExecution().getStartDateTime()
				: null);
		targetObject.setEndExecution(
				originalObject.getWorkPhaseExecution() != null ? originalObject.getWorkPhaseExecution().getEndDateTime()
						: null);
		targetObject.setScheduledMachineCode(originalObject.getEquipment() != null
				&& originalObject.getEquipment().getExecution() != null
				&& originalObject.getEquipment().getExecution().getResource() != null
				&& originalObject.getEquipment().getExecution().getResource().getChoosenEquipment() != null
						? originalObject.getEquipment().getExecution().getResource().getChoosenEquipment().getCode()
						: null);
		targetObject.setWorkCenterCode(
				originalObject.getEquipment() != null && originalObject.getEquipment().getExecution() != null
						&& originalObject.getEquipment().getExecution().getResource() != null
						&& originalObject.getEquipment().getExecution().getResource().getChoosenEquipment() != null
								? originalObject.getEquipment().getExecution().getResource().getChoosenEquipment()
										.getWorkCenterCode()
								: null);
		targetObject.setSequenceCode(originalObject.getSequenceCode());
		copySecondaryResourceData(originalObject, targetObject);
		copyMaterialTransfers(originalObject, targetObject);
		targetObject.setEquipmentSpecCode(
				originalObject.getEquipment() != null && originalObject.getEquipment().getMetaInfo() != null
						? originalObject.getEquipment().getMetaInfo().getCode()
						: null);
		targetObject.setAskedDeliveryDateTime(originalObject.getAskedDeliveryDateTime()!=null?new Timestamp(originalObject.getAskedDeliveryDateTime().getTime()):null);
		targetObject.setQtyTotal(originalObject.getQtyTotal());
		targetObject.setQtyProduced(originalObject.getQtyProduced());
		targetObject.setSetupTime(originalObject.getEquipment()!=null && originalObject.getEquipment().getPreparation()!=null?originalObject.getEquipment().getPreparation().getSetupTime():0.0);
		targetObject.setWorkTime(originalObject.getEquipment()!=null && originalObject.getEquipment().getExecution()!=null?originalObject.getEquipment().getExecution().getNominalWorkTime():0.0);
		targetObject.setSetupGroupCode(originalObject.getEquipment()!=null?originalObject.getEquipment().getSetupGroupCode():null);

	}

	private void copyMaterialTransfers(Task originalObject, TaskOutputDto targetObject) {
		List<ItemConsumed> consumptions = originalObject.getMaterialConsumptions();
		List<SupplyReservation> reservations = new ArrayList<>();
		for (ItemConsumed itemConsumed : consumptions) {
			reservations.addAll(itemConsumed.getOwnedReservations());
		}
		for (SupplyReservation supplyReservation : reservations) {
			ISupply supply = supplyReservation.getSupply();
			TaskMaterialTransfer mt = new TaskMaterialTransfer();
			mt.setTaskCode(originalObject.getCode());
			mt.setWarehouseCode(supply.getWarehouseCode());
			mt.setBatchQty(0);
			mt.setQtyReserved(supplyReservation.getQtyProvided());
			mt.setStartTransfer(supplyReservation.getMovementDate());
			mt.setMachineCode(targetObject.getScheduledMachineCode());
			mt.setWorkCenterCode(targetObject.getWorkCenterCode());
			mt.setProductCode(supply.getSuppliedItem().getCode());
			if (supply instanceof ProductionSupply) {
				mt.setLinkType(LinkType.PRODUCTION);
				ProductionSupply p = (ProductionSupply) supply;
				mt.setSupplyWorkOrderCode(p.getWorkOrderCode());
				mt.setSupplyTaskCode(p.getTaskCode());
			} else if (supply instanceof PurchaseSupply) {
				mt.setLinkType(LinkType.PURCHASE);
				PurchaseSupply p = (PurchaseSupply) supply;
				mt.setPurchaseOrderLineCode(p.getOrderCode());
			} else if (supply instanceof StockSupply) {
				mt.setLinkType(LinkType.STOCK);

			}
			targetObject.getMaterialTransfer().add(mt);
		}
	}

	private void copySecondaryResourceData(Task originalObject, TaskOutputDto targetObject) {
		List<TimesheetReservation> setup = new ArrayList<>();
		List<TimesheetReservation> work = new ArrayList<>();
		if (originalObject.getEquipment() != null && originalObject.getEquipment().getPreparation() != null
				&& originalObject.getEquipment().getPreparation().getSecondaryResources() != null) {
			List<SetupSecondaryResourceInfos> prepSecondaries = originalObject.getEquipment().getPreparation()
					.getSecondaryResources();
			for (SetupSecondaryResourceInfos secondary : prepSecondaries) {
				setup.addAll(secondary.getTimesheetReservations());
			}
		}
		if (originalObject.getEquipment() != null && originalObject.getEquipment().getExecution() != null
				&& originalObject.getEquipment().getExecution().getSecondaryResources() != null) {
			List<WorkSecondaryResourceInfos> execSecondaries = originalObject.getEquipment().getExecution()
					.getSecondaryResources();
			for (WorkSecondaryResourceInfos secondary : execSecondaries) {
				if (secondary.getTimesheetReservations() != null) {
					work.addAll(secondary.getTimesheetReservations());
				}
			}
		}
		for (TimesheetReservation timesheetReservation : setup) {
			targetObject.getSecondaryReservations().add(create(timesheetReservation, "SETUP"));
		}
		for (TimesheetReservation timesheetReservation : work) {
			targetObject.getSecondaryReservations().add(create(timesheetReservation, "WORK"));
		}
	}

	private SecondaryReservation create(TimesheetReservation res, String type) {
		SecondaryReservation r = new SecondaryReservation();
		Resource rc = (Resource) res.getReservedObject();
		r.setResourceCode(rc.getCode());
		r.setSecondaryResourceGroupCode(rc.getResourcesGroupCode());
		r.setStart(res.getStartDateTime());
		r.setEnd(res.getEndDateTime());
		r.setSlotType(type);
		return r;
	}
}
