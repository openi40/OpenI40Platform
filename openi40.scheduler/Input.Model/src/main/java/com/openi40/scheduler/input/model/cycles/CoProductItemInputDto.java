package com.openi40.scheduler.input.model.cycles;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
import com.openi40.scheduler.input.model.material.ProductInputDto;
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
public class CoProductItemInputDto extends InputDto {
	protected String operationCode=null;
	protected String productCode = null;
	protected String warehouseCode = null;
	protected double producedQty = 0.0;
	protected String plantCode = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoProductItemInputDto() {

	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ProductInputDto.class, nullable = false)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WarehouseInputDto.class, nullable = true)
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public double getProducedQty() {
		return producedQty;
	}

	public void setProducedQty(double producedQty) {
		this.producedQty = producedQty;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = PlantInputDto.class,nullable = false)
	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = OperationModelInputDto.class,nullable = true)
	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

}
