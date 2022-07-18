package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.jndi.TypeMismatchNamingException;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.cycles.BomItemModelInputDto;
import com.openi40.scheduler.input.model.cycles.CoProductItemInputDto;
import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;
import com.openi40.scheduler.input.model.cycles.OperationEquipmentSpecificationInputDto;
import com.openi40.scheduler.input.model.cycles.OperationModelInputDto;
import com.openi40.scheduler.input.model.cycles.SecondaryResourceUseSpecificationInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoExceptionRuleInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoWorkingTimeRuleInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.cycle.BatchTransferType;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationEdgeModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.ISecondaryResourceGroupDao;
import com.openi40.scheduler.model.dao.IWorkCenterDao;
import com.openi40.scheduler.model.equipment.GroupingPolicy;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.ResourceRequiredCalculationType;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.ResourceModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.equipment.TaskPreparationModel;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.TaskProcessMetaInfo;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.ItemProducedMetaInfo;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
import com.openi40.scheduler.model.time.TimesheetMetaInfoExceptionRule;
import com.openi40.scheduler.model.time.TimesheetMetaInfoWorkingTimeRule;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
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
public class CycleModelInputModel2CycleModelService
		extends AbstractInputModel2ApsModelService<CycleModelInputDto, CycleModel> {
	@Autowired
	public CycleModelInputModel2CycleModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(CycleModelInputDto.class, CycleModel.class, beanFactory, businessModelFactory);

	}

	@Autowired
	IPlantDao plantDao;
	@Autowired
	IProductDao productDao;

	@Override
	public void copyValue(CycleModelInputDto originalObject, CycleModel cycleObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, cycleObject, entityFactory, environment, recursive);
		try {
			String plantCode = originalObject.getPlantCode();
			cycleObject.setPlantCode(plantCode);
			cycleObject.setProductCode(originalObject.getProductCode());
			if (plantCode == null)
				throw new MapperException("CycleModel without plantCode set");
			Plant plant;
			plant = plantDao.findByCode(plantCode, cycleObject.getContext());
			OperationModel targetObject = businessModelFactory.create(OperationModel.class, cycleObject.getContext());
			targetObject.setCompanyCode(plant.getProductiveCompanyCode());
			targetObject.setPlantCode(originalObject.getPlantCode());
			String productCode = originalObject.getProductCode();
			if (productCode == null)
				throw new MapperException("CycleModel without productCode set");
			Product product = productDao.findByCode(productCode, targetObject.getContext());
			if (product == null)
				throw new MapperException("Referred CycleModel productCode = " + productCode + " cannot be found");
			if (targetObject.getItemProducedModel() == null) {
				targetObject.setItemProducedModel(new ItemProducedMetaInfo(targetObject.getContext(), product, 1.0));

			} else {
				targetObject.getItemProducedModel().setSuppliedItem(product);
				targetObject.getItemProducedModel().setQty(1);
			}
			targetObject.getItemProducedModel().setWarehouseCode(originalObject.getWarehouseCode());
			targetObject.getItemProducedModel().setCode(
					"prd-" + plantCode + "-" + originalObject.getProductCode() + "-" + originalObject.getCode());
			List<OperationModelInputDto> operations = originalObject.getOperations();
			OperationModel currentModel = targetObject;
			if (operations != null) {
				int i = 0;
				for (i = operations.size() - 1; i >= 0; i--) {
					OperationModelInputDto operation = operations.get(i);
					cycleObject.getOperations().add(currentModel);
					currentModel = syncOperation(originalObject, operation, currentModel, i > 0);
				}
			}
			cycleObject.restructureOperations();
		} catch (DataModelDaoException e) {
			throw new MapperException("problem retrieving plant", e);
		}
	}

	private OperationEdgeModel addAsChild(OperationModel t1, OperationModel t2) {
		OperationEdgeModel frameEdge = businessModelFactory.create(OperationEdgeModel.class, t1.getContext());
		frameEdge.parent = t1;
		frameEdge.child = t2;
		frameEdge.setAlignmentType(PeriodsAlignmentType.START_AFTER_END);
		frameEdge.consumingBatchInfo = t1.getConsumingBatchInfo();
		if (frameEdge.consumingBatchInfo != null && frameEdge.consumingBatchInfo.getBatchTransferType() != null) {
			switch (frameEdge.consumingBatchInfo.getBatchTransferType()) {
			case TRANSFER_ALL: {
				frameEdge.setAlignmentType(PeriodsAlignmentType.START_AFTER_END);
			}
				;
				break;
			case CONTINUOUS: {
				frameEdge.setAlignmentType(PeriodsAlignmentType.START_AFTER_START);
			}
				;
				break;
			case GROUPING: {
				frameEdge.setAlignmentType(PeriodsAlignmentType.START_AFTER_START);
			}
				;
				break;
			}
		}
		t1.getChildTasks().add(frameEdge);
		t2.setParentTask(frameEdge);
		return frameEdge;
	}

	/*
	 * addAsChild(wafer002TaskModel, wafer001TaskModel); addTaskModel(c,
	 * wafer002TaskModel);
	 */
	private OperationModel syncOperation(CycleModelInputDto cycle, OperationModelInputDto operation,
			OperationModel currentModel, boolean createOrReturnPrevius) throws DataModelDaoException, MapperException {
		currentModel.setPlantCode(cycle.getPlantCode());
		currentModel.setCode(operation.getCode());
		currentModel.setDescription(operation.getDescription());
		currentModel.setCycleCode(cycle.getCode());
		currentModel.setSequenceCode(operation.getSequenceCode());
		currentModel.setDefaultProductCycle(cycle.isDefaultProductCycle());
		currentModel.setWarehouseCode(cycle.getWarehouseCode());

		if (operation.getConsumingBatchTransferType() != null) {
			if (currentModel.getConsumingBatchInfo() == null)
				currentModel.setConsumingBatchInfo(new BatchingInfo());

			currentModel.getConsumingBatchInfo()
					.setBatchTransferType(BatchTransferType.valueOf(operation.getConsumingBatchTransferType()));
		} else {
			if (currentModel.getConsumingBatchInfo() == null)
				currentModel.setConsumingBatchInfo(new BatchingInfo());
			currentModel.getConsumingBatchInfo().setBatchTransferType(BatchTransferType.TRANSFER_ALL);
		}
		if (operation.getConsumingBatchQty() > 0) {
			if (currentModel.getConsumingBatchInfo() == null)
				currentModel.setConsumingBatchInfo(new BatchingInfo());
			currentModel.getConsumingBatchInfo().setBatchQty(operation.getConsumingBatchQty());
		}
		if (operation.getProducingBatchTransferType() != null) {
			if (currentModel.getProducingBatchInfo() == null)
				currentModel.setProducingBatchInfo(new BatchingInfo());

			currentModel.getProducingBatchInfo()
					.setBatchTransferType(BatchTransferType.valueOf(operation.getProducingBatchTransferType()));
		} else {
			if (currentModel.getProducingBatchInfo() == null)
				currentModel.setProducingBatchInfo(new BatchingInfo());
			currentModel.getProducingBatchInfo().setBatchTransferType(BatchTransferType.TRANSFER_ALL);

		}
		if (operation.getProducingBatchQty() > 0) {
			if (currentModel.getProducingBatchInfo() == null)
				currentModel.setProducingBatchInfo(new BatchingInfo());
			currentModel.getProducingBatchInfo().setBatchQty(operation.getConsumingBatchQty());
		}
		syncEquipmentModelOptions(operation, currentModel);
		syncBomItems(operation, currentModel);
		syncCoproducts(operation, currentModel);
		if (createOrReturnPrevius) {
			List<OperationEdgeModel> childs = currentModel.getChildTasks();
			if (childs.isEmpty()) {
				addAsChild(currentModel, new OperationModel(currentModel.getContext()));
			}
			return currentModel.getChildTasks().get(0).child;
		} else
			return null;
	}

	private void syncCoproducts(OperationModelInputDto operation, OperationModel currentModel)
			throws DataModelDaoException {
		if (operation.getCoProducts() != null) {
			List<CoProductItemInputDto> coprd = operation.getCoProducts();
			for (CoProductItemInputDto coProductItemInputDto : coprd) {
				List<ItemProducedMetaInfo> matching = new ArrayList<ItemProducedMetaInfo>();
				Product product = productDao.findByCode(coProductItemInputDto.getProductCode(),
						currentModel.getContext());
				currentModel.getCoProducts().forEach((coProd) -> {
					if (coProductItemInputDto.getCode() != null && coProd.getCode() != null
							&& coProd.getCode().equals(coProductItemInputDto.getCode())) {
						matching.add(coProd);
					}
				});
				if (matching.isEmpty()) {
					matching.add(new ItemProducedMetaInfo(currentModel.getContext()));
					currentModel.getCoProducts().addAll(matching);
				}
				matching.get(0).setSuppliedItem(product);
				matching.get(0).setQty(coProductItemInputDto.getProducedQty());
			}
		}

	}

	private void syncBomItems(OperationModelInputDto operation, OperationModel currentModel)
			throws DataModelDaoException {
		List<BomItemModelInputDto> bomItems = operation.getBomItems();
		if (bomItems != null) {
			for (BomItemModelInputDto bomItem : bomItems) {
				Product _item = this.productDao.findByCode(bomItem.getRequiredProductCode(), currentModel.getContext());
				if (_item == null)
					throw new DataModelDaoException("The product code " + bomItem.getRequiredProductCode()
							+ " is not found in the current aps data model");
				List<BomItemModel> requiredMaterials = currentModel.getBomItems();
				List<BomItemModel> matching = new ArrayList<BomItemModel>();
				if (requiredMaterials != null) {
					requiredMaterials.forEach(requiredMaterial -> {
						if (requiredMaterial.getConsumedItem().equals(_item)) {
							matching.add(requiredMaterial);
						}
					});
				}
				if (matching.isEmpty()) {
					matching.add(businessModelFactory.create(BomItemModel.class, currentModel.getContext()));
					currentModel.getBomItems().addAll(matching);
				}
				matching.get(0).setConsumedItem(_item);
				matching.get(0).setWarehouseCode(bomItem.getWarehouseCode());
				if (bomItem.getUseCoefficient() == null || bomItem.getUseCoefficient() == 0.0)
					throw new DataModelDaoException(
							"The bom item " + bomItem.getCode() + " does not have a set useCoefficient");
				matching.get(0).setQty(bomItem.getUseCoefficient());
				matching.get(0).setConsumingBatchInfo(new BatchingInfo());
				matching.get(0).getConsumingBatchInfo().setBatchQty(bomItem.getConsumingBatchQty());
				if (bomItem.getConsumingBatchTransferType() != null) {
					matching.get(0).getConsumingBatchInfo()
							.setBatchTransferType(BatchTransferType.valueOf(bomItem.getConsumingBatchTransferType()));
				} else {
					matching.get(0).getConsumingBatchInfo().setBatchTransferType(BatchTransferType.TRANSFER_ALL);
				}
				matching.get(0).setCode(bomItem.getCode());
			}
		}
		List<BomItemModel> reqMaterial = currentModel.getBomItems();
		if (reqMaterial != null) {
			List<BomItemModel> toRemove = new ArrayList<BomItemModel>();
			for (BomItemModel itemConsumedMetaInfo : reqMaterial) {
				List<BomItemModelInputDto> matchingItems = new ArrayList<BomItemModelInputDto>();
				if (operation.getBomItems() != null) {
					operation.getBomItems().forEach((bomItem) -> {
						if (bomItem.getRequiredProductCode().equals(itemConsumedMetaInfo.getConsumedItem().getCode())) {
							matchingItems.add(bomItem);
						}
					});
				}
				if (matchingItems.isEmpty()) {
					toRemove.add(itemConsumedMetaInfo);
				}
			}
			reqMaterial.removeAll(toRemove);
		}
	}

	@Autowired
	IWorkCenterDao workCenterDao;
	@Autowired
	ISecondaryResourceGroupDao secondaryResourceDao;

	private void syncEquipmentModelOptions(OperationModelInputDto operation, OperationModel currentModel)
			throws DataModelDaoException, MapperException {
		List<OperationEquipmentSpecificationInputDto> equipmentSpec = operation.getEquipmentSpecifications();
		currentModel.setEquipmentModelOptions(
				businessModelFactory.create(TaskEquipmentModelOptions.class, currentModel.getContext()));
		if (currentModel.getEquipmentModelOptions().getEquipmentModels() == null) {
			currentModel.getEquipmentModelOptions().setEquipmentModels(new ArrayList<TaskEquipmentModelInfo>());
		}
		if (equipmentSpec != null) {
			for (OperationEquipmentSpecificationInputDto espec : equipmentSpec) {
				List<TaskEquipmentModelInfo> matchingTaskEquipmentModels = new ArrayList<TaskEquipmentModelInfo>();
				currentModel.getEquipmentModelOptions().getEquipmentModels().forEach(equipmentModel -> {
					if (equipmentModel.getCode().equals(espec.getCode())) {
						matchingTaskEquipmentModels.add(equipmentModel);
					}
				});
				if (matchingTaskEquipmentModels.isEmpty()) {
					matchingTaskEquipmentModels.add(new TaskEquipmentModelInfo(currentModel.getContext()));
					currentModel.getEquipmentModelOptions().getEquipmentModels().addAll(matchingTaskEquipmentModels);
				}
				TaskEquipmentModelInfo model = matchingTaskEquipmentModels.get(0);
				model.setCode(espec.getCode());
				model.setDescription(espec.getDescription());
				model.setSetupGroupCode(espec.getSetupGroupCode());
				if (model.getTaskMetaInfo() == null) {
					model.setTaskMetaInfo(
							businessModelFactory.create(TaskProcessMetaInfo.class, currentModel.getContext()));
				}
				model.getTaskMetaInfo().setMachineTime(espec.getMachineTime());
				model.getTaskMetaInfo().setSetupTime(espec.getSetupTime());
				model.getTaskMetaInfo().setMachineTimeSpecCode(espec.getMachineTimeSpec());
				model.setPreparationModel(
						businessModelFactory.create(TaskPreparationModel.class, currentModel.getContext()));
				model.setExecutionModel(
						businessModelFactory.create(TaskExecutionModel.class, currentModel.getContext()));

				WorkCenter workCenter = workCenterDao.findByCode(espec.getWorkCenterCode(), currentModel.getContext());
				if (workCenter == null)
					throw new MapperException("workCenter " + espec.getWorkCenterCode()
							+ " not found in current model, it was referred from equipment specification in operation=>"
							+ operation.getCode());
				if (model.getPreparationModel().getResource() == null) {
					TaskPreparationUseModel<Machine, MachinesGroup> rc = new TaskPreparationUseModel<Machine, MachinesGroup>(
							currentModel.getContext(), Machine.class, MachinesGroup.class);
					model.getPreparationModel().setResource(rc);
				}
				model.getPreparationModel().getResource().setGroup(workCenter);
				model.getPreparationModel().getResource().setGroupingPolicy(GroupingPolicy.INSTANCE_IDENTIFIED);
				if (model.getExecutionModel().getResource() == null) {
					ResourceModelInfo rc = new ResourceModelInfo();
					model.getExecutionModel().setResource(rc);
				}
				model.getExecutionModel().getResource().setGroup(workCenter);
				model.getPreparationModel().getResource().setQty(1);
				model.getExecutionModel().getResource().setQty(1);
				model.getExecutionModel().getResource().setGroupingPolicy(GroupingPolicy.INSTANCE_IDENTIFIED);
				if (model.getPreparationModel().getSecondaryResources() == null) {
					model.getPreparationModel().setSecondaryResources(new ArrayList());
				}
				if (model.getExecutionModel().getSecondaryResources() == null) {
					model.getExecutionModel().setSecondaryResources(new ArrayList());
				}
				List<SecondaryResourceUseSpecificationInputDto> secondaryResources = espec.getSecondaryResourcesSpecs();
				for (SecondaryResourceUseSpecificationInputDto secSpec : secondaryResources) {
					ResourceGroup secondaryGroup = this.secondaryResourceDao
							.findByCode(secSpec.getSecondaryResourceGroupCode(), currentModel.getContext());
					if (secondaryGroup == null)
						throw new MapperException(
								"secondary resource group with code " + secSpec.getSecondaryResourceGroupCode()
										+ " has not been found in current model entries");
					if (secSpec.getUsedTime() != null) {
						boolean addSetup = true;
						boolean addWork = true;
						switch (secSpec.getUsedTime()) {
						case "SETUP": {
							addSetup = true;
							addWork = false;
						}
							break;
						case "WORK": {
							addSetup = false;
							addWork = true;
						}
							break;

						case "SETUP_WORK": {

							addSetup = true;
							addWork = true;

						}
							break;
						}
						if (addSetup) {
							List<TaskPreparationUseModel<Resource, ResourceGroup>> matching = new ArrayList<TaskPreparationUseModel<Resource, ResourceGroup>>();
							model.getPreparationModel().getSecondaryResources().forEach((secondary) -> {
								if (secondary.getCode().equals(secSpec.getCode())) {
									matching.add(secondary);
								}
							});
							if (matching.isEmpty()) {
								TaskPreparationUseModel<Resource, ResourceGroup> thisEntry = new TaskPreparationUseModel<Resource, ResourceGroup>(
										currentModel.getContext(), Resource.class, ResourceGroup.class);
								matching.add(thisEntry);
								model.getPreparationModel().getSecondaryResources().addAll(matching);
							}
							TaskPreparationUseModel<Resource, ResourceGroup> tofill = matching.get(0);
							tofill.setCode(secSpec.getCode());
							tofill.setDescription(secSpec.getDescription());
							tofill.setGroup(secondaryGroup);
							tofill.setQty(secSpec.getQty());
							tofill.setGroupingPolicy(GroupingPolicy.INSTANCE_IDENTIFIED);
							ResourceRequiredCalculationType calculationType = ResourceRequiredCalculationType.CONSTANT;
							if (secSpec.getUseType() != null) {
								switch (secSpec.getUseType()) {
								case "CONSTANT": {
									calculationType = ResourceRequiredCalculationType.CONSTANT;
								}
									break;
								case "PROPORTIONAL": {
									calculationType = ResourceRequiredCalculationType.PROPORTIONAL;
								}
									break;
								}
							}
							tofill.setResourceRequiredCalculationType(calculationType);
							tofill.setResourceCalculatedLot(secSpec.getQty());
						}
						if (addWork) {
							{
								List<SecondaryModelInfo> matching = new ArrayList<SecondaryModelInfo>();
								model.getExecutionModel().getSecondaryResources().forEach((secondary) -> {
									if (secondary.getCode().equals(secSpec.getCode())) {
										matching.add(secondary);
									}
								});
								if (matching.isEmpty()) {
									SecondaryModelInfo thisEntry = new SecondaryModelInfo();
									matching.add(thisEntry);
									model.getExecutionModel().getSecondaryResources().addAll(matching);
								}
								SecondaryModelInfo tofill = matching.get(0);
								tofill.setCode(secSpec.getCode());
								tofill.setDescription(secSpec.getDescription());
								tofill.setGroup(secondaryGroup);
								tofill.setQty(secSpec.getQty());
								tofill.setMaxQty(secSpec.getMaxQty());
								tofill.setMinQty(secSpec.getMinQty());
								tofill.setGroupingPolicy(GroupingPolicy.INSTANCE_IDENTIFIED);
								ResourceRequiredCalculationType calculationType = ResourceRequiredCalculationType.CONSTANT;
								if (secSpec.getUseType() != null) {
									switch (secSpec.getUseType()) {
									case "CONSTANT": {
										calculationType = ResourceRequiredCalculationType.CONSTANT;
									}
										break;
									case "PROPORTIONAL": {
										calculationType = ResourceRequiredCalculationType.PROPORTIONAL;
									}
										break;
									}
								}
								tofill.setResourceRequiredCalculationType(calculationType);
								tofill.setResourceCalculatedLot(secSpec.getQty());
							}
						}
					}
				}
			}
		}

	}

}