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
package com.openi40.scheduler.client.material.model;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.companystructure.WarehouseDto;
import com.openi40.scheduler.client.model.material.ProductionSupplyDto;
import com.openi40.scheduler.client.model.material.PurchaseSupplyDto;
import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.client.model.orders.PurchaseOrderLineDto;
import com.openi40.scheduler.client.model.orders.SalesOrderLineDto;
import com.openi40.scheduler.common.utils.DateUtil.Week;

import lombok.Data;

@Data
public class MaterialPlanningDto extends ClientDto {
	String productCode = null;

	public MaterialPlanningDto() {

	}

	public List<Week> analizedWeeks = new ArrayList<>();

	@Data
	public static class MaterialWarehousePlanningDto extends ClientDto {
		String warehouseCode = null;
		StockSupplyDto stockSupply = null;
		List<SalesOrderLineDto> salesOrderLines = new ArrayList<>();
		List<PurchaseSupplyDto> purchaseSupplies = new ArrayList<>();
		List<ProductionSupplyDto> productionSupplies = new ArrayList<>();
		List<SupplyReservationDto> productionConsumptions = new ArrayList<>();
		DailyWarehouseGraph dailyGraph = null;
	}

	List<MaterialWarehousePlanningDto> warehousesAnalisys = new ArrayList<>();

}
