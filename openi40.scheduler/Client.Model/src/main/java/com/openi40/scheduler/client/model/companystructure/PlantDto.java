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
package com.openi40.scheduler.client.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.orders.WorkOrderDto;


public class PlantDto extends ClientDto {
	List<DepartmentDto> departments = new ArrayList<DepartmentDto>();
	List<WarehouseDto> warehouses = new ArrayList<WarehouseDto>();
	List<WorkOrderDto> WorkOrders = new ArrayList<WorkOrderDto>();
	public List<DepartmentDto> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentDto> departments) {
		this.departments = departments;
	}
	public List<WarehouseDto> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<WarehouseDto> warehouses) {
		this.warehouses = warehouses;
	}
	public List<WorkOrderDto> getWorkOrders() {
		return WorkOrders;
	}
	public void setWorkOrders(List<WorkOrderDto> workOrders) {
		WorkOrders = workOrders;
	}
}
