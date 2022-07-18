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
package com.openi40.scheduler.client.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.orders.WorkOrderDto;

import lombok.Data;

@Data
public class PlantDto extends ClientDto {
	List<DepartmentDto> departments = new ArrayList<DepartmentDto>();
	List<WarehouseDto> warehouses = new ArrayList<WarehouseDto>();
	List<WorkOrderDto> WorkOrders = new ArrayList<WorkOrderDto>();
}
