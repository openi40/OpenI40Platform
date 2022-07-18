package com.openi40.scheduler.model.dao.impl;

import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.material.Product;
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
public class ItemConsumedMetaInfoDao {
	private OperationModel taskScope = null;

	public ItemConsumedMetaInfoDao(OperationModel task) {
		taskScope = task;
	}

	public final BomItemModel FindByItem(Product product) {
		BomItemModel found = null;
		for (BomItemModel consumed : taskScope.getBomItems()) {
			if (consumed.getConsumedItem() != null && consumed.getConsumedItem().getCode() != null && product != null && product.getCode() != null && product.getCode().equals(consumed.getConsumedItem().getCode())) {
				found = consumed;
			}
		}

		return found;
	}
}