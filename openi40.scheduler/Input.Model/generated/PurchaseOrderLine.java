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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.orders.PurchaseOrderLineInputDto;
@Entity
@Table(name = "purch_order_line")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "askedDeliveryDate", column = @Column(name = "asked_del_date")),
@AttributeOverride(name = "color", column = @Column(name = "color")),
@AttributeOverride(name = "completedQty", column = @Column(name = "completed_qty")),
@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
@AttributeOverride(name = "lineStatus", column = @Column(name = "line_status")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "orderCode", column = @Column(name = "order_code")),
@AttributeOverride(name = "orderType", column = @Column(name = "order_type")),
@AttributeOverride(name = "plannedDeliveryDate", column = @Column(name = "pld_del_date")),
@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "residualQty", column = @Column(name = "residual_qty")),
@AttributeOverride(name = "totalQty", column = @Column(name = "total_qty")),
@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code"))
})
public class PurchaseOrderLine extends PurchaseOrderLineInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
}
