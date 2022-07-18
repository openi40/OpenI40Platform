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
package com.openi40.scheduler.client.model.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;

import lombok.Data;

@Data
public class WorkOrderDto extends ClientDto {
	private List<TaskDto> tasks = new ArrayList<>();
	private Date deliveryDate = null;
	private Date askedDeliveryDateTime = null;
	private int Idx = 0;
	private String salesOrderLineCode=null;
	private boolean rootSalesOrderWorkOrder=false;
	private TimeSegmentDto execution = new TimeSegmentDto();
	private double TotalQty = 0.0;
	protected String color = null;
	@Data
	public static class PeggingDto extends ClientDto{
		private ClientDto consumerWorkOrder = null;
		private ClientDto producerWorkOrder = null;
	}
	@Data
	public static class ProducedPartDto extends ClientDto{
		ProductDto suppliedItem=null;
		double qty=0.0;
	}
	private List<PeggingDto> peggings=new ArrayList<PeggingDto>();
	private ProducedPartDto producedPart=null;
}
