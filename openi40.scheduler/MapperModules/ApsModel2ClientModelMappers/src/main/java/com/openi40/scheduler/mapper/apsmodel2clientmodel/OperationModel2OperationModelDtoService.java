package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.cycles.BomItemModelDto;
import com.openi40.scheduler.client.model.cycles.CycleModelDto;
import com.openi40.scheduler.client.model.cycles.OperationModelDto;
import com.openi40.scheduler.client.model.cycles.OperationModelDto.OperationEquipmentSpecification;
import com.openi40.scheduler.client.model.cycles.OperationTimeCalculationSpec;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
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
public class OperationModel2OperationModelDtoService extends AbstractReflectorMapper<OperationModel, OperationModelDto>
		implements IMapper<OperationModel, OperationModelDto> {
	public OperationModel2OperationModelDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, OperationModel.class, OperationModelDto.class);
	}

	@Override
	public void copyValue(OperationModel originalObject, OperationModelDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		List<BomItemModel> bomItems = originalObject.getBomItems();
		for (BomItemModel bomItemModel : bomItems) {
			BomItemModelDto item = new BomItemModelDto();
			item.setCode(bomItemModel.getCode());
			item.setDescription(bomItemModel.getDescription());
			item.setId(bomItemModel.getId());
			item.setRequiredProductCode(bomItemModel.getConsumedItem().getCode());
			item.setUseCoefficient(bomItemModel.getQty());
			item.setWarehouseCode(bomItemModel.getWarehouseCode());
			targetObject.getBomItems().add(item);
		}
		TaskEquipmentModelOptions equipment = originalObject.getEquipmentModelOptions();
		if (equipment != null) {
			List<TaskEquipmentModelInfo> eqOptions = equipment.getEquipmentModels();
			for (TaskEquipmentModelInfo eo : eqOptions) {
				OperationEquipmentSpecification equipmentSpec = new OperationEquipmentSpecification();
				equipmentSpec.setCode(eo.getCode());
				equipmentSpec.setDescription(eo.getDescription());
				equipmentSpec.setId(eo.getId());
				if (eo.getTaskMetaInfo() != null) {
					equipmentSpec.setMachineTime(eo.getTaskMetaInfo().getMachineTime());
					if (eo.getTaskMetaInfo().getMachineTimeSpecCode() != null) {
						equipmentSpec.setMachineTimeSpec(
								OperationTimeCalculationSpec.valueOf(eo.getTaskMetaInfo().getMachineTimeSpecCode()));
					}
					equipmentSpec.setSetupTime(eo.getTaskMetaInfo().getSetupTime());
					if (eo.getExecutionModel() != null && eo.getExecutionModel().getResource() != null
							&& eo.getExecutionModel().getResource().getGroup() != null) {
						equipmentSpec.setWorkCenterCode(eo.getExecutionModel().getResource().getGroup().getCode());
					}
				}
				targetObject.getEquipmentSpecifications().add(equipmentSpec);
			}
		}
	}
}
