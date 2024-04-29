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
package com.openi40.scheduler.client.model.cycles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;


public class OperationModelDto extends ClientDto implements Serializable {
	
	
	String plantCode = null;
	String sequenceCode = null;
	List<BomItemModelDto> bomItems = new ArrayList<BomItemModelDto>();
	List<CoProductItemDto> coProducts=new ArrayList<CoProductItemDto>();
	
	public static class OperationEquipmentSpecification extends EquipmentSpecification {
		
		String workCenterCode = null;
		
		public String getWorkCenterCode() {
			return workCenterCode;
		}

		public void setWorkCenterCode(String workCenterCode) {
			this.workCenterCode = workCenterCode;
		}
	}

	
	public static class MachineEquipmentSpecification extends EquipmentSpecification {
		
		String machineCode = null;
		
		public String getMachineCode() {
			return machineCode;
		}

		public void setMachineCode(String machineCode) {
			this.machineCode = machineCode;
		}
	}

	List<OperationEquipmentSpecification> equipmentSpecifications = new ArrayList<OperationModelDto.OperationEquipmentSpecification>();
	List<MachineEquipmentSpecification> machinesEquipmentSpecifications = new ArrayList<MachineEquipmentSpecification>();
	
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
	public List<BomItemModelDto> getBomItems() {
		return bomItems;
	}
	public void setBomItems(List<BomItemModelDto> bomItems) {
		this.bomItems = bomItems;
	}
	public List<OperationEquipmentSpecification> getEquipmentSpecifications() {
		return equipmentSpecifications;
	}
	public void setEquipmentSpecifications(List<OperationEquipmentSpecification> equipmentSpecifications) {
		this.equipmentSpecifications = equipmentSpecifications;
	}
	public List<MachineEquipmentSpecification> getMachinesEquipmentSpecifications() {
		return machinesEquipmentSpecifications;
	}
	public void setMachinesEquipmentSpecifications(List<MachineEquipmentSpecification> machinesEquipmentSpecifications) {
		this.machinesEquipmentSpecifications = machinesEquipmentSpecifications;
	}
	public List<CoProductItemDto> getCoProducts() {
		return coProducts;
	}
	public void setCoProducts(List<CoProductItemDto> coProducts) {
		this.coProducts = coProducts;
	}
	
}
