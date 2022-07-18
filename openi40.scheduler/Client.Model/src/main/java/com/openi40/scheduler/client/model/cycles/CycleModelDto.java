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
package com.openi40.scheduler.client.model.cycles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;


public class CycleModelDto extends ClientDto  implements Serializable{
	String productCode=null;
	String plantCode=null;
	String warehouseCode=null;
	boolean defaultProductCycle=false;
	List<OperationModelDto> operations=new ArrayList<OperationModelDto>();
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	public List<OperationModelDto> getOperations() {
		return operations;
	}
	public void setOperations(List<OperationModelDto> operations) {
		this.operations = operations;
	}
	public boolean isDefaultProductCycle() {
		return defaultProductCycle;
	}
	public void setDefaultProductCycle(boolean defaultProductCycle) {
		this.defaultProductCycle = defaultProductCycle;
	}
	
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}
