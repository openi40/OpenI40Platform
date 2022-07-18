package com.openi40.dbmodel.entities;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "whouse_prd_setting")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "averageMinPurchaseQty", column = @Column(name = "average_min_purch_qty")),
		@AttributeOverride(name = "averageProductionDays", column = @Column(name = "average_prdion_days")),
		@AttributeOverride(name = "averageleadTimeDays", column = @Column(name = "averagelead_time_days")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "produceAccordingToReorderLevel", column = @Column(name = "produce_according_to_reorder_level")),
		@AttributeOverride(name = "produced", column = @Column(name = "prdcd")),
		@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
		@AttributeOverride(name = "productionBySchedulerEnabled", column = @Column(name = "prdion_by_scheduler_enabled")),
		@AttributeOverride(name = "purchaseAccordingToReorderLevel", column = @Column(name = "purch_according_to_reorder_level")),
		@AttributeOverride(name = "purchaseBySchedulerEnabled", column = @Column(name = "purch_by_scheduler_enabled")),
		@AttributeOverride(name = "purchased", column = @Column(name = "purchd")),
		@AttributeOverride(name = "securityStock", column = @Column(name = "security_stock")),
		@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })
public class OI40DBWarehouseProductSetting  extends OI40DBBaseEntity implements Serializable {
	protected String warehouseCode = null;
	protected Integer averageleadTimeDays = null;
	protected Integer averageProductionDays = null;
	protected Double securityStock = null;
	protected Boolean produceAccordingToReorderLevel = null;
	protected Boolean purchaseAccordingToReorderLevel = null;
	protected Double averageMinPurchaseQty = null;
	protected Boolean produced = null;
	protected Boolean purchased = null;
	
	protected Boolean productionBySchedulerEnabled = null;
	protected Boolean purchaseBySchedulerEnabled = null;
	
	protected String productCode=null;
}
