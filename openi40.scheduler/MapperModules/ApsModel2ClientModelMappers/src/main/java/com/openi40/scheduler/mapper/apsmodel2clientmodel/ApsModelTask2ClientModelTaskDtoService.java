package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.aps.ApsMessageDto;
import com.openi40.scheduler.client.model.equipment.MachineDto;
import com.openi40.scheduler.client.model.material.MaterialConsumptionDto;
import com.openi40.scheduler.client.model.tasks.EquipmentInfoDto;
import com.openi40.scheduler.client.model.tasks.EquipmentInfoDto.RCUse;
import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.UseModel;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.Use;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
@Service
public class ApsModelTask2ClientModelTaskDtoService extends AbstractReflectorMapper<Task, TaskDto>
		implements IMapper<Task, TaskDto> {

	public ApsModelTask2ClientModelTaskDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Task.class, TaskDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(Task originalObject, TaskDto targetObject, IEntitiesFactory entityFactory, Map environment,
			boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		if (originalObject.getMainTimeRange() != null) {
			targetObject.getExecution().setStartDateTime(originalObject.getMainTimeRange().getStartDateTime());
			targetObject.getExecution().setEndDateTime(originalObject.getMainTimeRange().getEndDateTime());
		}
		if (originalObject.getSetupPhaseExecution() != null) {
			targetObject.getSetup().setStartDateTime(originalObject.getSetupPhaseExecution().getStartDateTime());
			targetObject.getSetup().setEndDateTime(originalObject.getSetupPhaseExecution().getEndDateTime());
		}
		if (originalObject.getWorkPhaseExecution() != null) {
			targetObject.getWork().setStartDateTime(originalObject.getWorkPhaseExecution().getStartDateTime());
			targetObject.getWork().setEndDateTime(originalObject.getWorkPhaseExecution().getEndDateTime());
		}
		if (originalObject.getEquipment() != null && originalObject.getEquipment().getExecution().getResource() != null
				&& originalObject.getEquipment().getExecution().getResource().getChoosenEquipment() != null) {
			targetObject.setScheduledMachineCode(
					originalObject.getEquipment().getExecution().getResource().getChoosenEquipment().getCode());
			if (originalObject.getEquipment().getPreparation() != null) {
				EquipmentInfoDto preparation = createEquipmentInfoDto(originalObject.getEquipment().getPreparation());
				targetObject.setSetupEquip(preparation);
			}
			if (originalObject.getEquipment().getExecution() != null) {
				EquipmentInfoDto work = createEquipmentInfoDto(originalObject.getEquipment().getExecution());
				targetObject.setWorkEquip(work);

			}
		}
		if (originalObject.getEquipment() != null && originalObject.getEquipment().getPreparation() != null
				&& originalObject.getEquipment().getPreparation().getResource() != null
				&& originalObject.getEquipment().getPreparation().getResource().getMetaInfo() != null
				&& originalObject.getEquipment().getPreparation().getResource().getMetaInfo().getGroup() != null) {
			String wc = originalObject.getEquipment().getPreparation().getResource().getMetaInfo().getGroup().getCode();

			targetObject.setWorkCenterCode(wc);
		}
		for (TaskEdge edge : originalObject.getChildTasks()) {
			String id = edge.getProducerTask().getId();
			targetObject.addSupplyTaskId(id);
		}
		IMapper<ItemConsumed, MaterialConsumptionDto> mapper = getMapperFactory().createMapper(ItemConsumed.class,
				MaterialConsumptionDto.class);
		for (ItemConsumed item : originalObject.getMaterialConsumptions()) {
			MaterialConsumptionDto mc = mapper.mapInstance(item, MaterialConsumptionDto.class, entityFactory,
					environment, true);
			targetObject.getMaterialConsumptions().add(mc);

		}
		if (targetObject.getMessages().isEmpty()) {
			IMapper<ApsMessage, ApsMessageDto> _mapper = getMapperFactory().createMapper(ApsMessage.class,
					ApsMessageDto.class);
			for (ApsMessage msg : originalObject.getMessages()) {
				ApsMessageDto mc = _mapper.mapInstance(msg, ApsMessageDto.class, entityFactory, environment, true);
				targetObject.getMessages().add(mc);
			}
		}
	}

	private EquipmentInfoDto createEquipmentInfoDto(TaskPreparationPlanned preparation) {
		EquipmentInfoDto info = new EquipmentInfoDto();
		info.setMachine(createRcUse(preparation.getResource()));
		if (preparation.getSecondaryResources() != null) {
			for (SetupSecondaryResourceInfos sr : preparation.getSecondaryResources()) {
				List<RCUse> sruse = this.createRcUse(sr);
				info.getSecondaries().addAll(sruse);
			}
		}
		return info;
	}

	private EquipmentInfoDto createEquipmentInfoDto(TaskExecutionPlanned taskExecutionPlanned) {
		EquipmentInfoDto info = new EquipmentInfoDto();
		info.setMachine(createRcUse(taskExecutionPlanned.getResource()));
		if (taskExecutionPlanned.getSecondaryResources() != null) {
			for (WorkSecondaryResourceInfos sr : taskExecutionPlanned.getSecondaryResources()) {
				List<RCUse> sruse = this.createRcUse(sr);
				info.getSecondaries().addAll(sruse);
			}
		}
		return info;
	}

	private <EquipmentType extends ITimesheetAllocableObject, EquipmentModelType extends UseModel<EquipmentGroupType, EquipmentType>, EquipmentGroupType extends Group<EquipmentType>> List<RCUse> createRcUse(
			Use<EquipmentType, EquipmentModelType, EquipmentGroupType> resource) {
		List<RCUse> use = new ArrayList<>();
		for (TimesheetReservation tr : resource.getTimesheetReservations()) {
			RCUse tsdto = new RCUse();
			tsdto.setId(tr.getReservedObject()!=null?tr.getReservedObject().getId():null);			
			tsdto.setCode(tr.getReservedObject() != null ? tr.getReservedObject().getCode() : null);
			tsdto.setDescription(tr.getReservedObject() != null ? tr.getReservedObject().getDescription() : null);
			tsdto.setStartDateTime(tr.getStartDateTime());
			tsdto.setEndDateTime(tr.getEndDateTime());
			use.add(tsdto);
		}
		return use;
	}
}