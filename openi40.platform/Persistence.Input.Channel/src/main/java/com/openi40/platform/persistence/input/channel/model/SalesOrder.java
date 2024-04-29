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

import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;

@Entity
@Table(name = "sales_order")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "askedDeliveryDate", column = @Column(name = "asked_del_date")),
		@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "orderStatus", column = @Column(name = "order_status")),
		@AttributeOverride(name = "orderType", column = @Column(name = "order_type")),
		@AttributeOverride(name = "partner", column = @Column(name = "partner")),
		@AttributeOverride(name = "plannedDeliveryDate", column = @Column(name = "pld_del_date")),
		@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")) })
public class SalesOrder extends SalesOrderInputDto {
	@Id
	@Override
	public String getCode() {

		return super.getCode();
	}

	@Override
	public void setCode(String code) {

		super.setCode(code);
	}

	@StreamLoadRelated(overriddenType = com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto.class, loadType = SalesOrderLine.class, relationType = RelationType.ONE2MANY, joinProperty = "orderCode")
	@Override
	public void setOrderLines(java.util.List<com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto> p) {
		super.setOrderLines(p);
	}
}
