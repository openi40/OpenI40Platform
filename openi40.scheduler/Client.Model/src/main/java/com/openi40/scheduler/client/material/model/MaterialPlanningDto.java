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
import com.openi40.scheduler.client.model.material.ProductionSupplyDto;
import com.openi40.scheduler.client.model.material.PurchaseSupplyDto;
import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.client.model.orders.SalesOrderLineDto;
import com.openi40.scheduler.common.utils.DateUtil.Week;


public class MaterialPlanningDto extends ClientDto {
	String productCode = null;

	public MaterialPlanningDto() {

	}

	public List<Week> analizedWeeks = new ArrayList<>();


	public static class MaterialWarehousePlanningDto extends ClientDto {
		String warehouseCode = null;
		StockSupplyDto stockSupply = null;
		List<SalesOrderLineDto> salesOrderLines = new ArrayList<>();
		List<PurchaseSupplyDto> purchaseSupplies = new ArrayList<>();
		List<ProductionSupplyDto> productionSupplies = new ArrayList<>();
		List<SupplyReservationDto> productionConsumptions = new ArrayList<>();
		DailyWarehouseGraph dailyGraph = null;
		public String getWarehouseCode() {
			return warehouseCode;
		}
		public void setWarehouseCode(String warehouseCode) {
			this.warehouseCode = warehouseCode;
		}
		public StockSupplyDto getStockSupply() {
			return stockSupply;
		}
		public void setStockSupply(StockSupplyDto stockSupply) {
			this.stockSupply = stockSupply;
		}
		public List<SalesOrderLineDto> getSalesOrderLines() {
			return salesOrderLines;
		}
		public void setSalesOrderLines(List<SalesOrderLineDto> salesOrderLines) {
			this.salesOrderLines = salesOrderLines;
		}
		public List<PurchaseSupplyDto> getPurchaseSupplies() {
			return purchaseSupplies;
		}
		public void setPurchaseSupplies(List<PurchaseSupplyDto> purchaseSupplies) {
			this.purchaseSupplies = purchaseSupplies;
		}
		public List<ProductionSupplyDto> getProductionSupplies() {
			return productionSupplies;
		}
		public void setProductionSupplies(List<ProductionSupplyDto> productionSupplies) {
			this.productionSupplies = productionSupplies;
		}
		public List<SupplyReservationDto> getProductionConsumptions() {
			return productionConsumptions;
		}
		public void setProductionConsumptions(List<SupplyReservationDto> productionConsumptions) {
			this.productionConsumptions = productionConsumptions;
		}
		public DailyWarehouseGraph getDailyGraph() {
			return dailyGraph;
		}
		public void setDailyGraph(DailyWarehouseGraph dailyGraph) {
			this.dailyGraph = dailyGraph;
		}
	}

	List<MaterialWarehousePlanningDto> warehousesAnalisys = new ArrayList<>();

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<Week> getAnalizedWeeks() {
		return analizedWeeks;
	}

	public void setAnalizedWeeks(List<Week> analizedWeeks) {
		this.analizedWeeks = analizedWeeks;
	}

	public List<MaterialWarehousePlanningDto> getWarehousesAnalisys() {
		return warehousesAnalisys;
	}

	public void setWarehousesAnalisys(List<MaterialWarehousePlanningDto> warehousesAnalisys) {
		this.warehousesAnalisys = warehousesAnalisys;
	}

}
