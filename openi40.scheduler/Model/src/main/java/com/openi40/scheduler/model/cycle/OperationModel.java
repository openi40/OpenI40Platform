package com.openi40.scheduler.model.cycle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.material.ItemProducedMetaInfo;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;
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

public class OperationModel extends AbstractPlantRelatedApsObject implements IMetaInfo ,IApsResourcesDependencyTreeObject{
	
	protected String warehouseCode = null;
	protected String cycleCode = null;
	protected boolean defaultProductCycle = false;
	protected List<OperationEdgeModel> ChildTasks = new ArrayList<OperationEdgeModel>();
	protected TaskEquipmentModelOptions EquipmentModelOptions = null;

	protected ItemProducedMetaInfo ItemProducedModel = null;
	protected OperationEdgeModel ParentTask;
	protected List<BomItemModel> bomItems = new ArrayList<BomItemModel>();
	protected List<ItemProducedMetaInfo> coProducts = new ArrayList<ItemProducedMetaInfo>();
	protected BatchingInfo producingBatchInfo = new BatchingInfo();
	protected BatchingInfo consumingBatchInfo = new BatchingInfo();
	protected String SequenceCode = null;
	protected PeriodsAlignmentType defaultPreceidingAlignmentType = PeriodsAlignmentType.START_AFTER_END;

	public OperationModel(ApsData context) {
		super(context);

	}

	

	protected void finalize() throws Throwable {
		getBomItems().clear();
		setBomItems(null);
		getChildTasks().clear();
		setChildTasks(null);
		setParentTask(null);
		setItemProducedModel(null);
		setEquipmentModelOptions(null);
	}



	public String getWarehouseCode() {
		return warehouseCode;
	}



	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}



	public String getCycleCode() {
		return cycleCode;
	}



	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}



	public boolean isDefaultProductCycle() {
		return defaultProductCycle;
	}



	public void setDefaultProductCycle(boolean defaultProductCycle) {
		this.defaultProductCycle = defaultProductCycle;
	}



	public List<OperationEdgeModel> getChildTasks() {
		return ChildTasks;
	}



	public void setChildTasks(List<OperationEdgeModel> childTasks) {
		ChildTasks = childTasks;
	}



	public TaskEquipmentModelOptions getEquipmentModelOptions() {
		return EquipmentModelOptions;
	}



	public void setEquipmentModelOptions(TaskEquipmentModelOptions equipmentModelOptions) {
		EquipmentModelOptions = equipmentModelOptions;
	}



	public ItemProducedMetaInfo getItemProducedModel() {
		return ItemProducedModel;
	}



	public void setItemProducedModel(ItemProducedMetaInfo itemProducedModel) {
		ItemProducedModel = itemProducedModel;
	}



	public OperationEdgeModel getParentTask() {
		return ParentTask;
	}



	public void setParentTask(OperationEdgeModel parentTask) {
		ParentTask = parentTask;
	}



	public List<BomItemModel> getBomItems() {
		return bomItems;
	}



	public void setBomItems(List<BomItemModel> requiredMaterial) {
		bomItems = requiredMaterial;
	}



	public List<ItemProducedMetaInfo> getCoProducts() {
		return coProducts;
	}



	public void setCoProducts(List<ItemProducedMetaInfo> coProducts) {
		this.coProducts = coProducts;
	}



	public BatchingInfo getProducingBatchInfo() {
		return producingBatchInfo;
	}



	public void setProducingBatchInfo(BatchingInfo producingBatchInfo) {
		this.producingBatchInfo = producingBatchInfo;
	}



	public BatchingInfo getConsumingBatchInfo() {
		return consumingBatchInfo;
	}



	public void setConsumingBatchInfo(BatchingInfo consumingBatchInfo) {
		this.consumingBatchInfo = consumingBatchInfo;
	}



	public String getSequenceCode() {
		return SequenceCode;
	}



	public void setSequenceCode(String sequenceCode) {
		SequenceCode = sequenceCode;
	}



	public PeriodsAlignmentType getDefaultPreceidingAlignmentType() {
		return defaultPreceidingAlignmentType;
	}



	public void setDefaultPreceidingAlignmentType(PeriodsAlignmentType defaultPreceidingAlignmentType) {
		this.defaultPreceidingAlignmentType = defaultPreceidingAlignmentType;
	}



	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		ResourceDepsItemMetaInfo info=new ResourceDepsItemMetaInfo(this);
		return info;
	}



	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		List<IApsResourcesDependencyTreeObject> list=new ArrayList<IApsResourcesDependencyTreeObject>();
		if (EquipmentModelOptions!=null) {
			list.add(EquipmentModelOptions);
		}
		if (bomItems!=null) {
			list.addAll(bomItems);
		}
		if (ItemProducedModel!=null) {
			list.add(ItemProducedModel);
		}
		return list;
	}

}