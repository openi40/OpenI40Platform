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

import com.openi40.scheduler.client.model.ClientDto;


public class BomItemModelDto extends ClientDto  implements Serializable{
	
	String requiredProductCode=null;
	Double useCoefficient=0.0;
	protected String warehouseCode=null;
	
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
	
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}
