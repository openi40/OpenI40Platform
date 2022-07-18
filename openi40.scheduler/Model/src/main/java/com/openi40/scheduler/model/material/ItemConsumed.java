package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.cycle.BomItemModel;

import lombok.Data;
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
public class ItemConsumed extends AbstractSupplyConsumer implements IReferencingMetaInfo<BomItemModel> {

	private BomItemModel metaInfo;
	private BatchingInfo consumingBatchInfo=null; 
	public BomItemModel getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(BomItemModel metaInfo) {
		this.metaInfo = metaInfo;
	}

	public ProductionSupply getTaskProductionLink() {
		return TaskProductionLink;
	}

	public void setTaskProductionLink(ProductionSupply taskProductionLink) {
		TaskProductionLink = taskProductionLink;
	}

	private ProductionSupply TaskProductionLink = null;

	public ItemConsumed(ApsData context) {
		super(context);
	}

	public ItemConsumed(ApsData context, BomItemModel Model) {
		super(context);
		this.setMetaInfo(Model);
		this.setProduct(Model.getConsumedItem());

		this.setRequiredQty(Model.getQty());
	}

	public ItemConsumed(ApsData context, BomItemModel Model, ProductionSupply linkedProduction) {
		super(context);
		this.setMetaInfo(Model);
		this.setProduct(Model.getConsumedItem());

		this.setRequiredQty(Model.getQty());
		this.setTaskProductionLink(linkedProduction);
	}

	public BatchingInfo getConsumingBatchInfo() {
		return consumingBatchInfo;
	}

	public void setConsumingBatchInfo(BatchingInfo consumingBatchInfo) {
		this.consumingBatchInfo = consumingBatchInfo;
	}

}