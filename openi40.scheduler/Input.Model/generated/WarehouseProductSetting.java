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
import com.openi40.scheduler.input.model.material.configuration.WarehouseProductSettingInputDto;
@Entity
@Table(name = "whouse_prd_setting")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "averageMinPurchaseQty", column = @Column(name = "average_min_purch_qty")),
@AttributeOverride(name = "averageProductionDays", column = @Column(name = "average_prdion_days")),
@AttributeOverride(name = "averageleadTimeDays", column = @Column(name = "averagelead_time_days")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "produceAccordingToReorderLevel", column = @Column(name = "produce_according_to_reorder_level")),
@AttributeOverride(name = "produced", column = @Column(name = "prdcd")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "productionBySchedulerEnabled", column = @Column(name = "prdion_by_scheduler_enabled")),
@AttributeOverride(name = "purchaseAccordingToReorderLevel", column = @Column(name = "purch_according_to_reorder_level")),
@AttributeOverride(name = "purchaseBySchedulerEnabled", column = @Column(name = "purch_by_scheduler_enabled")),
@AttributeOverride(name = "purchased", column = @Column(name = "purchd")),
@AttributeOverride(name = "securityStock", column = @Column(name = "security_stock")),
@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code"))
})
public class WarehouseProductSetting extends WarehouseProductSettingInputDto{
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
