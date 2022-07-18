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

import lombok.Data;
@Data
public class DailyWarehouseGraph extends ClientDto {

	public DailyWarehouseGraph() {
		
	}
	List<InventoryDay> inventoryDays=new ArrayList<>();

}
