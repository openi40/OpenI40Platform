package com.openi40.scheduler.input.model.tasks;

import javax.persistence.MappedSuperclass;
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

@MappedSuperclass
public class TaskPurchaseMaterialReservationInputDto extends AbstractTaskMaterialReservationInputDto {
	
	String purchaseOrderCode = null;
	String purchaseOrderLineCode = null;
	public TaskPurchaseMaterialReservationInputDto() {
		
	}
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	public String getPurchaseOrderLineCode() {
		return purchaseOrderLineCode;
	}
	public void setPurchaseOrderLineCode(String purchaseOrderLineCode) {
		this.purchaseOrderLineCode = purchaseOrderLineCode;
	}

}
