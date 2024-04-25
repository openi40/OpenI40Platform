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
@Table(name = "plant_prd_setting")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
		@AttributeOverride(name = "produced", column = @Column(name = "prdcd")),
		@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
		@AttributeOverride(name = "productionBySchedulerEnabled", column = @Column(name = "prdion_by_scheduler_enabled")),
		@AttributeOverride(name = "purchaseBySchedulerEnabled", column = @Column(name = "purch_by_scheduler_enabled")),
		@AttributeOverride(name = "purchased", column = @Column(name = "purchd")) ,
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})

public class OI40DBPlantProductSetting extends OI40DBBaseEntity implements Serializable {
	private String plantCode = null;
	private Boolean produced = null;
	private Boolean purchased = null;

	private Boolean productionBySchedulerEnabled = null;
	private Boolean purchaseBySchedulerEnabled = null;

	private String productCode = null;

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public Boolean getProduced() {
		return produced;
	}

	public void setProduced(Boolean produced) {
		this.produced = produced;
	}

	public Boolean getPurchased() {
		return purchased;
	}

	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}

	public Boolean getProductionBySchedulerEnabled() {
		return productionBySchedulerEnabled;
	}

	public void setProductionBySchedulerEnabled(Boolean productionBySchedulerEnabled) {
		this.productionBySchedulerEnabled = productionBySchedulerEnabled;
	}

	public Boolean getPurchaseBySchedulerEnabled() {
		return purchaseBySchedulerEnabled;
	}

	public void setPurchaseBySchedulerEnabled(Boolean purchaseBySchedulerEnabled) {
		this.purchaseBySchedulerEnabled = purchaseBySchedulerEnabled;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
