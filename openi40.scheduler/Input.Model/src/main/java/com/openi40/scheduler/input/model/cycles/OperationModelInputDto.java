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
public class OperationModelInputDto extends InputDto implements Serializable {

	String plantCode = null;
	String sequenceCode = null;
	String consumingBatchTransferType = BatchTransferType.TRANSFER_ALL.name();
	String producingBatchTransferType = BatchTransferType.TRANSFER_ALL.name();
	public double consumingBatchQty = 1.0;
	public double producingBatchQty = 1.0;
	
	List<BomItemModelInputDto> bomItems = new ArrayList<BomItemModelInputDto>();
	List<CoProductItemInputDto> coProducts = new ArrayList<CoProductItemInputDto>();
	List<OperationEquipmentSpecificationInputDto> equipmentSpecifications = new ArrayList<OperationEquipmentSpecificationInputDto>();
	List<MachineEquipmentSpecificationInputDto> machinesEquipmentSpecifications = new ArrayList<MachineEquipmentSpecificationInputDto>();

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = PlantInputDto.class, nullable = false)
	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	@Transient
	public List<BomItemModelInputDto> getBomItems() {
		return bomItems;
	}

	public void setBomItems(List<BomItemModelInputDto> bomItems) {
		this.bomItems = bomItems;
	}

	@Transient
	public List<OperationEquipmentSpecificationInputDto> getEquipmentSpecifications() {
		return equipmentSpecifications;
	}

	public void setEquipmentSpecifications(List<OperationEquipmentSpecificationInputDto> equipmentSpecifications) {
		this.equipmentSpecifications = equipmentSpecifications;
	}

	@Transient
	public List<MachineEquipmentSpecificationInputDto> getMachinesEquipmentSpecifications() {
		return machinesEquipmentSpecifications;
	}

	public void setMachinesEquipmentSpecifications(
			List<MachineEquipmentSpecificationInputDto> machinesEquipmentSpecifications) {
		this.machinesEquipmentSpecifications = machinesEquipmentSpecifications;
	}

	@Transient
	public List<CoProductItemInputDto> getCoProducts() {
		return coProducts;
	}

	public void setCoProducts(List<CoProductItemInputDto> coProducts) {
		this.coProducts = coProducts;
	}

	String cycleCode = null;

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = CycleModelInputDto.class, nullable = true)
	public String getCycleCode() {
		return cycleCode;
	}

	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	public double getConsumingBatchQty() {
		return consumingBatchQty;
	}

	public void setConsumingBatchQty(double consumingBatchQty) {
		this.consumingBatchQty = consumingBatchQty;
	}

	public double getProducingBatchQty() {
		return producingBatchQty;
	}

	public void setProducingBatchQty(double producingBatchQty) {
		this.producingBatchQty = producingBatchQty;
	}

	public String getConsumingBatchTransferType() {
		return consumingBatchTransferType;
	}

	public void setConsumingBatchTransferType(String consumingBatchTransferType) {
		this.consumingBatchTransferType = consumingBatchTransferType;
	}

	public String getProducingBatchTransferType() {
		return producingBatchTransferType;
	}

	public void setProducingBatchTransferType(String producingBatchTransferType) {
		this.producingBatchTransferType = producingBatchTransferType;
	}

	

}
