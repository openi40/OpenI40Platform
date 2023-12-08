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
@Table(name = "rc_use_spec")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "afterStartMinutes", column = @Column(name = "after_start_minutes")),
		@AttributeOverride(name = "beforeStopMinutes", column = @Column(name = "before_stop_minutes")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "maxQty", column = @Column(name = "max_qty")),
		@AttributeOverride(name = "minQty", column = @Column(name = "min_qty")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "qty", column = @Column(name = "qty")),
		@AttributeOverride(name = "secondaryResourceGroupCode", column = @Column(name = "rc_group_code")),
		@AttributeOverride(name = "useType", column = @Column(name = "use_type")),
		@AttributeOverride(name = "usedTime", column = @Column(name = "used_time")),
		@AttributeOverride(name = "operationEquipmentSpecCode", column = @Column(name = "op_equip_spec_code")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })

public class OI40DBSecondaryResourceUseSpecification extends OI40DBBaseEntity implements Serializable {
	private String useType = null;
	private String secondaryResourceGroupCode = null;
	private Integer qty = null;
	private Integer minQty = null;
	private Integer maxQty = null;
	private String usedTime = null;
	private Integer afterStartMinutes = null, beforeStopMinutes = null;
	private String operationEquipmentSpecCode = null;
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getSecondaryResourceGroupCode() {
		return secondaryResourceGroupCode;
	}
	public void setSecondaryResourceGroupCode(String secondaryResourceGroupCode) {
		this.secondaryResourceGroupCode = secondaryResourceGroupCode;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Integer getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}
	public String getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}
	public Integer getAfterStartMinutes() {
		return afterStartMinutes;
	}
	public void setAfterStartMinutes(Integer afterStartMinutes) {
		this.afterStartMinutes = afterStartMinutes;
	}
	public Integer getBeforeStopMinutes() {
		return beforeStopMinutes;
	}
	public void setBeforeStopMinutes(Integer beforeStopMinutes) {
		this.beforeStopMinutes = beforeStopMinutes;
	}
	public String getOperationEquipmentSpecCode() {
		return operationEquipmentSpecCode;
	}
	public void setOperationEquipmentSpecCode(String operationEquipmentSpecCode) {
		this.operationEquipmentSpecCode = operationEquipmentSpecCode;
	}
}
