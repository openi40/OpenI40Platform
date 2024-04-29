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

import com.openi40.scheduler.client.model.ClientDto;

public class CoProductItemDto extends ClientDto {
	protected String productCode = null;
	protected String warehouseCode = null;
	protected double producedQty = 0.0;
	protected String plantCode = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoProductItemDto() {

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

	public double getProducedQty() {
		return producedQty;
	}

	public void setProducedQty(double producedQty) {
		this.producedQty = producedQty;
	}
	
	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

}
