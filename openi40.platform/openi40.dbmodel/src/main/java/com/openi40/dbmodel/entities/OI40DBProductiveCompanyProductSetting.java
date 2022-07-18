package com.openi40.dbmodel.entities;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Entity
@Table(name = "prdive_company_prd_setting")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "produced", column = @Column(name = "prdcd")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "productionBySchedulerEnabled", column = @Column(name = "prdion_by_scheduler_enabled")),
@AttributeOverride(name = "productiveCompanyCode", column = @Column(name = "prdive_company_code")),
@AttributeOverride(name = "purchaseBySchedulerEnabled", column = @Column(name = "purch_by_scheduler_enabled")),
@AttributeOverride(name = "purchased", column = @Column(name = "purchd")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})
@Data
public class OI40DBProductiveCompanyProductSetting extends OI40DBBaseEntity implements Serializable{
	private Boolean produced = null;
	private Boolean purchased = null;

	private Boolean productionBySchedulerEnabled = null;
	private Boolean purchaseBySchedulerEnabled = null;

	private String productCode = null;
	private String productiveCompanyCode = null;
}
