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
@Table(name = "co_prd_item")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
@AttributeOverride(name = "producedQty", column = @Column(name = "prdcd_qty")),
@AttributeOverride(name = "productCode", column = @Column(name = "prd_code")),
@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})

public class OI40DBCoProductItem  extends OI40DBBaseEntity implements Serializable{
	protected String operationCode=null;
	protected String productCode = null;
	protected String warehouseCode = null;
	protected Double producedQty = null;
	protected String plantCode = null;
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
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
	public Double getProducedQty() {
		return producedQty;
	}
	public void setProducedQty(Double producedQty) {
		this.producedQty = producedQty;
	}
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
}
