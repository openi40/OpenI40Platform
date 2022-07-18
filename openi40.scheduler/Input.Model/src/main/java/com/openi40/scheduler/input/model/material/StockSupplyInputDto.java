package com.openi40.scheduler.input.model.material;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
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
public class StockSupplyInputDto extends InputDto {

	private String productCode = null;

	private String warehouseCode = null;
	private Double physicalStockQuantity ;
	private Boolean infiniteCapacity;
	public Boolean getInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(Boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ProductInputDto.class, nullable = false)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WarehouseInputDto.class, nullable = false)
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

}
