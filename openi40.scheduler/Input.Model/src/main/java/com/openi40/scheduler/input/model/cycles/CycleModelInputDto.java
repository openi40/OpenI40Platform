package com.openi40.scheduler.input.model.cycles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

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
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@MappedSuperclass
public class CycleModelInputDto extends InputDto  implements Serializable{
	String productCode=null;
	String plantCode=null;
	String warehouseCode=null;
	boolean defaultProductCycle=false;
	List<OperationModelInputDto> operations=new ArrayList<OperationModelInputDto>();
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = ProductInputDto.class,nullable = false)
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = PlantInputDto.class,nullable = false)
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	@Transient
	public List<OperationModelInputDto> getOperations() {
		return operations;
	}
	public void setOperations(List<OperationModelInputDto> operations) {
		this.operations = operations;
	}
	public boolean isDefaultProductCycle() {
		return defaultProductCycle;
	}
	public void setDefaultProductCycle(boolean defaultProductCycle) {
		this.defaultProductCycle = defaultProductCycle;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = WarehouseInputDto.class,nullable = true)
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}
