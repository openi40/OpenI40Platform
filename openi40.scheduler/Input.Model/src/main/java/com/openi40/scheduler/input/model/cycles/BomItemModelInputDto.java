package com.openi40.scheduler.input.model.cycles;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
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
public class BomItemModelInputDto extends InputDto  implements Serializable{
	
	String requiredProductCode=null;
	Double useCoefficient=0.0;
	String consumingBatchTransferType = BatchTransferType.TRANSFER_ALL.name();	
	public double consumingBatchQty = 1.0;	
	protected String warehouseCode=null;
	protected String operationCode=null;
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = ProductInputDto.class,nullable = false)
	public String getRequiredProductCode() {
		return requiredProductCode;
	}
	public void setRequiredProductCode(String requiredProductCode) {
		this.requiredProductCode = requiredProductCode;
	}
	public Double getUseCoefficient() {
		return useCoefficient;
	}
	public void setUseCoefficient(Double useCoefficient) {
		this.useCoefficient = useCoefficient;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = WarehouseInputDto.class,nullable = true)
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	@ObjectReferenceConstraint(containerType = ApsInputData.class,referencedType = OperationModelInputDto.class,nullable = true)
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getConsumingBatchTransferType() {
		return consumingBatchTransferType;
	}
	public void setConsumingBatchTransferType(String consumingBatchTransferType) {
		this.consumingBatchTransferType = consumingBatchTransferType;
	}
	public double getConsumingBatchQty() {
		return consumingBatchQty;
	}
	public void setConsumingBatchQty(double consumingBatchQty) {
		this.consumingBatchQty = consumingBatchQty;
	}
	
	
}
