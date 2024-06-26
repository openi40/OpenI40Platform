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
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Entity
@Table(name = "stock_supply")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "physicalStockQuantity", column = @Column(name = "physical_stock_quantity")),
		@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
		@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
		@AttributeOverride(name = "infiniteCapacity", column = @Column(name = "infinite_capacity")) })

public class OI40DBStockSupply extends OI40DBBaseEntity implements Serializable {
	private String productCode = null;
	private String warehouseCode = null;
	private Double physicalStockQuantity = null;
	private Boolean infiniteCapacity;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public Double getPhysicalStockQuantity() {
		return physicalStockQuantity;
	}
	public void setPhysicalStockQuantity(Double physicalStockQuantity) {
		this.physicalStockQuantity = physicalStockQuantity;
	}
	public Boolean getInfiniteCapacity() {
		return infiniteCapacity;
	}
	public void setInfiniteCapacity(Boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}
}
